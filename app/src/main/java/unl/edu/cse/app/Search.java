package unl.edu.cse.app;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Data.Game;

public final class Search {

    private static ExpandableListAdapter collectionListAdapter;
    private static ExpandableListView collection;
    private static List<Game> collectionGames;
    private static ExpandableListAdapter searchListAdapter;
    private static ExpandableListView search;
    private static List<Game> searchGames;

    //needs activity and view to access
    private static View view = null;
    private static Activity activity = null;

    public static void setActivity(Activity activity)
    {
        Search.activity = activity;
    }

    public static void setView(View view)
    {
        Search.view = view;
    }

    //hit up giant bomb then call processSearch!
    public static void search()
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                String query = "http://www.giantbomb.com/api/search/?api_key=7f4feae8d9cc9bc262d824cf64ce654fc4ed3b92&query=\"" + String.valueOf(((TextView) view.findViewById(R.id.editText)).getText()) + "\"&format=json&resources=game&limit=20&field_list=name,deck,site_detail_url,id,image";
                query = query.replaceAll(" ", "%20");
                try
                {
                    URL url = new URL(query);
                    Log.d("query?", url.toString());
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    url.openStream()));

                    String inputLine = in.readLine();
                    processSearch(inputLine);
                }
                catch (Exception e)
                {
                    Log.d("query?", e.getClass().toString());
                    //handle?
                }
            }
        }).start();
    }

    private static void processSearch(String inputLine)
    {
        JsonParser parser = new JsonParser();
        JsonObject response = (JsonObject) parser.parse(inputLine);

        //check if query was successful
        if (response.get("error").getAsString().compareTo("OK") == 0)
        {
            //convert list to games and add to list
            JsonElement g = response.get("results");
            Gson gson = new Gson();
            collectionGames = new ArrayList<Game>();
            for (Game a : gson.fromJson(g, Game[].class))
            {
                collectionGames.add(a);
            }

            //update visiuals
            activity.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    collection = (ExpandableListView) view.findViewById(R.id.listView);

                    // preparing list data
                    collectionListAdapter = new ExpandableListAdapter(activity, collectionGames, activity);

                    collection.setAdapter(collectionListAdapter);
                    collectionListAdapter.notifyDataSetChanged();
                }
            });


        }
        else
        {
            //todo:handle no results
        }

    }


    // get search button and text eneter intialized
    public static void initiateSearch()
    {
        TextView tv = (TextView) view.findViewById(R.id.editText);
        tv.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                Search.search();
                return false;
            }
        });
        ImageButton b = (ImageButton) view.findViewById(R.id.imageButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search.search();
            }
        });
    }
}

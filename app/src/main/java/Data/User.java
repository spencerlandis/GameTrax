package Data;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import unl.edu.cse.app.Accessors;
import unl.edu.cse.app.ExpandableListAdapter;
import unl.edu.cse.app.R;
import unl.edu.cse.app.Search;

/**
 * Created by spencerlandis on 3/8/14.
 */
public class User {

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    private int user_id;
    private ArrayList<Game> games;

    public User(int id, ArrayList<Game> games)
    {
        this.user_id = id;
        this.games = games;
    }

    public boolean hasGame(long id)
    {
        for(Game g : games)
        {
            if(g.getId() == id)
                return true;
        }
        return false;
    }

    public void addGame(View v)
    {
        ViewGroup temp = (ViewGroup)v.getParent().getParent();
        final Game g = Search.getGame(String.valueOf(((TextView) (temp.findViewById(R.id.description))).getText()));
        if(g != null)
        {
            games.add(g);
        }
        Log.d("addGame", "here?");
        final int userid = this.getId();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Accessors.addGame(userid, (int) g.getId(), g.getDeck(), g.getImage().getIconUrl(), g.getName(), g.getSite_detail_url());

            }
        }).start();

        Log.i("query?", "something didn't work? : " + games.size());
        Gson gson = new Gson();
        Search.activity.getPreferences(Activity.MODE_PRIVATE).edit().putString("user", gson.toJson(this)).commit();
        loadGames();
    }

    public void removeGame(View v)
    {
        ViewGroup temp = (ViewGroup)v.getParent().getParent();
        String description = ((TextView)(temp.findViewById(R.id.description))).getText().toString();
        final int userid = this.getId();
        for(int i = 0; i < games.size(); i++)
        {
            if(description.compareTo(games.get(i).getDeck()) == 0)
            {
                final int gameid = (int) games.get(i).getId();
                games.remove(i);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Accessors.removeGame(userid, gameid );
                    }
                }).start();
            }
        }



        Gson gson = new Gson();
        Search.activity.getPreferences(Activity.MODE_PRIVATE).edit().putString("user", gson.toJson(this)).commit();
        loadGames();
    }

    public void loadGames()
    {
        final ExpandableListView collection = (ExpandableListView) Search.view.findViewById(R.id.collectionList);

        Search.activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Search.view.findViewById(R.id.searchWindow).setVisibility(View.INVISIBLE);

                // preparing list data
                if(games.size() == 0)
                {
                    ((TextView)Search.view.findViewById(android.R.id.empty)).setText("Your collection is empty! Search above for new games!");
                }
                else
                {
                    ((TextView)Search.view.findViewById(android.R.id.empty)).setText("");
                }
                ExpandableListAdapter collectionListAdapter = new ExpandableListAdapter(Search.activity, games, Search.activity);

                collection.setAdapter(collectionListAdapter);
                collectionListAdapter.notifyDataSetChanged();
            }
        });
    }
}

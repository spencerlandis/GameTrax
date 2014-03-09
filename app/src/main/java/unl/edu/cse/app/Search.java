package unl.edu.cse.app;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Data.Game;

public abstract class Search {

    private static ExpandableListAdapter listAdapter;
    private static ExpandableListView expListView;
    private static List<Game> listDataHeader;

    private static View view = null;
    private static Activity activity = null;

    public static void setActivity(Activity activity) {
        Search.activity = activity;
    }

    public static void setView(View view) {
        Search.view = view;
    }

    public static ArrayList search() {
        Log.i("query?", String.valueOf(((TextView) view.findViewById(R.id.editText)).getText()));
        return null;
    }

    public static void initiateSearch() {
        TextView tv = (TextView) view.findViewById(R.id.editText);
        tv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
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

        expListView = (ExpandableListView) view.findViewById(R.id.listView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(activity, listDataHeader);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    private static void prepareListData() {
        listDataHeader = new ArrayList<Game>();

        // Adding child data
        listDataHeader.add(new Game(9993L, "Halo 3", "he conclusion to the original Halo trilogy has the super-soldier Master Chief joining forces with The Arbiter to finish off the threat of both the remaining Covenant Empire and the parasitic Flood, once and for all.", "http://static.giantbomb.com/uploads/square_avatar/8/87790/2079826-box_halo3.png", "http://www.giantbomb.com/halo-3/3030-9993/"));
    }
}

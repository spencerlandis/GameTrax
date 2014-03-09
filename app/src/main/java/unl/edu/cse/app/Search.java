package unl.edu.cse.app;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Search {

    private static ExpandableListAdapter listAdapter;
    private static ExpandableListView expListView;
    private static List<String> listDataHeader;
    private static HashMap<String, List<String>> listDataChild;

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

        listAdapter = new ExpandableListAdapter(activity, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    private static void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Halo 3");
        listDataHeader.add("Game 2");
        listDataHeader.add("Game 3");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The conclusion to the original Halo trilogy has the super-soldier Master Chief joining forces with The Arbiter to finish off the threat of both the remaining Covenant Empire and the parasitic Flood, once and for all.");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}

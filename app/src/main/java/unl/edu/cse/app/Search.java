package unl.edu.cse.app;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public abstract class Search {

    private static View view = null;

    public static ArrayList search() {
        Log.i("query?", String.valueOf(((TextView) view.findViewById(R.id.editText)).getText()));
        return null;
    }

    public static void initiateSearch(View view) {
        Search.view = view;
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
    }
}

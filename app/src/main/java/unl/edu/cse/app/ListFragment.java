package unl.edu.cse.app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by spencerlandis on 3/15/14.
 */
public class ListFragment extends Fragment {

    public ListFragment()
    {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.d("list on create", "failing after this");
        View v = inflater.inflate(R.layout.fragment_list_view, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        Log.d("list fragment", "created!");
        super.onActivityCreated(savedInstanceState);
        Search.setView(getView());
        Search.initiateSearch();
        Log.d("loading games", "made it to load games");
        HomeActivity.getUser().loadGames();
        Log.d("loading games", "made it past load games");
    }
}

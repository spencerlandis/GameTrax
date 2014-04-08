package unl.edu.cse.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by spencerlandis on 3/15/14.
 */
public class ListFragment extends Fragment {

    public ListFragment()
    {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_list_view, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Search.setView(getView());
        Search.initiateSearch();
        try {
            HomeActivity.getUser().loadGames();
        }
        catch(NullPointerException e)
        {
            Toast.makeText(getActivity(), "No internet available. Please connect to the internet then retry Game Trax.", Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
    }
}

package unl.edu.cse.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import Data.User;

public final class HomeActivity extends Activity {

    private static User user;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Search.setActivity(this);

        if (savedInstanceState == null)
        {
            FragmentManager ft = getFragmentManager();
            ft.beginTransaction().add(R.id.container, new LoginFragment()).commit();

        }
        setContentView(R.layout.activity_home);

        if(isNetworkAvailable())
        {
            Log.d("Network", "Available");
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No internet available. Please connect to the internet then retry Game Trax.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static User getUser()
    {
        return user;
    }

    public static void setUser(User user)
    {
        HomeActivity.user = user;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

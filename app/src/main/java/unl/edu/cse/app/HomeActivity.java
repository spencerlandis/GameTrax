package unl.edu.cse.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import Data.User;

public class HomeActivity extends Activity {

    private static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null)
        {
            FragmentManager ft = getFragmentManager();
            ft.beginTransaction().add(R.id.container, new ListFragment()).commit();

        }
        setContentView(R.layout.activity_home);
        Search.setActivity(this);
        loadUser();
    }

    private void loadUser()
    {
        user = User.loadUser();
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
}

package Data;

import android.view.View;

/**
 * Created by spencerlandis on 3/8/14.
 */
public class User {

    public boolean hasGame(int id)
    {
        return false;
    }

    public void addGame(View v)
    {
    }

    public void removeGame(View v)
    {

    }

    public static User loadUser()
    {
        return new User();
    }
}

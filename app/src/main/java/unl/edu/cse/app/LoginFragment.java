package unl.edu.cse.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Zane on 4/27/14.
 */
public class LoginFragment extends Fragment {


    public LoginFragment()
    {}
    private static View view = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View v = inflater.inflate(R.layout.login, container, false);
        LoginFragment.view = v;
        ((Button)v.findViewById(R.id.btnLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new Thread( new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EditText email = ((EditText) LoginFragment.view.findViewById(R.id.login_email));
                            EditText password = ((EditText) LoginFragment.view.findViewById(R.id.login_password));

                            HomeActivity.setUser(Accessors.login(email.getText().toString(), password.getText().toString()));

                           Log.d("icon url", "" + HomeActivity.getUser().getGames());

//                        Fragment fragment = new ListFragment();
//
//                        FragmentManager fm = getFragmentManager();
//                        FragmentTransaction transaction = fm.beginTransaction();
//                        transaction.replace(R.id.container, fragment);
//                        transaction.commit();
//                            FragmentManager ft = getFragmentManager();
//                            ft.beginTransaction().replace(R.id.container, new ListFragment()).commit();
                            Search.activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    FragmentManager ft = getFragmentManager();
                                    ft.beginTransaction().replace(R.id.container, new ListFragment()).commit();

                                }
                            });
                        }catch(Exception e)
                        {
                            Search.activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    CharSequence text = "Login failed!";
                                    Toast t = Toast.makeText(Search.activity.getApplicationContext(),text, Toast.LENGTH_LONG);
                                    t.show();
                                }
                            });

                            e.printStackTrace();
                        }
                    }
                }
                ).start();
            }
        });

        ((TextView)v.findViewById(R.id.link_to_register)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                FragmentManager ft = getFragmentManager();
                ft.beginTransaction().replace(R.id.container, new RegisterFragment()).commit();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }

}

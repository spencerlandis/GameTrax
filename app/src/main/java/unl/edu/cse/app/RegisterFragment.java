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
public class RegisterFragment extends Fragment {

    private static View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.register, container, false);
        RegisterFragment.view = v;
        ((Button)v.findViewById(R.id.btnRegister)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new Thread( new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EditText email = ((EditText) RegisterFragment.view.findViewById(R.id.reg_email));
                            EditText password = ((EditText) RegisterFragment.view.findViewById(R.id.reg_password));
                            EditText confirmPassword = ((EditText) RegisterFragment.view.findViewById(R.id.confirm_password));
                            if(password.getText().toString().trim().compareTo(confirmPassword.getText().toString().trim()) == 0)
                            {
                                HomeActivity.setUser(Accessors.createAccount(email.getText().toString(), password.getText().toString()));
                            }
                             else
                            {
                                HomeActivity.getUser().getId();
                            }
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
                                    CharSequence text = "Account creation failed!";
                                    Toast t = Toast.makeText(Search.activity.getApplicationContext(),text, Toast.LENGTH_LONG);
                                    t.show();

                                }
                            });

                            Log.d("error", e.toString());
                        }
                    }
                }
                ).start();
            }
        });

        ((TextView)v.findViewById(R.id.link_to_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                FragmentManager ft = getFragmentManager();
                ft.beginTransaction().replace(R.id.container, new LoginFragment()).commit();
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

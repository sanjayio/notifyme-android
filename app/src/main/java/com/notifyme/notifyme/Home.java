package com.notifyme.notifyme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.notifyme.notifyme.library.DatabaseHandler;
import com.notifyme.notifyme.library.UserFunctions;

import java.util.HashMap;

/**
 * Created by sanjay on 5/1/2015.
 */
public class Home extends Activity {

    Button btnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        btnLogout = (Button) findViewById(R.id.logout_btn);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        //load user details from sqlite database.
        HashMap<String,String> user = new HashMap<String, String>();
        user = db.getUserDetails();

        btnLogout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                UserFunctions logout = new UserFunctions();
                logout.logoutUser(getApplicationContext());
                Intent login = new Intent(getApplicationContext(), Login.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                finish();
            }
        });

        final TextView fname = (TextView) findViewById(R.id.user_fname);
        fname.setText("First Name: "+user.get("fname"));
        final TextView lname = (TextView) findViewById(R.id.user_lname);
        lname.setText("Last Name: "+user.get("lname"));
        final TextView email = (TextView) findViewById(R.id.user_email);
        email.setText("Email: "+user.get("email"));
        final TextView username = (TextView) findViewById(R.id.user_uname);
        username.setText("Username: "+user.get("uname"));
        final TextView created = (TextView) findViewById(R.id.user_created);
        created.setText("Created at: "+user.get("created_at"));

    }
}

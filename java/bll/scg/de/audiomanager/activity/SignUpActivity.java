package bll.scg.de.audiomanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;

import bll.scg.de.audiomanager.R;
import bll.scg.de.audiomanager.util.VolleyAdapter;

/**
 * Created by Simon C. Gorissen on 19.08.2016.
 */
public class SignUpActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView tw = (TextView) findViewById(R.id.textView_signIn);
        Button btn_continueFree = (Button) findViewById(R.id.btn_continueFree);
        Button btn_signUp = (Button) findViewById(R.id.btn_signUp);

        final EditText editText_nickname = (EditText) findViewById(R.id.editText_nickname);
        final EditText editText_email = (EditText) findViewById(R.id.editText_email);
        final EditText editText_pw = (EditText) findViewById(R.id.editText_pw);

        //SignUp Listener
        View.OnClickListener signUpListener = new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                VolleyAdapter.getInstance(getApplicationContext()).signUserUp(SignUpActivity.this, editText_nickname.getText().toString(),
                        editText_email.getText().toString(),
                        editText_pw.getText().toString());
            }
        };
        //Link to SignIn
        View.OnClickListener twListener = new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startSignInActivity();
            }
        };

        //Continue Free Button Listener
        View.OnClickListener continueFreeListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MainFeedActivity.class);
                startActivity(intent);
            }
        };

        //Set Listeners
        tw.setOnClickListener(twListener);
        btn_continueFree.setOnClickListener(continueFreeListener);
        btn_signUp.setOnClickListener(signUpListener);
    }

    public void startSignInActivity()
    {
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(intent);
    }

    public void startMainFeedActivity()
    {
        Intent intent = new Intent(getApplicationContext(), MainFeedActivity.class);
        startActivity(intent);
    }
}

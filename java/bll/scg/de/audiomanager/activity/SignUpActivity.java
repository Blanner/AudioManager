package bll.scg.de.audiomanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import bll.scg.de.audiomanager.R;

/**
 * Created by Simon C. Gorissen on 19.08.2016.
 */
public class SignUpActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final TextView tw = (TextView) findViewById(R.id.textView_signIn);

        //Register Listeners
        View.OnClickListener twListener = new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        };

        //Set Listeners
        tw.setOnClickListener(twListener);
    }
}

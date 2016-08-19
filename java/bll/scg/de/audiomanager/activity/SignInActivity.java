package bll.scg.de.audiomanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bll.scg.de.audiomanager.R;
import bll.scg.de.audiomanager.application.UrlHolder;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = SignInActivity.class.getSimpleName();

    TextView textView_linkToSignUp;
    Button btn_signIn;
    Button btn_continueFree;
    ImageView imgView_settings;
    RelativeLayout rl_signin;

    PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Retrieve views
        textView_linkToSignUp = (TextView) findViewById(R.id.textView_signUp);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        btn_continueFree = (Button) findViewById(R.id.btn_continueFree);
        imgView_settings = (ImageView) findViewById(R.id.imgView_settings);
        rl_signin = (RelativeLayout) findViewById(R.id.rl_signIn);

        //Register Listeners
        //1.TextView Listener
        View.OnClickListener twListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        };
        //2.Sign In Button listener
        View.OnClickListener signInListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        };
        //3.Continue Free Button Listener
        View.OnClickListener continueFreeListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        };
        //4. Settings Listener
        View.OnClickListener settingsListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), SetIpActivity.class);
                startActivity(intent);
            }
        };

        //set Listeners
        textView_linkToSignUp.setOnClickListener(twListener);
        btn_signIn.setOnClickListener(signInListener);
        btn_continueFree.setOnClickListener(continueFreeListener);
        imgView_settings.setOnClickListener(settingsListener);

    }
}

package bll.scg.de.audiomanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bll.scg.de.audiomanager.R;
import bll.scg.de.audiomanager.application.UrlHolder;

/**
 * Created by Simon C. Gorissen on 19.08.2016.
 */
public class SetIpActivity extends Activity
{
    EditText editText_enterIp;
    Button btn_setIp;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setip);

        //retrieve views
        editText_enterIp = (EditText) findViewById(R.id.editText_enterIp);
        btn_setIp = (Button) findViewById(R.id.btn_setIp);

        //1.SetIp Listener
        View.OnClickListener setIpListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(UrlHolder.setIP(editText_enterIp.getText().toString()))
                {
                    finish();
                }
            }
        };
        btn_setIp.setOnClickListener(setIpListener);
    }
}

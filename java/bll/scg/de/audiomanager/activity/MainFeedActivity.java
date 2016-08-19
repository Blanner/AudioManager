package bll.scg.de.audiomanager.activity;

import android.app.ListActivity;
import android.os.Bundle;

import bll.scg.de.audiomanager.R;

/**
 * Created by Simon C. Gorissen on 19.08.2016.
 */
public class MainFeedActivity extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);
    }
}
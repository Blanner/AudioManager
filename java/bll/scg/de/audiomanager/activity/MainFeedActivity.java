package bll.scg.de.audiomanager.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import bll.scg.de.audiomanager.R;
import bll.scg.de.audiomanager.application.AudioFileFragment;
import bll.scg.de.audiomanager.util.DrawerItemClickListener;

/**
 * Created by Simon C. Gorissen on 19.08.2016.
 */
public class MainFeedActivity extends AppCompatActivity {

    private String[] mSampleTitles;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mContentFrame;
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        /*
         * Drawer and Action Bar
         */

        mTitle = mDrawerTitle = getTitle();
        mSampleTitles = getResources().getStringArray(R.array.drawer_item_titles);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.listView_leftDrawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,  R.string.drawer_open, R.string.drawer_close)
                        {
                            /** Called when a drawer has settled in a completely closed state. */
                            public void onDrawerClosed(View view)
                            {
                                super.onDrawerClosed(view);
                                getSupportActionBar().setTitle(mTitle);
                                invalidateOptionsMenu();//create call to onPrepareOptionMenu()
                            }

                            /** Called when a drawer has settled in a completely open state. */
                            public void onDrawerOpened(View drawerView)
                            {
                                super.onDrawerOpened(drawerView);
                                getSupportActionBar().setTitle(mDrawerTitle);
                                invalidateOptionsMenu();//create call to onPrepareOptionMenu()
                            }
                        };

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.navdrawer_textview, mSampleTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /*
         * Content ListView
         */

        mContentFrame = (LinearLayout) findViewById(R.id.linearLayout_contentFrame);
        for(int i = 0; i < 10; i++)
        {
            addAudioFragments();
        }
    }

    protected void addAudioFragments()
    {
        //get the Fragment Manager to begin a transaction (adding fragments)
        FragmentManager fragmentManager = getFragmentManager();
        //Start new Fragment Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //create Fragment
        AudioFileFragment audioFileFragment = new AudioFileFragment();
        //add Fragment
        fragmentTransaction.add(R.id.linearLayout_contentFrame, audioFileFragment);

        //makes Transaction revertible (null -> no name)
        fragmentTransaction.addToBackStack(null);
        //executes Transaction
        fragmentTransaction.commit();
    }


    /*
     * SupportActionBar inherited methods
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();// Sync the toggle state after onRestoreInstanceState has occurred.
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Called whenever we call invalidateOptionsMenu()*/
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        //If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
 }
package bll.scg.de.audiomanager.util;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.Toast;

import bll.scg.de.audiomanager.R;
import bll.scg.de.audiomanager.activity.MainFeedActivity;
import bll.scg.de.audiomanager.application.AudioFileFragment;

/**
 * Created by Simon C. Gorissen on 18.10.2016.
 */

public class CustomScrollView extends ScrollView
{

    /*
     * Fields
     */

    private float screenHeight;
    private MainFeedActivity feedActivity;

    /*
     * Constructors
     */

    public CustomScrollView(Context context)
    {
        super(context);
        initMetrics();
    }

    public CustomScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initMetrics();
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initMetrics();
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        initMetrics();
    }

    /*
     * Initialization Methods
     */

    private void initMetrics()
    {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenHeight = metrics.heightPixels;
    }

    /*
     * Callbacks
     */

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        //Log.d("CustomScrollView","onScrollChanged");
        super.onScrollChanged(l, t, oldl, oldt);
        //Log.d("CustomScrollView","Scroll Y: " + scrollY);
        //Log.d("CustomScrollView", "Top Y: " + getTopY());
        //Log.d("CustomScrollView", "DocumentHeight: " + getDocumentHeight());
        //Log.d("CustomScrollView", "ScreenHeight: " + screenHeight);

        if((getTopY() +  2*screenHeight) >= getDocumentHeight()) //end of fragment list is about to be reached
        {
            feedActivity.addAudioFragments(5);
        }

    }

    /*
     * Helper Methods
     */

    public void setMainFeedActivity(MainFeedActivity activity)
    {
        feedActivity = activity;
    }

    private float getDocumentHeight()
    {
        return (computeVerticalScrollRange() * screenHeight) / computeVerticalScrollExtent();
    }

    private float getTopY()
    {
        return (getDocumentHeight() * computeVerticalScrollOffset()) / computeVerticalScrollRange();
    }
}

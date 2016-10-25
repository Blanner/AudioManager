package bll.scg.de.audiomanager.application;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import bll.scg.de.audiomanager.R;


/**
 * Created by Simon C. Gorissen on 18.10.2016.
 */

public class AudioFileFragment extends Fragment
{
    private ImageButton imgBtn_play;
    private TextView textView_length;
    private TextView textView_title;
    private Boolean mPlayback = false;
    private String mDownloadLink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_audiofile, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        /*
         * Play/Pause Button
         */

        imgBtn_play = (ImageButton) getView().findViewById(R.id.imgBtn_play);
        imgBtn_play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!mPlayback) //Audio File is playing
                {
                    imgBtn_play.setImageResource(R.drawable.pause);
                    mPlayback = true;
                    //TODO stop playback / stream
                }
                else //Audio File is paused
                {
                    imgBtn_play.setImageResource(R.drawable.play);
                    mPlayback = false;
                    //TODO start playback / stream
                }
            }
        });

        /*
         * Network-set Views
         */
        textView_length = (TextView) getView().findViewById(R.id.textView_length);
        textView_title = (TextView) getView().findViewById(R.id.textView_title);
    }

    /*
     * Setters
     */

    public void setTitle(String title)
    {
        textView_title.setText(title);
    }

    public void setLength(int lengthInSeconds)
    {
        int hours =  lengthInSeconds / 60*60;
        int minutes = (lengthInSeconds - hours * 60 * 60) / 60;
        int seconds = lengthInSeconds % 60;
        textView_length.setText(hours + ":" + minutes + ":" + seconds);
    }

    public void setDownloadLink(String link)
    {
        mDownloadLink = link;
    }
}

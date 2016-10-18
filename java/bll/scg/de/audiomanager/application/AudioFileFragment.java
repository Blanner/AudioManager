package bll.scg.de.audiomanager.application;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import bll.scg.de.audiomanager.R;


/**
 * Created by Simon C. Gorissen on 18.10.2016.
 */

public class AudioFileFragment extends Fragment
{
    private ImageButton playButton;
    private Boolean playback = false;

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

        playButton = (ImageButton) getView().findViewById(R.id.imgBtn_play);
        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!playback) //Audio File is playing
                {
                    playButton.setImageResource(R.drawable.pause);
                    playback = true;
                    //TODO stop playback / stream
                }
                else //Audio File is paused
                {
                    playButton.setImageResource(R.drawable.play);
                    playback = false;
                    //TODO start playback / stream
                }
            }
        });
    }


}

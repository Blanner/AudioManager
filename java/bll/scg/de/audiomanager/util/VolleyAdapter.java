package bll.scg.de.audiomanager.util;


import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import bll.scg.de.audiomanager.R;

/**
 * Created by Simon C. Gorissen on 18.08.2016.
 */
public class VolleyAdapter //Singleton class
{
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;


    private static VolleyAdapter mInstance;
    private static Context mContext;

    private String URL = "http://www.google.com";

    /*
     * Constructor
     */
    private VolleyAdapter(Context context)
    {
        mContext = context;
        mRequestQueue = getRequestQueue();

        //setup ImageLoader
        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(context));

    }

    /*
     * Instance Getter
     */
    public static synchronized VolleyAdapter getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new VolleyAdapter(context);
        }
        return mInstance;
    }

    /*
     * Request Methods
     */
    public void makeImageRequest(final ImageView displayImageView, final String URL)
    {
        ImageRequest request = new ImageRequest(URL,
                new Response.Listener<Bitmap>()
                {
                    @Override
                    public void onResponse(Bitmap bitmap)
                    {
                        displayImageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null, Bitmap.Config.RGB_565, //MaxWidth, MaxHeight, scaleType, decodeConfig
                new Response.ErrorListener()
                {
                    public void onErrorResponse(VolleyError error)
                    {
                        displayImageView.setImageResource(R.mipmap.missing_image);
                    }
                }
        );
        addToRequestQueue(request);
    }

    public void makeJsonRequest(final TextView displayImageView, final String URL)
    {
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        displayImageView.setText("Response: " + response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //TODO handle error
                    }
                }
        );
        addToRequestQueue(jsonObjRequest);
    }

    public void makeStringRequest(final TextView displayTextView)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        displayTextView.setText("Response: " + response.substring(0,500));
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        displayTextView.setText("An error occurred");
                    }
                }
        );
        addToRequestQueue(stringRequest);
    }

    /*
     *  RequestQueue Managing Methods
     */
    public RequestQueue getRequestQueue()
    {
        if(mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }

    /*
     * additional Getters
     */
    public ImageLoader getImageLoader()
    {
        return mImageLoader;
    }
}



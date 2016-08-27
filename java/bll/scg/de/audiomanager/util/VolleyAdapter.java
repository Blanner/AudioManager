package bll.scg.de.audiomanager.util;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

import bll.scg.de.audiomanager.R;
import bll.scg.de.audiomanager.activity.SignInActivity;
import bll.scg.de.audiomanager.activity.SignUpActivity;
import bll.scg.de.audiomanager.application.UrlHolder;

/**
 * Created by Simon C. Gorissen on 18.08.2016.
 */
public class VolleyAdapter //Singleton class
{
    private final String TAG = VolleyAdapter.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;


    private static VolleyAdapter mInstance;
    private static Context mContext;

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

    //Instance Getter
    public static synchronized VolleyAdapter getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new VolleyAdapter(context);
        }
        return mInstance;
    }


    public void signUserIn(final SignInActivity activity, String email, String password)
    {
        HashMap<String, String> params = new HashMap<String,String>();
        params.put("email", email);
        params.put("password", password);
        PHPRequest phpRequest = new PHPRequest(UrlHolder.signInUrl, params,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.equals("success"))
                        {
                            activity.startMainFeedActivity();
                        }
                        else if(response.equals(null))//this should never be called, I'll leave it anyway just to be sure
                        {
                            Log.e(TAG, "No Response from Server");
                            Toast.makeText(activity.getApplicationContext(), "Error: no Response from Server", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.e("Response: ", response);
                            Toast.makeText(activity.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            //response holds the error message the PHP script echos. It gets logged and shown on screen via a Toast
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e(TAG, error.toString());
                        Toast.makeText(activity.getApplicationContext(), "A Network-Error occurred" , Toast.LENGTH_LONG).show();
                    }
                }
        );
        addToRequestQueue(phpRequest);
    }

    public void signUserUp(final SignUpActivity activity, String nickname, String email, String password)
    {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("nickname", nickname);
        params.put("email", email);
        params.put("password", password);


        PHPRequest phpRequest = new PHPRequest(UrlHolder.signUpUrl, params,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        if(response.equals("success"))
                        {
                            activity.startMainFeedActivity();
                        }
                        else if(response.equals(null))
                        {
                            Log.e(TAG, "No Response from Server");
                            Toast.makeText(activity.getApplicationContext(), "Error: no Response from Server", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.e("Response: ", response);
                            Toast.makeText(activity.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                            //response holds the error message the PHP script echos. It gets logged and shown on screen via a Toast
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(activity.getApplicationContext(), "A Network-Error occurred" , Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.toString());
                    }
                }
        );
        addToRequestQueue(phpRequest);
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



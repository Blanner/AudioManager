package bll.scg.de.audiomanager.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Simon C. Gorissen on 19.08.2016.
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache
{
    /*
     * Constructors
     */
    public LruBitmapCache(int maxSize)
    {
        super(maxSize);
    }

    public LruBitmapCache(Context context)
    {
        this(getCacheSize(context));
    }


    @Override
    protected int sizeOf(String key, Bitmap value)
    {
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url)
    {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap)
    {
        put(url, bitmap);
    }

    /*
     * Statics
     */
    public static int getCacheSize(Context context)
    {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final int screenWidth = displayMetrics.widthPixels;
        final int screenHeight = displayMetrics.heightPixels;

        //4 bytes per pixel
        final int screenBytes = screenWidth * screenHeight * 4;

        return screenBytes * 3; //cache size is approx. 3 screens worth of images
    }
}

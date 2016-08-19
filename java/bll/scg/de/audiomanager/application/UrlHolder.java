package bll.scg.de.audiomanager.application;

import android.util.Log;

/**
 * Created by Simon C. Gorissen on 19.08.2016.
 */
public class UrlHolder
{
    public static String mCurrentIP = "192.168.104.7";
    public static final String TAG = UrlHolder.class.getSimpleName();

    public static String testImageUrl = "http://192.168.104.7/android_files/test_images/ic_launcher.png";

    public static boolean setIP(String ip)
    {
        if(ip.startsWith(mCurrentIP))
        {
            testImageUrl.replace(mCurrentIP, ip);
            mCurrentIP = ip;
            return true;
        }
        else
        {
            Log.d(TAG, "Set-IP failed: Invalid IP-Address");
            return false;
        }
    }
}

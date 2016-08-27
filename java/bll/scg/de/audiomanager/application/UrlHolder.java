package bll.scg.de.audiomanager.application;

import android.util.Log;

/**
 * Created by Simon C. Gorissen on 19.08.2016.
 */
public class UrlHolder
{
    private static String mCurrentIP = "192.168.104.63";
    private static String mBaseIp = "192.168.104";
    public static final String TAG = UrlHolder.class.getSimpleName();

    public static String testImageUrl = "http://192.168.104.63/android_files/test_images/ic_launcher.png";
    public static String signUpUrl = "http://192.168.104.63/android_audiomanager_scripts/signup.php";
    public static String signInUrl = "http://192.168.104.63/android_audiomanager_scripts/signin.php";

    public static boolean setIP(String ip)
    {
        if(ip.startsWith(mBaseIp))
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

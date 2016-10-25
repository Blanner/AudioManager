package bll.scg.de.audiomanager.application;

import android.util.Log;

/**
 * Created by Simon C. Gorissen on 19.08.2016.
 */
public class UrlHolder
{
    private static String mCurrentIP = "192.168.104.64";
    private static String mBaseIp = "192.168.104";
    public static final String TAG = UrlHolder.class.getSimpleName();

   // private static String testImageUrl = "http://192.168.104/android_files/test_images/ic_launcher.png";
    private static String signUpUrl = "http://192.168.104/android_audiomanager_scripts/signup.php";
    private static String signInUrl = "http://192.168.104/android_audiomanager_scripts/signin.php";

    private static String titleUrl = "http://192.168.104/android_audiomanager_scripts/title.php";
    private static String lengthUrl = "http://192.168.104/android_audiomanager_scripts/length.php";
    private static String downloadLinkUrl = "http://192.168.104/android_audiomanager_scripts/downloadLink.php";
    private static String thumbnailLinkUrl = "http://192.168.104/android_audiomanager_scripts/thumbnailLink.php";

    public static boolean setIP(String ip)
    {
        if(ip.startsWith(mBaseIp))
        {
            mCurrentIP = ip;
            return true;
        }
        else
        {
            Log.d(TAG, "Set-IP failed: Invalid IP-Address");
            return false;
        }
    }

    /*
     * Getters
     */

    public static String getSignUpUrl()
    {
        return signUpUrl.replace("192.168.104", getCurrentIP());
    }

    public static String getSignInUrl()
    {
        return signInUrl.replace("192.168.104", getCurrentIP());
    }

    public static String getTitleUrl()
    {
        return titleUrl.replace("192.168.104", getCurrentIP());
    }

    public static String getLengthUrl()
    {
        return lengthUrl.replace("192.168.104", getCurrentIP());
    }

    public static String getDownloadLinkUrl()
    {
        return downloadLinkUrl.replace("192.168.104", getCurrentIP());
    }

    public static String getThumbnailLinkUrl()
    {
        return thumbnailLinkUrl.replace("192.168.104", getCurrentIP());
    }


    private static String getCurrentIP()
    {
        return mCurrentIP;
    }
}

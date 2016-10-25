package bll.scg.de.audiomanager.util;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Simon C. Gorissen on 24.10.2016.
 */

/*
 * ParameterizedStringRequest is a standard String request
 * which supports an arbitrary number of Post-Parameters for php-scripts to work with.
 */
public class ParamStringRequest extends Request<String>
{
    private final Response.Listener<String> listener;
    private final Response.ErrorListener errorListener;

    private Map<String, String> params;

    public ParamStringRequest(String url, Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, url, errorListener);
        this.params = params;
        this.listener = listener;
        this.errorListener = errorListener;
    }

    @Override
    public Map<String, String> getParams()
    {
        return params;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response)
    {
        try
        {
            String responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders((response)));
        }
        catch(UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response)
    {
        listener.onResponse(response);
    }
}

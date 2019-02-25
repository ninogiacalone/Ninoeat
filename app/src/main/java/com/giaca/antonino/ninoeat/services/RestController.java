package com.giaca.antonino.ninoeat.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by anton on 14/02/2019.
 */
//https://4a517745.ngrok.io/api/"
   // "v1/"
public class RestController {
    private final static String BASE_URL = "http://138.68.86.70/";
    private final static String VERSION = "";
    private RequestQueue queue;


    public RestController(Context context) {

        queue = Volley.newRequestQueue(context);


    }

    public void getRequest(String endpoint, Response.Listener<String> success, Response.ErrorListener erro) {
        StringRequest request = new StringRequest(Request.Method.GET,
                BASE_URL.concat(VERSION).concat(endpoint),
                success,
                erro
        );
        queue.add(request);
    }

    public void postRequest(String endpoint, final Map<String, String> params, Response.Listener<String> success, Response.ErrorListener error) {
        String url = BASE_URL.concat(VERSION).concat(endpoint);
        StringRequest request = new StringRequest(Request.Method.POST, url, success, error) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

        };

        queue.add(request);
    }


    public void postRequest(String endpoint, final JSONObject params, Map<String, String> headers, Response.Listener<JSONObject> succes, Response.ErrorListener error) {


        String url = BASE_URL.concat(VERSION).concat(endpoint);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                params,
                succes,
                error
        );

/*@Override
    public Map<String,String> getHeaders() throws AuthFailureError {
        return headers;
public Map<String,String> getHeaders( throws AuthFailureError){
    return headers;

    }
    queue.add(request);

};
*/


    }
}

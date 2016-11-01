package droid.zaeem.notifierx.server;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import droid.zaeem.notifierx.helpers.CommonUtilities;
import droid.zaeem.notifierx.helpers.Constants;

public class ServerUtilities {


    public int registerVisitor(String name, String email, String phone, String token, final String serverURL) {
        int result = 0;
        Log.i(CommonUtilities.TAG, "registering device (token = " + token + ")");
        final Map<String, String> params = new HashMap<String, String>();
        params.put(Constants.Keys.TOKEN, token);
        params.put(Constants.Keys.NAME, name);
        params.put(Constants.Keys.EMAIL, email);
        params.put(Constants.Keys.PHONE, phone);
        try {
            URL url;
            try {
                url = new URL(serverURL);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("invalid url: " + serverURL);
            }
            StringBuilder bodyBuilder = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            // constructs the POST body using the parameters
            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                bodyBuilder.append(param.getKey()).append('=')
                        .append(param.getValue());
                if (iterator.hasNext()) {
                    bodyBuilder.append('&');
                }
            }
            String body = bodyBuilder.toString();
            Log.v(CommonUtilities.TAG, "Posting '" + body + "' to " + url);
            byte[] bytes = body.getBytes();
            HttpURLConnection conn = null;
            try {
                Log.e("URL", "> " + url);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setFixedLengthStreamingMode(bytes.length);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                // post the request
                OutputStream out = conn.getOutputStream();
                out.write(bytes);
                out.close();

                result = conn.getResponseCode();
                if (result != 200) {
                    throw new IOException("Post failed with error code " + result);
                } else
                    Log.v("post done", "post request done" + bytes.toString());
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public int registerStudent(String rollnumber, String classID, String year, String name, String email, String phone, String token, final String serverURL) {
        int result = 0;
        Log.i(CommonUtilities.TAG, "registering device (token = " + token + ")");
        final Map<String, String> params = new HashMap<String, String>();
        params.put(Constants.Keys.ROLLNUMBER, rollnumber);
        params.put(Constants.Keys.CLASS, classID);
        params.put(Constants.Keys.YEAR, year);
        params.put(Constants.Keys.TOKEN, token);
        params.put(Constants.Keys.NAME, name);
        params.put(Constants.Keys.EMAIL, email);
        params.put(Constants.Keys.PHONE, phone);
        try {
            URL url;
            try {
                url = new URL(serverURL);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("invalid url: " + serverURL);
            }
            StringBuilder bodyBuilder = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            // constructs the POST body using the parameters
            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                bodyBuilder.append(param.getKey()).append('=')
                        .append(param.getValue());
                if (iterator.hasNext()) {
                    bodyBuilder.append('&');
                }
            }
            String body = bodyBuilder.toString();
            Log.v(CommonUtilities.TAG, "Posting '" + body + "' to " + url);
            byte[] bytes = body.getBytes();
            HttpURLConnection conn = null;
            try {
                Log.e("URL", "> " + url);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setFixedLengthStreamingMode(bytes.length);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                // post the request
                OutputStream out = conn.getOutputStream();
                out.write(bytes);
                out.close();

                //read response data from server
         /*       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                StringBuilder responseOutput = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    responseOutput.append(line);
                }
                br.close();*/

                result = conn.getResponseCode();
                if (result != 200) {
                    throw new IOException("Post failed with error code " + result);
                } else
                    Log.v("post done", "post request done" + bytes.toString());
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int refreshUser(String user, String oldToken, String newToken, final String serverURL) {
        int result = 0;
        final Map<String, String> params = new HashMap<String, String>();
        params.put(Constants.Keys.USER, user);
        params.put(Constants.Keys.OLD, oldToken);
        params.put(Constants.Keys.NEW, newToken);
        try {
            URL url;
            try {
                url = new URL(serverURL);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("invalid url: " + serverURL);
            }
            StringBuilder bodyBuilder = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            // constructs the POST body using the parameters
            while (iterator.hasNext()) {
                Map.Entry<String, String> param = iterator.next();
                bodyBuilder.append(param.getKey()).append('=')
                        .append(param.getValue());
                if (iterator.hasNext()) {
                    bodyBuilder.append('&');
                }
            }
            String body = bodyBuilder.toString();
            Log.v(CommonUtilities.TAG, "Posting '" + body + "' to " + url);
            byte[] bytes = body.getBytes();
            HttpURLConnection conn = null;
            try {
                Log.e("URL", "> " + url);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setFixedLengthStreamingMode(bytes.length);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                // post the request
                OutputStream out = conn.getOutputStream();
                out.write(bytes);
                out.close();

                //read response data from server
             /*   BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                StringBuilder responseOutput = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    responseOutput.append(line);
                }
                br.close();*/

                result = conn.getResponseCode();
                if (result != 200) {
                    throw new IOException("Post failed with error code " + result);
                } else
                    Log.v("post done", "post request done" + bytes.toString());
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
package unl.edu.cse.app;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import Data.User;

/**
 * Created by spencerlandis on 4/26/14.
 */
public final class Accessors {
    public static User createAccount(String email, String password)
    {
        String url = "http://csce.unl.edu:8080/GameTraxService/CreateAccount/?";
        List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

        String paramString = URLEncodedUtils.format(params, "utf-8");

        url += paramString;
        URL obj = null;

        Log.d("request", url);
        try{
            obj = new URL(url);
        }
        catch(MalformedURLException e)
        {
            System.out.println(e.toString());
        }

        HttpURLConnection con = null;

        try{
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
        }catch(IOException e)
        {
            System.out.println(e.toString());
        }
        System.out.println(con.toString());
        int responseCode = 0;
        StringBuffer response = null;
        try{
            responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }catch(IOException e)
        {
            System.out.println(e.toString());
        }


        //print result
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();


        Log.d("response", response.toString().replace("iconUrl", "icon_url"));
        User user = (User) gson.fromJson(parser.parse(response.toString().replace("iconUrl", "icon_url")), User.class);
        return user;
    }

    public static User login(String email, String password)
    {
        String url = "http://csce.unl.edu:8080/GameTraxService/Login/?";
        List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

        String paramString = URLEncodedUtils.format(params, "utf-8");

        url += paramString;
        URL obj = null;

        Log.d("request", url);
        try{
            obj = new URL(url);
        }
        catch(MalformedURLException e)
        {
            System.out.println(e.toString());
        }

        HttpURLConnection con = null;

        try{
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
        }catch(IOException e)
        {
            System.out.println(e.toString());
        }
        System.out.println(con.toString());
        int responseCode = 0;
        StringBuffer response = null;
        try{
            responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }catch(IOException e)
        {
            System.out.println(e.toString());
        }


        //print result
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();


        Log.d("response", response.toString().replace("iconUrl", "icon_url"));
        User user = (User) gson.fromJson(parser.parse(response.toString().replace("iconUrl", "icon_url")), User.class);
        return user;
    }

    public static boolean addGame(int userId, int gameId, String deck, String iconUrl, String name, String siteDetailUrl)
    {
        String url = "http://csce.unl.edu:8080/GameTraxService/AddGame/?";
        List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("user_id", ""+userId));
        params.add(new BasicNameValuePair("game_id", ""+gameId));
        params.add(new BasicNameValuePair("deck",deck));
        params.add(new BasicNameValuePair("icon_url", iconUrl));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("site_detail_url", siteDetailUrl));
        params.add(new BasicNameValuePair("medium_url", "blah"));
        params.add(new BasicNameValuePair("screen_url", "blah"));
        params.add(new BasicNameValuePair("small_url", "blah"));
        params.add(new BasicNameValuePair("thumb_url", "blah"));
        params.add(new BasicNameValuePair("tiny_url", "blah"));

        String paramString = URLEncodedUtils.format(params, "utf-8");

        url += paramString;
        Log.d("addgame", url);
        URL obj = null;

        try{
            obj = new URL(url);
        }
        catch(MalformedURLException e)
        {
            System.out.println(e.toString());
        }

        HttpURLConnection con = null;

        try{
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
        }catch(IOException e)
        {
            System.out.println(e.toString());
        }
        System.out.println(con.toString());
        int responseCode = 0;
        StringBuffer response = null;
        try{
            responseCode = con.getResponseCode();
            if(responseCode != 200)
                return false;

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }catch(IOException e)
        {
            System.out.println(e.toString());
        }

        return true;
    }

    public static boolean removeGame(int userId, int gameId)
    {
        String url = "http://csce.unl.edu:8080/GameTraxService/RemoveGame/?";
        List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("user_id", ""+userId));
        params.add(new BasicNameValuePair("game_id", ""+gameId));

        String paramString = URLEncodedUtils.format(params, "utf-8");

        url += paramString;
        Log.d("removeGame", url);
        URL obj = null;

        try{
            obj = new URL(url);
        }
        catch(MalformedURLException e)
        {
            System.out.println(e.toString());
        }

        HttpURLConnection con = null;

        try{
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
        }catch(IOException e)
        {
            System.out.println(e.toString());
        }
        System.out.println(con.toString());
        int responseCode = 0;
        StringBuffer response = null;
        try{
            responseCode = con.getResponseCode();
            if(responseCode != 200)
                return false;

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }catch(IOException e)
        {
            System.out.println(e.toString());
        }

        return true;
    }

}

package com.company;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
	// write your code here

        String shortenedUrl = "https://t.co/cvfa9S08oL";
        Main t = new Main();
        System.out.println(expand(shortenedUrl));

    }

    public static String expand(String shortenedUrl){
        URL url = null;
        try {
            url = new URL(shortenedUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // open connection
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // stop following browser redirect
        httpURLConnection.setInstanceFollowRedirects(false);

        // extract location header containing the actual destination URL
        String expandedURL = httpURLConnection.getHeaderField("Location");
        httpURLConnection.disconnect();


        return expandedURL;
    }
}

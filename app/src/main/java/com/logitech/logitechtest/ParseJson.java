package com.logitech.logitechtest;


import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import java.io.Writer;


/**
 * Created by venkatesanr on 30/09/2015.
 */
public class ParseJson {
    private final MainActivity myActivity;
    private final Results listener;

    public ParseJson(MainActivity activity, Results lis) {
        this.myActivity = activity;
        this.listener = lis;
        new parseJsonFile().run();
    }

    /**
     * class to Parse Json File
     */
    private class parseJsonFile implements Runnable {
        InputStream inputStream = null;

        public parseJsonFile() {
            inputStream = myActivity.getResources().openRawResource(R.raw.devices);

        }

        @Override
        public void run() {
            listener.onPostResults(createFromJSON(inputStream));
        }
    }

    /**
     * Method to return the String from the inputjson file
     *
     * @param inputStream
     * @return
     */
    private String createFromJSON(InputStream inputStream) {
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = null;
            int read = -1;
            try {
                reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while ((read = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, read);
                }
                Log.i("this", "ssss" + writer.toString());
                return writer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
package com.example.localviewer;

import com.example.localviewer.serverRalated.Zone;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.Charset;

public class InfoJson {
    public static Zone get() throws IOException {
        URI info = URI.create("http://192.168.1.33/bitch/info.json");
        HttpURLConnection httpURLConnection= (HttpURLConnection) info.toURL().openConnection();
        httpURLConnection.setReadTimeout(20);
        httpURLConnection.connect();
        InputStream inputStream= httpURLConnection.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte [] buffer=new byte[64];
        int count=inputStream.read(buffer);
        while (count!=-1){
            byteArrayOutputStream.write(buffer,0,count);
            count=inputStream.read(buffer);
        }
        httpURLConnection.disconnect();
        Zone zone=new Gson().fromJson(new String(byteArrayOutputStream.toByteArray(), Charset.forName("UTF-8")),Zone.class);
        return zone;
    }
}

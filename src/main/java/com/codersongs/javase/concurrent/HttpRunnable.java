package com.codersongs.javase.concurrent;

import com.codersongs.javase.network.HttpUtils;

import java.io.IOException;

public class HttpRunnable implements Runnable{
    private String url;
    private String json;

    public HttpRunnable(String url, String json) {
        this.url = url;
        this.json = json;
    }

    @Override
    public void run() {
        try {
            String res = HttpUtils.postJson(this.url, this.json);
            System.out.println("request json:" + this.json + ",res:" + res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

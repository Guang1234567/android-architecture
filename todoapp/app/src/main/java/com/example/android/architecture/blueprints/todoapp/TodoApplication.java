package com.example.android.architecture.blueprints.todoapp;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.thirdparty.lib.okhttp3.OkHttpClientGenerator;
import com.android.thirdparty.lib.okhttp3.listener.impl.UIProgressListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.android.architecture.blueprints.todoapp.config.TodoGlide;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Guang1234567
 * @date 2018/1/3 15:28
 */

public class TodoApplication extends Application {
    public final static String TAG = "TodoApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClientGenerator.install(this, new HashMap<>());

        OkHttpClient client = OkHttpClientGenerator.createProgressResponseHttpsClient(new UIProgressListener() {
            @Override
            public void onStart(long currentBytes, long contentLength, boolean done) {

            }

            @Override
            public void onProgress(long currentBytes, long contentLength, boolean done) {
                Log.w(TAG, "#onProgress : " + (((double)currentBytes) / ((double)contentLength) * 100) + " %");
            }

            @Override
            public void onFinish(long currentBytes, long contentLength, boolean done) {

            }

            @Override
            public void onError(long currentBytes, long contentLength, boolean done, Throwable error) {
                Log.e(TAG, "", error);
            }
        });

        client.newCall(new Request.Builder().url("https://www.baidu.com").build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String htmlStr = response.body().string();

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TodoApplication.this, htmlStr, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        TodoGlide.with(this) //getContext() reture ApplicationContext, no memory leak.
                .downloadOnly()
                .load("https://www.baidu.com")
                .listener(new RequestListener<File>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .submit();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}

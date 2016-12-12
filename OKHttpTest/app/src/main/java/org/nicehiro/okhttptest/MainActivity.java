package org.nicehiro.okhttptest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvContent;

    private OkHttpClient mHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = (TextView) findViewById(R.id.tv_content);

        findViewById(R.id.do_get).setOnClickListener(this);
        findViewById(R.id.do_post).setOnClickListener(this);
        findViewById(R.id.do_async).setOnClickListener(this);
    }

    private String doGet() {
        Request request = new Request.Builder().url("http://httpbin.org/get")
                .header("User-Agent", "OKHttpDemo/1.0")
                .header("User_agent", "OKHttpDemo/2.0")
                .addHeader("X-key", "value1")
                .addHeader("X-key", "value2").build();

        try {
            Response response = mHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String doPost() {
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("text/plain");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                InputStream inputStream = getResources().openRawResource(R.raw.data);
                byte[] buffer = new byte[1024];
                int read;

                while ((read = inputStream.read(buffer)) > 0) {
                    sink.write(buffer, 0, read);
                }
                sink.flush();
                inputStream.close();
            }
        };

        MultipartBody multipartBody = new MultipartBody.Builder().addPart(requestBody)
                .addPart(RequestBody.create(MediaType.parse("text/plain"),
                        "this is a test data from code")).build();
        Request request = new Request.Builder().url("http://httpbin.org/post")
                .header("User_Agent", "OkHttpDemo/1.0")
                .method("POST", multipartBody)
                .build();

        Call call = mHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.isSuccessful()) {
            try {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private void doGetAsync() {
        Request request = new Request.Builder().url("http://httpbin.org/get")
                .header("User_Agent", "OkHttpDemo/1.0")
                .build();

        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    tvContent.post(new Runnable() {
                        @Override
                        public void run() {
                            tvContent.setText(result);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (R.id.do_async == view.getId()) {
            doGetAsync();
        } else {
            doRequest(view.getId());
        }
    }

    private void doRequest(final int id) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                switch (id) {
                    case R.id.do_get:
                        return doGet();
                    case R.id.do_post:
                        return doPost();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (null != s) {
                    tvContent.setText(s);
                }
            }
        };
        task.execute();
    }
}

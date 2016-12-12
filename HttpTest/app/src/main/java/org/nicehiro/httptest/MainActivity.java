package org.nicehiro.httptest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = (TextView) findViewById(R.id.tv_content);

        findViewById(R.id.do_get).setOnClickListener(this);
        findViewById(R.id.do_post).setOnClickListener(this);
        findViewById(R.id.do_get_by_http_client).setOnClickListener(this);
    }

    private void doRequest(final int id) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                switch (id) {
                    case R.id.do_get:
                        return doHttpGet();
                    case R.id.do_post:
                        return doHttpPost();
                    case R.id.do_get_by_http_client:
                        return doHttpGetByClient();
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

    private String doHttpGet() {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL("http://httpbin.org/get");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            if (HttpURLConnection.HTTP_OK == urlConnection.getResponseCode()) {
                InputStream inputStream = urlConnection.getInputStream();
                return readInputStream(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String doHttpPost() {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL("http://httpbin.org/post");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(10 * 1000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "text/plain");
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write("this is a test data".getBytes());
            outputStream.flush();

            if (HttpURLConnection.HTTP_OK == urlConnection.getResponseCode()) {
                InputStream inputStream = urlConnection.getInputStream();
                return readInputStream(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != urlConnection) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    private String doHttpGetByClient() {
        return null;
    }

    private String readInputStream(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int read = 0;
        try {
            while ((read = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(outputStream.toByteArray());
    }

    @Override
    public void onClick(View view) {
        doRequest(view.getId());
    }
}

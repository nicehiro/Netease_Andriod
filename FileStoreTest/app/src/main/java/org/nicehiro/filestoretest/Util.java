package org.nicehiro.filestoretest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by root on 16-11-8.
 */

public class Util {

    public static String getStringStream(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;

        try {
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            outputStream.close();
            inputStream.close();

            byte[] bytes = outputStream.toByteArray();
            String stringStream = new String(bytes, "utf-8");
            return stringStream;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}

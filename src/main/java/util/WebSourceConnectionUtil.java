package util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class WebSourceConnectionUtil {
    public static HttpURLConnection getConnection(String stringUrl)
            throws MalformedURLException, IOException {
        URL url = new URL(stringUrl);
        return (HttpsURLConnection) url.openConnection();
    }
}

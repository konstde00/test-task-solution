package service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Service;
import service.DataReader;
import util.WebSourceConnectionUtil;

@Service
public class WebSourceReader implements DataReader {
    public static String getJson(String stringUrl) {
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) WebSourceConnectionUtil.getConnection(stringUrl);
            BufferedReader bufferedReader
                    = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + System.lineSeparator());
            }
            bufferedReader.close();
            return stringBuilder.toString().substring(1, stringBuilder.length() - 2)
                    .substring(0, stringBuilder.toString()
                            .substring(1, stringBuilder.length()).length() - 2)
                    .replaceAll("}]", "");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Can't get JSONs from url:" + stringUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't get JSONs from url:" + stringUrl, e);
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                    throw new RuntimeException("Error in the process of disconnecting", e);
                }
            }
        }
    }

    @Override
    public List<String> readFromDataSource(String sourcePath) {
        return Stream.of(getJson(sourcePath).split("},"))
                .map(string -> string + "}")
                .collect(Collectors.toList());
    }
}

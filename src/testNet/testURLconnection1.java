package testNet;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class testURLconnection1 {
    public static void main(String[] args) {
        Date today = new Date();
        long secondADay = 24 * 60 * 60 * 1000;
        try {
            URL u = new URL("http://news.baidu.com");
            URLConnection uc = u.openConnection();
            uc.setUseCaches(false);//总是不使用缓存
            uc.setIfModifiedSince(new Date(today.getTime() - secondADay).getTime());
            uc.setRequestProperty("Cache-Control", "max-age=0");

            Map<String, List<String>> propsMap = uc.getRequestProperties();
            Set<String> propKeys = propsMap.keySet();
            for (String propKey : propKeys) {
                System.out.println(propKey + " " + propsMap.get(propKey));
            }


            try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
                Reader r = new InputStreamReader(in);
                int c;
                while ((c = r.read()) != -1) {
//                    System.out.print((char)c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

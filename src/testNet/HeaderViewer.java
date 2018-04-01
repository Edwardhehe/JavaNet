package testNet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class HeaderViewer {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.baidu.com");
            URLConnection uc = u.openConnection();
            System.out.println(uc.getLastModified());
            Date date = new Date(uc.getLastModified());
            System.out.println(date);
            String date2 = uc.getHeaderField("Content-length");
            System.out.println(date2);
            String header6 = uc.getHeaderFieldKey(6);
            System.out.println(header6);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

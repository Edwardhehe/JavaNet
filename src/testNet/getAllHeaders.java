package testNet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class getAllHeaders {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://163.com");
            URLConnection uc = u.openConnection();
            for (int i = 1; ; i++) {
                if (uc.getHeaderField(i) == null) {
                    break;
                }
                System.out.println(uc.getHeaderFieldKey(i) + " " + uc.getHeaderField(i));
            }
            System.out.println();

            Date date2 = new Date(uc.getHeaderFieldDate("date", 0));
            int length = uc.getHeaderFieldInt("content-length", -1);
            System.out.println(length);
            System.out.println(date2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

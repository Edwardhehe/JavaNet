package testNet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PostForm {
    private URL url;
    private QueryString query;

    public PostForm(URL url) {
        if (!url.getProtocol().startsWith("http"))
            this.url = null;
        this.url = url;
    }

    public void add(String name, String value) {
        query.add(name, value);
    }

    public URL getUrl() {
        return url;
    }

    public InputStream post() throws IOException {
        URLConnection uc = url.openConnection();
        uc.setDoInput(true);//设置这个字段为true就可以post了
        try (OutputStreamWriter out = new OutputStreamWriter
                (uc.getOutputStream(), "UTF-8")) {
            out.write(query.toString());
            out.flush();
        }
        return uc.getInputStream();
    }

    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.html");
            PostForm pf = new PostForm(url);
            pf.add("name", "lee");
            pf.add("Email", "lihaoyangyan@163.com");
            try (BufferedInputStream bf = new BufferedInputStream(pf.post())) {
                int c;
                while ((c = bf.read()) != -1) {
                    System.out.print((char) c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}

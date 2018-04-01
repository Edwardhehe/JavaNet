package TestSocket;

import java.io.*;
import java.net.*;

public class SocketComu {
    private static final String hostName="";
    private static final long timeout=15000;
    private static final int port=2628;

    public static void test01(String[] args) {
        Socket socket=null;
        try {
            socket=new Socket(hostName,port);
            InputStream in=socket.getInputStream();
            BufferedReader br=new BufferedReader(
                    new InputStreamReader(in,"UTF-8")
            );
            OutputStream out=socket.getOutputStream();
            Writer w=new OutputStreamWriter(out,"UTF-8");
            BufferedWriter bw=new BufferedWriter(w);

            for(String s:args){
                define(s,w,br);
            }
            bw.write("quit\r\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void define(String word,Writer writer,BufferedReader reader) throws IOException {
        writer.write("DEFINE eng-lat"+word+"\r\n");
        writer.flush();

        for(String line=reader.readLine();line!=null;line=reader.readLine()){
            if(line.startsWith("250")){
                return;
            }
            else if(line.startsWith("552")){
                System.out.println("无匹配");
                return;
            }
            else if(line.matches("\\d\\d\\d.*"))continue;
            else if(line.trim().equals("."))continue;
            else System.out.println(line);
        }
    }

    public static void test02(String[] args) {
        try(Socket connection =new Socket("www.oreilly.com",80)){
            Writer out= new OutputStreamWriter(connection.getOutputStream(),"8859_1");
            out.write("GET / HTTP 1.0\r\n\r\n");
            out.flush();
            connection.shutdownOutput();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test03(String[] args) {
        Socket socket=null;
        try {
            socket = new Socket();
            SocketAddress sa=new InetSocketAddress("time.nist.gov",13);
            socket.connect(sa);
        }catch (IOException e){
            System.err.println(e);
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        SocketAddress socketAddress=new InetSocketAddress("myproxy.example.com",1080);
        Proxy proxy=new Proxy(Proxy.Type.SOCKS,socketAddress);
        Socket socket=new Socket(proxy);
        SocketAddress remote=new InetSocketAddress("baidu.com",12);
        socket.connect(remote);

    }
}

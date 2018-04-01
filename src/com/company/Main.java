package com.company;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        // write your code here

        ServerSocket sersoc;
        Socket soc;
        DataInputStream in;
        PrintStream out;
        try {
            sersoc = new ServerSocket(8000);
            soc = sersoc.accept();
            in = new DataInputStream(soc.getInputStream());
            out = new PrintStream(soc.getOutputStream());
            InetAddress clientIp = soc.getInetAddress();
            System.out.println(clientIp);
            out.println("hello are you ok");

            String str = in.readLine();
            while (!str.equals("quit")) {
                System.out.println("客户端ip：" + str);
                str = in.readLine();
            }
            in.close();
            out.close();
            soc.close();
            sersoc.close();
        } catch (IOException e) {
            System.out.println("无法连接主机");
        } finally {

        }

    }
}

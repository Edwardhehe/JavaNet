package com.company;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TopServer {
    public static void main(String[] args) throws IOException {
        ServerSocket svrsoc = null;
        Socket soc = null;
        DataInputStream in = null;
        PrintStream out = null;
        InetAddress clientIp = null;
        String str = null;

        try {
            svrsoc = new ServerSocket(8000);
            soc = svrsoc.accept();
            in = new DataInputStream(soc.getInputStream());
            out = new PrintStream(soc.getOutputStream());
            clientIp = soc.getInetAddress();
            System.out.println("client's Ip is " + clientIp);
            out.println("Hello");
            str = in.readLine();
            while (!str.equals("quit")) {
                System.out.print("Client say:");
                str = in.readLine();

            }
            System.out.println("Client wants to leave");

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }

    }
}

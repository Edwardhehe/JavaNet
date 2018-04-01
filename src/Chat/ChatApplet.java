package Chat;

import java.applet.Applet;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class ChatApplet extends Applet implements Runnable {

    TextArea m_textArea;
    TextField m_textField;
    DataInputStream m_in;
    DataOutputStream m_out;

    public void init() {
        setLayout(null);
        setSize(426, 266);

        m_textArea = new TextArea(10, 10);
        m_textField = new TextField();
        m_in = null;
        m_out = null;

        URL url = getCodeBase();
        try {
            InetAddress inetAddr = InetAddress.getByName(url.getHost());
            Socket m_socket;
            System.out.println("Server" + inetAddr + " " + url.getHost() + " " + url.getProtocol());
            m_socket = new Socket(inetAddr, 5555);
            m_in = new DataInputStream(m_socket.getInputStream());
            m_out = new DataOutputStream(m_socket.getOutputStream());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        add("Center", m_textArea);
        add("South", m_textField);
        m_textArea.setEditable(false);

        new Thread(this).start();
    }

    public boolean handleEvent(Event event) {
        String b = m_textField.getText();

        if ((event.target == m_textField) && (event.id == Event.ACTION_EVENT)) {
            m_textField.setText("");
            try {
                m_out.writeUTF(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return super.handleEvent(event);
        }
    }

    @Override
    public void run() {

        try {
            while (true) {
                String s = m_in.readUTF();
                if (s != null)
                    m_textArea.append(s + "\n");
            }
        } catch (Exception e) {
            m_textArea.append("NetWork problem or Sever down.\n");
            m_textField.setVisible(false);
        }
    }

    public void stop() {
        try {
            m_out.writeUTF("leave");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

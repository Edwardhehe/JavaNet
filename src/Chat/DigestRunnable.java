package Chat;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestRunnable implements Runnable {

    private File file;

    public DigestRunnable(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(file);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) ;
            din.close();
            byte[] digest = sha.digest();

            StringBuilder result = new StringBuilder(file.getName());
            result.append(":");
            result.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(result);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        File files = new File("f:/audio");
        for (File file : files.listFiles()) {
            if (file.isFile()) {
                DigestThread dt = new DigestThread(file);
                Thread t = new Thread(dt);
                t.start();
            }

        }
    }
}

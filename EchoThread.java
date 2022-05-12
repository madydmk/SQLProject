import java.io.*;
import java.net.*;
import java.util.Scanner;
public class EchoThread extends Thread {
    protected Socket socket;
    protected Socket socketMe;

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
             ObjectOutputStream output =
             new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input =
             new ObjectInputStream(socket.getInputStream());
             // ..........................................................
             String chaine = (String) input.readObject();
             System.out.println(" recu : " + chaine);
             // ..........................................................
             System.out.println(" ca vient de : " + socket.getInetAddress() +
            ":" + socket.getPort());
             // ..........................................................
             output.writeObject(new String("bien recu"));

        } catch (Exception e) {
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    out.writeBytes(line + "\n\r");
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
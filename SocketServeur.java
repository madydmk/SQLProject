import java.io.*;
import java.util.HashMap;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    private static HashMap<Integer, String> listes;
 public static void main(String argv[]) {
 int port = 0;
 Scanner keyb = new Scanner(System.in);
 // .............................................................
 System.out.print("Port d'écoute : ");
 try {
 port = keyb.nextInt();
 } catch (NumberFormatException e) {
 System.err.println("Le paramètre n'est pas un entier.");
 System.err.println("Usage : java ServeurUDP port-serveur");
 System.exit(-1);
 }
 try {
 // ............................................................. 
 ServerSocket serverSocket = new ServerSocket(85);
 listes = new HashMap<Integer, String>();
 // ..........................................................
  while (true) {
            try{
            Socket socket = serverSocket.accept();
            run(socket);
                    aff();

            System.out.println(" recu : ");
           } catch (IOException e) {
                System.out.println("I/O error: " + e);
           }
            // new thread for a client
        }
 // ..........................................................
 
 // ..........................................................
 } catch (Exception e) {
 System.err.println("Erreur : " + e);
 }
 }

 public static void run(Socket socket) {
    Scanner sc = new Scanner(System.in);
        try {
             ObjectOutputStream output =
             new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input =
             new ObjectInputStream(socket.getInputStream());
             // ..........................................................

             // ..........................................................
             System.out.println(" ca vient de : " + socket.getInetAddress() +
            ":" + socket.getPort());
             String chaine = (String) input.readObject();
             System.out.println(" recu : " + chaine);
             
                listes.put(listes.size(), chaine);
             
            System.out.print("IP: " + listes.toString());
             // ..........................................................
             if ((chaine == null) || chaine.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
 System.out.println(chaine + "\n\r");
                    output.flush();
                }
                

        } catch (Exception e) {
            return;
        }
    }
    public static void aff(){
    System.out.print(listes.size());
        for(int i =0; i<listes.size(); i++){
            System.out.print("Aff: " + listes.get(i));
        }
    }
}
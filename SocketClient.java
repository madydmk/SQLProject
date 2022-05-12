import java.io.*;
import java.net.*;
import java.util.Scanner;
class SocketClient {
 public static void main(String argv[]) {
 int port = 0;
 String host = "";
 Scanner keyb = new Scanner(System.in);
 // .............................................................
 System.out.print("Nom du serveur : ");
 host = keyb.next();
 System.out.print("Port du serveur : ");
 
 try {
 port = keyb.nextInt();
 } catch (NumberFormatException e) {
 System.err.println("Le second paramètre n'est pas un entier.");
 System.exit(-1);
 }
 // ............................................................. 
 try {
 // .............................................................
 InetAddress adr = InetAddress.getByName("XFIPARLTPUSR009");
 // .............................................................
 Socket socket = new Socket(adr, port);
 // .............................................................
 ObjectOutputStream output =
 new ObjectOutputStream(socket.getOutputStream());
 ObjectInputStream input =
 new ObjectInputStream(socket.getInputStream());
 // .............................................................
// output.writeObject(new String("Nissan Micra"));
  System.out.print("IP: ");
String ip = keyb.next();
output.writeObject((String)ip);
 // .............................................................
 String chaine = (String) input.readObject();
 System.out.println(" recu du serveur : " + chaine);
 } catch (Exception e) {
 System.err.println("Erreur : " + e);
 }
 }
}

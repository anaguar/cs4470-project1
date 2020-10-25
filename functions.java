import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.ArrayList; 

import java.net.Socket;
import java.net.ServerSocket;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

public class functions {
  public static ArrayList<Peer> peerList = new ArrayList<>();

  // Incoming connection
  public static void listening(Integer port) {
    try {
      // Create socket instance, await new connection
      ServerSocket ss_socket = new ServerSocket(port);
      Socket s_socket = ss_socket.accept();

      Peer peer = new Peer(s_socket, s_socket.getPort());

      // Notify user new connection establish
      System.out.println("You have a new connection from " + s_socket.getInetAddress().getHostAddress() + "\n");
      peerList.add(peer);

      // Print new messages, continue checking status of remote peer
      peer.printMessage();

      // Reopen socket for new connection
      s_socket.close();
      ss_socket.close();
      terminate(peer.getId());
      listening(port);
    } catch (IOException e) {

    }
  }

  // FUNCTION # 1: help
  public static void showHelp() {
    try (BufferedReader br = new BufferedReader(new FileReader("help.txt")))
    {
      String helpline;
      while ((helpline = br.readLine()) != null) {
        System.out.println(helpline);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // FUNCTION # 2: myip
  public static String getMyIP() throws UnknownHostException{
    InetAddress ip;
    ip = InetAddress.getLocalHost();

    return ip.getHostAddress();
  }

  // FUNCTION # 3: myport implemented in main chat class

  // FUNCTION # 4: connect <destination> <port no>
  public static void connect(String destinationIP, Integer portNumber) {
    // TODO: Check if IP address is valid
    try {
      if (destinationIP.equals(getMyIP())) {
        System.out.println("ERROR: Destination IP is invalid (self-connection, duplicate, incorrect format)\n");
      } else {
        Socket socket = new Socket(destinationIP, portNumber);
        Peer peer = new Peer(socket, portNumber);
        System.out.println("You have connected to " + destinationIP + "\n");

        peerList.add(peer);

        Thread messages = new Thread(new Runnable() {
                public void run() {
                  peer.printMessage();
                  terminate(peer.getId());
                }
            });
        messages.start();
      }
    } catch (Exception e) {
      System.out.println("ERROR: Cannot connect to " + destinationIP + "\n");
    }
  }

  // FUNCTION # 5: list
  public static void listPeers() {
    System.out.println("id: IPaddress\t\tPortNo.");

    for ( Peer peer : peerList) {
      System.out.println(peer.toString());
    }

    System.out.println("");
  }

  // FUNCTION # 6: terminate <connection id.>
  public static void terminate(int connectionID) {
    for (int i = 0; i < peerList.size(); i++) {
      if (peerList.get(i).getId() == connectionID) {
        peerList.get(i).terminate();
        peerList.remove(i);
        System.out.println("Terminated connection ID " + connectionID + "\n");
        return;
      }
    }
  }

  // FUNCTION # 7: send <connection id.> <message>
  public static void send(int connectionID, String message) {
    boolean in_list = false;

    for (Peer peer : peerList) {
      if (peer.getId() == connectionID) {
        in_list = true;
        // Write message to output stream
        peer.sendMessage(message);

        // Confirmation to sender
        System.out.println("Message sent to " + peer.getId() + "\n");
      }
    }

    if (!in_list) {
      System.out.println("ERROR: Connection ID " + connectionID + " not in list. \n");
    }
  }

  // FUNCTION # 8: exit
  public static void exit() {
    for ( Peer peer : peerList) {
      peer.terminate();
    }

    System.out.println("Closing all connections. Terminating processes...\n");
  }
}
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
public static String getMyIP() {
  InetAddress ip;
  try {
    ip = Inet4Address.getLocalHost();
    return ip.getHostAddress();
  } catch (Exception e) {
    System.out.println("Can not get ip address:" + e.toString());
  }
  return "";
}

// FUNCTION # 3: myport
public static int getMyPortNumber() {
  return PORTNUMBER;
}

// FUNCTION # 4: connect <destination> <port no>
public static void connect(String destinationIP, int portNumber) {
  // TODO: Check if IP address is valid
  try {
    Socket socket = new Socket(destinationIP, portNumber);
    Peer peer = new Peer(socket, portNumber);
    peer.sendMessage("You have connected to " + socket.getLocalAddress().toString());
    System.out.println("You have connected to " + destinationIP);
    peerList.add(peer);
  } catch (Exception e) {
    System.out.println("Can not connect to " + destinationIP + ". Error:" + e.toString());
  }
}

// FUNCTION # 5: list
public static void listPeers() {
  System.out.println("id: IPaddress   PortNo.");
  for ( Peer peer : peerList) {
    System.out.println(peer.toString());
  }
}

// FUNCTION # 6: terminate <connection id.>
public static void terminate(int connectionID) {
  for (int i = 0; i < peerList.size(); i++) {
    if (peerList.get(i).getId() == connectionID) {
      peerList.get(i).terminate();
      peerList.remove(i);
      return;
    }
  }
}

// FUNCTION # 7: send <connection id.> <message>
public static void send(int connectionID, String message) {
  for ( Peer peer : peerList) {
    if (peer.getId() == connectionID) {
      peer.sendMessage(message);
      return;
    }
  }
}

// FUNCTION # 8: exit
public static void exit() {
  for ( Peer peer : peerList) {
    peer.terminate();
  }
  terminated = true;
}

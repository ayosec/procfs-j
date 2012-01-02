package com.ayosec.procfs;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TCPInfo {

  public static class Socket {
    // Pattern to parse /proc/net/tcp
    final private Pattern fieldsPattern = Pattern.compile("^\\s*(\\d+): ([0-9A-F]+):(....) ([0-9A-F]+):(....) (..) (?:\\S+ ){3}\\s*(\\d+)\\s+\\d+\\s+(\\d+).*$");

    private String slot;
    private String localAddress;
    private String localPort;
    private String remoteAddress;
    private String remotePort;
    private String state;
    private String uid;
    private String inode;

    Socket(String line) {
      Matcher match = fieldsPattern.matcher(line);
      match.lookingAt();

      slot            = match.group(1);
      localAddress    = match.group(2);
      localPort       = match.group(3);
      remoteAddress   = match.group(4);
      remotePort      = match.group(5);
      state           = match.group(6);
      uid             = match.group(7);
      inode           = match.group(8);
    }

    @Override
    public String toString() {
      return "TCPInfo.Socket(" +
        "slot = " + slot +
        ", localAddress = " + localAddress +
        ", localPort = " + getLocalPort() +
        ", remoteAddress = " + remoteAddress +
        ", remotePort = " + getRemotePort() +
        ", state = " + state +
        ", uid = " + uid +
        ", inode = " + inode + ")";
    }

    public int getSlot() {
      return Integer.parseInt(slot);
    }

    public String getLocalAddress() {
      return localAddress;
    }

    public int getLocalPort() {
      return Integer.parseInt(localPort, 16);
    }

    public String getRemoteAddress() {
      return remoteAddress;
    }

    public int getRemotePort() {
      return Integer.parseInt(remotePort, 16);
    }

    public String getState() {
      return state;
    }

    public int getUID() {
      return Integer.parseInt(uid);
    }

    public int getINode() {
      return Integer.parseInt(inode);
    }

  }

  private static void parseTCPTable(String path, ArrayList<Socket> sockets) throws IOException {
    String line;

    BufferedReader tcpInfoReader = new BufferedReader(new FileReader(path));
    tcpInfoReader.readLine(); // Skip header

    while((line = tcpInfoReader.readLine()) != null)
      sockets.add(new Socket(line));

    tcpInfoReader.close();
  }

  /**
   * @return a list of active sockets
   */
  public static ArrayList<Socket> getSockets() throws IOException {
    ArrayList<Socket> sockets = new ArrayList<Socket>();
    parseTCPTable("/proc/net/tcp", sockets);
    parseTCPTable("/proc/net/tcp6", sockets);
    return sockets;
  }

  /**
   * Find the process listening on port.
   *
   * @return an instance of Proc, or null is none is found.
   *
   * @param port The target port
   */
  public static Proc processOnPort(int port) {
    Socket socket = null;
    try {
      for(Socket s : getSockets()) {
        if(s.getLocalPort() == port) {
          socket = s;
          break;
        }
      }
    } catch(IOException e) {
      return null;
    }

    if(socket == null)
      return null;

    // Use a regex to extract the value of the socket[NNN] items
    Matcher matcher = Pattern.compile("socket:\\[(\\d+)\\]").matcher("");

    // Recursive search in the /proc/X/fd/Y files
    for(File dirItem : new File("/proc").listFiles()) {
      int pid;

      try {
        pid = Integer.parseInt(dirItem.getName());
      } catch (NumberFormatException e) {
        // Not a process. Skip this directory
        continue;
      }

      try {
        File[] fds = new File(dirItem, "fd").listFiles();

        if(fds == null)
          continue;

        for(File fd : fds) {
          String link = LibC.readlink(fd.getAbsolutePath());
          matcher.reset(link);
          if(matcher.lookingAt()) {
            int inode = Integer.parseInt(matcher.group(1));
            if(socket.getINode() == inode)
              return new Proc(pid);
          }
        }
      } catch(ErrnoException e) {
        // An ErrnoException error would occur when this process can not read
        // the /proc/<pid>/fd content. Normally, this means that this process
        // has no permission to get that information (security, whatever),
        // so we only need to ignore that case.
      }
    }

    return null;
  }

  /**
    * Block the current thread until the port is available
    *
    * @param port The port for waiting
    */
  public static void waitForReadyPort(int port) throws IOException {
    while(true) {
      for(Socket socket : getSockets()) {
        if(socket.getLocalPort() == port && socket.getState().equals("0A"))
          return;
      }

      // Wait 100 milliseconds before the next check
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        return;
      }
    }

  }

  /**
    * Block the current thread until the port is free
    *
    * @param port The port for waiting
    */
  public static void waitForFreePort(int port) throws IOException {
    while(true) {
      boolean portFound = false;

      for(Socket socket : getSockets()) {
        if(socket.getLocalPort() == port && socket.getState().equals("0A")) {
          portFound = true;
          break;
        }
      }

      if(!portFound)
        break;

      // Wait 100 milliseconds before the next check
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        return;
      }
    }
  }
}

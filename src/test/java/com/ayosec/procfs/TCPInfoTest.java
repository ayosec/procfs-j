package com.ayosec.procfs;

import java.net.ServerSocket;
import java.util.ArrayList;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TCPInfoTest extends TestCase
{

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() { return new TestSuite( TCPInfoTest.class ); }

  /**
   * Test the /proc/net/tcp parser (IPv4)
   */
  public void testParseLine4() {
    String sample = "  2: 0100007F:1111 01010101:AABB 0A 00000000:00000000 00:00000000 00000000  1000        0 12345 1   fff";
    TCPInfo.Socket socket = new TCPInfo.Socket(sample);

    assertEquals(2, socket.getSlot());
    assertEquals("0100007F", socket.getLocalAddress());
    assertEquals(0x1111, socket.getLocalPort());
    assertEquals("01010101", socket.getRemoteAddress());
    assertEquals(0xAABB, socket.getRemotePort());
    assertEquals("0A", socket.getState());
    assertEquals(1000, socket.getUID());
    assertEquals(12345, socket.getINode());
  }

  /**
   * Test the /proc/net/tcp parser (IPv4)
   */
  public void testParseLine6() {
    String sample = "  2: 00000000000000000000000000000001:1111 00000000000000000000000000000002:AABB 0A 00000000:00000000 00:00000000 00000000  1000        0 12345 1   fff";
    TCPInfo.Socket socket = new TCPInfo.Socket(sample);
    assertEquals(2, socket.getSlot());
    assertEquals("00000000000000000000000000000001", socket.getLocalAddress());
    assertEquals(0x1111, socket.getLocalPort());
    assertEquals("00000000000000000000000000000002", socket.getRemoteAddress());
    assertEquals(0xAABB, socket.getRemotePort());
    assertEquals("0A", socket.getState());
    assertEquals(1000, socket.getUID());
    assertEquals(12345, socket.getINode());

  }

  /**
   * Create a symbolic link and check if the content is the expected
   */
  public void testReadData() throws Exception
  {
    int found = 0;
    ServerSocket server = new ServerSocket(0);

    ArrayList<TCPInfo.Socket> sockets = TCPInfo.getSockets();

    for(TCPInfo.Socket socket : sockets)
      if(socket.getLocalPort() == server.getLocalPort()) {
        assertEquals(LibC.Handle.module.getuid(), socket.getUID());

        found++;
      }

    assertEquals(1, found);
  }

  /**
   * Create a server and find the process related.
   *
   * The PID should our own PID
   */
  public void testFindProcess() throws Exception {
    ServerSocket server = new ServerSocket(0);
    Proc process = TCPInfo.processOnPort(server.getLocalPort());

    assertEquals(process.getPID(), LibC.Handle.module.getpid());
  }

  /**
   * Wait to have a port with no use
   */
  public void testWaitForFreePort() throws Exception {
    final ServerSocket server = new ServerSocket(0);

    long start = System.currentTimeMillis();
    new Thread(new Runnable() {
      public void run() {
        try {
          Thread.sleep(500);
          server.close();
        } catch(Exception e) {
          fail(e.toString());
        }
      }
    }).start();

    TCPInfo.waitForFreePort(server.getLocalPort());
    assertTrue((System.currentTimeMillis() - start) >= 500);
  }

  /**
   * Wait to have a port ready to use
   */
  public void testWaitForReadyPort() throws Exception {
    final ServerSocket server = new ServerSocket();
    final int serverPort = 65123;

    long start = System.currentTimeMillis();
    new Thread(new Runnable() {
      public void run() {
        try {
          Thread.sleep(500);
          server.bind(new java.net.InetSocketAddress(serverPort));
        } catch(Exception e) {
          fail(e.toString());
        }
      }
    }).start();

    TCPInfo.waitForReadyPort(serverPort);
    assertTrue((System.currentTimeMillis() - start) >= 500);
  }
}

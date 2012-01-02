package com.ayosec.procfs;

import java.math.BigInteger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProcStatTest extends TestCase {

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() { return new TestSuite( ProcStatTest.class ); }

  /**
   * Parse a basic stat line
   */
  public void testStatLine() throws Exception {
    String sample = "8278 (some dirty) name) S 1000 2000 3000 4000 5000 600000 7543 886892 0 30 18 6 2557 154 20 0 1 0 219550 27205632 1819 18446744073709551615 4194304 5126820 140735922701584 140735922700160 140205718142462 0 65536 3686404 1266761467 0 1 2 17 3 4 5 6 7 8\n";

    ProcStat stat = new ProcStat(sample);

    assertEquals(8278,                                    stat.getPID());
    assertEquals("some dirty) name",                      stat.getCommand());
    assertEquals('S',                                     stat.getState());
    assertEquals(1000,                                    stat.getPpid());
    assertEquals(2000,                                    stat.getPgrp());
    assertEquals(3000,                                    stat.getSession());
    assertEquals(4000,                                    stat.getTtyNr());
    assertEquals(5000,                                    stat.getTpgid());
    assertEquals(600000,                                  stat.getFlags());
    assertEquals(new BigInteger("7543"),                  stat.getMinflt());
    assertEquals(new BigInteger("886892"),                stat.getCminflt());
    assertEquals(new BigInteger("0"),                     stat.getMajflt());
    assertEquals(new BigInteger("30"),                    stat.getCmajflt());
    assertEquals(18,                                      stat.getUtime());
    assertEquals(6,                                       stat.getStime());
    assertEquals(2557,                                    stat.getCutime());
    assertEquals(154,                                     stat.getCstime());
    assertEquals(new BigInteger("20"),                    stat.getPriority());
    assertEquals(new BigInteger("0"),                     stat.getNice());
    assertEquals(new BigInteger("1"),                     stat.getNumThreads());
    assertEquals(new BigInteger("0"),                     stat.getItrealvalue());
    assertEquals(new BigInteger("219550"),                stat.getStarttime());
    assertEquals(new BigInteger("27205632"),              stat.getVsize());
    assertEquals(new BigInteger("1819"),                  stat.getRss());
    assertEquals(new BigInteger("18446744073709551615"),  stat.getRsslim());
    assertEquals(new BigInteger("4194304"),               stat.getStartcode());
    assertEquals(new BigInteger("5126820"),               stat.getEndcode());
    assertEquals(new BigInteger("140735922701584"),       stat.getStartstack());
    assertEquals(new BigInteger("140735922700160"),       stat.getKstkesp());
    assertEquals(new BigInteger("140205718142462"),       stat.getKstkeip());
    assertEquals(new BigInteger("0"),                     stat.getSignal());
    assertEquals(new BigInteger("65536"),                 stat.getBlocked());
    assertEquals(new BigInteger("3686404"),               stat.getSigignore());
    assertEquals(new BigInteger("1266761467"),            stat.getSigcatch());
    assertEquals(new BigInteger("0"),                     stat.getWchan());
    assertEquals(new BigInteger("1"),                     stat.getNswap());
    assertEquals(new BigInteger("2"),                     stat.getCnswap());
    assertEquals(17,                                      stat.getExitSignal());
    assertEquals(3,                                       stat.getProcessor());
    assertEquals(new BigInteger("4"),                     stat.getRtPriority());
    assertEquals(new BigInteger("5"),                     stat.getPolicy());
    assertEquals(new BigInteger("6"),                     stat.getDelayacctBlkioTicks());
    assertEquals(new BigInteger("7"),                     stat.getGuestTime());
    assertEquals(new BigInteger("8"),                     stat.getCguestTime());

  }

}

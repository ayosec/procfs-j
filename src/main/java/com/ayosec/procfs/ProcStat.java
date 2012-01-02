package com.ayosec.procfs;

import java.math.BigInteger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ProcStat {

  private int pid;
  private String command;
  private char state;
  private int ppid;
  private int pgrp;
  private int session;
  private int ttyNr;
  private int tpgid;
  private long flags;
  private BigInteger minflt;
  private BigInteger cminflt;
  private BigInteger majflt;
  private BigInteger cmajflt;
  private int utime;
  private int stime;
  private int cutime;
  private int cstime;
  private BigInteger priority;
  private BigInteger nice;
  private BigInteger numThreads;
  private BigInteger itrealvalue;
  private BigInteger starttime;
  private BigInteger vsize;
  private BigInteger rss;
  private BigInteger rsslim;
  private BigInteger startcode;
  private BigInteger endcode;
  private BigInteger startstack;
  private BigInteger kstkesp;
  private BigInteger kstkeip;
  private BigInteger signal;
  private BigInteger blocked;
  private BigInteger sigignore;
  private BigInteger sigcatch;
  private BigInteger wchan;
  private BigInteger nswap;
  private BigInteger cnswap;
  private int exitSignal;
  private int processor;
  private BigInteger rtPriority;
  private BigInteger policy;
  private BigInteger delayacctBlkioTicks;
  private BigInteger guestTime;
  private BigInteger cguestTime;

  // The process ID.
  public int getPID() {
    return pid;
  }

  // The filename of the executable, in parentheses.  This is visible
  // whether  or  not  the  executable  is swapped out.
  public String getCommand() {
    return command;
  }

  // One character from the string "RSDZTW" where R is running, S is
  // sleeping in an interruptible wait, D is waiting in uninterruptible
  // disk sleep, Z is zombie, T is traced or stopped (on a signal), and W
  // is pag‐ ing.
  public char getState() {
    return state;
  }

  // The PID of the parent.
  public int getPpid() {
    return ppid;
  }

  // The process group ID of the process.
  public int getPgrp() {
    return pgrp;
  }

  // The session ID of the process.
  public int getSession() {
    return session;
  }

  // The  controlling  terminal of the process.  (The minor device number
  // is contained in the combination of bits 31 to 20 and 7 to 0; the major
  // device number is in bits 15 to 8.)
  public int getTtyNr() {
    return ttyNr;
  }

  // The ID of the foreground process group of the controlling terminal of
  // the process.
  public int getTpgid() {
    return tpgid;
  }

  // (BigInteger before Linux 2.6.22) The kernel flags word of the process.  For bit
  // meanings,  see  the  PF_*  defines  in  <linux/sched.h>.  Details depend
  // on the kernel version.
  public long getFlags() {
    return flags;
  }

  // The  number  of  minor  faults  the process has made which have not
  // required loading a memory page from disk.
  public BigInteger getMinflt() {
    return minflt;
  }

  // The number of minor faults that the process's waited-for children have
  // made.
  public BigInteger getCminflt() {
    return cminflt;
  }

  // The number of major faults the process has made which have required
  // loading a memory page from disk.
  public BigInteger getMajflt() {
    return majflt;
  }

  // The number of major faults that the process's waited-for children have
  // made.
  public BigInteger getCmajflt() {
    return cmajflt;
  }

  // Amount of time that this process has been scheduled in user mode,
  // measured in clock  ticks  (divide  by sysconf(_SC_CLK_TCK).   This
  // includes  guest  time,  guest_time (time spent running a virtual CPU,
  // see below), so that applications that are not aware of the guest time
  // field do  not  lose  that  time  from their calculations.
  public int getUtime() {
    return utime;
  }

  // Amount  of time that this process has been scheduled in kernel mode,
  // measured in clock ticks (divide by sysconf(_SC_CLK_TCK).
  public int getStime() {
    return stime;
  }

  // Amount of time that this process's waited-for children have been
  // scheduled in user  mode,  measured  in clock  ticks  (divide  by
  // sysconf(_SC_CLK_TCK).   (See  also  times(2).)   This  includes  guest
  // time, cguest_time (time spent running a virtual CPU, see below).
  public int getCutime() {
    return cutime;
  }

  // Amount of time that this process's waited-for children have been
  // scheduled in kernel mode, measured  in clock ticks (divide by
  // sysconf(_SC_CLK_TCK).
  public int getCstime() {
    return cstime;
  }

  // (Explanation  for  Linux  2.6)  For  processes running a real-time
  // scheduling policy (policy below; see sched_setscheduler(2)), this is the
  // negated scheduling priority, minus one; that is, a  number  in  the range
  // -2  to  -100, corresponding to real-time priorities 1 to 99.  For
  // processes running under a non-real-time scheduling policy, this is the
  // raw nice value (setpriority(2)) as represented in the  kernel.  The
  // kernel stores nice values as numbers in the range 0 (high) to 39 (low),
  // corresponding to the user-visible nice range of -20 to 19.
  // Before Linux 2.6, this was a scaled value based on the scheduler weighting
  // given to this process.
  public BigInteger getPriority() {
    return priority;
  }

  // The nice value (see setpriority(2)), a value in the range 19 (low
  // priority) to -20 (high priority).
  public BigInteger getNice() {
    return nice;
  }

  // Number of threads in this process (since Linux 2.6). Before kernel 2.6,
  // this field was hard coded to 0 as a placeholder for an earlier removed
  // field.
  public BigInteger getNumThreads() {
    return numThreads;
  }

  // The time in jiffies before the next SIGALRM is sent to the process
  // due to an interval timer. Since kernel 2.6.17, this field is no longer
  // maintained, and is hard coded as 0.
  public BigInteger getItrealvalue() {
    return itrealvalue;
  }

  // (was BigInteger before Linux 2.6) The time in jiffies the process started after
  // system boot.
  public BigInteger getStarttime() {
    return starttime;
  }

  // Virtual memory size in bytes.
  public BigInteger getVsize() {
    return vsize;
  }

  // Resident Set Size: number of pages the process has in real memory.
  // This is just the pages which count toward  text, data, or stack
  // space. This does not include pages which have not been
  // demand-loaded in, or which are swapped out.
  public BigInteger getRss() {
    return rss;
  }

  // Current soft limit in bytes on the rss of the process; see the
  // description of RLIMIT_RSS in getpriority(2).
  public BigInteger getRsslim() {
    return rsslim;
  }

  // The address above which program text can run.
  public BigInteger getStartcode() {
    return startcode;
  }

  // The address below which program text can run.
  public BigInteger getEndcode() {
    return endcode;
  }

  // The address of the start (i.e., bottom) of the stack.
  public BigInteger getStartstack() {
    return startstack;
  }

  // The current value of ESP (stack pointer), as found in the kernel stack
  // page for the process.
  public BigInteger getKstkesp() {
    return kstkesp;
  }

  // The current EIP (instruction pointer).
  public BigInteger getKstkeip() {
    return kstkeip;
  }

  // The  bitmap  of  pending signals, displayed as a decimal number.
  // Obsolete, because it does not provide information on real-time signals;
  // use /proc/[pid]/status instead.
  public BigInteger getSignal() {
    return signal;
  }

  // The bitmap of blocked signals, displayed as a decimal number. Obsolete,
  // because it  does  not  provide information on real-time signals; use
  // /proc/[pid]/status instead.
  public BigInteger getBlocked() {
    return blocked;
  }

  // The  bitmap  of  ignored signals, displayed as a decimal number.
  // Obsolete, because it does not provide information on real-time signals;
  // use /proc/[pid]/status instead.
  public BigInteger getSigignore() {
    return sigignore;
  }

  // The bitmap of caught signals, displayed as a decimal number. Obsolete,
  // because it does not provide information on real-time signals; use
  // /proc/[pid]/status instead.
  public BigInteger getSigcatch() {
    return sigcatch;
  }

  // This is the "channel" in which the process is waiting. It is the
  // address of a system call, and can be looked up in a namelist if you
  // need a textual name. (If you have an up-to-date /etc/psdatabase,
  // then try ps -l to see the WCHAN field in action.)
  public BigInteger getWchan() {
    return wchan;
  }

  // Number of pages swapped (not maintained).
  public BigInteger getNswap() {
    return nswap;
  }

  // Cumulative nswap for child processes (not maintained).
  public BigInteger getCnswap() {
    return cnswap;
  }

  // (since Linux 2.1.22) Signal to be sent to parent when we die.
  public int getExitSignal() {
    return exitSignal;
  }

  // (since Linux 2.2.8) CPU number last executed on.
  public int getProcessor() {
    return processor;
  }

  // (since Linux 2.5.19; was BigInteger before Linux 2.6.22) Real-time
  // scheduling priority, a number in the range 1 to 99 for processes scheduled
  // under a real-time policy, or 0, for non-real-time processes (see
  // sched_setscheduler(2)).
  public BigInteger getRtPriority() {
    return rtPriority;
  }

  // (since Linux 2.5.19; was BigInteger before Linux 2.6.22) Scheduling policy (see
  // sched_setscheduler(2)). Decode using the SCHED_* constants in
  // linux/sched.h.
  public BigInteger getPolicy() {
    return policy;
  }

  // (since Linux 2.6.18) Aggregated block I/O delays, measured in clock
  // ticks (centiseconds).
  public BigInteger getDelayacctBlkioTicks() {
    return delayacctBlkioTicks;
  }

  // (since Linux 2.6.24) Guest time of the process (time spent running a
  // virtual CPU for a guest operating system), measured  in clock ticks
  // (divide by sysconf(_SC_CLK_TCK).
  public BigInteger getGuestTime() {
    return guestTime;
  }

  // (since Linux 2.6.24) Guest time of the process's children, measured in
  // clock ticks (divide by sysconf(_SC_CLK_TCK).
  public BigInteger getCguestTime() {
    return cguestTime;
  }


  public class InvalidStatFormat extends Exception {};

  // We can not use StringTokenizer since the name field can have spaces
  private final Pattern statFormat = Pattern.compile("^\\s*(-?\\d+) \\((.*)\\) (.) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+) (-?\\d+)\\s*$");

  public ProcStat(String statLine) throws InvalidStatFormat {
    Matcher m = statFormat.matcher(statLine);
    if(!m.lookingAt())
      throw new InvalidStatFormat();

      this.pid                 = Integer.parseInt(m.group(1));
      this.command             = new String(m.group(2));
      this.state               = m.group(3).charAt(0);
      this.ppid                = Integer.parseInt(m.group(4));
      this.pgrp                = Integer.parseInt(m.group(5));
      this.session             = Integer.parseInt(m.group(6));
      this.ttyNr               = Integer.parseInt(m.group(7));
      this.tpgid               = Integer.parseInt(m.group(8));
      this.flags               = Long.parseLong(m.group(9));
      this.minflt              = new BigInteger(m.group(10));
      this.cminflt             = new BigInteger(m.group(11));
      this.majflt              = new BigInteger(m.group(12));
      this.cmajflt             = new BigInteger(m.group(13));
      this.utime               = Integer.parseInt(m.group(14));
      this.stime               = Integer.parseInt(m.group(15));
      this.cutime              = Integer.parseInt(m.group(16));
      this.cstime              = Integer.parseInt(m.group(17));
      this.priority            = new BigInteger(m.group(18));
      this.nice                = new BigInteger(m.group(19));
      this.numThreads          = new BigInteger(m.group(20));
      this.itrealvalue         = new BigInteger(m.group(21));
      this.starttime           = new BigInteger(m.group(22));
      this.vsize               = new BigInteger(m.group(23));
      this.rss                 = new BigInteger(m.group(24));
      this.rsslim              = new BigInteger(m.group(25));
      this.startcode           = new BigInteger(m.group(26));
      this.endcode             = new BigInteger(m.group(27));
      this.startstack          = new BigInteger(m.group(28));
      this.kstkesp             = new BigInteger(m.group(29));
      this.kstkeip             = new BigInteger(m.group(30));
      this.signal              = new BigInteger(m.group(31));
      this.blocked             = new BigInteger(m.group(32));
      this.sigignore           = new BigInteger(m.group(33));
      this.sigcatch            = new BigInteger(m.group(34));
      this.wchan               = new BigInteger(m.group(35));
      this.nswap               = new BigInteger(m.group(36));
      this.cnswap              = new BigInteger(m.group(37));
      this.exitSignal          = Integer.parseInt(m.group(38));
      this.processor           = Integer.parseInt(m.group(39));
      this.rtPriority          = new BigInteger(m.group(40));
      this.policy              = new BigInteger(m.group(41));
      this.delayacctBlkioTicks = new BigInteger(m.group(42));
      this.guestTime           = new BigInteger(m.group(43));
      this.cguestTime          = new BigInteger(m.group(44));

  }

}

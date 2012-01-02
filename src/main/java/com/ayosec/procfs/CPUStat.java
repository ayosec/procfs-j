package com.ayosec.procfs;

import java.util.StringTokenizer;

public class CPUStat {
  private int user;
  private int nice;
  private int system;
  private int idle;

  public int getTotalTime() {
    return user + nice + system + idle;
  }

  public int getUserTime() {
    return user;
  }

  public int getNiceTime() {
    return nice;
  }

  public int getSystemTime() {
    return system;
  }

  public int getIdleTime() {
    return idle;
  }

  public CPUStat(String timesString) {
    StringTokenizer times = new StringTokenizer(timesString, " ");

    times.nextToken(); // Ignore first column (CPU name)
    this.user = Integer.parseInt(times.nextToken());
    this.nice = Integer.parseInt(times.nextToken());
    this.system = Integer.parseInt(times.nextToken());
    this.idle = Integer.parseInt(times.nextToken());
  }

}

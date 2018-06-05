package com.undertherainbowapps.feelawake.models;

import java.util.List;

public class User {

  private String uid;
  private List<FeelAwakeLog> feelAwakeLog;
  private String lastUpdated;

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public List<FeelAwakeLog> getFeelAwakeLog() {
    return feelAwakeLog;
  }

  public void setFeelAwakeLog(
      List<FeelAwakeLog> feelAwakeLog) {
    this.feelAwakeLog = feelAwakeLog;
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }
}

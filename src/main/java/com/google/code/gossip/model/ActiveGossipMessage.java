package com.google.code.gossip.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActiveGossipMessage implements Serializable{

  private List<GossipMember> members = new ArrayList<>();
  
  public ActiveGossipMessage(){
  }

  public List<GossipMember> getMembers() {
    return members;
  }

  public void setMembers(List<GossipMember> members) {
    this.members = members;
  }
  
}

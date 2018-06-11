/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.code.gossip;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;

/**
 * A abstract class representing a gossip member.
 * 
 * @author joshclemm, harmenw
 */
public abstract class GossipMember implements Comparable<GossipMember>, Serializable {

  
  protected final String host;

  protected final int port;

  protected volatile long heartbeat;

  protected final String clusterName;
  
  protected String value;
  
  protected TreeMap<String, Integer> inst;
  
  protected ArrayList<ArrayList<String>> log = new ArrayList<ArrayList<String>>();
  
  protected boolean changed = true;
  
  protected HashSet<ArrayList<String>> eventIds = new HashSet<ArrayList<String>>();
  //newly added
  //protected ProxyLogs logObject;

  /**
   * The purpose of the id field is to be able for nodes to identify themselves beyond there
   * host/port. For example an application might generate a persistent id so if they rejoin the
   * cluster at a different host and port we are aware it is the same node.
   */
  protected String id;

  /**
   * Constructor.
   * 
   * @param host
   *          The hostname or IP address.
   * @param port
   *          The port number.
   * @param heartbeat
   *          The current heartbeat.
   * @param id
   *          an id that may be replaced after contact
 * @param log2 
   */
  public GossipMember(String clusterName, String host, int port, String id, ArrayList<ArrayList<String>> log2, long heartbeat) {
    this.clusterName = clusterName;
    this.host = host;
    this.port = port;
    this.id = id;
    this.heartbeat = heartbeat;
    this.value = "Hi";
    this.log = log2;
    for(int i = 0; i<this.log.size(); i++) {
    	this.eventIds.add(this.log.get(i));
    }
//    this.log = new ArrayList<ArrayList<String>>();
//    this.eventIds = new HashSet<ArrayList<String>>();
//	Random obj = new Random();
//	String seq = ""+obj.nextInt(10);
//	String sid = ""+obj.nextInt(1000);
//	String epoch = ""+obj.nextInt(10000);
//	ArrayList<String>temp = new ArrayList<String>();
//	temp.add(seq);
//	temp.add(sid);
//	temp.add(epoch);
//	this.log.add(temp);
//	this.eventIds.add(temp);
//	this.inst = new TreeMap<String,Integer>(new Comparator<String>() {
//		
//		@Override
//		public int compare(String o1, String o2) {
//			String[]splitArr_1 = o1.split(":");
//			String[]splitArr_2 = o2.split(":");
//			for(int i = 0; i<splitArr_1.length;i++) {
//				if(splitArr_1[i].compareTo(splitArr_2[i])<0) {
//					return -1;
//				}
//				else if(splitArr_1[i].compareTo(splitArr_2[i])>0) {
//					return 1;
//				}
//			}
//			// TODO Auto-generated method stub
//			return 0;
//		}});
//	Random obj = new Random();
//	String key = obj.nextInt(10)+":"+obj.nextInt(1000)+":"+obj.nextInt(10000);
//	this.inst.put(key, 1);
//	temp.remove(2);
//	this.inst.put(temp, 2);
//	temp.remove(1);
//	this.inst.put(temp, 3);
    //newly added
    //this.logObject = new ProxyLogs();
  }

  /**
   * Get the name of the cluster the member belongs to.
   * 
   * @return The cluster name
   */
  public String getClusterName() {
    return clusterName;
  }

  /**
   * Get the hostname or IP address of the remote gossip member.
   * 
   * @return The hostname or IP address.
   */
  public String getHost() {
    return host;
  }

  /**
   * Get the port number of the remote gossip member.
   * 
   * @return The port number.
   */
  public int getPort() {
    return port;
  }

  /**
   * The member address in the form IP/host:port Similar to the toString in
   * {@link InetSocketAddress}
   */
  public String getAddress() {
    return host + ":" + port;
  }

  /**
   * Get the heartbeat of this gossip member.
   * 
   * @return The current heartbeat.
   */
  public long getHeartbeat() {
    return heartbeat;
  }

  /**
   * Set the heartbeat of this gossip member.
   * 
   * @param heartbeat
   *          The new heartbeat.
   */
  public void setHeartbeat(long heartbeat) {
    this.heartbeat = heartbeat;
  }

  public String getId() {
    return id;
  }

  public void setId(String _id) {
    this.id = _id;
  }
  
  /**
   * Adding the getter and setter for the value field
   **/
  
  public String getValue() {
	return value;
  }

  public void setValue(String param) {
    this.value = param;
  }

  public String toString() {
    return "Member [address=" + getAddress() + ", id=" + id + ", heartbeat=" + heartbeat + ", size :"+log.size()+"]";
  }
  
  //newly added
  
  public ArrayList<ArrayList<String>> getLogInstance(){
	return this.log;
  }
  
  public void setLogInstance(ArrayList<ArrayList<String>> param){
	this.log = param;
  }
  
  public boolean isLogChanged() {
	  return this.changed;
  }
  
  public void setChanged(boolean param) {
	  this.changed = param;
  }
  
//  public boolean hasKey(String key) {
//	  if(inst.containsKey(key)) {
//		  return true;
//	  }
//	  else {
//		  return false;
//	  }
//  }
  
  public void sort() {
	  //finding the appropriate place for the new entry
  }
  
  public void addEntry(ArrayList<String>entry) {
	  if(!this.eventIds.contains(entry)) {
		  this.eventIds.add(entry);
		  this.log.add(entry);
		  setChanged(true);
		  System.out.println("Merged logs for "+this.id+ " are : "+this.log);
		  sort();
	  }
	  else {
		  System.out.println("Entry already present");
	  }
  }

//  public ProxyLogs getLogObject() {
//	  return logObject;
//  }
//  
//  public void setLogObject(ProxyLogs param) {
//	  logObject = param;
//  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    String address = getAddress();
    result = prime * result + ((address == null) ? 0 : address.hashCode()) + clusterName == null ? 0
            : clusterName.hashCode();
    return result;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      System.err.println("equals(): obj is null.");
      return false;
    }
    if (!(obj instanceof GossipMember)) {
      System.err.println("equals(): obj is not of type GossipMember.");
      return false;
    }
    // The object is the same of they both have the same address (hostname and port).
    return getAddress().equals(((LocalGossipMember) obj).getAddress())
            && getClusterName().equals(((LocalGossipMember) obj).getClusterName());
  }

  public int compareTo(GossipMember other) {
    return this.getAddress().compareTo(other.getAddress());
  }

  public void merge(ArrayList<ArrayList<String>> logInstance) {
	  //System.out.println("Thing to merge : "+logInstance);
	  for(int i = 0; i<logInstance.size(); i++) {
		  addEntry(logInstance.get(i));
	  }
  }
}

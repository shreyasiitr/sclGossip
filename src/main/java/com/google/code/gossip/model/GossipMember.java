package com.google.code.gossip.model;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;
import com.google.code.gossip.ProxyLogs;

public class GossipMember implements Serializable {

  private String cluster;
  private String host;
  private Integer port;
  private String id;
  private Long heartbeat;
  private String value;
  private TreeMap<String, Integer> inst;
  private ArrayList<ArrayList<String>> log = new ArrayList<ArrayList<String>>();
  private HashSet<ArrayList<String>> eventIds = new HashSet<ArrayList<String>>();
  private boolean changed = true;
  //private ProxyLogs logObject;
  
  public GossipMember(){
    
  }
  
  public GossipMember(String cluster, String host, Integer port, String id, ArrayList<ArrayList<String>>log2, Long heartbeat){
    this.cluster=cluster;
    this.host= host;
    this.port = port;
    this.id = id;
    this.value = "Model";
    this.log = log2;
//    this.log = new ArrayList<ArrayList<String>>();
//    this.eventIds = new HashSet<ArrayList<String>>();
//    Random obj = new Random();
//	String seq = ""+obj.nextInt(10);
//	String sid = ""+obj.nextInt(1000);
//	String epoch = ""+obj.nextInt(10000);
//	ArrayList<String>temp = new ArrayList<String>();
//	temp.add(seq);
//	temp.add(sid);
//	temp.add(epoch);
//	this.log.add(temp);
//	this.eventIds.add(temp);
//    this.inst = new TreeMap<String,Integer>(new Comparator<String>() {
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
//	this.logObject = new ProxyLogs();
  }

  public String getCluster() {
    return cluster;
  }

  public void setCluster(String cluster) {
    this.cluster = cluster;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getHeartbeat() {
    return heartbeat;
  }

  public void setHeartbeat(Long heartbeat) {
    this.heartbeat = heartbeat;
  }
  
  public String getValue() {
    return value;
  }

  public void setValue(String param) {
    this.value = param;
  }
  
  public ArrayList<ArrayList<String>> getLogInstance(){
	return log;
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
	  
  }
  
  public void addEntry(ArrayList<String>entry) {
	  if(!this.eventIds.contains(entry)) {
		  this.log.add(entry);
		  sort();
	  }
	  else {
		  System.out.println("Entry already present");
	  }
  }
  
//  public ProxyLogs getLogObject() {
//    return logObject;
//  }
//
//  public void setLogObject(ProxyLogs param) {
//    this.logObject = logObject;
//  }
}

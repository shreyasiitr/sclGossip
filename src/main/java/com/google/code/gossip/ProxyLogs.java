package com.google.code.gossip;

import java.awt.List;
import java.util.concurrent.ConcurrentHashMap;

public class ProxyLogs {
	
	private int dummy;
	private ConcurrentHashMap<List,Integer>inst;
	
	public ProxyLogs() {
		this.dummy = 0;
		this.inst = new ConcurrentHashMap<List,Integer>();
	}
	
	public ConcurrentHashMap<List,Integer> getLogInstance(){
		return inst;
	}

	public static void main(String[] args) {
		
	}
	
	public int getDummy() {
		return dummy;
	}
	
	public void merge() {
		
	}

}

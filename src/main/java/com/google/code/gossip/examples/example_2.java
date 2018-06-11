/**
 * 
 */
package com.google.code.gossip.examples;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.code.gossip.GossipMember;
import com.google.code.gossip.GossipService;
import com.google.code.gossip.GossipSettings;
import com.google.code.gossip.LocalGossipMember;
import com.google.code.gossip.RemoteGossipMember;
import com.google.code.gossip.manager.GossipManager;

/**
 * @author shreyas
 *
 */
public class example_2 extends Thread{
	
	private int nodeNum = 4;
	private String clusterName = "example";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new example_2();
		// TODO Auto-generated method stub
	}
	
	public example_2() {
		start();
	}
	
	public void run() {
		String hostAddr;
		try {
			Random obj = new Random();
			hostAddr = InetAddress.getLocalHost().getHostAddress();
			GossipSettings settings = new GossipSettings();
			List<GossipMember>nodes = new ArrayList<>();
			List<GossipService>services = new ArrayList<>();
			for(int i = 0; i<nodeNum; i++) {
				ArrayList<ArrayList<String>>logInst = new ArrayList<ArrayList<String>>();
				ArrayList<String>temp = new ArrayList<String>();
				String seq = ""+obj.nextInt(10);
				String sid = ""+obj.nextInt(1000);
				String epoch = ""+obj.nextInt(10000);
				temp.add(seq);
				temp.add(sid);
				temp.add(epoch);
				temp.add("data");
				logInst.add(temp);
				RemoteGossipMember member = new RemoteGossipMember(clusterName,hostAddr, 2000+i, ""+i,logInst);
				System.out.println("ID : "+member.getId() + " Initial log : "+temp);
				nodes.add(member);
			}
			for(GossipMember member : nodes) {
				try {
					//System.out.println("Size before service call : "+member.getLogInstance().size());
					GossipService service = new GossipService(clusterName, hostAddr, member.getPort(), member.getId(),member.getLogInstance(),nodes,settings,null);
					services.add(service);
					service.start();
					//System.out.println("Size after service call : "+member.getLogInstance().size());
					//System.out.println(member.toString());;
					//System.out.println("Added in example : "+member.getId()+" "+member.getPort()+" "+member.getLogInstance().size());
					sleep(settings.getCleanupInterval() + 1000);
				} catch (UnknownHostException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			GossipManager temp = services.get(1).get_gossipManager();
			List<LocalGossipMember> l = temp.getMemberList();
			for(LocalGossipMember member : l) {
				//System.out.println(member.getId()+" "+member.getPort()+" "+member.getAddress());
			}
//			for(GossipService service : services) {
//				GossipManager temp = service.get_gossipManager();
//				List<LocalGossipMember> l = temp.getMemberList();
//			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		try {
//			sleep(1000);
//			for(GossipService service : services) {
//				service.shutdown();
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}

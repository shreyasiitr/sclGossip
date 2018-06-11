package com.google.code.gossip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class CustomComaprator implements Comparator<ArrayList<String>>, Serializable {
	
	TreeMap<ArrayList<String>,Integer> map;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public CustomComaprator(TreeMap<ArrayList<String>,Integer> param) {
		map = param;
	}

	@Override
	public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		for(int i = 0; i<o1.size(); i++) {
			if(o1.get(i).compareTo(o2.get(i))<0) {
				return -1;
			}
			else if(o1.get(i).compareTo(o2.get(i))>0) {
				return 1;
			}
		}
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.sound.daytc8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
	
	public static void main(String[] args) {
		
		
		CopyOnWriteArrayList<String> s = new CopyOnWriteArrayList<>();
		s.add("123");
		s.get(10);
		s.remove(100);


		ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<String>();
		clq.add("123");
		clq.peek();
		clq.remove();

		BlockingQueue cq = new ArrayBlockingQueue(10);

		ConcurrentHashMap ch = new ConcurrentHashMap();
 	}

}

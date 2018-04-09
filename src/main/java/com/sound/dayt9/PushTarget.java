package com.sound.dayt9;

public class PushTarget implements Runnable {

	private ConditionBoundedBuffer tmall;
	
	public PushTarget(ConditionBoundedBuffer tmall) {
		this.tmall = tmall;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				tmall.put();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

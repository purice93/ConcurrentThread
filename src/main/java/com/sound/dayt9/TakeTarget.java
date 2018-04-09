package com.sound.dayt9;

public class TakeTarget implements Runnable {
	
	private ConditionBoundedBuffer tmall;
	
	public TakeTarget(ConditionBoundedBuffer tmall) {
		this.tmall = tmall;
	}

	@Override
	public void run() {
		while(true) {
			try {
				tmall.take();
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

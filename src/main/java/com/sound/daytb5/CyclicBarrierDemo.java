package com.sound.daytb5;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public void meeting(CyclicBarrier barrier) {
		System.out.println(Thread.currentThread().getName() + " 到达会议室，等待开会..");

		if(Thread.currentThread().getName().equals("Thread-7")) {
			System.out.println("Thread-7 出车祸了，到不了了，会议将无法开始");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			barrier.reset();
		}

		try {
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		CyclicBarrierDemo demo = new CyclicBarrierDemo();

		// 定义会议人数:10 和 内容run(){}
		CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
			@Override
			public void run() {
				System.out.println("好！我们开始开会...");
			}
		});

		for (int i = 0; i < 12; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					demo.meeting(barrier);
				}
			}).start();
		}

		// 监控等待线程数
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("等待的线程数 " + barrier.getNumberWaiting());
					System.out.println("屏障是否损坏or异常？ " + barrier.isBroken());
				}
			}
		}).start();
	}

}

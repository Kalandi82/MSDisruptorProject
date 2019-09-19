package com.test;

import org.apache.log4j.Logger;

import com.test.MessageSeries;
import com.test.ProducerManager;
import com.test.ResultSet;

public class ClientMain {

	static final Logger logger= Logger.getLogger(ClientMain.class);
	public static void main(String[] args) {
		ProducerManager pm = new ProducerManager();
		Runnable ra = createRunnableClientFor('A',10,pm);
		Thread aThread = new Thread(ra,"THREAD-A");
		
		Runnable rb = createRunnableClientFor('B',50, pm);
		Thread bThread = new Thread(rb,"THREAD-B");
		
		Runnable rc = createRunnableClientFor('C',75, pm);
		Thread cThread = new Thread(rc,"THREAD-C");
		
		Runnable rd = createRunnableClientFor('D',100, pm);
		Thread dThread = new Thread(rd,"THREAD-D");
		
		
		aThread.start();
		dThread.start();
		pm.startDisruptor();
		cThread.start();
		bThread.start();
		try {
			aThread.join();
			bThread.join();
			cThread.join();
			dThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ResultSet rs = MessageSeries.getResultSet();
		logger.info("OUTPUT : \n"+rs.getAllList().toString());
	}

	private static Runnable createRunnableClientFor(char producerType, int delayMultiplier, ProducerManager pm) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				for(int i=1;i<=5;i++) {
					try {
						Thread.sleep(10 * delayMultiplier);
					} catch (InterruptedException e) {}
					pm.createMessageAndPutOnQueue(producerType);
				}
				
			}
			
		};
		return r;
	}

}

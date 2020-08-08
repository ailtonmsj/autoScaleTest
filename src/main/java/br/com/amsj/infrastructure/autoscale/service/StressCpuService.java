package br.com.amsj.infrastructure.autoscale.service;

public class StressCpuService {

	public void stress(int seconds) {
		long limitDateTimeMillis = System.currentTimeMillis() + (seconds * 1000);
		new Stress(limitDateTimeMillis).start();
	}

	private class Stress extends Thread {
		long limitDateTimeMillis;

		Stress(long limitDateTimeMillis) {
			this.limitDateTimeMillis = limitDateTimeMillis;
		}

		@Override
		public void run() {
			final int PARTIAL_TIME = 100;
			final double CPU_LOAD = 0.8;
			try {
				while (System.currentTimeMillis() < limitDateTimeMillis) {
					if (System.currentTimeMillis() % PARTIAL_TIME == 0) {
						System.out.println(System.currentTimeMillis());
						Thread.sleep((long)((1 - CPU_LOAD) * PARTIAL_TIME));
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

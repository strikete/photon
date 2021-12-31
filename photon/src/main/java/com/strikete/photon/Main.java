package com.strikete.photon;

import java.io.IOException;

public class Main {
	
	/*
	 * MAIN METHOD
	 */	
	public static void main(String[] args) throws IOException {
		
		Photon photon = new Photon();
		
		String targetIp = "192.168.10.111";
		int targetPort = 3032;
		int listeningPort = 3033;
		
		photon.initializeOsc(targetIp, targetPort, listeningPort);
		
		photon.updateOscObjects();
		
		photon.waitForPause(5000000000L);
		
		
		while(true) {
			//photon.updateOscObjects();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
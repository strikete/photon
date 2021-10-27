package com.strikete.photon;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	/*
	 * MAIN METHOD
	 */	
	public static void main(String[] args) throws IOException {
		
		Photon photon = new Photon();
		Scanner scanner = new Scanner(System.in);
		
		String targetIp = "192.168.10.111";
		int targetPort = 3032;
		int listeningPort = 3033;
		
		photon.initializeOsc(targetIp, targetPort, listeningPort);
		
		
		while(true) {
			photon.updateOscObjects();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
package com.strikete.photon.osc;
import java.util.UUID;
import java.util.function.Consumer;
import com.illposed.osc.OSCMessage;
import com.strikete.photon.Photon;

public class OscListener {

	/*
	 * VARIABLES
	 */
	private UUID uuid;
	private Photon photon;
	private String addressListener;
	private Consumer<OSCMessage> consumer;
	private boolean exactMatch;
	private boolean expiration;
	private int expirationNum;
	
	
	/*
	 * METHODS - GETTERS
	 */
	public UUID getUuid() {
		return uuid;
	}
	public String getUuidString() {
		return uuid.toString();
	}
	public String getListenerString() {
		return addressListener;
	}
	public Consumer<OSCMessage> getConsumer() {
		return consumer;
	}
	public boolean getExactMatch() {
		return exactMatch;
	}
	public boolean getExpiration() {
		return expiration;
	}
	public int getExpirationNumber() {
		return expirationNum;
	}
	
	
	/*
	 * METHODS - POST TO CONSUMER
	 */
	public void postToConsumer(OSCMessage message, Photon photon) {
		consumer.accept(message);
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public OscListener(Photon photonIn, String listenerStringIn, Consumer<OSCMessage> consumerIn, boolean exactMatchIn) {
		this.photon = photonIn;
		this.uuid = UUID.randomUUID();
		this.addressListener = listenerStringIn;
		this.consumer = consumerIn;
		this.exactMatch = exactMatchIn;
		this.expiration = false;
		this.expirationNum = 0;
	}
	public OscListener(Photon photonIn, String listenerStringIn, Consumer<OSCMessage> consumerIn, boolean exactMatchIn, boolean expirationIn, int expirationNumber) {
		this.photon = photonIn;
		this.uuid = UUID.randomUUID();
		this.addressListener = listenerStringIn;
		this.consumer = consumerIn;
		this.exactMatch = exactMatchIn;
		this.expiration = expirationIn;
		this.expirationNum = expirationNumber;
	}
}
package com.strikete.photon.osc;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.google.common.annotations.Beta;
import com.strikete.photon.events.PhotonEventHandler;
import com.strikete.photon.utils.ObjectUpdater;
import com.strikete.photon.utils.OscParser;

/**
 * 
 * The OscInstance Class is a user-accessible object used to handle both outgoing and incoming OSC from a particular console.
 * 
 * @author Rusettsten
 *
 */
public class OscInstance {
	

	/**
	 * The OscFormat Enum contains four Strings, each defining the types of consoles planned for support.
	 * 
	 * ETC_EOS : Electronic Theatre Controls EOS Software
	 * CHAMSYS : Chamsys Consoles, specifically the MQ line
	 * HOG4    : The Hog 4 Console from High End Systems
	 */
	public enum OscFormat {
		ETC_EOS, CHAMSYS, HOG4, OTHER
	}

	private OscParser receiver;
	private OscSender sender;
	private OscMap map;
	private PhotonEventHandler eventBus;
	private ObjectUpdater updater;
	private int incomingPortNum;
	private OscFormat format;
	
	/*
	 * METHODS
	 */
	
	/**
	 * Returns OscSender initialized by this OscInstance.
	 * @return OscSender 
	 */
	public OscSender getOscSender() {
		return this.sender;
	}
	/**
	 * Returns OscParser initialized by this OscInstance.
	 * @return OscParser
	 */
	public OscParser getOscParser() {
		return this.receiver;
	}
	/**
	 * Returns the OscMap used by this OscInstance and its children.
	 * @return OscMap
	 */
	public OscMap getOscMap() {
		return this.map;
	}
	/**
	 * Returns the PhotonEventHandler used by this OscInstance and its children.
	 * @return PhotonEventHandler
	 */
	public PhotonEventHandler getEventHandler() {
		return this.eventBus;
	}
	/**
	 * Returns the ObjectUpdater created and linked to this OscInstance.
	 * @return
	 */
	public ObjectUpdater getObjectUpdater() {
		return this.updater;
	}
	
	/**
	 * Returns a pre-defined OscMap based on the OscFormat enum input.
	 * @param OscFormat format
	 * @return OscMap
	 */
	public OscMap getPredefinedOscMap(OscFormat format) {
		Yaml yaml = new Yaml(new Constructor(OscMap.class)); //Create a YAML constructor
		
		if(format == OscFormat.ETC_EOS) {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("etc_eos_revC.yaml");
			OscMap newMap = yaml.load(inputStream);
			return(newMap);
		}else if(format == OscFormat.CHAMSYS) {
			//TODO: define input stream for Chamsys configuration
		}else if(format == OscFormat.HOG4) {
			//TODO: define input stream for Hog 4 configuration
		}
		
		//If we've reached here, there's an oopsies.
		throw new IllegalArgumentException("Please input a valid pre-defined OscFormat.");
	}
	
	/**
	 * An OscInstance must be initialized by the user after calling the constructor.
	 */
	public void init() {
		this.updater = new ObjectUpdater(this, this.eventBus, this.sender, this.map);
		this.receiver = new OscParser(this, this.eventBus, this.updater, this.map, this.format, this.incomingPortNum);
	}
	
	/*
	 * CONSTRUCTORS
	 */
	
//TODO: Re-add this constructor
	//public OscInstance(OscMap mapIn, String targetIpStringIn, int targetPortNumIn, int incomingPortNumIn) { //The ideal constructor <3
		//this.map = mapIn;
		//this.sender = new OscSender(map,targetIpStringIn, targetPortNumIn);
		//TODO: Add format in, or itll throw a null pointer exception when called
	//}
	
	/**
	 * OscInstance constructor using pre-defined LX Console formats.
	 * @param formatIn Format using a String type from Enum OscFormat contained in this class.
	 * @param targetIpStringIn Target IPv4 address of the console this object will to talk to.
	 * @param targetPortNumIn The port on the Remote Device this object will be sending packets to.
	 * @param incomingPortNumIn The port this object will listen for OSC packets on.
	 */
	public OscInstance(OscFormat formatIn, String targetIpStringIn, int targetPortNumIn, int incomingPortNumIn) { //Constructor using a predefined format
		this.map = getPredefinedOscMap(formatIn);
		this.sender = new OscSender(map, targetIpStringIn, targetPortNumIn);
		this.format = formatIn;
		this.incomingPortNum = incomingPortNumIn;
		this.eventBus = new PhotonEventHandler(this);
	}
	
	//TODO: Add constructor for custom OscMappings

}

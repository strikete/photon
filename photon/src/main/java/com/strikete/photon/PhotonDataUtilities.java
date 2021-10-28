package com.strikete.photon;

public class PhotonDataUtilities {

	/*
	 * VARIABLES
	 */
	private Photon photon;
	
	
	/*
	 * METHODS - CHECK EXISTING ARRAY BY EOS-UID
	 */
	public boolean doesBeamPaletteExist(String uid) {
		for(int x = 0; x < photon.beamPalettes.size(); x++) {
			if(photon.beamPalettes.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesChannelExist(String uid) {
		for(int x = 0; x < photon.channels.size(); x++) {
			if(photon.channels.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesColorPaletteExist(String uid) {
		for(int x = 0; x < photon.colorPalettes.size(); x++) {
			if(photon.colorPalettes.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesCueExist(String uid) {
		for(int x = 0; x < photon.cues.size(); x++) {
			if(photon.cues.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesCuelistExist(String uid) {
		for(int x = 0; x < photon.cuelists.size(); x++) {
			if(photon.cuelists.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesCurveExist(String uid) {
		for(int x = 0; x < photon.curves.size(); x++) {
			if(photon.curves.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesEffectExist(String uid) {
		for(int x = 0; x < photon.effects.size(); x++) {
			if(photon.effects.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	//FIXTURES DO NOT HAVE A UID
	public boolean doesFocusPaletteExist(String uid) {
		for(int x = 0; x < photon.focusPalettes.size(); x++) {
			if(photon.focusPalettes.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesGroupExist(String uid) {
		for(int x = 0; x < photon.groups.size(); x++) {
			if(photon.groups.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesIntensityPaletteExist(String uid) {
		for(int x = 0; x < photon.intensityPalettes.size(); x++) {
			if(photon.intensityPalettes.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesMacroExist(String uid) {
		for(int x = 0; x < photon.macros.size(); x++) {
			if(photon.macros.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesMagicSheetExist(String uid) {
		for(int x = 0; x < photon.magicSheets.size(); x++) {
			if(photon.magicSheets.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesPixelmapExist(String uid) {
		for(int x = 0; x < photon.pixelmaps.size(); x++) {
			if(photon.pixelmaps.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesPresetExist(String uid) {
		for(int x = 0; x < photon.presets.size(); x++) {
			if(photon.presets.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesSnapshotExist(String uid) {
		for(int x = 0; x < photon.snapshots.size(); x++) {
			if(photon.snapshots.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	public boolean doesSubExist(String uid) {
		for(int x = 0; x < photon.subs.size(); x++) {
			if(photon.subs.get(0).getUid().equals(uid)) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public PhotonDataUtilities(Photon photonIn) {
		this.photon = photonIn;
	}
}
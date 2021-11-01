package com.strikete.photon;

import com.strikete.photon.exceptions.ObjectNotFoundException;

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
	 * METHODS - FIND OBJECT ARRAY INDEX VIA PK
	 */
	public int findBeamPalette(float paletteNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.beamPalettes.size(); x++) {
			if(photon.beamPalettes.get(x).getPaletteNumber() == paletteNum &&
					photon.beamPalettes.get(x).getIndex() == index &&
					photon.beamPalettes.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Beam Palette #" + paletteNum + " !");
	}
	public int findChannel(int channelNum, int part, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.channels.size(); x++) {
			if(photon.channels.get(x).getChannelNumber() == channelNum &&
					photon.channels.get(x).getPart() == part &&
					photon.channels.get(x).getIndex() == index &&
					photon.channels.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Channel #" + channelNum + " !");
	}
	public int findColorPalette(float paletteNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.colorPalettes.size(); x++) {
			if(photon.colorPalettes.get(x).getPaletteNumber() == paletteNum &&
					photon.colorPalettes.get(x).getIndex() == index &&
					photon.colorPalettes.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Color Palette #" + paletteNum + " !");
	}
	public int findCue(int cueNum, int index, String uid, int cuelistNum, int cuePart) throws ObjectNotFoundException {
		for(int x = 0; x < photon.cues.size(); x++) {
			if(photon.cues.get(x).getCueNumber() == cueNum &&
					photon.cues.get(x).getIndex() == index &&
					photon.cues.get(x).getUid().equals(uid) &&
					photon.cues.get(x).getCuelistNumber() == cuelistNum &&
					photon.cues.get(x).getPart() == cuePart) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Cue #" + cueNum + " from Cuelist #" + cuelistNum + " !");
	}
	public int findCuelist(int cuelistNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.cuelists.size(); x++) {
			if(photon.cuelists.get(x).getCuelistNumber() == cuelistNum &&
					photon.cuelists.get(x).getIndex() == index &&
					photon.cuelists.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Cuelist #" + cuelistNum + " !");
	}
	public int findCurve(int curveNum, int index, String uid) {
		for(int x = 0; x < photon.curves.size(); x++) {
			if(photon.curves.get(x).getCurveNumber() == curveNum &&
					photon.beamPalettes.get(x).getIndex() == index &&
					photon.beamPalettes.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Curve #" + curveNum + " !");
	}
	public int findEffect(float effectNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.effects.size(); x++) {
			if(photon.effects.get(x).getEffectNumber() == effectNum &&
					photon.beamPalettes.get(x).getIndex() == index &&
					photon.beamPalettes.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Effect #" + effectNum + " !");
	}
	public int findFixture(String fixtureName, String manufacturerName) throws ObjectNotFoundException {
		for(int x = 0; x < photon.fixtures.size(); x++) {
			if(photon.fixtures.get(x).getFixtureName().equals(fixtureName) &&
					photon.fixtures.get(x).getManufacturerName().equals(manufacturerName)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Fixture: " + fixtureName + " !");
	}
	public int findFocusPalette(float paletteNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.focusPalettes.size(); x++) {
			if(photon.focusPalettes.get(x).getPaletteNumber() == paletteNum &&
					photon.focusPalettes.get(x).getIndex() == index &&
					photon.focusPalettes.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Focus Palette #" + paletteNum + " !");
	}
	public int findGroup(float groupNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.groups.size(); x++) {
			if(photon.groups.get(x).getGroupNumber() == groupNum &&
					photon.groups.get(x).getIndex() == index &&
					photon.groups.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Group #" + groupNum + " !");
	}
	public int findIntensityPalette(float paletteNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.intensityPalettes.size(); x++) {
			if(photon.intensityPalettes.get(x).getPaletteNumber() == paletteNum &&
					photon.intensityPalettes.get(x).getIndex() == index &&
					photon.intensityPalettes.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Intensity Palette #" + paletteNum + " !");
	}
	public int findMacro(int macroNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.macros.size(); x++) {
			if(photon.macros.get(x).getMacroNumber() == macroNum &&
					photon.macros.get(x).getIndex() == index &&
					photon.macros.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Macro #" + macroNum + " !");
	}
	public int findMagicSheet(int magicSheetNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.magicSheets.size(); x++) {
			if(photon.magicSheets.get(x).getMagicSheetNumber() == magicSheetNum &&
					photon.beamPalettes.get(x).getIndex() == index &&
					photon.beamPalettes.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Magic Sheet #" + magicSheetNum + " !");
	}
	public int findPixelmap(int pixelmapNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.pixelmaps.size(); x++) {
			if(photon.pixelmaps.get(x).getPixelmapNumber() == pixelmapNum &&
					photon.beamPalettes.get(x).getIndex() == index &&
					photon.beamPalettes.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Pixelmap #" + pixelmapNum + " !");
	}
	public int findPreset(float presetNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.presets.size(); x++) {
			if(photon.presets.get(x).getPresetNumber() == presetNum &&
					photon.presets.get(x).getIndex() == index &&
					photon.presets.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Preset #" + presetNum + " !");
	}
	public int findSnapshot(int snapshotNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.snapshots.size(); x++) {
			if(photon.snapshots.get(x).getSnapshotNumber() == snapshotNum &&
					photon.beamPalettes.get(x).getIndex() == index &&
					photon.beamPalettes.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Snapshot #" + snapshotNum + " !");
	}
	public int findSub(int subNum, int index, String uid) throws ObjectNotFoundException {
		for(int x = 0; x < photon.subs.size(); x++) {
			if(photon.subs.get(x).getSubNumber() == subNum &&
					photon.subs.get(x).getIndex() == index &&
					photon.subs.get(x).getUid().equals(uid)) {
				return x;
			}
		}
		throw new ObjectNotFoundException("Could not find Sub #" + subNum + " !");
	}
	
	
	/*
	 * CONSTRUCTOR
	 */
	public PhotonDataUtilities(Photon photonIn) {
		this.photon = photonIn;
	}
}
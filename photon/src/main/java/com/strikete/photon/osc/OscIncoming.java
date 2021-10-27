package com.strikete.photon.osc;

public class OscIncoming {

	/*
	 * VARIABLES
	 */
	public static String RETURN_VERSION = "/eos/out/get/version";
	public static String RETURN_PATCH_COUNT = "/eos/out/get/patch/count";
	public static String RETURN_CUELIST_COUNT = "/eos/out/get/cuelist/count";
	public static String RETURN_CUE_COUNT = "/eos/out/get/cue/[int]/count"; //What to do about this one lacking a "count"....
	public static String RETURN_GROUP_COUNT = "/eos/out/get/group/count";
	public static String RETURN_MACRO_COUNT = "/eos/out/get/macro/count";
	public static String RETURN_SUB_COUNT = "/eos/out/get/sub/count";
	public static String RETURN_PRESET_COUNT = "/eos/out/get/preset/count";
	public static String RETURN_INTENSITY_PALETTE_COUNT = "/eos/out/get/ip/count";
	public static String RETURN_FOCUS_PALETTE_COUNT = "/eos/out/get/fp/count";
	public static String RETURN_COLOR_PALETTE_COUNT = "/eos/out/get/cp/count";
	public static String RETURN_BEAM_PALETTE_COUNT = "/eos/out/get/bp/count";
	public static String RETURN_CURVE_COUNT = "/eos/out/get/curve/count";
	public static String RETURN_EFFECT_COUNT = "/eos/out/get/fx/count";
	public static String RETURN_SNAPSHOT_COUNT = "/eos/out/get/snap/count";
	public static String RETURN_PIXELMAP_COUNT = "/eos/out/get/pixmap/count";
	public static String RETURN_MAGIC_SHEET_COUNT = "/eos/out/get/ms/count";
	
	public static String RETURN_PATCH = "/eos/out/get/patch/[int]/[int]/list/[int]/[int]";
	public static String RETURN_PATCH_NOTES = "/eos/out/get/patch/[int]/[int]/notes";
	public static String RETURN_CUELIST = "/eos/out/get/cuelist/[int]/list/[int]/[int]";
	public static String RETURN_CUELIST_LINKS = "/eos/out/get/cuelist/[int]/links/list/[int]/[int]";
	public static String RETURN_CUE = "/eos/out/get/cue/[int]/[float]/[int]/list/[int]/[int]";
	public static String RETURN_CUE_EFFECTS = "/eos/out/get/cue/[int]/[float]/[int]/fx/list/[int]/[int]";
	public static String RETURN_CUE_LINKS = "/eos/out/get/cue/[int]/[float]/[int]/links/list/[int]/[int]";
	public static String RETURN_CUE_ACTIONS = "/eos/out/get/cue/[int]/[float]/[int]/actions/list/[int]/[int]";
	
	
}

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
	public static String RETURN_GROUP = "/eos/out/get/group/[float]/list/[int]/[int]";
	public static String RETURN_GROUP_CHANNELS = "/eos/out/get/group/[float]/channels/list/[int]/[int]";
	public static String RETURN_MACRO = "/eos/out/get/macro/[int]/list/[int]/[int]";
	public static String RETURN_MACRO_COMMAND = "/eos/out/get/macro/[int]/text/list/[int]/[int]";
	public static String RETURN_SUB = "/eos/out/get/sub/[int]/list/[int]/[int]";
	public static String RETURN_SUB_EFFECTS = "/eos/out/get/sub/[int]/fx/list/[int]/[int]";
	public static String RETURN_PRESET = "/eos/out/get/preset/[float]/list/[int]/[int]";
	public static String RETURN_PRESET_CHANNELS = "/eos/out/get/preset/[float]/channels/list/[int]/[int]";
	public static String RETURN_PRESET_CHANNELS_BY_TYPE = "/eos/out/get/preset/[float]/byType/list/[int]/[int]";
	public static String RETURN_PRESET_EFFECTS = "/eos/out/get/preset/[float]/fx/list/[int]/[int]";
	public static String RETURN_INTENSITY_PALETTE = "/eos/out/get/ip/[float]/list/[int]/[int]";
	public static String RETURN_INTENSITY_PALETTE_CHANNELS = "/eos/out/get/ip/[float]/channels/list/[int]/[int]";
	public static String RETURN_INTENSITY_PALETTE_CHANNELS_BY_TYPE = "/eos/out/get/ip/[float]/byType/list/[int]/[int]";
	public static String RETURN_FOCUS_PALETTE = "/eos/out/get/fp/[float]/list/[int]/[int]";
	public static String RETURN_FOCUS_PALETTE_CHANNELS = "/eos/out/get/fp/[float]/channels/list/[int]/[int]";
	public static String RETURN_FOCUS_PALETTE_CHANNELS_BY_TYPE = "/eos/out/get/fp/[float]/byType/list/[int]/[int]";
	public static String RETURN_COLOR_PALETTE = "/eos/out/get/cp/[float]/list/[int]/[int]";
	public static String RETURN_COLOR_PALETTE_CHANNELS = "/eos/out/get/cp/[float]/channels/list/[int]/[int]";
	public static String RETURN_COLOR_PALETTE_CHANNELS_BY_TYPE = "/eos/out/get/cp/[float]/byType/list/[int]/[int]";
	public static String RETURN_BEAM_PALETTE = "/eos/out/get/bp/[float]/list/[int]/[int]";
	public static String RETURN_BEAM_PALETTE_CHANNELS = "/eos/out/get/bp/[float]/channels/list/[int]/[int]";
	public static String RETURN_BEAM_PALETTE_CHANNELS_BY_TYPE = "/eos/out/get/bp/[float]/byType/list/[int]/[int]";
	public static String RETURN_CURVE = "/eos/out/get/curve/[int]/list/[int]/[int]";
	public static String RETURN_EFFECT = "/eos/out/get/fx/[float]/list/[int]/[int]";
	public static String RETURN_SNAPSHOT = "/eos/out/get/snap/[int]/list/[int]/[int]";
	public static String RETURN_PIXELMAP = "/eos/out/get/pixmap/[int]/list/[int]/[int]";
	public static String RETURN_PIXELMAP_CHANNELS = "/eos/out/get/pixmap/[int]/channels/list/[int]/[int]";
	public static String RETURN_MAGIC_SHEET = "/eos/out/get/ms/[int]/list/[int]/[int]";
	
}

package com.strikete.photon.osc;

public class OscOutgoing {
	
	/*
	 * VARIABLES
	 */
	public static String GET_VERSION = "/eos/get/version";
	public static String GET_PATCH_COUNT = "/eos/get/patch/count";
	public static String GET_CUELIST_COUNT = "/eos/get/cuelist/count";
	public static String GET_CUE_COUNT = "/eos/get/cue/[parameter1]/count";
	public static String GET_GROUP_COUNT = "/eos/get/group/count";
	public static String GET_MACRO_COUNT = "/eos/get/macro/count";
	public static String GET_SUB_COUNT = "/eos/get/sub/count";
	public static String GET_PRESET_COUNT = "/eos/get/preset/count";
	public static String GET_INTENSITY_PALETTE_COUNT = "/eos/get/ip/count";
	public static String GET_FOCUS_PALETTE_COUNT = "/eos/get/fp/count";
	public static String GET_COLOR_PALETTE_COUNT = "/eos/get/cp/count";
	public static String GET_BEAM_PALETTE_COUNT = "/eos/get/bp/count";
	public static String GET_CURVE_COUNT = "/eos/get/curve/count";
	public static String GET_EFFECT_COUNT = "/eos/get/fx/count";
	public static String GET_SNAPSHOT_COUNT = "/eos/get/snap/count";
	public static String GET_PIXELMAP_COUNT = "/eos/get/pixmap/count";
	public static String GET_MAGIC_SHEET_COUNT = "/eos/get/ms/count";
	
	public static String GET_PATCH_INFO = "/eos/get/patch/index/[parameter1]";
	public static String GET_CUELIST_INFO = "/eos/get/cuelist/index/[parameter1]";
	public static String GET_CUE_INFO = "/eos/get/cue/[parameter1]/index/[parameter2]";
	public static String GET_GROUP_INFO = "/eos/get/group/index/[parameter1]";
	public static String GET_MACRO_INFO = "/eos/get/macro/index/[parameter1]";
	public static String GET_SUB_INFO = "/eos/get/sub/index/[parameter1]";
	public static String GET_PRESET_INFO = "/eos/get/preset/index/[parameter1]";
	public static String GET_INTENSITY_PALETTE_INFO = "/eos/get/ip/index/[parameter1]";
	public static String GET_FOCUS_PALETTE_INFO = "/eos/get/fp/index/[parameter1]";
	public static String GET_COLOR_PALETTE_INFO = "/eos/get/cp/index/[parameter1]";
	public static String GET_BEAM_PALETTE_INFO = "/eos/get/bp/index/[parameter1]";
	public static String GET_CURVE_INFO = "/eos/get/curve/index/[parameter1]";
	public static String GET_EFFECT_INFO = "/eos/get/fx/index/[parameter1]";
	public static String GET_SNAPSHOT_INFO = "/eos/get/snap/index/[parameter1]";
	public static String GET_PIXELMAP_INFO = "/eos/get/pixmap/index/[parameter1]";
	public static String GET_MAGIC_SHEET_INFO = "/eos/get/ms/index/[parameter1]";
	
	public static String SELECT_CHANNEL = "/eos/chan/";
	public static String COMMAND = "/eos/newevent";
	
	public static String KEY_ENTER = "/eos/key/enter";
	public static String KEY_CLEAR = "/eos/key/clear_cmd";
	public static String KEY_RESET_COMMAND_LINE = "/eos/key/clear_cmdline";
	public static String KEY_LIVE = "/eos/key/live";
	public static String KEY_BLIND = "/eos/key/blind";
	
}

package com.hoatieu;

/**
 * Define all constant variable in project
 **/
public class Constant {
	public static final String APPID_WEATHER = "8dae1d74724aedf66e43d9cac2de2538";
	//http://api.openweathermap.org/data/2.5/find?lat=45&lon=-2.15
	//public static final String API_WETHER_URL = "http://api.openweathermap.org/data/2.5/find";
	public static final String API_WETHER_URL = "http://api.openweathermap.org/data/2.5/forecast?APPID=%s&lat=%s&lon=%s";
	// Timeout
	public final static int TIME_OUT = 4500;
	// of : truong : 498720258430
	// old : 565050102112
	public static final String SENDER_ID = "498720258430";
	public final static String SP_PARAM_PATH = "HOATIEU";
	public final static String KEY_PREFERENCE_UNIT = "KEY_UNIT";

	public final static String API_REGIS_URL = "http://hoatieuws.99s.vn/index.php/api/webservice/hoatieuregister/format/json";
	public final static String API_GETFRIENDS = "http://hoatieuws.99s.vn/index.php/api/webservice/hoatieufixlocations/format/json";
	public final static String API_GETSTORM = "http://hoatieuws.99s.vn/index.php/api/webservice/hoatieueventlocations/format/json";
	public final static String API_HELP = "http://hoatieuws.99s.vn/index.php/api/webservice/hoatieuupdate/format/json";

}

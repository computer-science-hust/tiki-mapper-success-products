package decode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Decoder {
	/**
	 * return result is id and url
	 */
	public static String urlEncoder1(String input) throws UnsupportedEncodingException, ParseException {
		String urlEncode = URLDecoder.decode(input, "UTF-8");
		// url encode have shape 
		// 749@@@100112:{"url":"https://tiki.vn/bao-da-danh-cho-samsung-galaxy-a12-flip-wallet-leather-dang-vi-da-nang-sieu-ben-sieu-em-p77656689.html?src=ss-organic"}###
		// from 749@@@100112%3A%7B%22url%22%3A%22https%3A%2F%2Ftiki.vn%2Fbao-da-danh-cho-samsung-galaxy-a12-flip-wallet-leather-dang-vi-da-nang-sieu-ben-sieu-em-p77656689.html%3Fsrc%3Dss-organic%22%7D###
		
		System.out.println(urlEncode);
		
		String pattern = "(^d+@@@d+):(.*)###";
		Pattern r = Pattern.compile(pattern);
		
		Matcher m = r.matcher(urlEncode);
		String json = null;
		
		if(m.find()) {
			json = m.group(2);
			JSONParser parse = new JSONParser();
			JSONObject obj = (JSONObject) parse.parse(json);
			
			String url = (String) obj.get("url");
			
			return url;
		}
		
		return null;
	}
	
	public static String urlEncoder(String input) throws UnsupportedEncodingException, ParseException {
		String urlEncode = URLDecoder.decode(input, "UTF-8");
		System.out.println(urlEncode);
		urlEncode = urlEncode.replace("###", "");
//		int index = urlEncode.indexOf("@@@");
//		urlEncode = urlEncode.substring(index + 10);
		
//		System.out.println(urlEncode);
//		JSONParser parse = new JSONParser();
//		JSONObject json = (JSONObject) parse.parse(urlEncode);
//				
//		return json.get("links").toString();
		
		return urlEncode;
	}
	
//	public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
//		System.out.println(Decoder.urlEncoder("749@@@100118%3A%7B%22total%22%3A%22140000%22%2C%22links%22%3A%5B%22%2Fproduct-p3216415.html%3Fspid%3D100931619%22%5D%2C%22count%22%3A1%7D###"));
//	}
}

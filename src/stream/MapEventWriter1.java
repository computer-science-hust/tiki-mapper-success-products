package stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.spark.sql.ForeachWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import models.SuccessEvent;

public class MapEventWriter1 extends ForeachWriter<String>{
	private static final long serialVersionUID = 1L;
	
	public MapEventWriter1() throws IOException {
		
	}
	
	@Override
	public void close(Throwable errorOrNull) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean open(long partitionId, long epochId) {
		return true;
	}

	@Override
	public void process(String input) {
		JSONParser parse = new JSONParser();
		
		JSONObject json = null;
		try {
			json = (JSONObject) parse.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long guid = (long) json.get("guid");
		long time = (long) json.get("time");
		String products = (String) json.get("url");
		
		SuccessEvent success = new SuccessEvent(time, products, guid);

		// write result
		BufferedWriter buffer;
		try {
			buffer = new BufferedWriter(new FileWriter("resources/success.txt", true));
			buffer.write(success.toString() + "\n");
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}

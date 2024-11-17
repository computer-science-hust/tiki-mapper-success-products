package stream;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.spark.sql.ForeachWriter;
import org.apache.spark.sql.SparkSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dataservices.OrderDataServices;
import models.OrderEvent;
import models.SuccessEvent;

public class MapEventWriter extends ForeachWriter<String>{
	private SparkSession spark;
	
	public MapEventWriter(SparkSession spark) {
		this.spark = spark;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void close(Throwable errorOrNull) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean open(long partitionId, long epochId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void process(String input) {
//		OrderDataServices services = new OrderDataServices(this.spark);
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
//		OrderEvent order = services.getData(guid, time).get(0);
//		MapperEvent mapper = new MapperEvent(order, success);
		
		// write result
		PrintWriter print = null;
		try {
			print = new PrintWriter("result.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		print.append(success.toString());
		
		print.close();
	}

}

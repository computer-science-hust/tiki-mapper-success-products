package dataservices;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import models.OrderEvent;

public class OrderDataServices {
	private SparkSession spark;
	private Dataset<Row> dataView;
	
	public OrderDataServices(SparkSession spark) {
		this.spark = spark;
		dataView = spark.read().parquet("/home/trannguyenhan/dataset/data/2021_06_09_view");
	}
	
	public List<OrderEvent> getData(String idS, String timeS) {
		long id = Long.parseLong(idS);
		long time = Long.parseLong(timeS);
		
		dataView.createOrReplaceTempView("dataView");
		Dataset<Row> dataFilter = 
				spark.sql("select * from dataView where guid = " + id + " and time_group.time_create between " + (time-30*60) + " and " + time);
				//spark.sql("select * from dataView where guid = 4432075802885467266 and time_group.time_create between 1623207598400 and 1623207602000");
		dataFilter.show();
		List<OrderEvent> listOrderEvent = new ArrayList<OrderEvent>();	
		List<Row> lr = dataFilter.collectAsList(); // parse Dataset<Row> to List
		
		
		for(Row r : lr) {
			long timeCreate = r.getStruct(0).getLong(0);
			long guid = r.getLong(4);
			
			OrderEvent order = new OrderEvent(timeCreate, guid);
			listOrderEvent.add(order);
		}
		
		return listOrderEvent;
	}
	
	public void closeService() {
		spark.close();
	}
}

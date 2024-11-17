package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.ForeachWriter;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.json.simple.parser.ParseException;

import dataservices.OrderDataServices;
import models.MapperEvent;
import models.OrderEvent;
import models.SuccessEvent;
import stream.MapEventWriter;
import stream.MapEventWriter1;

public class Demo {
	public static void main(String[] args) throws TimeoutException, StreamingQueryException, ParseException, IOException {
		try (SparkSession spark = SparkSession.builder().appName("Read kafka").getOrCreate()) {
			Dataset<String> data = spark
					.readStream()
			        .format("kafka")
			        .option("kafka.bootstrap.servers", "localhost:9092")
			        .option("subscribe", "tiki-1")
			        .load()
					.selectExpr("CAST(value AS STRING)")
					.as(Encoders.STRING());
			
//			MapEventWriter eventWriter = new MapEventWriter(spark);
			Dataset<Row> df = data.toDF();
			
			StreamingQuery query = df
					.writeStream()
					.foreach(new ForeachWriter<Row>() {
						private static final long serialVersionUID = 1L;

						@Override
						public void process(Row value) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public boolean open(long partitionId, long epochId) {
							// TODO Auto-generated method stub
							return false;
						}
						
						@Override
						public void close(Throwable errorOrNull) {
							// TODO Auto-generated method stub
							
						}
					})
					.start();
			
//			if(Files.exists(Paths.get("resources/success.txt"))) {
//				Files.createFile(Paths.get("hello.txt"));
//				
//				BufferedReader buffer = new BufferedReader(new FileReader("resources/success.txt"));
//				String line;
//				while((line = buffer.readLine()) != null) {
//					String[] values = line.split(" ");
//					String idS = values[0];
//					String timeS = values[1];
//					String urlS = values[2];
//					
//					OrderDataServices services = new OrderDataServices(spark);
//					OrderEvent order = services.getData(idS, timeS).get(0);
//					SuccessEvent success = new SuccessEvent(Long.parseLong(timeS), urlS, Long.parseLong(idS));
//					MapperEvent mapper = new MapperEvent(order, success);
//					
//					// write result
//					BufferedWriter reader;
//					try {
//						reader = new BufferedWriter(new FileWriter("result.txt", true));
//						reader.write(mapper.toString() + "\n");
//						reader.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//				buffer.close();
//				Files.deleteIfExists(Paths.get("resources/success.txt"));
//			}
			
			
			query.awaitTermination();
			
		}
	}
	
	
}

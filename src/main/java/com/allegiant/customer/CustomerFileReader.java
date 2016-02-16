package com.allegiant.customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomerFileReader {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("allegiant");
	
	private static SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	@Autowired
	CustomerService customerService;
	
	public static Date parseDate(String dateStr) throws ParseException{
		Date date =  dateParser.parse(dateStr);
		return date;
	}
	public void readFile(String path, CustomerFileFormat format) throws Exception{
		
		File f = new File(path);
		if (f.exists()){
			FileInputStream fis = new FileInputStream(f);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String line = reader.readLine(); //read the initial headers
			line = reader.readLine(); //read the first row
			int lineNumber = 2;
			
			while (line != null){
				//SignUpDate	First	Last	Email	Latitude	Longitude	IP
				String[] tokens = line.split(",");
				if (tokens.length == 7){
					String signUpDateStr = tokens[0];
					Customer customer = new Customer();
					customer.setCreatedAt(parseDate(signUpDateStr));
					customer.setFirstName(format == CustomerFileFormat.FORMAT1 ? tokens[1] : tokens[4]);
					customer.setLastName(format == CustomerFileFormat.FORMAT1 ? tokens[2] : tokens[5]);
					customer.setEmail(format == CustomerFileFormat.FORMAT1 ? tokens[3] : tokens[6]);
					try{
						String latStr = format == CustomerFileFormat.FORMAT1 ? tokens[4] : tokens[2];
						String lngStr = format == CustomerFileFormat.FORMAT1 ? tokens[5] : tokens[3];
						Float lat = latStr.length() == 0 ? 0f : Float.parseFloat(latStr);
						Float lng = lngStr.length() == 0 ? 0f : Float.parseFloat(lngStr);						
						customer.setLatitude(lat);
						customer.setLongitude(lng);
					}catch (NumberFormatException nfe){
						throw new Exception("Error in line # " + lineNumber + ": the latitude or longitude is in the wrong format.( " + tokens[4] + "," + tokens[5] + ")");
					}
					catch (Exception e){
						throw new Exception("Error in line # " + lineNumber + ":" + e.getMessage());
					}
					customer.setIp(format == CustomerFileFormat.FORMAT1 ? tokens[6] : tokens[1]); //TODO: validation ??
					try{
						customerService.create(customer);
					}catch (Exception e){
						throw new Exception("Error constructing db entity for line # " + lineNumber + ":" + e.getMessage());
					}
					customer=null;
					System.gc();
					
				}else{
					throw new Exception("Error in Line #" + lineNumber + ": wrong number of data columns : " + tokens.length);
				}			
				lineNumber++;
				line=reader.readLine();
			}
				
		}else{
			throw new FileNotFoundException(path + " : not found");
		}
	}


}

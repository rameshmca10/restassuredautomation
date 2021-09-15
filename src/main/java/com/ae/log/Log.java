package com.ae.log;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log {

	public Logger log = Logger.getLogger(Log.class);
	public Properties prop;
	//PropertyConfigurator.Configure("log4j.properties");
	//log.set(Level.info);

	public Log() {
		try {
			BasicConfigurator.configure();
			PropertyConfigurator.configure("log4j.properties");
			prop = new Properties();
			FileInputStream fis = new FileInputStream("st.properties");
			prop.load(fis);
		}

		catch(Exception e) {}
	}

}
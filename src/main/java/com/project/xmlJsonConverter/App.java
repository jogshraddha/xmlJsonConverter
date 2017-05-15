package com.project.xmlJsonConverter;
import java.io.File;

import com.project.utils.ConversionUtil;
import com.project.utils.JsonWriter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		final String dir = App.class.getClassLoader().getResource("ALPH-PROD-0413-1022").getPath();
		File directory = new File(dir);
		File[] list = directory.listFiles();
		for (File file : list) {
			String jsonString = new ConversionUtil().convertToJson(file);
			File jsonFile = new File(dir + File.separator + file.getName() + ".json");
			new JsonWriter().writeJson(jsonFile, jsonString);
		}
		
	}
}

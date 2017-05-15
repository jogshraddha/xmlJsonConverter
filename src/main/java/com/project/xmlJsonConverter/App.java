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
		String extension = ".json";
		JsonWriter jsonWriter = new JsonWriter();
		File[] list = directory.listFiles();
		for (File file : list) {
			String jsonString = new ConversionUtil().convertToJson(file);
			StringBuilder jsonPathBuilder = new StringBuilder();
			jsonPathBuilder.append(dir).append(File.separator).append(file.getName().split("\\.")[0]).append(extension);
			File jsonFile = new File(jsonPathBuilder.toString());
			jsonWriter.writeJson(jsonFile, jsonString);
		}
		
	}
}

package com.project.xmlJsonConverter;
import java.io.File;
import java.util.List;

import com.project.utils.ConversionUtil;
import com.project.utils.JsonWriter;
import com.project.utils.XbrlConverter;

import org.apache.commons.io.FileUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		/*final String dir = App.class.getClassLoader().getResource("ALPH-PROD-0413-1022").getPath();
		File directory = new File(dir);
		String extension = ".json";
		String[] extensions = new String[] { "xml"};
		JsonWriter jsonWriter = new JsonWriter();
		List<File> files = (List<File>) FileUtils.listFiles(directory, extensions, true);
		for (File file : files) {
			System.out.println(file.getName());
			String jsonString = new ConversionUtil().convertToJson(file);
			StringBuilder jsonPathBuilder = new StringBuilder();
			jsonPathBuilder.append(dir).append(File.separator).append(file.getName().split("\\.")[0]).append(extension);
			File jsonFile = new File(jsonPathBuilder.toString());
			jsonWriter.writeJson(jsonFile, jsonString);
		}*/
		new XbrlConverter().convertXbrlData();
		
	}
}

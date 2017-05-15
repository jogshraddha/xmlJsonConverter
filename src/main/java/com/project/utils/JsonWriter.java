package com.project.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

	public void writeJson(File jsonFile, String json) {
		FileWriter file = null;
		try {
			file = new FileWriter(jsonFile);
			file.write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

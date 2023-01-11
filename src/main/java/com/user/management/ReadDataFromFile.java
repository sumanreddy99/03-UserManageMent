package com.user.management;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.user.management.entity.User;

public class ReadDataFromFile {

	public static void main(String[] args) {
		// Read Data from File
	}
		private String readEmailBody(String filename, User user) {

			StringBuffer sb = new StringBuffer();
			try(Stream<String> lines = Files.lines(Paths.get(filename))) {

				
				lines.forEach(line -> {
					line = line.replace("${FNAME}", user.getFirstName());
					line = line.replace("#{LNAME}", user.getLastName());
					line = line.replace("#{TEMP_PWD}", user.getPassword());
					line = line.replace("#{EMAIL}", user.getEmail());
					line = line.replace("#{PWD}", user.getPassword());
					sb.append(line);
				});

			} catch (Exception e) {
				e.printStackTrace();
			}
			return sb.toString();

		}


	}



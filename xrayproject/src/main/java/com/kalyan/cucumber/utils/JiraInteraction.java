package com.kalyan.cucumber.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class JiraInteraction {

	public void exportTests(String auth, String jiraUrl, String jiraExecutionId, String featureFileDir)
			throws Exception {
		/// api/v1/export/cucumber?keys=PROJ-4
		String url = jiraUrl + "/api/v1/export/cucumber?keys=" + jiraExecutionId;
		System.out.println("url is    "+url);
		downloadUsingStream(url, auth, featureFileDir);

	}

	/*public void importResults(String auth, String jiraUrl, String testResultdir) throws Exception {

		String[] command = { "curl.exe", "-H", "Content-Type: text/xml", "-X", "POST", "-H", "Authorization: Bearer "
				+ "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJlZTM0MTU3MC1mZDRjLTMzOWItYjFjYy03OTMxYzM5NDg2NDkiLCJhY2NvdW50SWQiOiI1ZGVlYzcwZGY0YWIyOTBlY2ZjNWU5NjQiLCJpYXQiOjE1NzY1NDA5OTQsImV4cCI6MTU3NjYyNzM5NCwiYXVkIjoiRkI4NkJBOEE1RkUwNDBEQzgwRjczQjNGNjZDN0JDODkiLCJpc3MiOiJjb20ueHBhbmRpdC5wbHVnaW5zLnhyYXkiLCJzdWIiOiJGQjg2QkE4QTVGRTA0MERDODBGNzNCM0Y2NkM3QkM4OSJ9.g7-iTj1vqYPc6a6tAsMEuEkpA49Cc5YPsot3FxLBVlk",
				"--data", "@"+"C:\\Users\\PawanPadma\\Documents\\TestingDocs\\cucumber-java-xray-automation-master\\target\\cucumber-reports\\Cucumber.xml",
				"https://xray.cloud.xpand-it.com/api/v1/import/execution/junit?testExecKey=PROJ-6" };
		
		System.out.println("command is   "+command.toString());
		ProcessBuilder process = new ProcessBuilder(command);
		Process p;
		p = process.start();
		Thread.sleep(10000);
	}*/
	
	public void importResults(String auth, String jiraUrl, String testResultdir) throws Exception {

		String[] command = { "curl.exe", "-H", "Content-Type: application/json", "-X", "POST", "-H", "Authorization: Bearer "+ auth,
				"--data", "@"+System.getProperty("user.dir")+"\\target\\cucumber\\Destination\\cucumber.json",
				jiraUrl+"/api/v1/import/execution/cucumber" };
		
		System.out.println("command is   "+command);
		ProcessBuilder process = new ProcessBuilder(command);
		Process p;
		p = process.start();
		Thread.sleep(10000);
	}

	private static void downloadUsingStream(String urlStr, String auth, String file) throws Exception {
		String featureFile;
		String filePath;
		String currentDir = System.getProperty("user.dir");
		System.out.println(currentDir);
		String fileFormat = GetProperties.downloadFileFormat;
		filePath = currentDir + "/" + file;
		if (fileFormat.equals("zip")) {
			featureFile = filePath + "/" + "feature.zip";
		} else {
			featureFile = filePath + "/" + "test.feature";
		}

		URL url = new URL(urlStr);
		URLConnection urlConnection = url.openConnection();
		urlConnection.setRequestProperty("Authorization",
				"Bearer "+auth);
		InputStream inputStream = urlConnection.getInputStream();
		OutputStream outputStream = new FileOutputStream(featureFile);
		byte[] b = new byte[2048];
		int length;

		while ((length = inputStream.read(b)) != -1) {
			outputStream.write(b, 0, length);
		}

		inputStream.close();
		outputStream.close();
		if (fileFormat.equals("zip")) {
			UnzipFile unzipFile = new UnzipFile();
			unzipFile.unzipFunction(filePath, featureFile);

		}

	}

}

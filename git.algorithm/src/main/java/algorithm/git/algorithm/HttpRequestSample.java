package algorithm.git.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequestSample {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String getMethodResult = sendGetHttpRequest("https://kyfw.12306.cn/otn/leftTicket/queryO"
				+ "?leftTicketDTO.train_date=2018-01-27&leftTicketDTO.from_station=CWQ&leftTicketDTO.to_station=IOQ&purpose_codes=ADULT");
		long endTime = System.currentTimeMillis();
		System.out.println("The total spending time is: " + (endTime-startTime) + " ms");
		System.out.println(getMethodResult);
		
	}

	public static String sendGetHttpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			try {
				HttpURLConnection httpUrlConn = (HttpURLConnection) url
						.openConnection();
				httpUrlConn.setDoOutput(false);
				httpUrlConn.setDoInput(true);
				httpUrlConn.setUseCaches(false);
				httpUrlConn.setRequestMethod("GET");
				httpUrlConn.connect();
				InputStream inputStream = httpUrlConn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					buffer.append(line);
				}
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
				inputStream = null;
				httpUrlConn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}

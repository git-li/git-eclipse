package algorithm.git.algorithm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

public class ObtainIdtyCode {
	public static void main(String[] args) {
		obtainJpgImage("https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&0.21191171556711197");
	}

	public static void obtainImageByUrl(String url) {
		try {
			URL localUrl = new URL(url);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) localUrl
					.openConnection();
			httpUrlConnection.setDoOutput(false);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.connect();
			int responseCode = httpUrlConnection.getResponseCode();
			if (responseCode == 200) {
				InputStream input = httpUrlConnection.getInputStream();
				byte[] date = new byte[1024];
				int len = -1;
				File file = new File("E:\\git-eclipse\\git.algorithm\\src\\img\\idty-image.jpg");
				FileOutputStream out = new FileOutputStream(file);
				while ((len = input.read(date)) != -1) {
					out.write(date, 0, 1024);
				}
				out.flush();
				if (null != out) {
					out.close();
				}
				if (null != input) {
					input.close();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void obtainJpgImage(String url){
		try {
			URL localUrl = new URL(url);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) localUrl
					.openConnection();
			httpUrlConnection.setDoOutput(false);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.connect();
			int responseCode = httpUrlConnection.getResponseCode();
			if (responseCode == 200) {
				InputStream input = httpUrlConnection.getInputStream();
				JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(input);
				BufferedImage image = decoder.decodeAsBufferedImage();
				ImageIO.write(image, "jpg", new File("E:\\git-eclipse\\git.algorithm\\src\\img\\idty-image.jpg"));
				if (null != input) {
					input.close();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

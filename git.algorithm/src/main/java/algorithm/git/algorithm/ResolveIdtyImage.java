package algorithm.git.algorithm;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ResolveIdtyImage {
	public static void main(String[] args) {
		obtainAndResolveIdtyImage();
	}

	public static void obtainAndResolveIdtyImage() {
		try {
			URL url = new URL(
					"https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand&0.21191171556711197");
			HttpURLConnection request = (HttpURLConnection) url
					.openConnection();
			request.setDoOutput(false);
			request.setDoInput(true);
			request.setUseCaches(false);
			request.setRequestMethod("GET");
			request.connect();
			int responseCode = request.getResponseCode();
			if (200 == responseCode) {
				InputStream input = request.getInputStream();

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = input.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				baos.flush();

				InputStream input1 = new ByteArrayInputStream(
						baos.toByteArray());
				InputStream input2 = new ByteArrayInputStream(
						baos.toByteArray());

				Iterator<ImageReader> imageReaders = ImageIO
						.getImageReadersByFormatName("jpg");
				ImageReader reader = imageReaders.next();
				ImageInputStream imageStream = ImageIO
						.createImageInputStream(input1);
				reader.setInput(imageStream, true);
				ImageReadParam param = reader.getDefaultReadParam();
				Rectangle rectQst = new Rectangle(118, 3, 170, 23);
				param.setSourceRegion(rectQst);
				BufferedImage imgQst = reader.read(0, param);
				FileOutputStream outputQst = new FileOutputStream(
						new File(
								"E:\\git-eclipse\\git.algorithm\\src\\img\\imageQst.jpg"));
				ImageIO.write(imgQst, "jpg", outputQst);

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 4; j++) {
						int x = 5 + (67 + 5) * j;
						int y = 41 + (67 + 5) * i;
						Rectangle rect = new Rectangle(x, y, 67, 67);
						param.setSourceRegion(rect);
						BufferedImage image = reader.read(0, param);
						FileOutputStream output = new FileOutputStream(
								new File(
										"E:\\git-eclipse\\git.algorithm\\src\\img\\image"
												+ i + "_" + j + ".jpg"));
						ImageIO.write(image, "jpg", output);
					}
				}

				BufferedImage bi = ImageIO.read(input2);
				ImageIO.write(
						bi,
						"jpg",
						new File(
								"E:\\git-eclipse\\git.algorithm\\src\\img\\idty-image.jpg"));

				if (null != imageStream) {
					imageStream.close();
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
}

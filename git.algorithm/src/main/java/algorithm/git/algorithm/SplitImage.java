package algorithm.git.algorithm;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang3.StringUtils;

public class SplitImage {
	public static void splitJpgImg(InputStream input, OutputStream output,
			int x, int y, int width, int height) throws IOException {
		ImageInputStream imageStream = null;
		Iterator<ImageReader> readers = ImageIO
				.getImageReadersByFormatName("jpg");
		ImageReader reader = readers.next();
		imageStream = ImageIO.createImageInputStream(input);
		reader.setInput(imageStream, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, width, height);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", output);
		if (null != imageStream) {
			imageStream.close();
		}
	}

	public static void splitPngImg(InputStream input, OutputStream output,
			int x, int y, int width, int height) throws IOException {
		ImageInputStream imageStream = null;
		Iterator<ImageReader> readers = ImageIO
				.getImageReadersByFormatName("png");
		ImageReader reader = readers.next();
		imageStream = ImageIO.createImageInputStream(input);
		reader.setInput(imageStream, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, width, height);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "png", output);
		if (null != imageStream) {
			imageStream.close();
		}
	}

	public static void splitImage(String type, InputStream input,
			OutputStream output, int x, int y, int width, int height)
			throws IOException {
		ImageInputStream imageStream = null;
		String imageType = StringUtils.isBlank(type) ? "jpg" : type;
		Iterator<ImageReader> readers = ImageIO
				.getImageReadersByFormatName(imageType);
		ImageReader reader = readers.next();
		imageStream = ImageIO.createImageInputStream(input);
		reader.setInput(imageStream, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, width, height);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, imageType, output);
		if (null != imageStream) {
			imageStream.close();
		}
	}

	public static void main(String[] args) {
		try {
			FileInputStream input = new FileInputStream(
					"E:\\git-eclipse\\git.algorithm\\src\\img\\captcha-image.jpg");
			FileOutputStream output = new FileOutputStream(
					"E:\\git-eclipse\\git.algorithm\\src\\img\\split-image.jpg");
			FileOutputStream qstImg = new FileOutputStream(
					"E:\\git-eclipse\\git.algorithm\\src\\img\\qst-image.jpg");
			try {
				SplitImage.splitJpgImg(input, output, 5, 41, 67, 67);
				SplitImage.splitJpgImg(input, qstImg, 118, 3, 170, 23);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

package com.super2048;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 资源加载类
 * 
 * @author 爱寂寞的时光
 *
 */
public class ResourcesLoader {
	private static final ClassLoader LOADER = ResourcesLoader.class.getClassLoader();

	/**
	 * 加载文件
	 * 
	 * @param path
	 *            路径
	 * @return URL
	 * @throws FileNotFoundException
	 *             如果文件不存在
	 */
	public static URL load(String path) throws FileNotFoundException {
		URL url = LOADER.getResource(path);
		if (url != null) {
			return url;
		} else {
			throw new FileNotFoundException(path + "文件不存在");
		}
	}

	/**
	 * 加载图片
	 * 
	 * @param path
	 *            路径
	 * @return 图片
	 * @throws IOException
	 *             如果文件不存在或发生IO异常
	 */
	public static Image loadImage(String path) throws IOException {
		URL url = load(path);
		return ImageIO.read(url);
	}
}

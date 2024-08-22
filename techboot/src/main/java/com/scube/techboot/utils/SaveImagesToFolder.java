package com.scube.techboot.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;



public class SaveImagesToFolder {
	private static final Logger LOGGER = Logger.getLogger(SaveImagesToFolder.class);

	public static void saveImageToFolder(String filename, MultipartFile image, String filePath)
			throws RuntimeException, IOException {
		try {
			File file = new File(filePath + filename);
			FileUtils.writeByteArrayToFile(file, image.getBytes());			
		} catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
	}
}

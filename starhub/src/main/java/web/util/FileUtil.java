package web.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static byte[] getFileContent(Part file) {
		byte[] fileContent = null;
		try {
			InputStream inputStream = file.getInputStream();
			if (inputStream == null)
				logger.info("File inputstream is null");
			fileContent = IOUtils.toByteArray(inputStream);
		} catch (IOException ex) {
			logger.error("Error saving uploaded file");
		}
		return fileContent;
	}
}

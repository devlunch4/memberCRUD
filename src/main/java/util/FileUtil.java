package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//파일 이름 추출시 사용
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static String getFileName(String contentDisposition) {
		logger.debug("파일이름 / 확장자 추출 진입");
		String[] attrs = contentDisposition.split("; ");
		for (String attr : attrs) {
			if (attr.startsWith("filename=")) {
				// filename = "brown.jpg"
				attr = attr.replace("filename=", "");
				//
				// "brown.jpg"
				return attr.substring(1, attr.length() - 1);
			}
		}
		return "";
	}

	public static String getFileExtension(String filename) {
		// brown
		if (filename.indexOf(".") == -1) {
			return "";
		}
		return "." + filename.substring(filename.lastIndexOf(".") + 1);
	}
}

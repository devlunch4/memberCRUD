package utilltest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.FileUtil;

public class FileUtilTest {

	@Test
	public void FileUtilTest1() {
		/*** Given ***/
		String contentDisposition = "\"form-data; name=\"brown\"; filename=\"brown.jpg\"";

		/*** When ***/
		String filename = FileUtil.getFileName(contentDisposition);

		/*** Then ***/
		assertEquals("brown.jpg", filename);
	}

	@Test
	public void FileUtilTest2() {
		// System.out.println(UUID.randomUUID().toString());

	}

	@Test
	public void getFileExtensionTest() {
		/*** Given ***/
		String filename = "brown.png";

		/*** When ***/
		String extension = FileUtil.getFileExtension(filename);
		// String extensionx = FileUtil.getFileExtensionx(filename);

		/*** Then ***/
		assertEquals(".png", extension);
		// assertEquals("png", extensionx);
	}

	@Test
	public void getFileExtensionTest2() {
		/*** Given ***/
		//String filename = "line.brown.png";

		/*** When ***/
		// String extension = FileUtil.getFileExtensionxxx(filename);

		/*** Then ***/
		// assertEquals("png", extension);
	}

	@Test
	public void getFileExtensionTest3() {
		/*** Given ***/
		String filename = "brown";

		/*** When ***/
		String extension = FileUtil.getFileExtension(filename);

		/*** Then ***/
		assertEquals("", extension);
	}

}

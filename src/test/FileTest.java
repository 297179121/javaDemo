package test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class FileTest {
	
	@Test
	public void testUnZip() {
		try {
			String pathSrcZip = "C:\\Users\\yhr\\Desktop\\tmp\\drs.zip";
			String pathDesZip = "D:\\mywork\\project\\eclipsespace\\hscr\\WebContent\\doc\\drs\\21130219871126085X\\2017-01-08";
			File fileSrcZip = new File(pathSrcZip);
			//File fileDesZip = new File(pathDesZip, "drs.zip");
			YFile.decompress( fileSrcZip, pathDesZip );
		} catch ( Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}


}

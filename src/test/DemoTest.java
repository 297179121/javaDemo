package test;

import java.io.File;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class DemoTest {

	@Ignore
	@Test
	public void testUnZip() {
		try {
			String pathSrcZip = "C:\\Users\\yhr\\Desktop\\tmp\\drs.zip";
			String pathDesZip = "D:\\mywork\\project\\eclipsespace\\hscr\\WebContent\\doc\\drs\\21130219871126085X\\2017-01-08";
			File fileSrcZip = new File(pathSrcZip);
			YFile.unZip( fileSrcZip, pathDesZip );
		} catch ( Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Ignore
	@Test
	public void testEncode(){
		File file = new File("D:\\mywork\\project\\eclipsespace\\javaDemo\\doc\\123.jpg");
		String str = YEncode.toBase64(file);
		System.out.println(str);
	}
	
	@Ignore
	@Test
	public void testDateAge(){
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String age = YDate.getAge( sf.parse("2016-01-01") );
			System.out.println(age);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testData(){
		try {
			String str = YData.format(300);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

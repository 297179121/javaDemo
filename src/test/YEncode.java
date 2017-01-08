package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class YEncode {
	
	public static String toBase64( File file ){
		byte[] array = new byte[]{};
		try {
			array = IOUtils.toByteArray( new FileInputStream(file) );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return new String(Base64.encodeBase64(array));
    }
	
}

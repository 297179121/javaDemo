package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;

public class YFile {
	public static void decompress(File zipFile, String folderPath){

		List<File> archiveContents = new ArrayList<File>();
		
		try {
			// create the input stream for the file, then the input stream for the actual zip file
			final InputStream is = new FileInputStream(zipFile);
			ArchiveInputStream ais = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.ZIP, is);
			
			// cycle through the entries in the zip archive and write them to the system temp dir
			ZipArchiveEntry entry = (ZipArchiveEntry) ais.getNextEntry();
			while (entry != null) {
			    File outputFile = new File(folderPath, entry.getName());   // don't do this anonymously, need it for the list
			    OutputStream os = new FileOutputStream(outputFile);
			
			    // copy from the archiveinputstream to the output stream
			    IOUtils.copy(ais, os);  
			    
			    // close the output stream
			    os.close();     
			
			    archiveContents.add(outputFile);
			
			    entry = (ZipArchiveEntry) ais.getNextEntry();
			}
			
			ais.close();
			is.close();
		} catch ( ArchiveException | IOException e ) {
			e.printStackTrace();
		}
	}
}

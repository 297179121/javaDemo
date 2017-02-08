package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileFilter;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbFilenameFilter;

/**
 * 访问远程共享文件夹中的文件
 * @author yhr
 *
 */
public class SmbDemo {
	
	private final static String SMB_FOLDER = "smb://drs-3332/shared";
	private final static String SMB_FILE = SMB_FOLDER + File.separator + "1_anonymous_20170208_100656_drs-3332_OS_0_66.jpg";

	/** 
     * @param args 
     * @throws IOException  
     */  
    public static void main(String[] args) throws IOException {
    	
        try {
			//smbGet1(SMB_FILE);  
			//smbGet(SMB_FILE,"D:/drs/");
			
			filter();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }  
  
    static void filter() throws Exception {
    	/*NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "", "");
    	SmbFile dir = new SmbFile(SMB_FOLDER, auth);*/
    	SmbFile folder = new SmbFile("smb://drs-3332/shared/");
    	SmbFile[] smbFiles = folder.listFiles(new SmbFilenameFilter() {
			public boolean accept(SmbFile dir, String name) throws SmbException {
				if( name.contains("100656") ){
					return true;
				}
				return false;
			}
		});
    	
    	for( SmbFile smbFile: smbFiles ){
    		System.out.println("start download file==="+smbFile.getName());
    		File localFile = new File("D:\\drs" + File.separator + smbFile.getName());
    		IOUtils.copy(new SmbFileInputStream(smbFile), new FileOutputStream(localFile));
    		System.out.println("download success");
    		smbFile.delete();
    	}

    }
    
    /** 
     * 方法一： 
     *  
     * @param remoteUrl 
     *            远程路径 smb://192.168.75.204/test/新建 文本文档.txt 
     * @throws IOException 
     */  
    public static void smbGet1(String remoteUrl) throws IOException {  
        SmbFile smbFile = new SmbFile(remoteUrl);  
        int length = smbFile.getContentLength();// 得到文件的大小  
        byte buffer[] = new byte[length];  
        SmbFileInputStream in = new SmbFileInputStream(smbFile);  
        // 建立smb文件输入流  
        while ((in.read(buffer)) != -1) {  
  
            System.out.write(buffer);  
            System.out.println(buffer.length);  
        }  
        in.close();  
    }  
  
    // 从共享目录下载文件  
    /** 
     * 方法二： 
     *    路径格式：smb://192.168.75.204/test/新建 文本文档.txt 
     *              smb://username:password@192.168.0.77/test 
     * @param remoteUrl 
     *            远程路径 
     * @param localDir 
     *            要写入的本地路径 
     */  
    public static void smbGet(String remoteUrl, String localDir) {  
        InputStream in = null;  
        OutputStream out = null;  
        try {  
            SmbFile remoteFile = new SmbFile(remoteUrl);
            if (remoteFile == null){
            	System.out.println("共享文件不存在");
            	return;
            }  
            String fileName = remoteFile.getName();  
            File localFile = new File(localDir + File.separator + fileName);  
            in = new BufferedInputStream(new SmbFileInputStream(remoteFile));  
            out = new BufferedOutputStream(new FileOutputStream(localFile));  
            byte[] buffer = new byte[1024];  
            while (in.read(buffer) != -1) {  
                out.write(buffer);  
                buffer = new byte[1024];  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                out.close();  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    // 向共享目录上传文件  
    public static void smbPut(String remoteUrl, String localFilePath) {  
        InputStream in = null;  
        OutputStream out = null;  
        try {  
            File localFile = new File(localFilePath);  
  
            String fileName = localFile.getName();  
            SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);  
            in = new BufferedInputStream(new FileInputStream(localFile));  
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));  
            byte[] buffer = new byte[1024];  
            while (in.read(buffer) != -1) {  
                out.write(buffer);  
                buffer = new byte[1024];  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                out.close();  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    // 远程url smb://192.168.0.77/test  
    // 如果需要用户名密码就这样：  
    // smb://username:password@192.168.0.77/test  

}

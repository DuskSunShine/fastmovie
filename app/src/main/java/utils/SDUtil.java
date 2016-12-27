package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.R.integer;
import android.R.string;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

/**
 *SD卡帮助类
 * @author runmu
 *
 */
public class SDUtil {
	
	/**
	 * 判断SD卡是否挂载
	 * @return
	 */
	public static boolean isMounted(){
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
	
	/**
	 *获取SD卡根路径
	 * @return
	 */
	public static String getSDCardRootPath(){
		if (isMounted()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		
		return null;
	}
	
	/**
	 * 向SD写数据
	 * @param data
	 * @param dir
	 * @param filename
	 * @return
	 * @throws IOException 
	 */
	public static boolean writeDataToSDCard(byte[] data,String dir,String filename) throws IOException{
		if (isMounted()) {
			String path = getSDCardRootPath() + File.separator + dir;
			File directory = new File(path);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			
			//ʹ��IO��������д��SD�����ļ�
			BufferedOutputStream buffOs = null;
			try{
				buffOs = new BufferedOutputStream(new FileOutputStream(new File(directory,filename)));
				
				buffOs.write(data,0,data.length);//������"С"�ļ�д��,"��"�ļ���ȻҪʹ��ѭ��д���ֽ�������
				buffOs.flush();
				
				return true;
			}
			finally{
				if (null != buffOs) {
					buffOs.close();
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 从SD卡中读取数据
	 * @param dir
	 * @param filename
	 * @return
	 * @throws IOException 
	 */
	public static byte[] readDataFromSDCard(String dir,String filename) throws IOException{
		if (isMounted()) {
			
			//��ô洢�ļ���Ŀ¼
			String path = getSDCardRootPath() + File.separator + dir;
			File file = new File(path, filename);
			
			if(file.exists() && file.isFile()){
				BufferedInputStream buffIs = null;
				ByteArrayOutputStream bos = null;
				try{
					bos = new ByteArrayOutputStream();
					buffIs = new BufferedInputStream(new FileInputStream(file));
					
					byte[] buff = new byte[1024*8];
					int readLength = 0;
					while((readLength=buffIs.read(buff)) != -1){
						bos.write(buff,0,readLength);
						bos.flush();
					}
					
					return bos.toByteArray();
				}
				finally{
					if (null != bos) {
						bos.close();
					}
					if(null != buffIs){
						buffIs.close();
					}
				}
			}
			
		}
		
		return null;
	}
	
	/**
	 * 获取SD卡的内存
	 */
	public static long getSDCardSize(){
		long num=0;
		long kongJian=0;
		if (isMounted()) {
			StatFs statFs = new StatFs(getSDCardRootPath());
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
				num=statFs.getAvailableBlocksLong();
				kongJian=statFs.getBlockSizeLong();
			}
		}
		return num*kongJian;
	}

	
}





















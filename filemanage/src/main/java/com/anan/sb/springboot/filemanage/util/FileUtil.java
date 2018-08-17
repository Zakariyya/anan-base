package com.urundp.corona.file.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DecimalFormat;

/**
 * @author yaokunyi
 * Created on 2018/8/15.
 */
public class FileUtil {


  /**
   * 上传文件到服务器
   * @param mulFile：文件流
   * @param uploadPath：上传路径
   * @return
   */
  public static File uploadFile(MultipartFile mulFile, String uploadPath) {

    String fileName = mulFile.getOriginalFilename();// 获取文件名（包括后缀名）
    String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀名
    String infrontName = fileName.substring(0,fileName.lastIndexOf("."));//获取文件的文件名

    String timestamp = new Timestamp(System.currentTimeMillis()).getTime()+"";//当前时间戳
    String filePath = uploadPath + infrontName + "-" + timestamp + suffixName;// 给名字添加时间戳

    /**
     * 文件存储
     */
    File target = new File(filePath);// 创建一个新文件
    if (!target.getParentFile().exists()) {// 检测是否存在目录
      target.getParentFile().mkdirs();// 新建文件夹
    }
    try {
      mulFile.transferTo(target);// 把file文件写入dest
    } catch (IOException e) {
      e.printStackTrace();
    }
    return target;
  }


  /**
   * 获取文件的 MD5 值
   * @param file
   * @return
   * @throws FileNotFoundException
   */
  public static String getMd5ByFile(File file) throws FileNotFoundException {
    String value = null;
    FileInputStream in = new FileInputStream(file);
    try {
      MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(byteBuffer);
      BigInteger bi = new BigInteger(1, md5.digest());
      value = bi.toString(16);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if(null != in) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return value;
  }


  /**
   * 获取文件大小（带单位）
   * @param filesize
   * @return
   */
  public  static String getFileLength(long filesize){

    String strFileSize;
    if(filesize < 1024){
      strFileSize = filesize+"B";
      return strFileSize;
    }

    DecimalFormat df = new DecimalFormat("######0.00");

    if ((filesize >= 1024) && (filesize < 1024*1024)){//KB
      strFileSize = df.format(((double)filesize)/1024)+"KB";
    }else if((filesize >= 1024*1024)&&(filesize < 1024*1024*1024)){//MB
      strFileSize = df.format(((double)filesize)/(1024*1024))+"MB";
    }else{//GB
      strFileSize = df.format(((double)filesize)/(1024*1024*1024))+"GB";
    }
    return strFileSize;
  }







}

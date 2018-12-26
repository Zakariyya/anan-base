package com.anan.sb.springboot.filemanage.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anan
 * Created on 2018/8/20.
 */
public class FtpUtil {


  /****************************************************
   *******                                     ********
   *******    upload / download by FtpServer   ********
   *******                                     ********
   ****************************************************/

  /***
   * connect the ftp server
   * @param url https://localhost
   * @param port port
   * @param username account name of ftp
   * @param password account password of ftp
   * @return
   */
  public static FtpClient connectFTP(String url, int port, String username, String password) {
    //创建ftp
    FtpClient ftp = null;
    try {
      //创建地址
      SocketAddress addr = new InetSocketAddress(url, port);
      //连接
      ftp = FtpClient.create();
      ftp.connect(addr);
      //登陆
      ftp.login(username, password.toCharArray());
      ftp.setBinaryType();
    } catch (FtpProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ftp;
  }

  /**
   * download the file content in the List by FTP server
   * need foreach the List for read or write
   * @param ftpFilePath need to download the file's path in the ftp server
   * @param ftp a Object by connect ftp, need run connectFTP() method
   * @return
   */
  public static List<String> download(String ftpFilePath, FtpClient ftp) {
    List<String> list = new ArrayList<String>();
    String str = "";
    InputStream is = null;
    BufferedReader br = null;
    try {
      // 获取ftp上的文件
      is = ftp.getFileStream(ftpFilePath);
      //转为字节流
      br = new BufferedReader(new InputStreamReader(is,"Utf-8"));
      while((str=br.readLine())!=null){
        list.add(str);
      }
      br.close();
    }catch (FtpProtocolException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }



  /**
   * upload file to FTP server
   * @Version      1.0
   * @param url FTP  hostname
   * @param port  FTP  port
   * @param username FTP account name
   * @param password  FTP account password
   * @param path  FTP save path
   * @param filename  the file name with upload to FTP
   * @param input   inputstream
   * @return success return true，other return false *
   */
  public static boolean uploadFile(String url,// FTP服务器hostname
                                   Integer port,// FTP服务器端口
                                   String username, // FTP登录账号
                                   String password, // FTP登录密码
                                   String path, // FTP服务器保存目录
                                   String filename, // 上传到FTP服务器上的文件名
                                   InputStream input // 输入流
  ){
    boolean success = false;
    FTPClient ftp = new FTPClient();
    ftp.setControlEncoding("UTF-8");
    try {
      int reply;
      ftp.connect(url, port);// 连接FTP服务器
      // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
      ftp.login(username, password);// 登录
      reply = ftp.getReplyCode();
      if (!FTPReply.isPositiveCompletion(reply)) {
        ftp.disconnect();
        return success;
      }
      ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
      ftp.makeDirectory(path);
      ftp.changeWorkingDirectory(path);
      ftp.storeFile(filename, input);
      input.close();
      ftp.logout();
      success = true;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (ftp.isConnected()) {
        try {
          ftp.disconnect();
        } catch (IOException ioe) {
        }
      }
    }
    return success;
  }

  /**
   * upload local file to ftp *
   */
  public static void upLoadFromProduction(String url,// FTP服务器hostname
                                          Integer port,// FTP服务器端口
                                          String username, // FTP登录账号
                                          String password, // FTP登录密码
                                          String path, // FTP服务器保存目录
                                          String filename, // 上传到FTP服务器上的文件名
                                          String orginfilename // 输入流文件名
  ) {
    try {
      FileInputStream in = new FileInputStream(new File(orginfilename));
      boolean flag = uploadFile(url, port, username, password, path,filename, in);
      System.out.println(flag);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }





}

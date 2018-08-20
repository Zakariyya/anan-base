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
 * @author yaokunyi
 * Created on 2018/8/20.
 */
public class FtpUtil {


  /****************************************************
   *******                                     ********
   *******    upload / download by FtpServer   ********
   *******                                     ********
   ****************************************************/

  /***
   * 连接ftp
   * @param url  必须是  192.168.8.1  否则提示异常
   * @param port
   * @param username
   * @param password
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
   * 从 FTP 下载文件内容，以list存储，需自行遍历list，读取或做文件存储
   * @param ftpFilePath
   * @param ftp
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
   * Description: 向FTP服务器上传文件
   * @Version      1.0
   * @param url FTP服务器hostname
   * @param port  FTP服务器端口
   * @param username FTP登录账号
   * @param password  FTP登录密码
   * @param path  FTP服务器保存目录
   * @param filename  上传到FTP服务器上的文件名
   * @param input   输入流
   * @return 成功返回true，否则返回false *
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
   * 将本地文件上传到FTP服务器上 *
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

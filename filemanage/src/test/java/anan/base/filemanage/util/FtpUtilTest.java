package anan.base.filemanage.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.net.ftp.FtpClient;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@Slf4j
public class FtpUtilTest {

  /**
   * FTP 的信息
   */
  private String url="127.0.0.1";  // FTP服务器host or domain
  private Integer port=21;  // FTP服务器端口

  /*
   * 注意：这里记得赋予该FTP账号的 读写，创建文件夹 权限
   */
  private String username="uftp";  // FTP登录账号
  private String password="111";  // FTP登录密码

  /*
    FTP服务器保存目录  "/"为根路径，FTP上按照linux存，
     windows也可以设置为"/"，假设win的默认根路径是-->d:,文件储存后的全路径是-->d:/aaa/xxx.txt
     设 linux默认路径是-->/ ,文件存储后的全路径是--> /etc/xxx.txt
   */
  private String path="/aaa";

  private String filename="aa.md";  // 上传到FTP服务器上的文件名
  private String orginfilename="d:/源文件.md";  // 输入流  需要上传的文件名（全路径）


  /****************************************************
   *******                                     ********
   *******    upload / download by FtpServer   ********
   *******                                     ********
   ****************************************************/


  @Test
  public void connectFTP() {
    FtpClient ftpClient = FtpUtil.connectFTP(url, port, username, password);
    log.info("连接FTP，并获取其对象::"+ftpClient);
  }

  /**
   * 上传文件到 FTP 上
   */
  @Test
  public void upLoadFromProduction() {
    FtpUtil.upLoadFromProduction(url, port, username, password, path, filename, orginfilename);
  }


  @Test
  public void download() {
    FtpClient ftp = FtpUtil.connectFTP(url, port, username, password);
    String ftpFilePath = "aa.md";
    List<String> list = FtpUtil.download(ftpFilePath, ftp);

    for(int i=0;i<list.size();i++){
      log.info("list ==="+ i + " === :"+list.get(i));
    }
    try {
      ftp.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
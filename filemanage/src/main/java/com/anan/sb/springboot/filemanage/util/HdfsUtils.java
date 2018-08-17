package com.urundp.corona.file.util;

import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.web.WebHdfsFileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HdfsUtils {

  private static String hdfsUrl =null ;
  private static String hdfsDir =null ;
  private static String hadoopUserName =null ;

  static Configuration conf;

  @Value("${hdfs.url}")
  public void setHdfsUrl(String hdfsUrl) {
    HdfsUtils.hdfsUrl = hdfsUrl;
  }
  @Value("${hdfs.dir}")
  public void setHdfsDir(String hdfsDir) {
    HdfsUtils.hdfsDir = hdfsDir;
  }
  @Value("${hdfs.user-name}")
  public void setHadoopUserName(String hadoopUserName) {
    HdfsUtils.hadoopUserName = hadoopUserName;
  }



  /**
   * 在application启动的时候启动
   */
  public static synchronized Configuration getConf() {
    if (conf == null) {
      conf = new Configuration();
      System.setProperty("HADOOP_USER_NAME", hadoopUserName); //hadoop用户才有hdfs操作权限
    }

    return conf;
  }


  /**
   * 上传文件
   * @param conf 读取集群配置
   * @param localPath
   * @throws Exception
   * uploadFile(conf, "E:\\development\\apache-maven-3.5.0-bin.zip");
   */
  public static void uploadFile(Configuration conf, String localPath) throws Exception {
    WebHdfsFileSystem webHdfsFileSystem = new WebHdfsFileSystem();
    webHdfsFileSystem.initialize(new URI(hdfsUrl), conf);
    webHdfsFileSystem.copyFromLocalFile(new Path(localPath), new Path(hdfsUrl + hdfsDir));
    webHdfsFileSystem.close();

  }


  /**
   * 下载文件
   * @param conf 读取集群配置
   * @param hdfsFilePath HDFS路径
   * @param localPath 本地下载存放的目录/路径
   * @throws Exception
   * downloadFile(conf, hdfsUrl + hdfsDir + "/apache-maven-3.5.0-bin.zip", "D:/file");
   */
  public static void downloadFile(Configuration conf, String hdfsFilePath, String localPath) throws Exception {
    WebHdfsFileSystem webHdfsFileSystem = new WebHdfsFileSystem();
    webHdfsFileSystem.initialize(new URI(hdfsUrl), conf);
    webHdfsFileSystem.copyToLocalFile(new Path(hdfsFilePath), new Path(localPath));
    webHdfsFileSystem.close();
  }


  /**
   * 删除文件
   * @param conf
   * @param hdfsFilePath
   * @throws Exception
   * deleteFile(conf, hdfsUrl + hdfsDir + "/8.txt");
   */
  /*public static void deleteFile(Configuration conf, String hdfsFilePath) throws Exception {
      WebHdfsFileSystem webHdfsFileSystem = new WebHdfsFileSystem();
      webHdfsFileSystem.initialize(new URI(hdfsUrl), conf);
      webHdfsFileSystem.delete(new Path(hdfsFilePath), false);
  }*/

  /**
   * 检查
   * @param conf 读取集群配置
   * @param hdfsFilePath HDFS路径
   * @return true/false
   * @throws Exception
   */
  public static boolean checkFile(Configuration conf, String hdfsFilePath) throws Exception {
    WebHdfsFileSystem webHdfsFileSystem = new WebHdfsFileSystem();
    webHdfsFileSystem.initialize(new URI(hdfsUrl), conf);
    return webHdfsFileSystem.exists(new Path(hdfsFilePath));
  }

  //  public static void main(String[] args) {
  //    try {
  //      downloadFile(getConf(), hdfsUrl + hdfsDir + "/apache-maven-3.5.0-bin.zip", "D:/file");
  ////    System.out.println(checkFile(conf, hdfsUrl + hdfsDir + "/7.txt"));
  //    } catch (Exception e) {
  //    }
  //  }


}
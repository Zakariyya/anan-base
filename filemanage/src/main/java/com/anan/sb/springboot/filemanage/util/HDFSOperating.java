package com.urundp.corona.file.util;

/**
 * @author yaokunyi
 * Created on 2018/8/16.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class HDFSOperating {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * @param webhdfs
   * @param stream       the InputStream of file to upload
   * @param hdfsFilePath
   * @param op
   * @param parameters
   * @param method
   * @throws IOException
   */
  public void uploadFile(String webhdfs, InputStream stream, String hdfsFilePath, String op, Map<String, String> parameters, String method) throws IOException {
    HttpURLConnection con;
    try {
      con = getConnection(webhdfs, hdfsFilePath, op, parameters, method);

      byte[] bytes = new byte[1024];
      int rc = 0;
      while ((rc = stream.read(bytes, 0, bytes.length)) > 0)
        con.getOutputStream().write(bytes, 0, rc);
      con.getInputStream();
      con.disconnect();
    } catch (IOException e) {
      logger.info(e.getMessage());
      e.printStackTrace();
    }
    stream.close();
  }




  /**
   * @param strurl     webhdfs like http://ip:port/webhdfs/v1 ,port usually 50070 or 14000
   * @param path       hdfs path + hdfs filename  eg:/user/razor/readme.txt
   * @param op         the operation for hdfsFile eg:GETFILESTATUS,OPEN,MKDIRS,CREATE  etc.
   * @param parameters other parameter if you need
   * @param method     method eg: GET POST PUT etc.
   * @return
   */
  public HttpURLConnection getConnection(String strurl, String path, String op, Map<String, String> parameters, String method) {
    URL url = null;
    HttpURLConnection con = null;
    StringBuffer sb = new StringBuffer();
    try {
      sb.append(strurl);
      sb.append(path);
      sb.append("?op=");
      sb.append(op);
      if (parameters != null) {
        for (String key : parameters.keySet())
          sb.append("&").append(key + "=" + parameters.get(key));
      }
      url = new URL(sb.toString());
      con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod(method);
      con.setRequestProperty("accept", "*/*");
      con.setRequestProperty("connection", "Keep-Alive");
      String s = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)";
      String s1 = "ozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
      con.setRequestProperty("User-Agent", s1);
//            con.setRequestProperty("Accept-Encoding", "gzip");
//            con.setDoInput(true);
      con.setDoOutput(true);
      con.setUseCaches(false);
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return con;
  }
}

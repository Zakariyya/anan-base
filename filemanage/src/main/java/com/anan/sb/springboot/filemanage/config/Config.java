package com.urundp.corona.file.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
@Data
public class Config {
    
	@Value("${config.upload-path}")
	protected  String uploadPath;

	@Value("${hdfs.url}")
	protected  String hdfsUrl;

	@Value("${hdfs.dir}")
	protected  String hdfsDir;

	@Value("${hdfs.user-name}")
	protected  String hdfsUserName;








}

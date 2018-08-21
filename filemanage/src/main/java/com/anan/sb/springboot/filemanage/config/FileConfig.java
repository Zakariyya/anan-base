package com.anan.sb.springboot.filemanage.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * get from the yml
 */
@Configuration
@Data
public class FileConfig {

	@Value("${fileConfig.uploadPath}")
	protected  String uploadPath;


}

package com.anan.springboot.content.orm;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author yaokunyi
 * Created on 2018/8/23.
 */
@Entity
@Data
@DynamicUpdate
public class Content implements Serializable {

  private static final long serialVersionUID = -7930494651140764731L;

  private String id;

  private Category category;


















}

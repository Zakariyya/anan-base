package com.anan.springboot.core.form;

import lombok.Data;

/**
 * @author anan
 * for Form ,<T> is the parentId's and id's type
 * Created on 2018/8/24.
 */
@Data
public class CoreForm<T>{

  private T id;

  private T parentId;


}

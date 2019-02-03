package anan.base.core.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Converter Base
 * can extends it and get the class A converter to class ADto
 * T : data
 * E : dto
 *
 * eg:
 *    DemoConverter extends CoreConverter<A,ADto>{
 *
 *      @Override
 *      public ADto set(A data, ADto dto){
 *        // dto.setXXX(A.getXXX());
 *        // dto.setXXXXXX(A.getXXXXXX());

 *        return dto;
 *      }
 *    }
 *
 *    Main{
 *      DemoConverter demo;
 *      ADto dto = demo.convert(A ,ADto.class);
 *
 *      List<A> listA;
 *      List<ADto> listADto = demo.convert(listA ,ADto.class);
 *    }
 *
 * @author anan
 * @created 2019/1/31 13:58
 */

public abstract class CoreConverter<T, E> {


  /**
   *  @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
   *  p2p = this;
   *  p2p.service = this.service;
   *  // 初使化时将已静态化的testService实例化
   */
  public abstract void  init() ;



  E createContents(Class<?> clazz) throws IllegalAccessException, InstantiationException {
    return (E) clazz.newInstance();
  }


  public E convert(T data, Class<?> clazz) throws InstantiationException, IllegalAccessException {
    E dto = this.createContents(clazz);
    return set(data, dto);
  }


  public List<E> convert(List<T> all, Class<?> clazz) throws IllegalAccessException, InstantiationException {
    List<E> pdto = new ArrayList(Collections.nCopies(all.size(), clazz));
    int index = 0;
    for (T item : all) {
      pdto.set(index, convert(item, clazz));
      index++;
    }
    return pdto;
  }


  public abstract E set(T data, E dto);

}

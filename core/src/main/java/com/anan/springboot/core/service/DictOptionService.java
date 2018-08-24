package com.anan.springboot.core.service;

/**
 * @author yaokunyi
 * Created on 2018/8/24.
 */

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;


/**
 * 字典表 service 类
 *
 * @author zhying
 *
 */
@Service
public class DictOptionService{

  /**
   * 查找某一种类型的字典列表
   *
   */
  @SuppressWarnings({ "rawtypes" })
  public <T> List listByType(Class<T> clazz) {
    return this.createCriteria(clazz).addOrder(Order.asc("showOrder")).list();
  }

  @SuppressWarnings("unchecked")
  public <T> T findByType(Class<T> clazz, String id) {
    return (T) this.createCriteria(clazz).add(Restrictions.eq("id", id)).uniqueResult();
  }

  @SuppressWarnings("unchecked")
  public <T> T findByTypeName(Class<T> clazz, String name) {
    return (T) this.createCriteria(clazz).add(Restrictions.eq("name", name)).uniqueResult();
  }

  @SuppressWarnings("unchecked")
  public <T> T findByType(Class<T> clazz, int id) {
    return (T) this.createCriteria(clazz).add(Restrictions.eq("id", id)).uniqueResult();
  }

  @SuppressWarnings("unchecked")
  public <T> T findByType(Class<T> clazz, char id) {
    return (T) this.createCriteria(clazz).add(Restrictions.eq("id", id)).uniqueResult();
  }

  /**
   * @param clazz
   * @return
   */
  @SuppressWarnings("unchecked")
  public <T> List listByTypeForRelation(Class<T> clazz) {
    List<T> list = this.listByType(clazz);
    ArrayList jsonArray = new ArrayList();
    for (T dict : list) {
      if (dict == null) {
        continue;
      }
      Map<String,Object> jsonObject;
      try {
        jsonObject = (Map<String, Object>) clazz.getMethod("toJSONObject").invoke(dict);
        jsonArray.add(jsonObject);
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (SecurityException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return jsonArray;
  }

  @SuppressWarnings("unchecked")
  public <T> ArrayList listByTypeForRelation(Class<T> clazz, Object b) {
    List<T> list = this.listByType(clazz);
    ArrayList jsonArray = new ArrayList();
    for (T dict : list) {
      if (dict == null) {
        continue;
      }
      Map<String,Object> jsonObject;
      try {
        jsonObject = (Map<String, Object>) clazz.getMethod("toJSONObject", b.getClass()).invoke(dict, b);
        if (jsonObject != null) {
          jsonArray.add(jsonObject);
        }
      } catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (SecurityException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return jsonArray;
  }

  protected Criteria createCriteria(Class<?> entityClass) {
    return dao.createCriteria(entityClass);
  }

  protected Criteria createCriteria(Class<?> entityClass, String alias) {
    return dao.createCriteria(entityClass, alias);
  }



}

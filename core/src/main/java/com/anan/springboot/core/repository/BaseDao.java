package com.anan.springboot.core.repository;


import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Base Dao
 * @author yaokunyi
 * Created on 2018/8/24.
 */

@Component
public class BaseDao {

  @PersistenceContext
  private EntityManager entityManager;

  public Session getSession() {
    return entityManager.unwrap(Session.class);
  }

  @SuppressWarnings("unchecked")
  public <T> T get(Class<T> entityClass, Serializable id) {
    return (T) getSession().get(entityClass, id);
  }

  public void save(Object entity) {
    getSession().save(entity);
  }

  public void saveAll(Collection<?> entities) {
    Session session = this.getSession();
    for (Object e : entities) {
      session.save(e);
    }
  }

  public void updateAll(Collection<?> entities) {
    Session session = this.getSession();
    for (Object e : entities) {
      session.update(e);
    }
  }

  public void saveOrUpdateAll(Collection<?> entities) {
    Session session = this.getSession();
    for (Object e : entities) {
      session.saveOrUpdate(e);
    }
  }

  public void update(Object entity) {
    getSession().update(entity);
  }

  public void saveOrUpdate(Object entity) {
    getSession().saveOrUpdate(entity);
  }

  public void delete(Object eitity) {
    getSession().delete(eitity);
  }

  public Criteria createCriteria(Class<?> clazz) {
    try {
      return getSession().createCriteria(clazz);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public Criteria createCriteria(Class<?> clazz, String ailias) {
    try {
      return getSession().createCriteria(clazz, ailias);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public SQLQuery createSQLQuery(String sql) {
    return getSession().createSQLQuery(sql);
  }

  public void flushSession() {
    this.getSession().flush();
  }

}

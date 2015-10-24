package com.zealot.orm.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zealot.common.MessageCode;
import com.zealot.exception.AppException;
import com.zealot.orm.dao.BaseDAO;
import com.zealot.orm.model.Pagination;
import com.zealot.orm.util.Finder;


@Repository("baseDAO")
@SuppressWarnings("all")
public class BaseDAOImpl<T> implements BaseDAO<T>
{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public Serializable save(T o) throws AppException
    {
        try
        {
            return this.getCurrentSession().save(o);
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public void delete(T o) throws AppException
    {
        try
        {
            this.getCurrentSession().delete(o);
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public void update(T o) throws AppException
    {
        try
        {
            this.getCurrentSession().update(o);
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public void saveOrUpdate(T o) throws AppException
    {
        try
        {
            this.getCurrentSession().saveOrUpdate(o);
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public List<T> find(String hql) throws AppException
    {
        try
        {
            return this.getCurrentSession().createQuery(hql).list();
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public List<T> find(String hql, Object[] param) throws AppException
    {
        Query q;
        try
        {
            q = this.getCurrentSession().createQuery(hql);
            if (param != null && param.length > 0)
            {
                for (int i = 0; i < param.length; i++)
                {
                    q.setParameter(i,param[i]);
                }
            }
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
        return q.list();
    }

    public List<T> find(String hql, List<Object> param) throws AppException
    {
        Query q;
        try
        {
            q = this.getCurrentSession().createQuery(hql);
            if (param != null && param.size() > 0)
            {
                for (int i = 0; i < param.size(); i++)
                {
                    q.setParameter(i,param.get(i));
                }
            }
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
        return q.list();
    }

    public List<T> find(String hql, Object[] param, Integer page, Integer rows) 
            throws AppException
    {
        if (page == null || page < 1)
        {
            page = 1;
        }
        if (rows == null || rows < 1)
        {
            rows = 10;
        }
        try
        {
            Query q = this.getCurrentSession().createQuery(hql);
            if (param != null && param.length > 0)
            {
                for (int i = 0; i < param.length; i++)
                {
                    q.setParameter(i,param[i]);
                }
            }
            return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public List<T> find(String hql, List<Object> param, Integer page, Integer rows) 
            throws AppException
    {
        if (page == null || page < 1)
        {
            page = 1;
        }
        if (rows == null || rows < 1)
        {
            rows = 10;
        }
        try
        {
            Query q = this.getCurrentSession().createQuery(hql);
            if (param != null && param.size() > 0)
            {
                for (int i = 0; i < param.size(); i++)
                {
                    q.setParameter(i,param.get(i));
                }
            }
            return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public T get(Class<T> c, Serializable id) throws AppException
    {
        try
        {
            return (T) this.getCurrentSession().get(c,id);
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public T get(String hql, Object[] param) throws AppException
    {
        try
        {
            List<T> l = this.find(hql,param);
            if (l != null && l.size() > 0)
            {
                return l.get(0);
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public T get(String hql, List<Object> param) throws AppException
    {
        try
        {
            List<T> l = this.find(hql,param);
            if (l != null && l.size() > 0)
            {
                return l.get(0);
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public Long count(String hql) throws AppException
    {
        try
        {
            return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public Long count(String hql, Object[] param) throws AppException
    {
        try
        {
            Query q = this.getCurrentSession().createQuery(hql);
            if (param != null && param.length > 0)
            {
                for (int i = 0; i < param.length; i++)
                {
                    q.setParameter(i,param[i]);
                }
            }
            return (Long) q.uniqueResult();
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public Long count(String hql, List<Object> param) throws AppException
    {
        try
        {
            Query q = this.getCurrentSession().createQuery(hql);
            if (param != null && param.size() > 0)
            {
                for (int i = 0; i < param.size(); i++)
                {
                    q.setParameter(i,param.get(i));
                }
            }
            return (Long) q.uniqueResult();
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public Integer executeHql(String hql) throws AppException
    {
        try
        {
            return this.getCurrentSession().createQuery(hql).executeUpdate();
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public Integer executeHql(String hql, Object[] param) throws AppException
    {
        try
        {
            Query q = this.getCurrentSession().createQuery(hql);
            if (param != null && param.length > 0)
            {
                for (int i = 0; i < param.length; i++)
                {
                    q.setParameter(i,param[i]);
                }
            }
            return q.executeUpdate();
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public Integer executeHql(String hql, List<Object> param) throws AppException
    {
        try
        {
            Query q = this.getCurrentSession().createQuery(hql);
            if (param != null && param.size() > 0)
            {
                for (int i = 0; i < param.size(); i++)
                {
                    q.setParameter(i,param.get(i));
                }
            }
            return q.executeUpdate();
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
    }

    public Pagination<T> queryPage(Finder finder, int pageNo, int pageSize) 
            throws AppException
    {
        Pagination p;
        try
        {
            //获得总行数
            int totalCount = getTotalCount(finder,null);
            if (pageSize == -1)
                pageSize = totalCount;
            p = new Pagination(pageNo, pageSize, totalCount);
            if (totalCount < 1 || ((pageNo <= 0 && pageNo != -1) || pageSize <= 0))
            {
                p.setList(new ArrayList());
                return p;
            }
            //查询分页数据
            Query query = this.getCurrentSession().createQuery(finder.getOrigHql());
            finder.setParamsToQuery(query);
            if (pageNo != -1)
            {
                query.setFirstResult(p.getFirstResult());
                query.setMaxResults(p.getPageSize());
            }
            List<T> list = query.list();
            p.setList(list);
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }
        return p;
    }

    /**
     * 通过count查询获得本次查询所能获得的对象总数.
     * 
     * @param finder
     * @return
     */
    private int getTotalCount(Finder finder, Map param) throws AppException
    {
        int returnInt = 0;
        try
        {
            Query query = this.getCurrentSession().createQuery(finder.getRowCountHql());
            if (param != null && !param.isEmpty())
            {
                setFilterToQuery(query,param);
            }
            finder.setParamsToQuery(query);

            Iterator it = query.iterate();
            if (it.hasNext())
            {
                returnInt = ((Number) it.next()).intValue();
            }
            else
            {
                returnInt = 0;
            }
        }
        catch (Exception e)
        {
            throw new AppException(MessageCode.SERVER_EXCUTE_ERROR,e);
        }

        return returnInt;
    }

    private static void setFilterToQuery(Query query, Map filterMap)
    {
        if (filterMap == null)
            return;

        Iterator it = filterMap.keySet().iterator();
        while (it.hasNext())
        {
            Object key = it.next();
            query.setParameter(key.toString(),filterMap.get(key));
        }

    }
}

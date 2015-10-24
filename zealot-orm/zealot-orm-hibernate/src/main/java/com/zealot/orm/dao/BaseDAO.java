package com.zealot.orm.dao;

import java.io.Serializable;
import java.util.List;

import com.zealot.exception.AppException;
import com.zealot.orm.model.Pagination;
import com.zealot.orm.util.Finder;

/**
 * 基础数据库操作类
 * 
 * 
 */
public interface BaseDAO<T> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o) throws AppException;

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(T o) throws AppException;

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(T o) throws AppException;

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o) throws AppException;

	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql) throws AppException;

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param) throws AppException;

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param) throws AppException;

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            查询第几页
	 * @param rows
	 *            每页显示几条记录
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows) throws AppException;

	/**
	 * 查询集合(带分页)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, Integer page, Integer rows) throws AppException;

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id) throws AppException;

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param) throws AppException;

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param) throws AppException;

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql) throws AppException;

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param) throws AppException;

	/**
	 * select count(*) from 类
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param) throws AppException;

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return 响应数目
	 */
	public Integer executeHql(String hql) throws AppException;

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return 响应数目
	 */
	public Integer executeHql(String hql, Object[] param) throws AppException;

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param) throws AppException;

	/**
	 * 分页查询
	 * @param finder
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @创建时间 2015-3-5 下午4:11:11
	 */
	public Pagination<T> queryPage(Finder finder, int pageNo, int pageSize) throws AppException;
	
}

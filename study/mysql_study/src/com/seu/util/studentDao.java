package com.seu.util;

import java.util.ArrayList;

public interface studentDao {
	/**
	 * 查看
	 * @author dnhua
	 */
	ArrayList<student> query();
	
	/**
	 * 插入
	 */
	boolean insert(student s);
	
	/**
	 * 删除，通过id
	 */
	boolean delete(int id);
	
	/**
	 * 删除，通过student
	 */
	boolean delete(student s);
	
	/**
	 * 更新
	 */
	boolean update(student s, int id);
}

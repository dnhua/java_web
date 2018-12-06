package com.seu.util;

import java.util.ArrayList;
import java.util.Vector;

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
	student delete(int id);
	
	/**
	 * 删除，通过student
	 */
	student delete(student s);
	
	/**
	 * 更新
	 */
	boolean update(student s);
}

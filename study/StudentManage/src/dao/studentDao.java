package dao;

import java.util.ArrayList;

import com.seu.util.student;

public interface studentDao {
	/**
	 * 查询出来所有的学生信息
	 * @return List集合
	 */
	ArrayList<student> findAll();
}

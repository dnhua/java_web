package com.seu.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;


import java.sql.Connection;
import java.sql.Statement;

public class studentDaoImpl implements studentDao {

	
	
	@Override
	public ArrayList<student> query() {
		ArrayList<student> students = new ArrayList<student>();
		Connection conn = null;
	    Statement st = null;
	    ResultSet rs = null;
	    try {
	        //1. 连接
	        conn =  JDBCUtil.getConn();
	        //2. statement
	        st = conn.createStatement();
	        String sql = "select * from t_stu";
	        rs = st.executeQuery(sql);
	        
	        while(rs.next()){
	        	student stu = new student();	//要在循环里新建对象，不然每次引用的都是同一个值
	            stu.setid(rs.getInt("id"));
	            stu.setage(rs.getInt("age"));
	            stu.setname(rs.getString("name"));
	            students.add(stu);
	        }
	        System.out.println(students);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	        JDBCUtil.release(conn, st, rs);
	    }
		return students;
	}

	@Override
	public boolean insert(student s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public student delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public student delete(student s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(student s) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		studentDaoImpl sData = new studentDaoImpl();
		sData.query();
	}

}

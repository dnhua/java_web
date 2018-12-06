package com.seu.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class studentDaoImpl implements studentDao {
	private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
	
	@Override
	public ArrayList<student> query() {
		ArrayList<student> students = new ArrayList<student>();
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
		PreparedStatement ps = null;
		try {
            conn =  JDBCUtil.getConn();
            String sql = "insert into t_stu values(?,?,?)";
			ps = conn.prepareStatement(sql);
			
			if(s.getid()==-1) {
				ps.setString(1, null);
			}else {
				ps.setInt(1, s.getid());
			}
			ps.setString(2,s.getname());
			ps.setInt(3, s.getage());
			
			int result = ps.executeUpdate();
			
			if(result > 0) {
				System.out.println("添加成功");
				return true;
			}else {
				System.out.println("添加失败");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		PreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "delete from t_stu where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			if(result > 0) {
				System.out.println("删除成功");
				return true;
			}else {
				System.out.println("删除失败");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
		}
		
		return false;
	}

	@Override
	public boolean delete(student s) {
		PreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "delete from t_stu where id=? and name=? and age=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s.getid());
			ps.setString(2, s.getname());
			ps.setInt(3, s.getage());
			int result = ps.executeUpdate();
			if(result > 0) {
				System.out.println("删除成功");
				return true;
			}else {
				System.out.println("删除失败");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
		}
		
		return false;
	}

	@Override
	public boolean update(student s, int id) {
		PreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "update t_stu set name=?,age=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, s.getname());
			ps.setInt(2, s.getage());
			ps.setInt(3, id);
			int result = ps.executeUpdate();
			if(result > 0) {
				System.out.println("更新成功");
				return true;
			}else {
				System.out.println("更新失败");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps);
		}
		
		return false;
	}

	public static void main(String[] args) {
		studentDaoImpl sData = new studentDaoImpl();
		sData.query();
//		sData.insert(new student(-1,"dnhua",24));
//		sData.query();
//		sData.delete(new student(10,"dnhua",24));
//		sData.query();
		sData.update(new student(10,"dnhua",55), 11);
		sData.query();
	}

}

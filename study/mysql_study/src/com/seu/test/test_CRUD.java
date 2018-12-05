package com.seu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test_CRUD {

	public static void main(String[] args) {
		Connection conn;
		Statement st;
		ResultSet rs = null;
		try {
			//step1 ע������
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//step2 ��������, url�����ݿ�����, ����2��user, ����3������
			conn = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "root");
			//step3 ����statement
			st = conn.createStatement();
			//step4 ִ��sql, �õ������
			String sql = "select * from t_stu";		//sql ���
			rs = st.executeQuery(sql);
			//����  ������������
			while(rs.next()){
	   			int id = rs.getInt("id");
	   			String name = rs.getString("name");
	   			int age = rs.getInt("age");
	   			System.out.println("id="+id + "===name="+name+"==age="+age);
	   				
	   		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	//���һ����Ҫ�����ͷ�rs
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}

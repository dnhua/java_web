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
			//step1 注册驱动
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//step2 建立连接, url是数据库链接, 参数2是user, 参数3是密码
			conn = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "root");
			//step3 创建statement
			st = conn.createStatement();
			//step4 执行sql, 得到结果集
			String sql = "select * from t_stu";		//sql 语句
			rs = st.executeQuery(sql);
			//操作  例如遍历结果集
			while(rs.next()){
	   			int id = rs.getInt("id");
	   			String name = rs.getString("name");
	   			int age = rs.getInt("age");
	   			System.out.println("id="+id + "===name="+name+"==age="+age);
	   				
	   		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	//最后一定不要忘了释放rs
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

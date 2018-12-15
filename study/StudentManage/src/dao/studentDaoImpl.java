package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.seu.util.JDBCUtil;
import com.seu.util.student;

public class studentDaoImpl implements studentDao {

	@Override
	public ArrayList<student> findAll() {
		ArrayList<student> list = new ArrayList<student>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "select * from student";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				student stu = new student();
				
				stu.setid(rs.getInt("id"));
				stu.setage(rs.getInt("age"));
				stu.setname(rs.getString("name"));
				stu.setGender(rs.getString("gender"));
				
				list.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		studentDaoImpl st = new studentDaoImpl();
		ArrayList<student> list= st.findAll();
		for(student stu : list) {
			System.out.println(stu);
		}
	}

}

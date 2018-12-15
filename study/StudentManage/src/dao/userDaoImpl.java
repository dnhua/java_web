package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seu.util.JDBCUtil;

public class userDaoImpl implements userDao {

	@Override
	public boolean login(String name, String password) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "select * from user where name=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

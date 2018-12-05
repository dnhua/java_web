#mysql学习

##使用JDBC的基本步骤

1. 注册驱动

   	DriverManager.registerDriver(new com.mysql.jdbc.Driver());

2. 建立连接

   	//DriverManager.getConnection("jdbc:mysql://localhost/test?user=monty&password=greatsqldb");
   		//2. 建立连接 参数一： 协议 + 访问的数据库 ， 参数二： 用户名 ， 参数三： 密码。
   		conn = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "root");

3. 创建statement

   	//3. 创建statement ， 跟数据库打交道，一定需要这个对象
   	st = conn.createStatement();

4. 执行sql ，得到ResultSet

   	//4. 执行查询 ， 得到结果集
   		String sql = "select * from t_stu";
   		rs = st.executeQuery(sql);

5. 遍历结果集

   	//5. 遍历查询每一条记录
   		while(rs.next()){
   			int id = rs.getInt("id");
   			String name = rs.getString("name");
   			int age = rs.getInt("age");
   			System.out.println("id="+id + "===name="+name+"==age="+age);
   				
   		}

6. 释放资源


		if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException sqlEx) { } // ignore 
	        rs = null;
	    }
	
		...
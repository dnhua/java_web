# 项目介绍

这是作者第一次写web相关的项目，也是学习web的第一周的作业。项目是一个极其简易的学生管理系统。其功能如图所示：

* 登陆页面
* login servlet(获取信息；查询数据库；判断账号信息；正确即可前往下一页，错误就输出登陆失败)
* 显示学生信息页面
* 另外为了实现这些功能需要配合设计两个数据表：管理员用户表，学生表。


![](https://github.com/dnhua/java_web/blob/master/study/StudentManage/img/demandAnalysis.PNG)

# 实施步骤

## login.jsp

先写一个login.jsp，放在WebContent目录下，最好修改下WEB-INF下的web.xml文件，使之默认可以索引到这个界面。

![welcome](https://github.com/dnhua/java_web/blob/master/study/StudentManage/img/welcome.png)

此时若直接发布，会有中文乱码，解决方案：
将编码换成 UTF-8 

![loginjsp](https://github.com/dnhua/java_web/blob/master/study/StudentManage/img/loginjsp.png)

```jsp
<body>
	<form action="" method="get">
       	账号:<input type="text" name="username"/><br>
       	密码:<input type="text" name="password"/><br>
       	<input type="submit" value="登录"/>
   	</form>
</body>
```
发布得到如图的结果：

![logininterface](https://github.com/dnhua/java_web/blob/master/study/StudentManage/img/logininterface.png)

## 创建用户表以及学生表

![](https://github.com/dnhua/java_web/blob/master/study/StudentManage/img/usertable.png)  ![](https://github.com/dnhua/java_web/blob/master/study/StudentManage/img/studenttable.png)

## 创建UserDao, 定义登录的方法

```java
//userDao.java
public interface userDao {
	boolean login(String name, String password);
}
```

```java
//userDaoImpl.java
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
```

## 创建loginServlet并在里面访问UserDao，判断登录结果。 以区分对待

1. 尝试获取姓名和密码，使用request.getParameter("")方法。获取后打印一下，发现出现乱码。解决方法是：加一句 name = new String(username.getBytes("ISO-8859-1") , "UTF-8")。
2. 尝试将得到的姓名和密码写到页面上，中文也会乱码，加一句：

```java
response.setContentType("text/html;charset=UTF-8");
String name = request.getParameter("name");
String password = request.getParameter("password");
name = new String(name.getBytes("ISO-8859-1") , "UTF-8");
password = new String(password.getBytes("ISO-8859-1") , "UTF-8");
System.out.println("name="+name);
System.out.println("password="+password);
//写到页面上
PrintWriter writer = response.getWriter();
writer.write(name+":"+password);

```

## 创建student_list.jsp, 让loginServlet可以在等了成功的时候跳转过去。

然而这里出问题了，找不到数据库。主要是配置文件找不到了。这里我一开始犯了好几个错误：
* jar文件放错了位置，应该放到WEB-INF/lib下。
* 工程的相对路径和tomcat中的文件路径有差入，所以找不到配置文件了。有两种解决方案，一是将配置文件放到src文件夹下，然后使用类加载器加载配置文件；二是使用相对tomcat相对bin的相对路径。
* 数据库的名字配置错了，表名也写错了。

```java
//使用类加载器，去读取src底下的资源文件。 后面在servlet
InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
//导入输入流。
properties.load(is);
```

```java
response.setContentType("text/html;charset=UTF-8");
String name = request.getParameter("name");
String password = request.getParameter("password");
name = new String(name.getBytes("ISO-8859-1") , "UTF-8");
password = new String(password.getBytes("ISO-8859-1") , "UTF-8");

userDaoImpl userdao = new userDaoImpl();
boolean userIn = userdao.login(name, password);
if(userIn) {
	//登陆成功
	//请求转发的写法： 参数即跳转的位置
    request.getRequestDispatcher("student_list.jsp").forward(request, response);
}else {
	//登陆失败
	PrintWriter writer = response.getWriter();
	writer.write("密码或者用户名不正确");
}
```

## 得到学生数据

上述只是测试了可以正常跳转，并未将学生数据放进去。实际上在跳转前还需要：
1. 查询到所有学生数据。这需要实现studentDao并返回一个ArrayList<student>。
2. ** 把这个所有的学生集合存储到session作用域中。 **

## 编写student_list.jsp

JSTL方式读取session中的数据。

** 需要导入jar文件到工程的WebContent/Web-Inf/lib  jstl.jar standard.jar。 **

```
<table border="1" width="700">
	<tr align="center">
		<td>编号</td>
		<td>姓名</td>
		<td>年龄</td>
		<td>性别</td>
	</tr>

	<c:forEach items="${list }" var="stu">
		<tr align="center">
			<td>${stu.id }</td>
			<td>${stu.name }</td>
			<td>${stu.age }</td>
			<td>${stu.gender }</td>
			<td><a href="#">更新</a>   <a href="#">删除</a></td>
		</tr>
	</c:forEach>
</table>
```

![result1](https://github.com/dnhua/java_web/blob/master/study/StudentManage/img/result1.png)  
![result2](https://github.com/dnhua/java_web/blob/master/study/StudentManage/img/result2.png)  

# 知识点记录

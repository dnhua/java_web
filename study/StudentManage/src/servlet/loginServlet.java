package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seu.util.student;

import dao.studentDaoImpl;
import dao.userDaoImpl;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		name = new String(name.getBytes("ISO-8859-1") , "UTF-8");
		password = new String(password.getBytes("ISO-8859-1") , "UTF-8");
//		System.out.println("name="+name);
//	    System.out.println("password="+password);
//		//写到页面上
//		PrintWriter writer = response.getWriter();
//		writer.write(name+":"+password);
		userDaoImpl userdao = new userDaoImpl();
		boolean userIn = userdao.login(name, password);
		if(userIn) {
			//登陆成功
			//获取所有学生的数据
			studentDaoImpl st = new studentDaoImpl();
			ArrayList<student> list= st.findAll();
			//将数据存到session中
			request.getSession().setAttribute("list", list);
//			// 重定向
//			response.sendRedirect("stu_list.jsp");
			//请求转发的写法： 参数即跳转的位置
		    request.getRequestDispatcher("student_list.jsp").forward(request, response);
		}else {
			//登陆失败
			PrintWriter writer = response.getWriter();
			writer.write("密码或者用户名不正确");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

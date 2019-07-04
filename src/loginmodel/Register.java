package loginmodel;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.BaseResponse;

import entity.LoginResponse;
import entity.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> a = request.getParameterNames();
	       String name = request.getParameter("name");
	       String pwd = request.getParameter("pwd");
	       String photo = request.getParameter("photo");
			  UserDao ud = new UserDaoImpl();
			  LoginResponse loginresponse = new LoginResponse("omoenenmgfe");
			  BaseResponse<LoginResponse> baseresponse = new BaseResponse<>();
			  baseresponse.setData(loginresponse);
			  System.out.println(name);
			  if(ud.checkusername(name)){
				  baseresponse.setStatus("1");
				  baseresponse.setMessage("ע��ʧ�ܣ��û��Ѵ��ڡ�");
				  System.out.println("ע��ʧ��");
				}else{
					 User user = new User();
					 user.setName(name);
					 user.setPwd(pwd);
					 user.setPhoto("1");
					  boolean ischegonon = ud.register(user);
					  baseresponse.setStatus("0");
					  baseresponse.setMessage("ע��ɹ�");
					  System.out.println("ע��ɹ�");
					  System.out.println(ischegonon);
				}
			  Gson gson = new Gson();
			  String strJson = gson.toJson(baseresponse);
			  response.setContentType("application/json;charset=utf-8");
			  response.getWriter().print(strJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

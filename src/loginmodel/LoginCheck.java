package loginmodel;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.BaseResponse;
import entity.ChatRecordResponse.User;
import entity.LoginResponse;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
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
		  UserDao ud = new UserDaoImpl();
		  List<entity.User> list = ud.getUserAll(name);
		  LoginResponse loginresponse = new LoginResponse("omoenenmgfe");
		  loginresponse.setUserid(list.get(0).getId()+"");
		  loginresponse.setUsername(name);
		  loginresponse.setUserphoto(list.get(0).getPhoto());
		  loginresponse.setPassword(pwd);
		  BaseResponse<LoginResponse> baseresponse = new BaseResponse<>();
		  baseresponse.setData(loginresponse);
		
		  if(ud.login(name, pwd)){
			  baseresponse.setStatus("0");
			  baseresponse.setMessage("µÇÂ¼³É¹¦");
			  System.out.println("µÇÂ¼³É¹¦");
			}else{
				  baseresponse.setStatus("1");
				  baseresponse.setMessage("µÇÂ¼Ê§°Ü");
				  System.out.println("µÇÂ¼Ê§°Ü");
			}
		  Gson gson = new Gson();
		  String strJson = gson.toJson(baseresponse);
		  response.setContentType("application/json;charset=utf-8");
		  response.getWriter().print(strJson);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

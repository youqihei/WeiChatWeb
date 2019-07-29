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
import com.google.gson.reflect.TypeToken;

import dao.ChatRecordDao;
import dao.ChatRecordDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import entity.BaseResponse;
import entity.FRequestGroupBean;
import entity.FriendNameTable;
import entity.LoginResponse;
import entity.User;

/**
 * Servlet implementation class AddGroup
 */
@WebServlet("/AddGroup")
public class AddGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> a = request.getParameterNames();
	       String username = request.getParameter("username");
	       String grouplist = request.getParameter("grouplist");
	 	  System.out.println(username+":"+grouplist);

        Gson gson = new Gson(); 
        List<FRequestGroupBean> persons = gson.fromJson(grouplist, new TypeToken<List<FRequestGroupBean>>(){}.getType());
//	       String friendname = request.getParameter("friendname");
//	       String userid = request.getParameter("userid");
//			 UserDao ud = new UserDaoImpl();
//			 ChatRecordDao cd = new ChatRecordDaoImpl();
//			  User user = new User();
			  BaseResponse<LoginResponse> baseresponse = new BaseResponse<>();
//			  if(!ud.checkusername(friendname)){
//				  baseresponse.setStatus("1");
//				  baseresponse.setMessage("用户不存在。");
//				  System.out.println("用户不存在");
//				}else{
//					 User mUser = new User();
//					 mUser = ud.getUserAll(username).get(0);
//					 User fUser = new User();
//					 fUser = ud.getUserAll(friendname).get(0);
//					  FriendNameTable friendNameTable = new FriendNameTable();
//					  friendNameTable.setUser_id(mUser.getId()+"");
//					  friendNameTable.setUsername(mUser.getName());
//					  friendNameTable.setUserphoto(mUser.getPhoto());
//					  friendNameTable.setFriend_id(fUser.getId()+"");
//					  friendNameTable.setFriendname(fUser.getName());
//					  friendNameTable.setFriendphoto(fUser.getPhoto());
//					  FriendNameTable friendNameTable1 = new FriendNameTable();
//					  friendNameTable1.setUser_id(fUser.getId()+"");
//					  friendNameTable1.setUsername(fUser.getName());
//					  friendNameTable1.setUserphoto(fUser.getPhoto());
//					  friendNameTable1.setFriend_id(mUser.getId()+"");
//					  friendNameTable1.setFriendname(mUser.getName());
//					  friendNameTable1.setFriendphoto(mUser.getPhoto());
//					  boolean ischegonon = cd.addFriendIdName(friendNameTable);
//					  boolean ischegonon1 = cd.addFriendIdName(friendNameTable1);
//					  baseresponse.setStatus("0");
//					  baseresponse.setMessage("添加成功");
//					  System.out.println("添加成功");
//					  System.out.println(ischegonon);
//				}
			  
			  LoginResponse loginResponse = new LoginResponse("");
			  baseresponse.setData(loginResponse);
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

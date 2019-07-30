package loginmodel;

import java.io.IOException;
import java.sql.SQLException;
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
import entity.GroupNameTable;
import entity.LoginResponse;
import entity.User;
import util.DBconn;

/**
 * Servlet implementation class AddGroup
 */
@WebServlet("/AddGroup")
public class AddGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count_id = 0;
       
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
	       String userid = request.getParameter("userid");
	       String userphoto = request.getParameter("userphoto");
	       String grouplist = request.getParameter("grouplist");
	 	  System.out.println(username+":"+grouplist);          
        Gson gson = new Gson(); 
        List<FRequestGroupBean> persons = gson.fromJson(grouplist, new TypeToken<List<FRequestGroupBean>>(){}.getType());
           if(username!=null)
           {
			  BaseResponse<LoginResponse> baseresponse = new BaseResponse<>();
			  ChatRecordDao cDao = new ChatRecordDaoImpl();
			  GroupNameTable groupNameTable  = new GroupNameTable();
			  groupNameTable.setUser_id(userid);
			  groupNameTable.setUsername(username);
			  groupNameTable.setGroupphoto(userphoto);
			  groupNameTable.setUserphoto(userphoto);
			  groupNameTable.setGroupname("群聊");
			  int id = 0;
			  synchronized (AddGroup.class) {
				count_id++;
			}
			  id = count_id;
			  groupNameTable.setGroup_id(id+"");
			  //插入群主
			  if(cDao.addGroupMaster(groupNameTable))
			  {
				
					  String groupid = id+"";
					  //插入群主，另一个表
					  GroupNameTable groupNameTable2  = new GroupNameTable();
					  groupNameTable2.setUser_id(userid);
					  groupNameTable2.setUsername(username);
					  groupNameTable2.setGroupphoto(userphoto);
					  groupNameTable2.setUserphoto(userphoto);
					  groupNameTable2.setGroupname("群聊");  
					  groupNameTable2.setGroup_id(groupid);
					  if(!cDao.addGroupMember(groupNameTable2))
					  {
					  baseresponse.setStatus("1");
					  baseresponse.setMessage("发起群聊失败");
					  return;
					  }
					  //插入所有该群成员
					  for(int i=0;i<persons.size();i++)
					  {
						  GroupNameTable groupNameTable1  = new GroupNameTable();
						  groupNameTable1.setUser_id(persons.get(i).getUserid()+"");
						  groupNameTable1.setUsername(persons.get(i).getUsername());
						  groupNameTable1.setGroupphoto(userphoto);
						  groupNameTable1.setUserphoto(persons.get(i).getUserphoto());
						  groupNameTable1.setGroupname("群聊");  
						  groupNameTable1.setGroup_id(groupid);
						  if(!cDao.addGroupMember(groupNameTable1))
						  {
						  baseresponse.setStatus("1");
						  baseresponse.setMessage("发起群聊失败");
						  break;
						  }
					  }
			
					
					  baseresponse.setStatus("1");
					  baseresponse.setMessage("发起群聊成功");
				  
			  }
			  else {
				  baseresponse.setStatus("1");
				  baseresponse.setMessage("发起群聊失败");
			}
		
			  LoginResponse loginResponse = new LoginResponse("");
			  baseresponse.setData(loginResponse);
			  String strJson = gson.toJson(baseresponse);
			  response.setContentType("application/json;charset=utf-8");
			  response.getWriter().print(strJson);
           }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package businessmodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ChatRecordDaoImpl;
import entity.BaseResponse;
import entity.ChatFriendMessage;
import entity.ChatRecordResponse;
import entity.FriendNameTable;
import entity.GroupNameTable;
import entity.GroupRecordTable;
import entity.PersonRecordTable;
import entity.ChatRecordResponse.Group;
import entity.ChatRecordResponse.User;
import entity.ChatRecordResponse.Group.GroupContent;
import util.DBconn;

/**
 * Servlet implementation class ChatFriendMes
 */
@WebServlet("/ChatFriendMes")
public class ChatFriendMes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatFriendMes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBconn.init();
	       String username = request.getParameter("username");
	       String friendname = request.getParameter("friendname");
		   System.out.println("username"+username);
		   System.out.println("friendname"+friendname);
			  ChatRecordDaoImpl ud = new ChatRecordDaoImpl();
			  List<ChatFriendMessage> chatFriendMessage = new ArrayList<>();
			  
			   //添加某好友的所有聊天记录
				   String sql = "select * from precordtable where (username = '"+username+"'" + "and friendname = '"+friendname+"')"
			         +" or (username = '"+friendname+"'"+" and friendname = '"+username+"') order by timestamp";
					   List<PersonRecordTable> tempo = ud.getPersonRecords(sql);
					   System.out.println(tempo.size());
					   for(int j=0;j<tempo.size();j++)
					   {
						   ChatFriendMessage user = new ChatFriendMessage();
						   user.setMessage((tempo.get(j).getContent()));
						   if(tempo.get(j).getUsername().equals(username))
						   {
						   user.setMessage_id(tempo.get(j).getUser_id());
						   user.setTitle(tempo.get(j).getUsername());
						   user.setMine(true);
						   user.setImv(tempo.get(j).getUser_photo());
						   }
						   else {
							   user.setMessage_id(tempo.get(j).getFriend_id());
							   user.setTitle(tempo.get(j).getFriend_name());
							   user.setMine(false);
							   user.setImv(tempo.get(j).getFriend_photo());
						   }
						   chatFriendMessage.add(user);  
					   }
					      
			  BaseResponse<List<ChatFriendMessage>> baseresponse = new BaseResponse<>();
			  baseresponse.setStatus("0");
			  baseresponse.setMessage("获取成功");
			  baseresponse.setData(chatFriendMessage);
			  Gson gson = new Gson();
			  String strJson = gson.toJson(baseresponse);
			  response.setContentType("application/json;charset=utf-8");
			  response.getWriter().print(strJson);
				DBconn.closeConn();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

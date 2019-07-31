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
import entity.ChatGroupMessage;
import entity.GroupRecordTable;
import entity.PersonRecordTable;

/**
 * Servlet implementation class ChatGroupMes
 */
@WebServlet("/ChatGroupMes")
public class ChatGroupMes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatGroupMes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	      String userid = request.getParameter("userid");
	       String groupid = request.getParameter("groupid");
		   System.out.println("username"+userid);
		   System.out.println("friendname"+groupid);
			  ChatRecordDaoImpl ud = new ChatRecordDaoImpl();
			  List<ChatGroupMessage> chatgroupMessage = new ArrayList<>();
			  
			   //添加用户某个群的所有聊天记录
			  if(userid!=null)
			  {
				   String sql = "select * from grecordtable where ( groupid = '"+groupid+"') order by timestamp";
					   List<GroupRecordTable> tempo = ud.getGroupRecords(sql);
				if(tempo!=null)
				{
					   for(int j=0;j<tempo.size();j++)
					   {
						   ChatGroupMessage user = new  ChatGroupMessage();
						   user.setMessage((tempo.get(j).getContent()));
						   user.setMessage_id(tempo.get(j).getUserid());
						   user.setTitle(tempo.get(j).getUsername());
						   user.setImv(tempo.get(j).getUserphoto());
						   user.setGroupid(groupid);
						   if(tempo.get(j).getUserid().equals(userid))
						   {
						   user.setMine(true);
						   }
						   else {
							   user.setMine(false);
						   }
						   chatgroupMessage.add(user);  
					   }
					      
			  BaseResponse<List<ChatGroupMessage>> baseresponse = new BaseResponse<>();
			  baseresponse.setStatus("0");
			  baseresponse.setMessage("获取成功");
			  baseresponse.setData(chatgroupMessage);
			  Gson gson = new Gson();
			  String strJson = gson.toJson(baseresponse);
			  response.setContentType("application/json;charset=utf-8");
			  response.getWriter().print(strJson);
				}
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

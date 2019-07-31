package loginmodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ChatRecordDaoImpl;
import entity.BaseResponse;
import entity.ChatRecordResponse;
import entity.FriendNameTable;
import entity.GroupNameTable;
import entity.GroupRecordTable;
import entity.PersonRecordTable;
import util.DBconn;

/**
 * Servlet implementation class GetChatRecord
 */
@WebServlet("/GetChatRecord")
public class GetChatRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetChatRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	       String userid = request.getParameter("userid");
			  ChatRecordDaoImpl ud = new ChatRecordDaoImpl();
			  ChatRecordResponse chatRecordResponse = new ChatRecordResponse();
			  List<ChatRecordResponse.Group> grouplist = new ArrayList<>();
			  //获取某用户的所有群聊天名称
			   List<GroupNameTable> groupNameTables = ud.getGroupMembers("select * from groupmember where userid = '"+userid+"'");
			   for(int i=0;i<groupNameTables.size();i++)
			   {
				   //添加某用户某一个群的所有聊天记录
					   List<GroupRecordTable> tempo = ud.getGroupRecords("select * from grecordtable where groupid = '"+ groupNameTables.get(i).getGroup_id()+"'");
					   List<String> userids = new ArrayList<>();
					   List<String> usernames  = new ArrayList<>();
					   List<String> userphotos = new ArrayList<>();
					   List<String> content = new ArrayList<>();
					   for(int j=0;j<tempo.size();j++)
					   {
						   userids.add(tempo.get(j).getUserid());
						   usernames.add(tempo.get(j).getUsername());
						   userphotos.add(tempo.get(j).getUserphoto());
						   content.add(tempo.get(j).getContent());
					   }
					   ChatRecordResponse.Group group = new ChatRecordResponse().new Group();
					   ChatRecordResponse.Group.GroupContent groupContent = new ChatRecordResponse().new Group().new GroupContent();
					   groupContent.setUser_ids(userids);
					   groupContent.setUser_names(usernames);
					   groupContent.setUser_photos(userphotos);
					   groupContent.setContent(content);
					   group.setGroupcontent(groupContent);
					   group.setGroupid(groupNameTables.get(i).getGroup_id());
					   group.setGroupname(groupNameTables.get(i).getGroupname());
					   group.setGroupphoto(groupNameTables.get(i).getGroupphoto());
					   grouplist.add(group);        
			   }
			  List<ChatRecordResponse.User> userlist = new ArrayList<>();
			  //获取某用户的所有好友列表
			   List<FriendNameTable> friendNameTables = ud.getFriendNameTalbe("select * from fnametable where userid = '"+userid+"'");
			   //添加某好友的所有聊天记录
			   if(friendNameTables!=null)
			   {
			   for(int i=0;i<friendNameTables.size();i++)
			   {
				   String sql = "select * from precordtable where (userid = '"+userid+"'" + "and friendid = '"+friendNameTables.get(i).getFriend_id()+"')"
			         +" or (userid = '"+friendNameTables.get(i).getFriend_id()+"'"+" and friendid = '"+userid+"') order by timestamp";
					   List<PersonRecordTable> tempo = ud.getPersonRecords(sql);
					 
					   List<String> content = new ArrayList<>();
					   if(tempo!=null)
					   {
					   for(int j=0;j<tempo.size();j++)
					   {
						   content.add(tempo.get(j).getContent());
					   }
			         }
					   ChatRecordResponse.User user = new ChatRecordResponse().new User();
					   user.setUsercontent(content);
					   user.setUserid(friendNameTables.get(i).getFriend_id());
					   user.setUsername(friendNameTables.get(i).getFriendname());
					   user.setUserphoto(friendNameTables.get(i).getFriendphoto());
					  userlist.add(user);        
			   }
			  chatRecordResponse.setGroups(grouplist);
			  chatRecordResponse.setUsers(userlist);
			  
			  BaseResponse<ChatRecordResponse> baseresponse = new BaseResponse<>();
			  baseresponse.setStatus("0");
			  baseresponse.setMessage("获取成功");
			  baseresponse.setData(chatRecordResponse);
			  Gson gson = new Gson();
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

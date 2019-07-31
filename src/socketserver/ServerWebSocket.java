package socketserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.mysql.cj.log.Log;

import businessmodel.ChatGroupMes;
import dao.ChatRecordDaoImpl;
import dao.UserDaoImpl;
import entity.ChatFriendMessage;
import entity.ChatGroupMessage;
import entity.GroupNameTable;
import entity.GroupRecordTable;
import entity.PersonRecordTable;
import entity.User;
import util.DBconn;
 
@ServerEndpoint("/serverWebsocket/{userid}")
public class ServerWebSocket  {    
	    private static int onlineCount = 0;
	    private static Map<String,Session> clients = new ConcurrentHashMap<String,Session>();
	    private ChatRecordDaoImpl chatRecordDaoImpl = new ChatRecordDaoImpl();
	    private UserDaoImpl userDaoImpl = new UserDaoImpl();
	    private Session session;
	    private String userid;
	    
	    //连接时执行
	    @OnOpen
	    public void onOpen(@PathParam("userid") String userid,Session session) throws IOException{
	        this.userid = userid;
	        this.session = session;
			DBconn.init();
	        addOnlineCount();
	        clients.put(userid,session);
	  	   System.out.println("连接");
	    }
	    
	    //关闭时执行
	    @OnClose
	    public void onClose(){
	    	clients.remove(userid);
			DBconn.closeConn();
	    	subOnlineCount();
	    	   System.out.println("连接：{} 关闭");
	    }
	    
	    //收到消息时执行
	    @OnMessage
	    public void onMessage(String message) throws IOException {
	    	   System.out.println("收到用户的消息"+message);
	    	   JSONObject json = new JSONObject(message);
	    	   if("group".equals(json.getString("require")))
	    	   {
	    		   sendMessageGroup(json.getString("user_id"),json.getString("groupid"),json.getString("content")); 
	    	   }
	    	   else
	    	   {
	        	 
	    		   sendMessageFriend(json.getString("friendid"),json.getString("content")); 
	    	   }
	        session.getBasicRemote().sendText("收到消息 "); //回复用户
	    }
	    //收发某个群的消息
	    public void sendMessageGroup(String userid,String groupid,String  content) throws IOException {  
	        // session.getBasicRemote().sendText(message);  
	        //session.getAsyncRemote().sendText(message);  
	      	GroupNameTable groupNameTable = chatRecordDaoImpl.getGroupMembers("select * from groupmember where groupid = '"+groupid+"' and userid = '"+userid+"'").get(0);
	    	//添加到数据库
	    	GroupRecordTable  grouprecordtable = new GroupRecordTable();
	    	if(!groupid.equals(""))
        	{
        		if(content!=null)
        		{
        		grouprecordtable.setUserid(userid);
        		grouprecordtable.setUsername(groupNameTable.getUsername());
        		grouprecordtable.setUserphoto(groupNameTable.getUserphoto());
        		grouprecordtable.setGroupid(groupNameTable.getGroup_id());
        		grouprecordtable.setGroupname(groupNameTable.getGroupname());
        		grouprecordtable.setGroupphoto(groupNameTable.getGroupphoto());
        		grouprecordtable.setTimestamp(System.currentTimeMillis()+"");
        		grouprecordtable.setContent(content);
        		chatRecordDaoImpl.addGroupRecord(grouprecordtable);
        		}
        	}
            ChatGroupMessage chatGroupMessage = new ChatGroupMessage();
            chatGroupMessage.setMessage_id(userid);
            chatGroupMessage.setImv(groupNameTable.getUserphoto());
            chatGroupMessage.setMessage(content);
            chatGroupMessage.setMine(false);
            chatGroupMessage.setGroupid(groupid);
            chatGroupMessage.setTitle(groupNameTable.getUsername());
	    	//群发给群里的人
	     	List<GroupNameTable> current = chatRecordDaoImpl.getGroupMembers("select * from groupmember where groupid = '"+groupid+"'");
	    	if(current!=null&&current.size()>0)
	    	{
    		JSONObject jsonObject = new JSONObject(chatGroupMessage);
	    	for(int i=0;i<current.size();i++)
	    	 {
	      	 //如果群成员在线
	             	if(clients.containsKey(current.get(i).getUser_id()))
	             	{
	             	   	if(!current.get(i).getUser_id().equals(this.userid))
	              	  {   	
        	    clients.get(current.get(i).getUser_id()).getBasicRemote().sendText(jsonObject.toString());
	             	}
        	      }
	             	else
	             	{
	             		
	             	}
	    	 }
        	}
	    	else
        	{
        		this.session.getBasicRemote().sendText("消息发送错误");
        	}
	  
        
	    }  
	    //收发某个好友的消息
	    public void sendMessageFriend(String friendid,String content) throws IOException {  
	    	PersonRecordTable personRecordTable = new PersonRecordTable();
    		User friend = userDaoImpl.getUserAll(friendid).get(0);
    		User current = userDaoImpl.getUserAll(this.userid).get(0);
    		personRecordTable.setUser_id(current.getId()+"");
    		personRecordTable.setUsername(current.getName());
    		personRecordTable.setUser_photo(current.getPhoto());
    		personRecordTable.setFriend_id(friend.getId()+"");
    		personRecordTable.setFriend_name(friend.getName());
    		personRecordTable.setFriend_photo(friend.getPhoto());
    		personRecordTable.setContent(content);
    		personRecordTable.setTimestamp(System.currentTimeMillis()+"");
    		//插入对话数据库
    		chatRecordDaoImpl.addPersonRecord(personRecordTable);
	    	   //如果好友在线
	        	if(clients.containsKey(friendid))
	        	{	        		
	        		ChatFriendMessage chatFriendMessage = new ChatFriendMessage();
	        		chatFriendMessage.setImv("");
	        		chatFriendMessage.setMessage(content);
	        		chatFriendMessage.setMessage_id(current.getId()+"");
	        		chatFriendMessage.setMine(false);
	        		chatFriendMessage.setTitle(current.getName());
	        		JSONObject jsonObject = new JSONObject(chatFriendMessage);
	        	    clients.get(friendid).getBasicRemote().sendText(jsonObject.toString());   	
	        	}
	        	else
	        	{
	        		
	        	}
	       
	    }  
	    //获取离线消息
	      private void getOnlineMessage()
	      {
	    	  
	      }
	    //连接错误时执行
	    @OnError
	    public void onError(Session session, Throwable error){
	    	   System.out.println("消息发送错误");
	        error.printStackTrace();
	    }
	    public static synchronized int getOnlineCount() {  
	        return onlineCount;  
	    }  
	  
	    public static synchronized void addOnlineCount() {  
	    	ServerWebSocket.onlineCount++;  
	    }  
	  
	    public static synchronized void subOnlineCount() {  
	    	ServerWebSocket.onlineCount--;  
	    }  
	  
	    public static synchronized Map<String,Session> getClients() {  
	        return clients;  
	    }  
	    

}
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

import dao.ChatRecordDaoImpl;
import dao.UserDaoImpl;
import entity.ChatFriendMessage;
import entity.GroupNameTable;
import entity.GroupRecordTable;
import entity.PersonRecordTable;
import entity.User;
import util.DBconn;
 
@ServerEndpoint("/serverWebsocket/{username}")
public class ServerWebSocket  {    
	    private static int onlineCount = 0;
	    private static Map<String,Session> clients = new ConcurrentHashMap<String,Session>();
	    private ChatRecordDaoImpl chatRecordDaoImpl = new ChatRecordDaoImpl();
	    private UserDaoImpl userDaoImpl = new UserDaoImpl();
	    private Session session;
	    private String username;
	    
	    //����ʱִ��
	    @OnOpen
	    public void onOpen(@PathParam("username") String username,Session session) throws IOException{
	        this.username = username;
	        this.session = session;
			DBconn.init();
	        addOnlineCount();
	        clients.put(username,session);
	  	   System.out.println("����");
	    }
	    
	    //�ر�ʱִ��
	    @OnClose
	    public void onClose(){
	    	clients.remove(username);
			DBconn.closeConn();
	    	subOnlineCount();
	    	   System.out.println("���ӣ�{} �ر�");
	    }
	    
	    //�յ���Ϣʱִ��
	    @OnMessage
	    public void onMessage(String message) throws IOException {
	    	   System.out.println("�յ��û�{}����Ϣ{}"+message);
	    	   JSONObject json = new JSONObject(message);
	    	   if("group".equals(json.getString("require")))
	    	   {
	    		   sendMessageGroup(json.getString("friendname"),json.getString("groupname"),json.getString("content")); 
	    	   }
	    	   else
	    	   {
	    		
	    		   sendMessageFriend(json.getString("friendname"),json.getString("content")); 
	    	   }
	        session.getBasicRemote().sendText("�յ���Ϣ "); //�ظ��û�
	    }
	    //�շ�ĳ��Ⱥ����Ϣ
	    public void sendMessageGroup(String username,String groupname,String  content) throws IOException {  
	        // session.getBasicRemote().sendText(message);  
	        //session.getAsyncRemote().sendText(message);  
	      	GroupNameTable groupNameTable = chatRecordDaoImpl.getGroupIdNames("select * from gnametable where groupname = '"+groupname+"' and username ='"+username+"'").get(0);
	    	//��ӵ����ݿ�
	    	GroupRecordTable  grouprecordtable = new GroupRecordTable();
	    	if(!groupname.equals(""))
        	{
        		if(groupNameTable!=null)
        		{
        		grouprecordtable.setUserid(groupNameTable.getUser_id()+"");
        		grouprecordtable.setUsername(groupNameTable.getUsername());
        		grouprecordtable.setUserphoto(groupNameTable.getUserphoto());
        		grouprecordtable.setGroupid(groupNameTable.getGroup_id());
        		grouprecordtable.setGroupname(groupNameTable.getGroupname());
        		grouprecordtable.setGroupphoto(groupNameTable.getGroupphoto());
        		grouprecordtable.setContent(content);
        		chatRecordDaoImpl.addGroupRecord(grouprecordtable);
        		}
        	}
	    	//Ⱥ����Ⱥ�����
	    	List<GroupNameTable> current = chatRecordDaoImpl.getGroupIdNames("select * from gnametable where groupname = '"+groupname+"'");
	    	if(current!=null&&current.size()>0)
	    	{
        		grouprecordtable.setContent(content);
    		JSONObject jsonObject = new JSONObject(grouprecordtable);
	    	for(int i=0;i<current.size();i++)
	    	 {
	         	if(current.get(i).getUsername()!=this.username)
        	  {   	
        	    clients.get(current.get(i).getUsername()).getBasicRemote().sendText(jsonObject.toString());
        	   }
	    	 }
        	}
	    	else
        	{
        		this.session.getBasicRemote().sendText("��Ϣ���ʹ���");
        	}
	  
        
	    }  
	    //�շ�ĳ�����ѵ���Ϣ
	    public void sendMessageFriend(String friendname,String content) throws IOException {  
	        	if(clients.containsKey(friendname))
	        	{
	        		PersonRecordTable personRecordTable = new PersonRecordTable();
	        		User friend = userDaoImpl.getUserAll(friendname).get(0);
	        		User current = userDaoImpl.getUserAll(this.username).get(0);
	        		personRecordTable.setUser_id(current.getId()+"");
	        		personRecordTable.setUsername(current.getName());
	        		personRecordTable.setUser_photo(current.getPhoto());
	        		personRecordTable.setFriend_id(friend.getId()+"");
	        		personRecordTable.setFriend_name(friend.getName());
	        		personRecordTable.setFriend_photo(friend.getPhoto());
	        		personRecordTable.setContent(content);
	        		personRecordTable.setTimestamp(System.currentTimeMillis()+"");
	        		chatRecordDaoImpl.addPersonRecord(personRecordTable);
	        		ChatFriendMessage chatFriendMessage = new ChatFriendMessage();
	        		chatFriendMessage.setImv("");
	        		chatFriendMessage.setMessage(content);
	        		chatFriendMessage.setMessage_id(current.getId()+"");
	        		chatFriendMessage.setMine(false);
	        		chatFriendMessage.setTitle(current.getName());
	        		JSONObject jsonObject = new JSONObject(chatFriendMessage);
	        		   System.out.println("���ͻؿͻ���"+jsonObject.toString());
	        	    clients.get(friendname).getBasicRemote().sendText(jsonObject.toString());
	        	}
	        	else
	        	{
	        		this.session.getBasicRemote().sendText("��Ϣ���ʹ���");
	        	}
	    }  
	    //���Ӵ���ʱִ��
	    @OnError
	    public void onError(Session session, Throwable error){
	    	   System.out.println("�û�idΪ��{}�����ӷ��ʹ���");
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
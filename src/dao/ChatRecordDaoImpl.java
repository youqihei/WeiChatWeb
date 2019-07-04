package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.FriendNameTable;
import entity.GroupNameTable;
import entity.GroupRecordTable;
import entity.PersonRecordTable;
import entity.User;
import util.DBconn;

public class ChatRecordDaoImpl implements ChatRecordDao{

	@Override
	public boolean addPersonRecord(PersonRecordTable user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int i =DBconn.addUpdDel("insert into precordtable(userid,username,userphoto,friendid,friendname,friendphoto,issend,content,timestamp) " +
				"values('"+user.getUser_id()+"','"+user.getUsername()+"','"+user.getUser_photo()+"','"+user.getFriend_id()+"','"+user.getFriend_name()
				+"','"+user.getFriend_photo()+"','"+user.isSend()+"','"+user.getContent()+"','"+user.getTimestamp()+"')");
		if(i>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deletePersonRecord(String sql) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<PersonRecordTable> getPersonRecords(String sql) {
		// TODO Auto-generated method stub
		List<PersonRecordTable> list = new ArrayList<PersonRecordTable>();
    	try {
			ResultSet rs;
			 rs = DBconn.selectSql(sql);
			while(rs.next()){
				PersonRecordTable personRecordTable = new PersonRecordTable();
                personRecordTable.setUser_id(rs.getString("userid"));
                personRecordTable.setUsername(rs.getString("username"));
                personRecordTable.setUser_photo(rs.getString("userphoto"));
                personRecordTable.setFriend_id(rs.getString("friendid"));
                personRecordTable.setFriend_name(rs.getString("friendname"));
                personRecordTable.setFriend_photo(rs.getString("friendphoto"));
                personRecordTable.setSend(rs.getBoolean ("issend"));
                personRecordTable.setContent(rs.getString("content"));
				list.add(personRecordTable);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
	@Override
	public boolean addFriendIdName(FriendNameTable user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		DBconn.init();
		int i =DBconn.addUpdDel("insert into fnametable(userid,username,userphoto,friendid,friendname,friendphoto) " +
				"values('"+user.getUser_id()+"','"+user.getUsername()+"','"+user.getUserphoto()+"','"+user.getFriend_id()+"','"+user.getFriendname()+
				"','"+user.getFriendphoto()+"')");
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

	@Override
	public boolean deleteFriendIdName(String sql) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<FriendNameTable> getFriendNameTalbe(String sql) {
		// TODO Auto-generated method stub
		List<FriendNameTable> list = new ArrayList<FriendNameTable>();
    	try {
			ResultSet rs;
		  	rs = DBconn.selectSql(sql);	
			while(rs.next()){
				FriendNameTable friendNameTable = new FriendNameTable();
				friendNameTable.setUser_id(rs.getString("userid"));
				friendNameTable.setUsername(rs.getString("username"));
				friendNameTable.setUserphoto(rs.getString("userphoto"));
				friendNameTable.setFriend_id(rs.getString("friendid"));
				friendNameTable.setFriendname(rs.getString("friendname"));
				friendNameTable.setFriendphoto(rs.getString("friendphoto"));
				list.add(friendNameTable);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
   @Override
public boolean isSomename(String sql,String friendname) {
	// TODO Auto-generated method stub
		boolean flag = false;
	    DBconn.init();
		try {
		ResultSet rs = DBconn.selectSql(sql);
		if(rs!=null)
		{
			while(rs.next()){
			if(friendname.equals(rs.getString("friendname")))
					{
				     flag = true;
					}		
			}
		}
		DBconn.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
return flag;
}
	
	@Override
	public boolean addGroupIdName(GroupNameTable user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int i =DBconn.addUpdDel("insert into gnametable(userid,username,userphoto,groupid,groupname,groupphoto) " +
				"values('"+user.getUser_id()+"','"+user.getUsername()+"','"+user.getUserphoto()+"','"+user.getGroup_id()+"','"+user.getGroupname()+
				"','"+user.getGroupphoto()+"')");
		if(i>0){
			flag = true;
		}
		return flag;
	}
	@Override
	public boolean deleteGroupIdName(String sql) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		return flag;
	} 
	

	@Override
	public List<GroupNameTable> getGroupIdNames(String sql) {
		// TODO Auto-generated method stub
		List<GroupNameTable> list = new ArrayList<GroupNameTable>();
    	try {
			ResultSet rs;
		  	rs = DBconn.selectSql(sql);	
			while(rs.next()){
				GroupNameTable groupNameTable = new GroupNameTable();
				groupNameTable.setUser_id(rs.getString("userid"));
				groupNameTable.setUsername(rs.getString("username"));
				groupNameTable.setUserphoto(rs.getString("userphoto"));
				groupNameTable.setGroup_id(rs.getString("groupid"));
				groupNameTable.setGroupname(rs.getString("groupname"));
				groupNameTable.setGroupphoto(rs.getString("groupphoto"));
				list.add(groupNameTable);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean isSomeGroup(String sql,String groupname) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
		ResultSet rs = DBconn.selectSql(sql);
		if(rs!=null)
		{
			while(rs.next()){
			if(groupname.equals(rs.getString("groupname")))
					{
				     flag = true;
					}		
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     return flag;
	}
	
	
	@Override
	public boolean addGroupRecord(GroupRecordTable user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int i =DBconn.addUpdDel("insert into grecordtable(groupid,groupname,groupphoto,userid,username,userphoto,content) " +
				"values('"+user.getGroupid()+"','"+user.getGroupname()+"','"+user.getGroupphoto()+"','"+user.getUserid()+"','"+user.getUsername()+
				"','"+user.getUserphoto()+"','"+user.getContent()+"')");
		if(i>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteGroupRecord(String sql) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<GroupRecordTable> getGroupRecords(String sql) {
		
		// TODO Auto-generated method stub
		List<GroupRecordTable> list = new ArrayList<GroupRecordTable>();
    	try {
			ResultSet rs;
		  	rs = DBconn.selectSql(sql);	
			while(rs.next()){
				GroupRecordTable  groupRecordTable = new GroupRecordTable();
				 groupRecordTable.setUserid(rs.getString("userid"));
				 groupRecordTable.setUsername(rs.getString("username"));
				 groupRecordTable.setUserphoto(rs.getString("userphoto"));
				 groupRecordTable.setGroupid(rs.getString("groupid"));
				 groupRecordTable.setGroupname(rs.getString("groupname"));
				 groupRecordTable.setGroupphoto(rs.getString("groupphoto"));
				 groupRecordTable.setContent(rs.getString("content"));
				list.add(groupRecordTable);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}

package dao;

import java.util.List;

import entity.FriendNameTable;
import entity.GroupNameTable;
import entity.GroupRecordTable;
import entity.PersonRecordTable;

public interface ChatRecordDao {
	//个人记录表
   public boolean addPersonRecord(PersonRecordTable user);//增加个人记录
   public boolean deletePersonRecord(String sql);//删除个人记录表数据
   public List<PersonRecordTable> getPersonRecords(String sql);//获取个人记录表数据
   //好友列表
   public boolean addFriendIdName(FriendNameTable user);//增加好友
   public boolean deleteFriendIdName(String sql);//删除好友列表数据
   public List<FriendNameTable> getFriendNameTalbe(String sql);//获取好友列表数据
   public boolean isSomename(String sql,String friendname) ;//查询好友是否存在列表数据
    //群名列表
   public boolean addGroupIdName(GroupNameTable user);//增加聊天群名记录
   public boolean deleteGroupIdName(String sql);//删除群名记录数据
   public List<GroupNameTable> getGroupIdNames(String sql);//获取聊天群名数据
   public boolean isSomeGroup(String sql,String groupname);//查询是否存在群名数据
   //群聊天记录表
   public boolean addGroupRecord(GroupRecordTable user);//增加群聊天记录
   public boolean deleteGroupRecord(String sql);//删除聊天记录表数据
   public List<GroupRecordTable> getGroupRecords(String sql);//获取群聊天记录表数据
}

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
   //群主列表
   public boolean addGroupMaster(GroupNameTable user);//增加群或改变群主
   public boolean deleteGroupMaster(String sql);//删除群
   public List<GroupNameTable> getGroupMaster(String sql);//获取满足某些条件的群
   public boolean isGroupMaster(String sql,String username);//查询是否存在满足某些条件的某群主
    //群成员列表
   public boolean addGroupMember(GroupNameTable user);//增加聊天群成员
   public boolean deleteGroupMember(String sql);//删除群成员数据
   public List<GroupNameTable> getGroupMembers(String sql);//获取聊天群成员数据
   public boolean isGroupMember(String sql,String username);//查询是否存在某群成员
   //群聊天记录表
   public boolean addGroupRecord(GroupRecordTable user);//增加群聊天记录
   public boolean deleteGroupRecord(String sql);//删除聊天记录表数据
   public List<GroupRecordTable> getGroupRecords(String sql);//获取群聊天记录表数据
}

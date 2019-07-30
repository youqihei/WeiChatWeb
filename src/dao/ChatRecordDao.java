package dao;

import java.util.List;

import entity.FriendNameTable;
import entity.GroupNameTable;
import entity.GroupRecordTable;
import entity.PersonRecordTable;

public interface ChatRecordDao {
	//���˼�¼��
   public boolean addPersonRecord(PersonRecordTable user);//���Ӹ��˼�¼
   public boolean deletePersonRecord(String sql);//ɾ�����˼�¼������
   public List<PersonRecordTable> getPersonRecords(String sql);//��ȡ���˼�¼������
   //�����б�
   public boolean addFriendIdName(FriendNameTable user);//���Ӻ���
   public boolean deleteFriendIdName(String sql);//ɾ�������б�����
   public List<FriendNameTable> getFriendNameTalbe(String sql);//��ȡ�����б�����
   public boolean isSomename(String sql,String friendname) ;//��ѯ�����Ƿ�����б�����
   //Ⱥ���б�
   public boolean addGroupMaster(GroupNameTable user);//����Ⱥ��ı�Ⱥ��
   public boolean deleteGroupMaster(String sql);//ɾ��Ⱥ
   public List<GroupNameTable> getGroupMaster(String sql);//��ȡ����ĳЩ������Ⱥ
   public boolean isGroupMaster(String sql,String username);//��ѯ�Ƿ��������ĳЩ������ĳȺ��
    //Ⱥ��Ա�б�
   public boolean addGroupMember(GroupNameTable user);//��������Ⱥ��Ա
   public boolean deleteGroupMember(String sql);//ɾ��Ⱥ��Ա����
   public List<GroupNameTable> getGroupMembers(String sql);//��ȡ����Ⱥ��Ա����
   public boolean isGroupMember(String sql,String username);//��ѯ�Ƿ����ĳȺ��Ա
   //Ⱥ�����¼��
   public boolean addGroupRecord(GroupRecordTable user);//����Ⱥ�����¼
   public boolean deleteGroupRecord(String sql);//ɾ�������¼������
   public List<GroupRecordTable> getGroupRecords(String sql);//��ȡȺ�����¼������
}

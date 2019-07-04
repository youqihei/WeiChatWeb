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
   public boolean addGroupIdName(GroupNameTable user);//��������Ⱥ����¼
   public boolean deleteGroupIdName(String sql);//ɾ��Ⱥ����¼����
   public List<GroupNameTable> getGroupIdNames(String sql);//��ȡ����Ⱥ������
   public boolean isSomeGroup(String sql,String groupname);//��ѯ�Ƿ����Ⱥ������
   //Ⱥ�����¼��
   public boolean addGroupRecord(GroupRecordTable user);//����Ⱥ�����¼
   public boolean deleteGroupRecord(String sql);//ɾ�������¼������
   public List<GroupRecordTable> getGroupRecords(String sql);//��ȡȺ�����¼������
}

package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	public boolean login(String name,String pwd);//��¼
	public boolean checkusername(String name);//�鿴�Ƿ����ĳ�û�
	public boolean register(User user);//ע��
	public List<User> getUserAll(String uid);//����ĳ�û���Ϣ����
	public boolean delete(int id) ;//����idɾ���û�
	public boolean update(int id,String name, String pwd) ;//�����û���Ϣ
}

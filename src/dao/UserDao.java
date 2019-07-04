package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	public boolean login(String name,String pwd);//登录
	public boolean checkusername(String name);//查看是否存在某用户
	public boolean register(User user);//注册
	public List<User> getUserAll(String uid);//返回某用户信息集合
	public boolean delete(int id) ;//根据id删除用户
	public boolean update(int id,String name, String pwd) ;//更新用户信息
}

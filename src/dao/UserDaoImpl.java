package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.DBconn;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean login(String name, String pwd) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			    DBconn.init();
				ResultSet rs = DBconn.selectSql("select * from login_info where username='"+name+"' and password='"+pwd+"'");
				while(rs.next()){
					if(rs.getString("username").equals(name) && rs.getString("password").equals(pwd)){
						flag = true;
					}
				}
				DBconn.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		boolean flag = false;
		DBconn.init();
		int i =DBconn.addUpdDel("insert into login_info(name,pwd) " +
				"values('"+user.getName()+"','"+user.getPwd()+"')");
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

	@Override
	public List<User> getUserAll() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
    	try {
		    DBconn.init();
			ResultSet rs = DBconn.selectSql("select * from login_info");
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				list.add(user);
			}
			DBconn.closeConn();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		DBconn.init();
		String sql = "delete  from login_info where id="+id;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}

	@Override
	public boolean update(int id, String name, String pwd) {
		// TODO Auto-generated method stub
		boolean flag = false;
		DBconn.init();
		String sql ="update login_info set username ='"+name
				+"' , password ='"+pwd
				+"' where id = "+id;
		int i =DBconn.addUpdDel(sql);
		if(i>0){
			flag = true;
		}
		DBconn.closeConn();
		return flag;
	}
    
}

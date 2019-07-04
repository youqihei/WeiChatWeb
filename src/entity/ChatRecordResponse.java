package entity;

import java.util.List;

public class ChatRecordResponse { 
	    private List<Group> groups;
	    private List<User> users;
	    
	    public List<Group> getGroups() {
			return groups;
		}

		public void setGroups(List<Group> groups) {
			this.groups = groups;
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

		public class Group
	    {
    private String groupid;
    private String groupname;
    private String groupphoto;
    private GroupContent groupcontent;
    
    public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroupphoto() {
		return groupphoto;
	}

	public void setGroupphoto(String groupphoto) {
		this.groupphoto = groupphoto;
	}
	public GroupContent getGroupcontent() {
		return groupcontent;
	}

	public void setGroupcontent(GroupContent groupcontent) {
		this.groupcontent = groupcontent;
	}

	public class GroupContent 
    {
    private List<String> user_names;
    private List<String> user_ids;
    private List<String> user_photos;
    
    
    public List<String> getUser_names() {
		return user_names;
	}
	public void setUser_names(List<String> user_names) {
		this.user_names = user_names;
	}
	public List<String> getUser_ids() {
		return user_ids;
	}
	public void setUser_ids(List<String> user_ids) {
		this.user_ids = user_ids;
	}
	public List<String> getUser_photos() {
		return user_photos;
	}
	public void setUser_photos(List<String> user_photos) {
		this.user_photos = user_photos;
	}
	private List<String> content;
  
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	
    
    }
	    }
  
    public class User
    {
    private String userid;
    private String username;
    private String userphoto;
    private List<String> usercontent;
    
    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserphoto() {
		return userphoto;
	}

	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}

	public List<String> getUsercontent() {
		return usercontent;
	}

	public void setUsercontent(List<String> usercontent) {
		this.usercontent = usercontent;
	}
    }
   
}

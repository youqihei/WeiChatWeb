package entity;

public class PersonRecordTable {
   private String user_id;
   private String username;
   private String user_photo;
   private String friend_id;
   private String friend_name;
   private String friend_photo;
   private boolean  isSend;
   private String content;
   private String timestamp;
   
public String getTimestamp() {
	return timestamp;
}
public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUser_photo() {
	return user_photo;
}
public void setUser_photo(String user_photo) {
	this.user_photo = user_photo;
}
public String getFriend_id() {
	return friend_id;
}
public void setFriend_id(String friend_id) {
	this.friend_id = friend_id;
}
public String getFriend_name() {
	return friend_name;
}
public void setFriend_name(String friend_name) {
	this.friend_name = friend_name;
}
public String getFriend_photo() {
	return friend_photo;
}
public void setFriend_photo(String friend_photo) {
	this.friend_photo = friend_photo;
}

public boolean isSend() {
	return isSend;
}
public void setSend(boolean isSend) {
	this.isSend = isSend;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
   
}

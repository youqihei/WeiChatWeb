package entity;

public class ChatGroupMessage {
	   private String imv;
	    private String title;
	    private String message_id;
	    private String message;
	    private String groupid;
	    private boolean isMine;

	    
	    
	    public String getGroupid() {
			return groupid;
		}

		public void setGroupid(String groupid) {
			this.groupid = groupid;
		}

		public String getImv() {
	        return imv;
	    }

	    public void setImv(String imv) {
	        this.imv = imv;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	  

	    public String getMessage_id() {
			return message_id;
		}

		public void setMessage_id(String message_id) {
			this.message_id = message_id;
		}

		public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public boolean isMine() {
	        return isMine;
	    }

	    public void setMine(boolean mine) {
	        isMine = mine;
	    }
}


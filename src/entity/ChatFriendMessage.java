package entity;

public class ChatFriendMessage {
	   private String imv;
	    private String title;
	    private String message_id;
	    private String message;
	    private boolean isMine;

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

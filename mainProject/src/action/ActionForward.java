package action;

public class ActionForward {
	private String path;	// 경로정보
	private boolean redirect; // true:리다이렉트, false:디스패치
	
	public ActionForward() {

	}

	public ActionForward(String path, boolean redirect) {
		super();
		this.path = path;
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	
	
	
	
}

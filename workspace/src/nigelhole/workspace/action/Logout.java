package nigelhole.workspace.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * Servlet implementation class Logout
 */
@ResultPath(value="/")
public class Logout extends BaseAction{
	private static final long serialVersionUID = 1L;
       
   
    public Logout() {
        super();
        
    }

    @Override
	@Action(value="logout")
	public String execute() throws Exception {
		HttpSession session = request.getSession();
		if( session != null ) {
			session.invalidate();
			session = null;
		}
		return null;
	}

}

package nigelhole.workspace.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * Servlet implementation class PasswordRemainder
 */
@ResultPath(value="/")
public class PasswordRemainder extends 	BaseAction {
	private static final long serialVersionUID = 1L;

	 
    /**
     * Default constructor. 
     */
    public PasswordRemainder() {
      super();
    }

	@Action(value="passwordremainder")
	public String execute() throws Exception {
	
		System.out.println(outputParameters());
		return null;
	}

}

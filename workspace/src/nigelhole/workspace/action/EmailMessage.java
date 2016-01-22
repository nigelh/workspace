package nigelhole.workspace.action;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * Servlet implementation class EmailMessage
 */


@ResultPath(value="/")
public class EmailMessage extends BaseAction {
	
	private static final long serialVersionUID = 1L;
       
    public EmailMessage() {
        super();
       
    }


	@Override
	@Action(value="email")
	public String execute() throws Exception {
		
		String name = parameters.get("name")[0];
		String email = parameters.get("email")[0];
		String subject = parameters.get("subject")[0];
		String text = parameters.get("text")[0];
		ServletOutputStream output = response.getOutputStream();
		response.setContentType("text/html");
		System.out.println("name="+name+",email="+email+",subject="+subject+",text="+text);
		output.print("name="+name+",email="+email+",subject="+subject+",text="+text);
		output.flush();
		output.close();

		return null;
	}



}

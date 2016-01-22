package nigelhole.workspace.action;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionSupport;

//@Namespace("/User")
@ResultPath(value="/")

public class ActionTest extends ActionSupport{

	private static final long serialVersionUID = 1L;

	@Override
	@Action(value="Welcome", results={
			@Result(name="success",location="news.jsp")
		})
	public String execute() throws Exception {
        return SUCCESS;
    }

}

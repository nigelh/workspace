package nigelhole.workspace.action;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * Servlet implementation class Feedback
 */
@ResultPath(value="/")
public class Feedback extends BaseAction {
	private static final long serialVersionUID = 1L;


	
    /**
     * Default constructor. 
     */
    public Feedback() {
        super();
    }
    
	@Override
	@Action(value="feedback",results={@Result(name="success",location="feedback.jsp")})
	public String execute() throws Exception  {
		System.out.println("feedback called");
		for(Iterator<Entry<String,String[]>> i = parameters.entrySet().iterator(); i.hasNext(); )
		{
		    Entry<String,String[]> entry = i.next();
		    System.out.println(entry.getKey()+":"+entry.getValue()[0]);
		}
		return SUCCESS;
	}
	

}

package nigelhole.workspace.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware ,ServletRequestAware, ParameterAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String,String[]> parameters;
	protected Map<String,Object> session;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}


	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}


	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
		
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
	protected String outputParameters() {
		StringBuilder out = new StringBuilder();
		for(Iterator<Entry<String,String[]>> i = parameters.entrySet().iterator(); i.hasNext(); )
		{
			Entry <String,String[]>e = i.next();
			out.append(e.getKey()+"="+e.getValue()[0]+"\n");
		}
		return out.toString();
	}
	
	protected String outputSession() {
		StringBuilder out = new StringBuilder();
		for(Iterator<Entry<String,Object>> i = session.entrySet().iterator(); i.hasNext(); )
		{
			Entry <String,Object>e = i.next();
			out.append(e.getKey()+"="+e.toString()+"\n");
		}
		return out.toString();
		
	}

}

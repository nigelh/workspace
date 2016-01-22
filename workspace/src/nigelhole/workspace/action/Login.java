package nigelhole.workspace.action;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * Servlet implementation class Login
 */
@ResultPath(value="/")
public class Login extends BaseAction {
	private static final long serialVersionUID = 1L;

	public static final String PASSWORD = "password";
	public static final String GUEST = "guest";
	public static final String NIGELH = "nigelh";
	public static final String USERNAME = "username";
	public static final String REMEMBER = "remember";
	public static final String LOGIN_SUCCESS = "jobtracker/index.html";
	public static final String LOGIN_FAILURE = "jobtracker/login.html";
	public static final String FAILURE = "failure";
	public static final String SUCCESS = "success";

	public Login() {
		super();

	}

	@Action(value = "login")
	public String login() throws Exception {

		ServletOutputStream output = response.getOutputStream();
		response.setContentType("text/html");
		if (request.getMethod().equals("GET")) {
			if (session != null) {
				String username = (String) session.get(USERNAME);
				output.print(username != null ? username : "");

			} else {
				output.print("");
			}
		} else {
			String username = request.getParameter(USERNAME);
			String password = request.getParameter(PASSWORD);
			String reply = FAILURE;

			if ((username.equals(NIGELH) || username.equals(GUEST))
					&& password.equals(PASSWORD)) {
				HttpSession session = request.getSession(true);
				session.setAttribute(USERNAME, username);
				reply = SUCCESS;
			}
			output.print(reply);
		}
		output.flush();
		output.close();
		return null;
	}

}

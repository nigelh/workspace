package nigelhole.workspace.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/*")
public class AllowAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
       
        HttpServletRequest request = (HttpServletRequest)sRequest;
        HttpServletResponse response = (HttpServletResponse)sResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); 
        chain.doFilter(request, response);
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
    
}

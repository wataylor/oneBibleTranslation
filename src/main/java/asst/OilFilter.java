package asst;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class OilFilter
 */
@WebFilter("/*")
public class OilFilter implements Filter {

    /**
     * Default constructor. 
     */
    public OilFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println(getClass().getSimpleName() + " Desroy called.");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest request = (HttpServletRequest) arg0;
	    HttpServletResponse response = (HttpServletResponse) arg1;

	    // String con = request.getContextPath();
	    String path = request.getServletPath();
	    // String url = request.getRequestURL().toString();
	    // String que = request.getQueryString();
	    // System.out.println("con " + con + " path " + path);
	    // System.out.println("url " + url + " que  " + que);
	    if (MapLanguagesToTables.LANG_2_TABLE.get(path.substring(1)) != null) {
	    	String where = "oneTran?lang=" + path.substring(1);
	    	// System.out.println(where);
	    	RequestDispatcher rd = request.getRequestDispatcher(where);
	    	rd.forward(request, response); // Does not tell browser
	    	return;
	    }
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println(getClass().getSimpleName() + " Init called.");
	}

}

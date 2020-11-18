package asst.one;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asst.MapLanguagesToTables;
import asst.Utils;

/**
 * Servlet implementation class OneTranslation
 */
@WebServlet(description = "Handle the display of one Bible translation", urlPatterns = { "/oneTran" })
public class OneTranslation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OneTranslation() {
        super();
    }

	/**
	 * @see HttpServlet#init(ServletConfig)
	 */
    public void init(ServletConfig config) throws ServletException {
    	System.out.println(getClass().getSimpleName() + " Init called");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF8");
		String lang = request.getParameter("lang");
		String key = MapLanguagesToTables.LANG_2_TABLE.get(lang);
		PrintWriter out = response.getWriter();
		if ((lang == null) || (lang.length() < 2) || (key == null)) {
			out.print("Did not recognize the translation language " + lang);
			return;
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//out.println("<br>Onward");
		//System.out.println(Utils.getHtmlHead());
		StringBuilder sb = new StringBuilder(Utils.getHtmlHead());
		Utils.replaceAll(sb, "__LANG__", lang);
		Utils.replaceAll(sb, "__COL_KEY__", key);
		out.print(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

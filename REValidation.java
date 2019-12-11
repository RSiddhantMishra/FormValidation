package formValidation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class REValidation
 */
@WebServlet("/REValidation")
public class REValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public REValidation() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
	    String name = request.getParameter("name");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("psw");
		String emailregex = "^[A-Za-z0-9+_.-]+@(.+)$";
		String namereg="[a-zA-Z]*";
		boolean Name=Pattern.compile(namereg).matcher(name).matches();
		boolean mail=Pattern.compile(emailregex).matcher(email).matches();
		String pswd = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
		boolean pswrd=Pattern.matches(pswd, password);
		String unameregex = "^[a-z0-9_-]{8,15}$";
		boolean uname=Pattern.matches(unameregex, username);
		if(Name==true && mail==true && uname==true && pswrd==true)
		{
			RequestDispatcher req = request.getRequestDispatcher("welcome.html");
			req.forward(request, response);
		}
		else
		{
			out.println("<title>Input Rules</title>" + "<body bgcolor=FFFFFF>" + 
		"<center><h1>Required Correct Input</h1></center>");
			if(Name==false)
			{
				out.println("<br><h3>Name Must be Characters only</h3>");
			}
			else if(mail==false)
			{
				out.println("<br><h3>Wrong Email Format</h3>");
			}
			else if(uname==false)
			{
				out.println("<h1>Incorrect Username</h1><br><h2>Whole combination is means:</h2><br>"+"<h3> 6 to 20 characters string with at least one digit,</h3>" + 
						" <br><h3>* one upper case letter, one lower case letter and one special symbol (“@#$%”).</h3><br>" + 
						" <h3>* This regular expression pattern is very useful to implement a strong and complex password.</h3>");
			}
			else if(pswrd==false)
			{
				out.println("<h1>Weak Password</h1><br><h2>Whole combination is means:</h2><br>" + 
			" <h3> 8 to 15 characters with any lower case character, digit or special symbol “_-” only.</h3>");
			}
			out.println("</body>");
		}
		out.close();
	}

}

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DbConnectionSingleton;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			DbConnectionSingleton connectionSingleton = DbConnectionSingleton.getInstance();
			Statement statement = connectionSingleton.getConnection().createStatement();
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter printWriter = response.getWriter();
			String sql = "SELECT book_name, isbn FROM library3.books;";
			ResultSet resultSet = statement.executeQuery(sql);
			printWriter.println("<head><meta charset='utf-8'><title>Rezultati naslova knjiga i ISBN</title></head>");
			printWriter.println("<body>");
			printWriter.println("<h1>Rezultati naslova knjiga i ISBN</h1>");
			printWriter.println("<br/>");
			while (resultSet.next()) {
				String bookName = resultSet.getString("book_name");
				String isbn = resultSet.getString("isbn");
				printWriter.println(bookName + " ");
				printWriter.println(isbn + "<br>" + "<p>");
			}
			printWriter.println("</body></html>");
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

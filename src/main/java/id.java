import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search_id")
public class id extends HttpServlet {
	Connection c1;
	Statement s1;
	ResultSet r1;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_1", "root", "Qwert@54321");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("empid"));
		PrintWriter pw = resp.getWriter();
		try {
			s1 = c1.createStatement();
			r1 = s1.executeQuery("select id,name,salary,date_format(birthdate,'" + "%D %M %Y"
					+ "'),date_format(joindate,'" + "%D %M %Y" + "') from employee where id = " + id + " ;");
			pw.print("<table style=" + "Color:black;" + "width:700px;" + "text-align:center;" + "margin:auto;" + ">");
			pw.print("<tr style=" + "font-size:25px;" + ">");
			pw.print("<th style=" + "padding:15px;" + ">id</th>");
			pw.print("<th>name</th>");
			pw.print("<th>salary</th>");
			pw.print("<th>birth date</th>");
			pw.print("<th>join date</th>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<th>------</th>");
			pw.print("<th>------------</th>");
			pw.print("<th>----------------</th>");
			pw.print("<th>-----------------------------</th>");
			pw.print("<th>-----------------------------</th>");
			pw.print("</tr>");
			while (r1.next()) {
				pw.print("<tr>");
				pw.print("<td style=" + "padding:5px;" + ">" + r1.getInt(1) + "</td>");
				pw.print("<td>" + r1.getString(2) + "</td>");
				pw.print("<td>" + r1.getDouble(3) + "</td>");
				pw.print("<td>" + r1.getString(4) + "</td>");
				pw.print("<td>" + r1.getString(5) + "</td>");
				pw.print("</tr>");
			}
			pw.print("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add_emp")
public class addemp extends HttpServlet {
	Connection c1;

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
		String name = req.getParameter("empname");
		double salary = Double.parseDouble(req.getParameter("empsalary"));
		String birthdate = req.getParameter("empdirthdate");
		String joindate = req.getParameter("empjoiningdate");
		PreparedStatement ps;
		PrintWriter pw = resp.getWriter();
		try {
			ps = c1.prepareStatement("insert into employee values (?,?,?,?,?);");
			ps.setInt(1, 0);
			ps.setNString(2, name);
			ps.setDouble(3, salary);
			ps.setString(4, birthdate);
			ps.setString(5, joindate);
			ps.execute();
			pw.println("data inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

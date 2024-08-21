package accountmanagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNumber = request.getParameter("studentId");
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("name");
        String date_of_birth = request.getParameter("date_of_birth");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        int phone_number = Integer.parseInt(request.getParameter("phone_number"));
        String address = request.getParameter("address");
        String enrollment_date = request.getParameter("enrollment_date");
        int program_id = Integer.parseInt(request.getParameter("program_id"));
        
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String username = "root";
        String password = "Rupa";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE student SET student_id=?, name=?, date_of_birth=?, gender=?, email=?, phone_number=?, address=?, enrollment_date=?, program_id=?  WHERE student_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, student_id);
            ps.setString(2, name);
            ps.setString(3, date_of_birth);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setInt(6, phone_number);
            ps.setString(7, address);
            ps.setString(8, enrollment_date);
            ps.setInt(9, program_id);
            ps.setString(10, rollNumber);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("viewAccounts.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

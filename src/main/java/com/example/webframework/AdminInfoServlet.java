import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminInfoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Simulate access control check
        if (isAdmin(request)) {
            // Return the admin information
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("Admin Information");
            out.println("</body></html>");
        } else {
            // Return an error message
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }
    }

    private boolean isAdmin(HttpServletRequest request) {
        // Simulate admin access control check
        String role = request.getParameter("role");
        return role.equals("admin");
    }
}

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by isuru on 5/25/15.
 */

public class Put extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String key = request.getParameter("key");
        String value = request.getParameter("value");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (key == null || value == null) {
            out.println("<HTML>");
            out.println("<HEAD><TITLE>Cache WRITE</TITLE></HEAD>");
            out.println("<BODY>");
            out.println("<BR/>");
            out.println("<BR/>");
            out.println("Bad Request, request parameters key and value are required");
            out.println("</BODY></HTML>");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Cache.put(key, value);

            out.println("<HTML>");
            out.println("<HEAD><TITLE>Cache WRITE</TITLE></HEAD>");
            out.println("<BODY>");
            out.println("<BR/>");
            out.println("<BR/>");
            out.println("Key = " + key);
            out.println("<BR/>");
            out.println("Value = " + value);
            out.println("</BODY></HTML>");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}

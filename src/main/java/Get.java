

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by isuru on 5/25/15.
 */
public class Get extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String key = request.getParameter("key");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (key == null) {
            out.println("<HTML>");
            out.println("<HEAD><TITLE>Cache READ</TITLE></HEAD>");
            out.println("<BODY>");
            out.println("<BR/>");
            out.println("<BR/>");
            out.println("Bad Request, request parameter key required for cache look up");
            out.println("</BODY></HTML>");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {

            out.println("<HTML>");
            out.println("<HEAD><TITLE>Cache READ</TITLE></HEAD>");
            out.println("<BODY>");
            out.println("<BR/>");
            out.println("<BR/>");
            out.println("Key = " + key);
            out.println("<BR/>");
            out.println("Value = " + Cache.get(key));
            out.println("</BODY></HTML>");

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}

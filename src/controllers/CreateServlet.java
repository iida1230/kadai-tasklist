package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import models.validators.TasksValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            Tasks ta= new Tasks();
            String content = request.getParameter("content");
            ta.setContent(content);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            ta.setCreated_at(currentTime);
            ta.setUpdated_at(currentTime);

            List<String> errors = TasksValidator.validate(ta);
            if(errors.size() > 0) {
                em.close();
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("tasks", ta);
                request.setAttribute("errors", errors);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
                rd.forward(request, response);
            } else {

            em.getTransaction().begin();
            em.persist(ta);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "登録が完了しました。");
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
}

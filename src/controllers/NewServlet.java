package controllers;
import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();
        Tasks ta = new Tasks();

     String content = "kadai";
     ta.setContent(content);

     Timestamp currentTime = new Timestamp(System.currentTimeMillis());
     ta.setCreated_at(currentTime);
     ta.setUpdated_at(currentTime);

     em.persist(ta);
     em.getTransaction().commit();

     response.getWriter().append(Integer.valueOf(ta.getId()).toString());

     em.close();
}

}

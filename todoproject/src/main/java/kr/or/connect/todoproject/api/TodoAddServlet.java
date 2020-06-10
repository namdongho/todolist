package kr.or.connect.todoproject.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.jdbcexam.dao.TodoDao;
import kr.or.connect.jdbcexam.dto.Todo;

@WebServlet("/TodoAddServlet")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html; charset=euc-kr");

		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String getsequence = request.getParameter("radio");
		int sequence;

		if (getsequence.equals("1")) {
			sequence = 1;
		} else if (getsequence.equals("2")) {
			sequence = 2;
		} else {
			sequence = 3;
		}

		Todo newTodo = new Todo(title, name, sequence);

		TodoDao dao = new TodoDao();

		dao.addTodo(newTodo);

		response.sendRedirect("/todoproject/TodoServlet");

	}

}

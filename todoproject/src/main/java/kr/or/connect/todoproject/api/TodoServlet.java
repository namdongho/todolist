package kr.or.connect.todoproject.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.jdbcexam.dao.TodoDao;
import kr.or.connect.jdbcexam.dto.Todo;

@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		TodoDao dao = new TodoDao();

		List<Todo> list = dao.getTodos();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(list);

		request.setAttribute("todo", list);
		request.setAttribute("json", json);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/main.jsp");
		rd.forward(request, response);

	}
}

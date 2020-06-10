package kr.or.connect.todoproject.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.connect.jdbcexam.dao.TodoDao;
import kr.or.connect.jdbcexam.dto.Todo;


@WebServlet("/TodoTypeServlet")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TodoTypeServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idS = request.getParameter("id");
		int id = Integer.parseInt(idS);
		TodoDao dao = new TodoDao();
		List<Todo> list = dao.getTodos();
		for(int i =0; i<list.size(); i++) {
			if(list.get(i).getId()==id) {
				if(list.get(i).getType().equals("TODO")) {
					list.get(i).setType("DOING");
				}else {
					list.get(i).setType("DONE");
				}
				dao.updateTodo(list.get(i));
				break;
			}
		}
		
	}

}

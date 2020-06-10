package kr.or.connect.jdbcexam.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.jdbcexam.dto.Todo;

public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/todo?useSSL=false&serverTimezone=UTC";
	private static String dbuser = "connectuser";
	private static String dbpasswd = "connect123!@#";

	public int updateTodo(Todo todo) {
		int updateCount = 0;

		String sql = "update todo set type = ? where id = ?";

		try (
				Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql);
		){
			Class.forName("com.mysql.jdbc.Driver");
			
			ps.setString(1, todo.getType());
			ps.setInt(2, todo.getId());

			updateCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} 


		return updateCount;
	}

	public List<Todo> getTodos() {
		List<Todo> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "select title, regdate, name, sequence, type, id from todo order by id asc";
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String title = rs.getString(1);
					Date reg = rs.getDate("regdate");
					String name = rs.getString(3);
					int sequence = rs.getInt("sequence");
					String type = rs.getString(5);
					int id = rs.getInt("id");

					Todo todo = new Todo(title, reg, name, sequence, type, id);
					list.add(todo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int addTodo(Todo todo) {

		int insertCount = 0;

		String sql = "insert into todo (title, name, sequence, type) values ( ?, ?, ?, 'TODO' )";

		try (
				Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql);
		) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());

			insertCount = ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return insertCount;
	}

	public Todo getTodo(Integer todoId) {

		Todo todo = null;
		
	
		String sql = "select title, regdate, name, sequence, type from todo where id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql);){
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			ps.setInt(1, todoId);
			try(ResultSet rs = ps.executeQuery();){
				if (rs.next()) {
					String title = rs.getString(1);
					Date reg = rs.getDate("regdate");
					String name = rs.getString(3);
					int sequence = rs.getInt("sequence");
					String type = rs.getString(5);
					todo = new Todo(title, reg, name, sequence, type);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return todo;
	}

}

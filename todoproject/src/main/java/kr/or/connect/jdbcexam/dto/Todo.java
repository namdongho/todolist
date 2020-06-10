package kr.or.connect.jdbcexam.dto;

import java.sql.Date;

public class Todo {
	private String title;
	private String name;
	private Date regdate;
	private Integer sequence;
	private String type;
	private Integer id;

	public Todo() {
		// TODO Auto-generated constructor stub
	}

	public Todo(String title, Date regdate, String name, Integer sequence, String type, Integer id) {
		super();
		this.type = type;
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.regdate = regdate;
		this.id = id;
	}
	public Todo(String title, Date regdate, String name, Integer sequence, String type) {
		super();
		this.type = type;
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.regdate = regdate;
	}
	public Todo(String title, String name, Integer sequence) {
		super();
		this.title = title;
		this.name = name;
		this.sequence = sequence;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Todo [title = " + title + ", name = " + name + ", sequence = " + sequence + " regdate = " + regdate
				+ "type = " + type + "]";
	}

}

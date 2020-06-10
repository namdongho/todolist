<%@page import="java.util.ArrayList" import="java.util.List"
	import="kr.or.connect.jdbcexam.dto.Todo"%>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link href="<%=request.getContextPath()%>/css/mainStyle.css"
	rel="stylesheet">
<title>Main</title>
</head>
<body>

	<% 
		List<Todo> todoAll = (List<Todo>) request.getAttribute("list");
		String json = (String)request.getAttribute("json");
	%>
	

	<div id="container1">
		<button class="newTodo" onclick="location='jsp/todoForm.jsp'">새로운	todo 만들기</button>
	</div>

	<div id="container2">

		<section class="listbox" id="todo">
			<div class="nav">
				<p class="h1">TODO</p>
			</div>
			<ul id="todoCol" class="col">
			</ul>
		</section>



		<section class="listbox">
			<div class="nav">
				<p class="h1">DOING</p>
			</div>
			<ul id="doingCol" class="col">
			</ul>
		</section>

		<section class="listbox">
			<div class="nav">
				<p class="h1">DONE</p>
			</div>
			<ul id="doneCol" class="col">
			</ul>
		</section>

	</div>
	
</body>

<script type="text/javascript">

	const todoCol = document.getElementById("todoCol");
	const doingCol = document.getElementById("doingCol");
	const doneCol = document.getElementById("doneCol");
	const date = new Array();
	const json = eval('<%=json%>');
	
	function ajax(id){
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST", "/todoproject/TodoTypeServlet?id="+id, true);
			
		xmlhttp.send(null);
	}
	
	
	function move(event){
		
			const btn = event.target;
			const ul = btn.parentNode.parentNode;
			var id = json[btn.id].id;
			
			if(json[btn.id].type==='TODO'){
				
				json[btn.id] = {
					title : json[btn.id].title,
					name : json[btn.id].name,
					regdate : date[btn.id],
					sequence : json[btn.id].sequence,
					type : 'DOING',
					id : id
				};
				todoCol.removeChild(ul);
				loadTodo(btn.id);
				ajax(id);
				
			}else{
				
				json[btn.id] = {
						title : json[btn.id].title,
						name : json[btn.id].name,
						regdate : date[btn.id],
						sequence : json[btn.id].sequence,
						type : 'DONE',
						id : id
				};
				doingCol.removeChild(ul);
				loadTodo(btn.id);
				ajax(id);
			}
			
	}
	
	
	function loadTodo(i){
		
			
			var currentTime = new Date(json[i].regdate);
			var month = ('00'+(currentTime.getMonth()+1)).slice(-2);
			var day = ('00'+currentTime.getDate()).slice(-2);
			var year = currentTime.getFullYear();
			date[i] = year + "-" + month + "-"+day;
			
			var ul = document.createElement("ul");
			var li01 = document.createElement("li");
			var li02 = document.createElement("li");
			li01.innerHTML = json[i].title;
			li02.innerHTML = '등록 날짜 : '+date[i]+', '+json[i].name+', '+json[i].sequence+" ";
			
			if(json[i].type==='TODO'||json[i].type==='DOING'){	
				var btn = document.createElement("button");
				btn.innerText="->";
				btn.addEventListener("click", move);
				btn.id = i;
				li02.appendChild(btn);
				ul.appendChild(li01);
				ul.appendChild(li02);
				
				if(json[i].type==='TODO'){
					todoCol.appendChild(ul);
				}else{
					doingCol.appendChild(ul);
				}
				
			}else{
				ul.appendChild(li01);
				ul.appendChild(li02);
				doneCol.appendChild(ul);
			}
		
	}
	
	function init(){
		for(var i=0; i<json.length; i++){
			loadTodo(i);
		}
		
	}
	
	
	document.addEventListener('DOMContentLoaded', function(event){
		init();
	});
</script> 

</html>
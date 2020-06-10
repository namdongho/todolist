const todoCol = document.getElementById("todoCol");
const doingCol = document.getElementById("doingCol");
const doneCol = document.getElementById("doneCol");
const date = new Array();

var oReq = new XMLHttpRequest();
oReq.open("GET", "/todoproject/Todoservlet/");


oReq.addEventListener("load", function() {
	
		var json = JSON.parse(this.responseText);
		console.log(json);
		function loadTodo(i) {

			var currentTime = new Date(json[i].regdate);
			var month = ('00' + (currentTime.getMonth() + 1)).slice(-2);
			var day = ('00' + currentTime.getDate()).slice(-2);
			var year = currentTime.getFullYear();
			date[i] = year + "-" + month + "-" + day;

			var ul = document.createElement("ul");
			var li01 = document.createElement("li");
			var li02 = document.createElement("li");
			li01.innerHTML = json[i].title;
			li02.innerHTML = '등록 날짜 : ' + date[i] + ', ' + json[i].name + ', '
					+ json[i].priority + " ";

			if (json[i].type === 'todo' || json[i].type === 'doing') {
				var btn = document.createElement("button");
				btn.innerText = "->";
				btn.addEventListener("click", move);
				btn.id = i;
				li02.appendChild(btn);
				ul.appendChild(li01);
				ul.appendChild(li02);

				if (json[i].type === 'todo') {
					todoCol.appendChild(ul);
				} else {
					doingCol.appendChild(ul);
				}

			} else {
				ul.appendChild(li01);
				ul.appendChild(li02);
				doneCol.appendChild(ul);
			}

		}

		function init() {
			for(var i=0; i<json.length; i++){
				loadTodo(i);
			}

		}

		
		function move(event) {

			const btn = event.target;
			const ul = btn.parentNode.parentNode;

			if (json[btn.id].type === 'todo') {

				json[btn.id] = {
					title : json[btn.id].title,
					name : json[btn.id].name,
					regdate : date[btn.id],
					priority : json[btn.id].priority,
					type : 'doing'
				};
				todoCol.removeChild(ul);
				loadTodo(btn.id);

			} else {

				json[btn.id] = {
					title : json[btn.id].title,
					name : json[btn.id].name,
					regdate : date[btn.id],
					priority : json[btn.id].priority,
					type : 'done'
				};
				doingCol.removeChild(ul);
				loadTodo(btn.id);

			}
		}
		init();
	
	/*var request = new XMLHttpRequest();
	function readyFunction() {
		request.open("GET", "/todoproject/TodoServlet");

		for (var i = 0; i < json.length; i++) {
			loadTodo(i);
		}
	}*/
});
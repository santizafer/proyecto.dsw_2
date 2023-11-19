const uri = window.location.search;
const params = new URLSearchParams(uri);

if(window.controlador_reserva) {
	document.querySelector("#btn_reservado").addEventListener("click", () => filtrar("cod_1") );
	document.querySelector("#btn_ejecucion").addEventListener("click", () => filtrar("cod_2") );
	document.querySelector("#btn_finalizado").addEventListener("click", () => filtrar("cod_3") );
	document.querySelector("#btn_cancelado").addEventListener("click", () => filtrar("cod_4") );
	document.querySelector("#btn_todos").addEventListener("click", () => filtrar("todos") );	
	
	function filtrar(estado) {
		let row = document.querySelectorAll(".row-reserva");
		for(let i=0; i<row.length; i++) {
				if (estado == "todos") {
					row[i].style.display = "block";
				} else {
					row[i].style.display = "block";
					if(row[i].classList[1] !== estado) {
						row[i].style.display = "none";
					}
				}		
		}		
	}	
	
}

if(window.location.href == "http://localhost:8080/Proyecto/reserva-login.jsp" || window.location.pathname == "/Proyecto/ingresar") {
	const frm = document.querySelector("form");
	frm.onsubmit = function(){myFunction()};
	function myFunction() {
		const user = document.querySelector("#txtCorreo").value;			
		localStorage.setItem("user", user);
	}
}
/*
if(localStorage.getItem("user") == "agente" || localStorage.getItem("user") == "osantibanez") {
	const user =  document.querySelector("#user");
	const panel =  document.querySelector("#panel");
	const session =  document.querySelector("#session");
	
	user.textContent = localStorage.getItem("user");
	panel.textContent = "Mi panel";
	panel.style.padding = "0 10px";
	panel.setAttribute("href", "http://localhost:8080/Proyecto/ControladorReservas");
	session.textContent = "Salir";
	session.addEventListener("click", ()=> { 
		localStorage.removeItem("user");
		history.pushState({}, null, "reserva-login.jsp"); 
	}); 	
}
*/
if(localStorage.getItem("user") == "agente") {
		
	if(window.location.href === "http://localhost:8080/Proyecto/ControladorReservas" || params.get("instruccion") == "actualizarBBDD") {
		let btns = document.querySelectorAll("a.btn-borrar");
		btns.forEach(e => {
			e.style.display = "none";
		})		
	}
	
	document.querySelectorAll(".col-1 .row-form input[type='number'], .col-1 .row-form input[type='text'], .col-1 .row-form input[type='date'], .col-1 .row-form input[type='time'], .col-1 .row-form textarea, .col-1 .row-form input[type='email']")
	.forEach(e => {e.style.color = "#a4a4a4"; e.style.borderColor = "#ed97b4", e.style.outline = "none"}) 
	
	if(params.get("instruccion") == "cargar") {	
		document.querySelector("#coddestino").setAttribute("readonly",true);	
		document.querySelector("#coddestino-fake").setAttribute("disabled",true);
		document.querySelector("#coddestino-fake").style.userSelect = "none";
		document.querySelector("#nombre").setAttribute("readonly",true);
		document.querySelector("#apellido").setAttribute("readonly",true);
		document.querySelector("#email").setAttribute("readonly",true);
		document.querySelector("#telMovil").setAttribute("readonly",true);
		document.querySelector("#fecVisita").setAttribute("readonly",true);
		document.querySelector("#horaVisita").setAttribute("readonly",true);
		document.querySelector("#numPersonas").setAttribute("readonly",true);
		document.querySelector("#infoAdicional").setAttribute("readonly",true);			
	}

}
var beforeBtn = 0;

var xhr =  0;

window.onload = initAll;

 
//var xhr =  0;

function initAll() {
	 beforeBtn = document.getElementById("news");
	 var width= ((document.getElementById("footer").offsetWidth))/4;
  
  	 document.getElementById("news").style.width=width+"px";
     document.getElementById("msg").style.width=width+"px";
     document.getElementById("tasks").style.width=width+"px";
     document.getElementById("settion").style.width=width+"px";
	
	
	 document.getElementById("msg").onclick=function(){
		initfooterbtncolor(this);
		beforeBtn = document.getElementById("msg");
		 }
	 document.getElementById("tasks").onclick=function(){
		initfooterbtncolor(this);
		beforeBtn = document.getElementById("tasks");
		 }
	 document.getElementById("settion").onclick=function(){
		initfooterbtncolor(this);
		beforeBtn = document.getElementById("settion");
		 }
	 document.getElementById("news").onclick =function(){
		initfooterbtncolor(this);
		beforeBtn = document.getElementById("news");
		getNewFile("DATA/WEB.txt");
		//addcontext();
		}
	
}
function addcontext(){
			  document.getElementById("context").innerHTML="<div class='contextrow'>something</div><hr>";
			}
function initfooterbtncolor(btn){
		beforeBtn.style.backgroundColor="#000"; 
		btn.style.backgroundColor="#069"; 
/*
		 document.getElementById("news").style.backgroundColor="#000"; 
		 document.getElementById("msg").style.backgroundColor="#000"; 
	     document.getElementById("tasks").style.backgroundColor="#000"; 
	     document.getElementById("settion").style.backgroundColor="#000"; 
    	 btn.style.backgroundColor="#069"; 
*/
		 
		}

function getNewFile(url) {
	//initfooterbtncolor(this);
	//addcontext();
	makeRequest(url);
	return false;
}

function makeRequest(url) {
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	}
	else {
		if (window.ActiveXObject) {
			try {
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e) { }
		}
	}

	if (xhr) {
		xhr.onreadystatechange = showContents;
		xhr.open("GET",url, true);
		xhr.setRequestHeader("Content-Type","text/html;charset=utf-8");
		xhr.send(null);
	}
	else {
		document.getElementById("context").innerHTML = "Sorry, but I couldn't create an XMLHttpRequest";
	}
}

function showContents() {
	if (xhr.readyState == 4) {
		if (xhr.status == 200) {
			if (xhr.responseXML && xhr.responseXML.childNodes.length > 0) {
				//var outMsg = getText(xhr.responseXML.getElementsByTagName("choices")[0]);
				document.getElementById("context").innerHTML = "NO  XML_FILE";
			}
			else {
				var result=xhr.responseText;
				while(xhr.responseText.readline())
				var outMsg = xhr.responseText;
			}
		}
		else {
			var outMsg = "There was a problem with the request " + xhr.status;
		}
		document.getElementById("context").innerHTML = outMsg;
	}
	
	function getText(inVal) {
		if (inVal.textContent) {
			return inVal.textContent;
		}
		return inVal.text;
	}
}	
function createRelease(){
      var modalcr = document.getElementById("ModalCR");
      var spancr = document.getElementsByClassName("closecr")[0];
  	  document.getElementById("createRelease").onclick = function() {
        modalcr.style.display = "block";
		  }
	     spancr.onclick = function() {
	        modalcr.style.display = "none";
	    }
	// When the user clicks anywhere outside of the modal, close it
	   window.onclick = function(event) {
  	  if (event.target == modalcr) {
  	    modalcr.style.display = "none";
  	  }
	   }
}

function sendMom(){
    var modaler = document.getElementById("ModalMOM");
    var spaner = document.getElementsByClassName("closemom")[0];
	  document.getElementById("sendMom").onclick = function() {
      modaler.style.display = "block";
		  }
	     spaner.onclick = function() {
	        modaler.style.display = "none";
	    }
	// When the user clicks anywhere outside of the modal, close it
	   window.onclick = function(event) {
	  if (event.target == modaler) {
	    modaler.style.display = "none";
	  }
	   }
}

function mydetails(){
    var modaldt = document.getElementById("ModalDTL");
    var spandt = document.getElementsByClassName("closedtl")[0];
	  document.getElementById("mydetails").onclick = function() {
      modaldt.style.display = "block";
		  }
	     spandt.onclick = function() {
	        modaldt.style.display = "none";
	    }
	// When the user clicks anywhere outside of the modal, close it
	   window.onclick = function(event) {
	  if (event.target == modaldt) {
	    modaldt.style.display = "none";
	  }
	   }
}
function addTeamMember(){
      var modalatm = document.getElementById("ModalATM");
      var spanatm = document.getElementsByClassName("closeatm")[0];
  	  document.getElementById("addTeam").onclick = function() {
        modalatm.style.display = "block";
		  }
	     spanatm.onclick = function() {
	        modalatm.style.display = "none";
	    }
	// When the user clicks anywhere outside of the modal, close it
	   window.onclick = function(event) {
  	  if (event.target == modalatm) {
  	    modalatm.style.display = "none";
  	  }
	   }
}
function deleteTeamMember(){
      var modaldtm = document.getElementById("ModalDTM");
      var spandtm = document.getElementsByClassName("closedtm")[0];
  	  document.getElementById("deleteTeam").onclick = function() {
        modaldtm.style.display = "block";
		  }
	     spandtm.onclick = function() {
	        modaldtm.style.display = "none";
	    }
	// When the user clicks anywhere outside of the modal, close it
	   window.onclick = function(event) {
  	  if (event.target == modaldtm) {
  	    modaldtm.style.display = "none";
  	  }
	   }
}
function assignSecondary(){
      var modalsm = document.getElementById("ModalSM");
      var spansm = document.getElementsByClassName("closesm")[0];
  	  document.getElementById("assignTeam").onclick = function() {
        modalsm.style.display = "block";
		  }
	     spansm.onclick = function() {
	        modalsm.style.display = "none";
	    }
	// When the user clicks anywhere outside of the modal, close it
	   window.onclick = function(event) {
  	  if (event.target == modalsm) {
  	    modalsm.style.display = "none";
  	  }
	   }
}
function Upload(){
  var fileUpload = document.getElementById("fileUpload");
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
        if (regex.test(fileUpload.value.toLowerCase())) {
            if (typeof (FileReader) != "undefined") {
                var reader = new FileReader();
                reader.onload = function (e) {
                    var table = document.createElement("table");
                    var rows = e.target.result.split("\n");
                    for (var i = 0; i < rows.length; i++) {
                        var cells = rows[i].split(",");
                        if (cells.length > 1) {
                            var row = table.insertRow(-1);
                            for (var j = 0; j < cells.length; j++) {
                                var cell = row.insertCell(-1);
                                cell.innerHTML = cells[j];
                            }
                        }
                    }
                    var dvCSV = document.getElementById("dvCSV");
                    dvCSV.innerHTML = "";
                    dvCSV.appendChild(table);
                }
                reader.readAsText(fileUpload.files[0]);
            } else {
                alert("This browser does not support HTML5.");
            }
        } else {
            alert("Please upload a valid CSV file.");
        }
}
function hide(){
	  if( document.getElementById("sm").value !="Yes"){
	    document.getElementById("addTeam").style.display = "none";
	    document.getElementById("deleteTeam").style.display = "none";
	    document.getElementById("createRelease").style.display = "none";
	    document.getElementById("sendMom").style.display="none";
	  }
	  document.getElementById("ModalDTL").style.display = "none";
	}
function del_hide(){
	if( document.getElementById("sm").value !="Yes"){
	    document.getElementById("del").style.display = "none";
	  }
}
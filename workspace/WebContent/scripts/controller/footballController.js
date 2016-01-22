var server = 'localhost';

function populateTeamsTable() {
	
	
	$.getJSON('http://'+server+':8080/workspace/json/teams', function(data){ 
		$("#team_table").find("tr:gt(0)").remove();
	     var trHTML = '';
	    $(data).each(function(idx, obj){
	        trHTML += '<tr><td>' + obj.name + '</td><td>' + obj.city + '</td><td>' + obj.owner + '</td><td>' + obj.competition + '</td><td>'+new Date(obj.created).toUTCString()+'</td><td> <button onclick=ShowPlayers('+obj.id+')>Show Players</button></td></tr>';
	        });
	         $('#team_table').append(trHTML);  
	    });
	
}

function ShowPlayers(id) {
	 $.getJSON('http://'+server+':8080/workspace/json/players/'+id, function(data){ 
			$("#player-table").find("tr:gt(0)").remove();
		     var trHTML = '';
		    $(data).each(function(idx, obj){
		        trHTML += '<tr><td>' + obj.name + '</td><td>' + obj.number + '</td></tr>';
		        });
		         $('#player-table').append(trHTML);  
		    }); 
  $("#players-dialog").dialog("open");
	
}
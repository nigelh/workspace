Football SIS Demo

Technology Stack
----------------
Eclipse Luna
Eclipse Maven Project
GitHub Repository nigelh/workspace (www.github.com,username:nigelh,password:manselt0n,email:nigelh@hotmail.com)
MySQL Database server: www.nigelhole.com port:3306 userName:nigelhol_work Password:sn00py schema:nigelhol_workspace 
Database URL: jdbc:mysql://www.nigelhole.com:3306/nigelhol_workspace
Backend Server: Struts2,Spring, Spring Rest, Log4J,hibernate JPA,Jackson, Tomcat7 
Front End: Broswer,Java Script,Jquery, Jquery-UI, AngularJ,css,html

Testing:
Using Junit testing with the Junit extension called restfuse that creates a HTML client that tests.

Projects
The Gitbub repository consists of two eclipse maven projects called workspace and workspace_junits.

Workspace Project
-----------------
The first project is a is a web application that I have developed and have extended to incorporate the
SIS DEMO. The startup page consists of a number of tabs, of which the named "SIS Football" displays
the rest api being used to populate the Team table which displays the Football Team details. For each team
the table has a "show player" button. This button upon selection will display the team player details within a 
popup dialog. These details are obtained by the implemented rest api. The following shows the api details:

@RequestMapping(value = "/teams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Team>> teams() {
        log.info("workspace/json/teams Service entered");
		try {
			List<Team> list = teamFascade.findAll();
			log.debug("response with status OK");
			return new ResponseEntity<List<Team>>(list, HttpStatus.OK);
		} catch (Throwable any) {
			log.fatal(any.getMessage(),any);
			return new ResponseEntity<List<Team>>((List<Team>) null,
					HttpStatus.BAD_REQUEST);
		}

	}
	
@RequestMapping(value = "/players/{team}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Player>> players(@PathVariable int team) {
		log.info("workspace/json/players/"+team+" Service entered");
		try {
			Team t = (Team) teamFascade.find(Long.valueOf(team));
			if (t == null) {
				log.error("team value is invalid");
				log.error("response is BAD_REQUEST");
				return new ResponseEntity<List<Player>>((List<Player>) null,
						HttpStatus.BAD_REQUEST);
			} else {
				log.error("response is OK");
				return new ResponseEntity<List<Player>>(t.getPlayers(),
						HttpStatus.OK);
			}
		} catch (Throwable any) {
			log.fatal("response is BAD_REQUEST",any);
			return new ResponseEntity<List<Player>>((List<Player>) null,
					HttpStatus.BAD_REQUEST);
		}

	}

Both API's return a JSON response.
Each method uses a DAO class called TeamFascade which provides access to the Mysql database.

These methods can be found the java class nigelhole.workspace.controllers.rest.controller.JobTrackerController.java

Some Important Files
--------------------
The persistence.xml found in the META-INF directory specifies the database connection details

<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
	<persistence-unit name="workspace">
		<class>nigelhole.workspace.entity.Registration</class>
		<class>nigelhole.workspace.entity.Player</class>
		<class>nigelhole.workspace.entity.Team</class>
		<properties>
         <property name="javax.persistence.jdbc.url" value="jdbc:mysql://www.nigelhole.com:3306/nigelhol_workspace"/>
         <property name="javax.persistence.jdbc.user" value="nigelhol_work"/>
         <property name="javax.persistence.jdbc.password" value="sn00py"/>
         <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
         <property name="eclipselink.logging.level" value="FINE"/>
         <property name="eclipselink.ddl-generation" value="none"/>
      </properties>
	</persistence-unit>
</persistence>

The footballController.js file found in the WebContent/scripts/controller directory
provides the javascript functions that uses the implemented REST interface.

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

Workspace_Junits Project
------------------------
This project uses the restfuse java package to test the above REST api. For each rest service, the junit test
checks the servers response. See below for the Junits implementation found in the java source file called FootballTests.java.

@RunWith( HttpJUnitRunner.class )
public class FootballTests {
	@Rule
	  public Destination restfuse = new Destination( this, "http://localhost:8080" );
	  
	  @Context
	  private Response response;
	  
	  @HttpTest( method = Method.GET, path = "/workspace/json/teams" ) 
	  public void checkRestTeamsStatus() {
	    assertOk( response );
	  }
	  
	  @HttpTest( method = Method.GET, path = "/workspace/json/players/0" ) 
	  public void checkRestPlayers0Status() {
	    assertOk( response );
	  }
	  
	 
	  
	  @HttpTest( method = Method.GET, path = "/workspace/json/players/1" ) 
	  public void checkRestPlayers1Status() {
	    assertOk( response );
	  }
	  
	  @HttpTest( method = Method.GET, path = "/workspace/json/players/2" ) 
	  public void checkRestPlayers2Status() {
	    assertOk( response );
	  }
	  
	  @HttpTest( method = Method.GET, path = "/workspace/json/players/3" ) 
	  public void checkRestPlayers3Status() {
	    assertOk( response );
	  }
	  
	  @HttpTest( method = Method.GET, path = "/workspace/json/players/4" ) 
	  public void checkRestPlayers4Status() {
	    assertOk( response );
	  }
	  
	  @HttpTest( method = Method.GET, path = "/workspace/json/players/5" ) 
	  public void checkRestPlayers5Status() {
	    assertBadRequest(response);
	  }
	  
}

These tests can be executed when the workspace web application is up and running.


Installation
------------
Both projects are held within the GitHub repository www.github.com. The following are the repository details:
url:https://github.com/nigelh/workspace.git
username:nigelh
password:manselt0n

Procedure:
You will need the following technologies: Tomcat7,Maven and Git. These are bundled together with an Eclipse IDE 
installation. I assume that you are familiar with Eclipse IDE. I am using Eclipse Version Luna. The following are the
steps to setup Eclipse for GIT and Maven. The Luna Eclipse version has these technologies already installed as plugins.

1) First install Eclipse Luna from the URL: https://projects.eclipse.org/releases/luna
2) Upon installation, open up Eclipse and configure it for GIT. GO to the Git preferences and
assign the user.name and user.email property settings.
3) Now you can import the workspace and workspave_junit projects by selecting the File->import->git option.
4) fill in the required git url:https://github.com/nigelh/workspace.git and enter the user name of "nigelh" and my
password of "manselt0n" and proceed to download the master-branch (by default option). Eclipse will proceed to download
both projects into the workspace.
5)Now install Tomcat7 by selecting the "server" tab and by either downloading or assign an existing Tomcat7 installation.
6)Upon installing Tomcat7, add the "workspace" project to the servers deployment list.
7) Now start tomcat and point your browser to http://localhost:8080/workspace
8) Now select the "Football SIS" tab and you see a table populated by Team details brought down from the MySQL database
9) Now select one of the "Show Players" buttons in  the table and see a dialog popup showing the Team's Players.

Executing the Junits in the Workspace_Junit project.
----------------------------------------------------
1) Left click the Football.java file in the Workspace_junits project and select tp "run As"->junit"
2) You will see the Junit suite of tests running against the Tomcat server with a "green" run.

Inspecting The MySQL database
----------------------------
1) In Eclipse select the "Database Development" perspective and setup a MySQL connection.
2) You may have to setup the MySQL connector jar which should be found in your local Maven Repository under the
   directory path of "mysq.mysql-connector-java/5.1.6/mysql-connector-java-5.1.6.jar"
3) Setup a remote connection using the URL:jdbc:mysql://www.nigelhole.com:3306/nigelhol_workspace and using the
   username of nigelhol_work and password of "sn00py" i.e. two zeros
4) Select to Test the connection and select to "Finish"

5) Open the database connection and until "tables" to see two tables called Team and Players. The Team table has a
   one-to-many relationship with Players. And Players have a many-to-one relationship with Team.












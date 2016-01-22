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






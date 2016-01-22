package workspace_junits;

import static com.eclipsesource.restfuse.Assert.*;

import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

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




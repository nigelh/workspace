package nigelhole.workspace.rest.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import nigelhole.workspace.entity.Player;
import nigelhole.workspace.entity.Registration;
import nigelhole.workspace.entity.Team;
import nigelhole.workspace.fascade.PlayerFascade;
import nigelhole.workspace.fascade.RegistrationFascade;
import nigelhole.workspace.fascade.TeamFascade;
import nigelhole.workspace.rest.bean.User;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class JobTrackerController {
	private Logger log = Logger.getLogger("workspacelogger");
	
	private RegistrationFascade registrationFascade = new RegistrationFascade();
	private TeamFascade teamFascade = new TeamFascade();

	public static HttpSession getSession(boolean create) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(create); // true == allow create
	}

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

	@RequestMapping(value = "login/{username}/{password}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE )
	public User login(@PathVariable String username,
			@PathVariable String password) {
		List<User> userList = createUserList();
		for (User u : userList) {
			if (u.getUserName().equals(username)
					&& u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> user(@RequestParam("callback") String callback) {
		HttpSession session = getSession(false);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("USER");
		}
		return ResponseEntity.ok().body(user);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(@RequestParam("callback") String callback,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		HttpSession session = getSession(false);
		User user = null;
		if (session != null
				&& (user = (User) session.getAttribute("USER")) != null) {
			if (user.getEmail().equals(email)
					&& user.getPassword().equals(password))
				session.invalidate();
			session = null;

		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Registration> register(
			@RequestBody Registration register) {
		System.out.println(register);
		try {
			List<Registration> list = registrationFascade.findByEmailPassword(
					register.getEmail(), register.getPassword());
			if (list.size() > 0) {
				return new ResponseEntity<Registration>(register,
						HttpStatus.FOUND);
			} else {
				registrationFascade.begin();
				register.setDate(new Date());
				registrationFascade.create(register);
				registrationFascade.commit();
				return new ResponseEntity<Registration>(register, HttpStatus.OK);
			}
		} catch (Throwable any) {
			System.out.println(any.getMessage());
		}
		return new ResponseEntity<Registration>(register,
				HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(
			@RequestParam("callback") String callback,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		System.out.println("callback=" + callback + ",email=" + email
				+ ",password=" + password);
		User user = new User();
		if (email.equals("nigelh@hotmail.com") && password.equals("snoopy")) {
			user = new User(0L, "nigelh", "Nigel", "Hole",
					"nigelh@hotmail.com", "snoopy", "ADMIN", "01614286850",
					"07765343099");
			HttpSession session = getSession(true);
			session.setAttribute("USER", user);
			return ResponseEntity.ok().body(user);
		} else {
			return ResponseEntity.ok().body(user);
		}

	}

	@RequestMapping(value = "country/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getCountryById(@PathVariable int id) {
		List<User> listOfUsers = createUserList();

		for (User u : listOfUsers) {
			if (u.getId() == id)
				return u;
		}

		return null;
	}

	public List<User> createUserList() {
		User user1 = new User(1L, "nigelh", "Nigel", "Hole",
				"nigelh@hotmail.com", "password", "ADMIN", "0161 428 6850",
				"0776 534 3099");
		User user2 = new User(2L, "davids", "David", "Jones",
				"davidj@hotmail.com", "kig", "USER", "0161 429 6740",
				"0776 538 8253");

		List<User> listOfUsers = new ArrayList<User>();
		listOfUsers.add(user1);
		listOfUsers.add(user2);
		return listOfUsers;
	}
}
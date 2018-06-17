package awake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
	@Autowired
	private MenuDescDAO menuDAO;
	
	@RequestMapping(value="/menudesc", method=RequestMethod.GET)
	public ResponseEntity<Iterable<MenuDesc>> getDepartments() {
	Iterable<MenuDesc> depts = menuDAO.findAll();
	return new ResponseEntity<Iterable<MenuDesc>>(depts, HttpStatus.OK);
	}
}

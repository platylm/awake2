package awake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenuDescApplication implements CommandLineRunner {
	@Autowired
	private MenuDescDAO menuDAO;

	public static void main(String[] args) {
		SpringApplication.run(MenuDescApplication.class, args);

	}

	@Override
	public void run(String... strings) {
		//MenuDesc menu = new MenuDesc();
		//menu.setMName("test");
		//menu.setDiscount(1);
		//menu = menuDAO.save(menu);
		//System.out.println(menu.getMName());
	}
}

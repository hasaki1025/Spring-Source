package Component;

import Bean.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

	@Autowired
	MyBean myBean;

	private static String name;
	private static String id;
}

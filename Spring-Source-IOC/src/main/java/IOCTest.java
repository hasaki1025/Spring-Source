import Config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.lang.*;
public class IOCTest {
	public static void main(String[] args) {
		//指定配置类创建ApplicationContext
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		System.out.println("aa");
	}
}

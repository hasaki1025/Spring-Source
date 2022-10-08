import Bean.MyBean;
import Config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.lang.*;
public class IOCTest {
	public static void main(String[] args) {
		//指定配置类创建ApplicationContext
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		MyBean bean = context.getBean(MyBean.class);
		bean.SayYourName();
	}
}

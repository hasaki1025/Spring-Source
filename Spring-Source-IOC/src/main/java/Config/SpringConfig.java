package Config;
import Bean.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//Configuration文件等同与Spring的xml文件
@ComponentScan(basePackages = {"Component"})
public class SpringConfig {

	@Bean("MyBean")//注册Bean到IOC容器中
	MyBean myBean()
	{
		return new MyBean();
	}
}


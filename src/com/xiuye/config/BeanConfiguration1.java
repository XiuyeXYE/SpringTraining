package com.xiuye.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.xiuye.bean.Car;
import com.xiuye.bean.Student;
import com.xiuye.bean.WhiteCar;
import com.xiuye.component.ComponentForStudent2;
import com.xiuye.config.aspect.AspectConfig;
import com.xiuye.config.condition.StudentCondition;

@Configuration
@ComponentScan({
		"com.xiuye.component"/* , "com.xiuye.config.aspect" *//*
																 * @Aspect可以生效,
																 * 但是必须让spring能识别
																 * ,如@Component,
																 * @
																 * Configuration等
																 */ })
@EnableAspectJAutoProxy/*(proxyTargetClass=true)*/
@Profile("dev")
@PropertySource("test.properties")
@Import({AspectConfig.class})/*@Aspect可以生效,相当于Configuration类作用,都是配置类*/
public class BeanConfiguration1 {

	/**
	 * 启用el表达式 ("${express}")解析功能,否则原样子语句注入 必须为static否则警告:
	 *
	 *
	 * 警告: @Bean method BeanConfiguration1.pspc is non-static and returns an
	 * object assignable to Spring's BeanFactoryPostProcessor interface. This
	 * will result in a failure to process annotations such
	 * as @Autowired, @Resource and @PostConstruct within the method's
	 * declaring @Configuration class. Add the 'static' modifier to this method
	 * to avoid these container lifecycle issues; see @Bean javadoc for complete
	 * details.
	 *
	 * @return
	 */

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Conditional(StudentCondition.class)
	public Student student() {
		return new Student("xiuye", "man", 18, 99);
	}

	/**
	 * @param name
	 * @param sex
	 * @param age
	 * @param level
	 * @return
	 */
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 必须原生,否则no longer has
													// any effect
	public Student student(String name, String sex, int age, int level) {
		return new Student(name, sex, age, level);
	}

	@Bean
	public ComponentForStudent2 cfs2(Student s) {
		ComponentForStudent2 cfs2 = new ComponentForStudent2();
		cfs2.setStudent(s);
		return cfs2;
	}

	@Bean
	public Car car() {
		return new Car();
	}

	@Bean
	public WhiteCar wCar() {
		return new WhiteCar();
	}

}

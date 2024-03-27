package springbootoracledatabase.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextTeller implements CommandLineRunner {
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
      //  Arrays.asList(applicationContext.getBeanDefinitionNames()).stream().sorted().forEach(System.out::println);

    }
}

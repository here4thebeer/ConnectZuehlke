package ch.zuehlke.fullstack.ConnectZuehlke;

import ch.zuehlke.fullstack.ConnectZuehlke.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ConnectZuehlkeApplication implements ApplicationRunner {

    private final CrawlerService crawlerService;

    @Autowired
    public ConnectZuehlkeApplication(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConnectZuehlkeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        crawlerService.crawlInsight();
    }
}


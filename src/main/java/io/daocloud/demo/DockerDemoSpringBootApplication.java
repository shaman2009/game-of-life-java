package io.daocloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@RestController
public class DockerDemoSpringBootApplication {

    @Resource
    VisitorRepository visitorRepository;

    public static void main(String[] args) {
        SpringApplication.run(DockerDemoSpringBootApplication.class, args);
    }

    @RequestMapping("")
    public String visit(HttpServletRequest request) {

        Visitor visitor = new Visitor(UUID.randomUUID().toString(),
                request.getRemoteAddr(),
                new Date());

        visitorRepository.save(visitor);

        Long count = visitorRepository.count();

        return String.format("你是来自%s的第%d位访问者。", request.getRemoteAddr(), count);
    }
}

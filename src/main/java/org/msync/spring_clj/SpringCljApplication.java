package org.msync.spring_clj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.blockhound.BlockHound;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
@EnableWebFlux
public class SpringCljApplication {

    public static void main(String[] args) {
        // Initialize blackhound to catch any blocking calls.
//        BlockHound.install();
//        ReactorDebugAgent.init();
        SpringApplication.run(SpringCljApplication.class, args);
    }

}

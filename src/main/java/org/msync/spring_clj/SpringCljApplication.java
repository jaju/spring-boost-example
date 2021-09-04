package org.msync.spring_clj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import reactor.blockhound.BlockHound;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
@ComponentScan("org.msync.spring_boot_bugger")
public class SpringCljApplication {

    public static void main(String[] args) {
        BlockHound.install();
        ReactorDebugAgent.init();
        SpringApplication.run(SpringCljApplication.class, args);
    }

}

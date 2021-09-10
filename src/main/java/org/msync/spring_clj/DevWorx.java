package org.msync.spring_clj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Profile("dev")
@Component
@ComponentScan("org.msync.spring_boost")
@RestController
@RequestMapping("/spring")
public class DevWorx {

    @GetMapping("/")
    Mono<String> get() {
        return Mono.just("Hello, World!");
    }

}

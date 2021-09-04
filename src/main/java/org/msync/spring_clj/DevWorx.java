package org.msync.spring_clj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
@ComponentScan("org.msync.spring_boot_bugger")
public class DevWorx {
}

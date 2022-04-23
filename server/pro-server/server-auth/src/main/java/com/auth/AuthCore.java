package com.auth;

import com.common.CommonCore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import({ CommonCore.class})
@SpringBootApplication
public class AuthCore {
}

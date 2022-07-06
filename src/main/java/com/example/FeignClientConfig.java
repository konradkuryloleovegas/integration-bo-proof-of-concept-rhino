package com.example;

import com.gearsofleo.rhino.core.bootstrap.AppProfile;
import com.gearsofleo.rhino.feign.config.FeignClientConfigurationInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile(AppProfile.NORMAL)
@Configuration
@Import(FeignClientConfigurationInitializer.Config.class)
public class FeignClientConfig {
}

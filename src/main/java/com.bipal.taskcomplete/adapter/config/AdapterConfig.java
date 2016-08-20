package com.bipal.taskcomplete.adapter.config;

import com.bipal.taskcomplete.adapter.TaskToRegisteredTaskAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Bean
    TaskToRegisteredTaskAdapter taskToRegisteredTaskAdapter(){
        return new TaskToRegisteredTaskAdapter();
    }
}

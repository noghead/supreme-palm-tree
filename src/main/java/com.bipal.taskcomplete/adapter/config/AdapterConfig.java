package com.bipal.taskcomplete.adapter.config;

import com.bipal.taskcomplete.adapter.DoneTaskToTaskAdapter;
import com.bipal.taskcomplete.adapter.RegisteredTaskToTaskAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Bean
    RegisteredTaskToTaskAdapter registeredTaskToTaskAdapter(){
        return new RegisteredTaskToTaskAdapter();
    }

    @Bean
    DoneTaskToTaskAdapter doneTaskToTaskAdapter(){
        return new DoneTaskToTaskAdapter();
    }
}

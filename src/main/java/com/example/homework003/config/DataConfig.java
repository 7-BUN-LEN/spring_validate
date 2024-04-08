package com.example.homework003.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataConfig {
    @Bean
    public DataSource dataSource(){
        return new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/homework003",
                "postgres",
                "246800"
        );
    }
}

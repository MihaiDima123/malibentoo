package com.malibentoo.configuration.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {
    @Value("${app.datasource.username}")
    private String datasourceUsername;

    @Value("${app.datasource.username}")
    private String datasourcePassword;

    @Value("${app.datasource.connectionUrl}")
    private String url;

    @Value("${app.datasource.connection-pool-size}")
    private int connectionPoolSize;

    @Value("${app.datasource.max-idle}")
    private int maxIdle;

    @Value("${app.datasource.leak-detection-threshold}")
    private int leakDetectionThreshold;

    @Bean
    public DataSource dataSource() {
        var hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(datasourceUsername);
        hikariDataSource.setPassword(datasourcePassword);
        hikariDataSource.setMaximumPoolSize(connectionPoolSize);
        hikariDataSource.setMinimumIdle(maxIdle);
        hikariDataSource.setLeakDetectionThreshold(leakDetectionThreshold);

        return hikariDataSource;
    }
}

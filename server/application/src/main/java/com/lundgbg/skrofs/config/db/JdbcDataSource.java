package com.lundgbg.skrofs.config.db;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Profile("default")
@Configuration
public class JdbcDataSource {

	@Value("${jdbc.driverClassName}")
	private String jdbcDriverClassName;

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	@Bean(destroyMethod = "close")
	public DataSource createPooledDataSource() throws PropertyVetoException {
		final ComboPooledDataSource dataSource = new ComboPooledDataSource();

		dataSource.setDriverClass(jdbcDriverClassName);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setMaxStatements(180);

		return dataSource;
	}

}

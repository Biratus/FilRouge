package fr.dta.formafond.repository;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JpaConfiguration {

	@Autowired
	private Environment env;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
		ds.setUsername(env.getRequiredProperty("jdbc.username"));
		ds.setPassword(env.getRequiredProperty("jdbc.password"));
		ds.setDriverClass("org.postgresql.Driver");
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("fr.dta.model");

		JpaVendorAdapter vAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vAdapter);
		emf.setJpaProperties(getProperties());

		return emf;
	}

	private Properties getProperties() {
		Properties prop = new Properties();
		set(prop, "hibernate.hbm2ddl.auto");
		set(prop, "hibernate.dialect");
		set(prop, "hibernate.format_sql");
		set(prop, "hibernate.use_second_level_cache");
		set(prop, "hibernate.show_sql");
		return prop;
	}

	private void set(Properties prop, String key) {
		prop.setProperty(key, env.getRequiredProperty(key));
	}
}

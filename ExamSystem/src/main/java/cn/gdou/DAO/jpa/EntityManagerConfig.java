package cn.gdou.DAO.jpa;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("cn.gdou.DAO.ds")
@EnableTransactionManagement
@EnableJpaRepositories("cn.gdou.DAO.repository")
public class EntityManagerConfig {

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManager(
             DataSource ds, JpaVendorAdapter adapter){
        LocalContainerEntityManagerFactoryBean emfb=
                new LocalContainerEntityManagerFactoryBean();
        emfb.setPackagesToScan("cn.gdou.dao.entity");
        emfb.setDataSource(ds);
        emfb.setJpaVendorAdapter(adapter);
        Properties properties=new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        emfb.setJpaProperties(properties);
        return emfb;
    }

    @Bean
    public JpaVendorAdapter adapter(){
        HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL57InnoDBDialect");
        return adapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager();
    }

    @Bean
    public BeanPostProcessor persistenceTransition(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

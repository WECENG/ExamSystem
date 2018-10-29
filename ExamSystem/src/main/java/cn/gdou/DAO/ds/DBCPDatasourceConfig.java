package cn.gdou.DAO.ds;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

@Configuration
public class DBCPDatasourceConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.username}")
    private String mysqlUser;
    @Value("${spring.datasource.password}")
    private String mysqlpassword;
    @Value("${spring.datasource.dbcp2.initial-size}")
    private int initSize;
    @Value("${spring.datasource.dbcp2.max-total}")
    private int maxSize;
    @Value("${spring.datasource.dbcp2.max-wait-millis}")
    private int maxTime;

    @Bean
    public BasicDataSource dataSource()throws PropertyVetoException {
        BasicDataSource ds=new BasicDataSource();
        ds.setUrl(url);
        ds.setDriverClassName(driverName);
        ds.setUsername(mysqlUser);
        ds.setPassword(mysqlpassword);
        ds.setInitialSize(initSize);
        ds.setMaxTotal(maxSize);
        ds.setMaxWaitMillis(maxTime);
        return ds;
    }
}

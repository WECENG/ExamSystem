package cn.gdou.morphia.dao.transaction;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * @Auther: werson
 * @Date: 2018/11/4/004 17:45
 * @Description:    MongoDB事务
 */
@Configuration
public class MongoTransManager extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String mongoUrl;
    @Value("${spring.data.mongodb.port}")
    private int mongoPort;
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Bean("mongoTransaction")
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(mongoUrl,mongoPort);
    }

    @Override
    protected String getDatabaseName() {
        return mongoDatabase;
    }
}

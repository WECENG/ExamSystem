package cn.gdou.morphia.dao.store;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Auther: werson
 * @Date: 2018/10/29/029 15:22
 * @Description:  该类用于获取 Morphia库的Datastore对象
 */
@Component
public class DatastoreConfig {

    final static Morphia morphia=new Morphia();

    @Value("${spring.data.mongodb.host}")
    private String mongoUrl;
    @Value("${spring.data.mongodb.port}")
    private int mongoPort;
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;


    public Datastore getDs(){
        morphia.mapPackage("cn.gdou.morphia.model");
        MongoClient mongoClient=new MongoClient(mongoUrl,mongoPort);
        Datastore datastore=morphia.createDatastore(mongoClient,mongoDatabase);
        return datastore;
    }
}

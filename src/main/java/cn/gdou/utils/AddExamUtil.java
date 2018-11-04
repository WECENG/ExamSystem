package cn.gdou.utils;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


public class AddExamUtil {
    public static final Logger log=LoggerFactory.getLogger(AddExamUtil.class);

    public static void addExam(int typeNum,int page) {
        RestTemplate rest = new RestTemplate();
        String result = rest.getForObject("http://apicloud.mob.com/tiku/kemu"+typeNum+"/query?key=520520test&page="+page+"&size=20",
                String.class);
        try {
            JSONObject jsonObject = new JSONObject(result);
            String str = jsonObject.getString("result");
            MongoClient mongoClient = new MongoClient();
            MongoDatabase database = mongoClient.getDatabase("ExamPool");
            MongoCollection<DBObject> collection = database.getCollection("ExamPool", DBObject.class);
            DBObject bson = (DBObject) JSON.parse(str);
            collection.insertOne(bson);
            log.info("第"+page+"\t\t套试题添加成功!");
        } catch (Exception e) {

        }
    }
}

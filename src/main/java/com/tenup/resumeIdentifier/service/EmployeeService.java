package com.tenup.resumeIdentifier.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.tenup.resumeIdentifier.utils.AppConstant;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class EmployeeService<T> {


    private final MongoDatabase mongoDb;

    public EmployeeService(MongoDatabase mongoDb) {
        this.mongoDb = mongoDb;
    }

    public void saveEmployeeDetail(Map<String, T> employeeDetails) throws JsonProcessingException {
        var empDocument = Document.parse(new ObjectMapper().writeValueAsString(employeeDetails));
        if (!empDocument.containsKey("_id")) {
            empDocument.put("created_at", new Date());
            mongoDb.getCollection(AppConstant.EMPLOYEE_DETAIL_COLLECTION_NAME).insertOne(empDocument);
        } else {
            String id = (String) empDocument.get("_id");
            var objectId = new ObjectId(id);
            empDocument.put("_id", objectId);
            empDocument.remove("created_at");
            mongoDb.getCollection(AppConstant.EMPLOYEE_DETAIL_COLLECTION_NAME)
                    .updateOne(new BasicDBObject("_id", objectId), new BasicDBObject("\\$set", empDocument));

        }
    }
}

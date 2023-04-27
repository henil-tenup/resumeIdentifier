package com.tenup.resumeIdentifier.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.tenup.resumeIdentifier.AbstractController;
import com.tenup.resumeIdentifier.utils.AppConstant;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;

@Service
public class EmployeeService<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private final MongoDatabase mongoDb;

    public EmployeeService(MongoDatabase mongoDb) {
        this.mongoDb = mongoDb;
    }

    @Value("${resume.local.storage.path}")
    private String resumeLocation;

    public void saveEmployeeDetail(Map<String, T> employeeDetails, MultipartFile file) throws IOException {
        var empDocument = Document.parse(new ObjectMapper().writeValueAsString(employeeDetails));
        if (!empDocument.containsKey("_id")) {
            storeFile(file);
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

    public void storeFile(MultipartFile file) throws IOException {

        FileOutputStream out = null;
        try {
            File path = new File(resumeLocation + file.getOriginalFilename());
            Files.createDirectories(Paths.get(resumeLocation));
            out = new FileOutputStream(path);
            out.write(file.getBytes());
            LOGGER.debug("File is uploaded successfully!");
        } catch (Exception e) {
            LOGGER.error("Exception thrown : {} ", e);
        } finally {
            if (out != null)
                out.close();
        }
    }
}

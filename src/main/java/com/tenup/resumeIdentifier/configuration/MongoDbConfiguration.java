package com.tenup.resumeIdentifier.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDbConfiguration {
    @Value("${resumeIdentifier.mongo.host}")
    private String mongoHost;

    @Value("${resumeIdentifier.mongo.port}")
    private String mongoPort;

    @Value("${resumeIdentifier.mongo.database}")
    private String mongodb;

    @Value("${resumeIdentifier.mongo.connectTimeout}")
    private Integer connectTimeout;

    @Bean
    public MongoClient mongoClient() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        var option = MongoClientOptions.builder()
                .sslEnabled(false).applicationName("resumeIdentifier")
                .codecRegistry(codecRegistry)
                .connectTimeout(connectTimeout)
                .build();
        return new MongoClient(mongoHost+":"+mongoPort);
    }

    @Bean
    public  MongoDatabase getMongoDatabase(){
        return mongoClient().getDatabase(mongodb);
    }

}

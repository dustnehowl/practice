package com.example.practice2.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class GoogleStorageConfig {
//    @Bean
//    public Storage storage(){
//        StorageOptions storageOptions = StorageOptions.getDefaultInstance()
//                .toBuilder()
//                .setProjectId("friendly-legacy-382417")
//                .build();
//        return storageOptions.getService();
//    }
//}

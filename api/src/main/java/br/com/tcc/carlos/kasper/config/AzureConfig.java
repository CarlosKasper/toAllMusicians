package br.com.tcc.carlos.kasper.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfig {

    @Value("${cloud.azure.storage-account.access-key}")
    private String accessKey;

    @Bean
    public BlobServiceClient blobClient() {
        return new BlobServiceClientBuilder()
                .connectionString(accessKey)
                .buildClient();
    }
}
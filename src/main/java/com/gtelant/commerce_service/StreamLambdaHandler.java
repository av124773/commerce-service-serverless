package com.gtelant.commerce_service;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamLambdaHandler implements RequestStreamHandler {
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            // 指向您的 Spring Boot 主應用程式類別
            // CommerceServiceApplication.class
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(CommerceServiceApplication.class);

            // 為了 SnapStart 效能優化：預先初始化
            handler.onStartup(lambdaContext -> {});
        } catch (Exception e) {
            // 處理初始化錯誤
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}
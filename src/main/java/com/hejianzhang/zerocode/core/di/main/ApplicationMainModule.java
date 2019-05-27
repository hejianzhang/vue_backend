package com.hejianzhang.zerocode.core.di.main;


import com.alibaba.fastjson.JSON;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.hejianzhang.zerocode.core.di.module.GsonModule;
import com.hejianzhang.zerocode.core.di.module.HttpClientModule;
import com.hejianzhang.zerocode.core.di.module.ObjectMapperModule;
import com.hejianzhang.zerocode.core.di.module.PropertiesInjectorModule;
import com.hejianzhang.zerocode.core.engine.executor.JavaExecutor;
import com.hejianzhang.zerocode.core.engine.executor.JavaExecutorImpl;
import com.hejianzhang.zerocode.core.engine.executor.JsonServiceExecutor;
import com.hejianzhang.zerocode.core.engine.executor.JsonServiceExecutorImpl;
import com.hejianzhang.zerocode.core.engine.preprocessor.ZeroCodeExternalFileProcessor;
import com.hejianzhang.zerocode.core.engine.preprocessor.ZeroCodeExternalFileProcessorImpl;
import com.hejianzhang.zerocode.core.engine.preprocessor.ZeroCodeJsonTestProcesor;
import com.hejianzhang.zerocode.core.engine.preprocessor.ZeroCodeJsonTestProcesorImpl;
import com.hejianzhang.zerocode.core.report.ZeroCodeReportGenerator;
import com.hejianzhang.zerocode.core.report.ZeroCodeReportGeneratorImpl;
import com.hejianzhang.zerocode.core.runner.ZeroCodeMultiStepsScenarioRunner;
import com.hejianzhang.zerocode.core.runner.ZeroCodeMultiStepsScenarioRunnerImpl;
import com.hejianzhang.dao.envMapper;
import com.hejianzhang.model.env;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;
import java.util.logging.Logger;

import static com.hejianzhang.zerocode.core.di.PropertyKeys.*;
import java.util.Map;
import java.util.Set;

public class ApplicationMainModule extends AbstractModule {
    private static final Logger LOGGER = Logger.getLogger(com.hejianzhang.zerocode.core.di.main.ApplicationMainModule.class.getName());

    private final String serverEnv;
    private final Map<String,String> param;

    public ApplicationMainModule(String serverEnv,Map<String,String> param) {
        this.serverEnv = serverEnv;
        this.param = param;
    }

    @Override
    public void configure() {
        /*
         * Install other guice modules
         */
        install(new ObjectMapperModule());
        install(new HttpClientModule());
        install(new GsonModule());
        install(new PropertiesInjectorModule(serverEnv));
        //install(new KafkaModule());

        /*
         * Bind Direct classes, classes to interfaces etc
         */
        bind(ZeroCodeMultiStepsScenarioRunner.class).to(ZeroCodeMultiStepsScenarioRunnerImpl.class);
        bind(JsonServiceExecutor.class).to(JsonServiceExecutorImpl.class);
        bind(JavaExecutor.class).to(JavaExecutorImpl.class);
        bind(ZeroCodeJsonTestProcesor.class).to(ZeroCodeJsonTestProcesorImpl.class);
        bind(ZeroCodeReportGenerator.class).to(ZeroCodeReportGeneratorImpl.class);
        bind(ZeroCodeExternalFileProcessor.class).to(ZeroCodeExternalFileProcessorImpl.class);

        // ------------------------------------------------
        // Bind properties for localhost, CI, DIT, SIT etc
        // ------------------------------------------------
        Names.bindProperties(binder(), getProperties(serverEnv,param));
    }

    public Properties getProperties(String serverEnv,Map<String,String> param) {
        final Properties properties = new Properties();
        try {
              Set<String> keys = param.keySet();
//            properties.load(getClass().getClassLoader().getResourceAsStream(host));
//            properties.setProperty("web.application.endpoint.port",param.get("port"));
//            properties.setProperty("web.application.endpoint.context",);
//            properties.setProperty("web.application.endpoint.host","");
            for(String key:keys){
                properties.setProperty(key,param.get(key));
            }

            // ----------------------------------------------------
//            properties.setProperty("web.application.endpoint.host","https://vms-service.tezign.com");
            // Below code is for backward compatibility,
            // remove this after publishing for withdrawing support
            // ----------------------------------------------------
            checkAndLoadOldProperties(properties);

        } catch (Exception e) {
            LOGGER.info("###Oops!Exception### while reading target env file: " + serverEnv + ". Have you mentioned env details?");
            throw new RuntimeException("could not read the target-env properties file --" + serverEnv + "-- from the classpath.");
        }

        return properties;
    }

    private void checkAndLoadOldProperties(Properties properties) {

        if(properties.get(WEB_APPLICATION_ENDPOINT_HOST) == null && properties.get(RESTFUL_APPLICATION_ENDPOINT_HOST) != null){
            Object oldPropertyValue = properties.get(RESTFUL_APPLICATION_ENDPOINT_HOST);
            properties.setProperty(WEB_APPLICATION_ENDPOINT_HOST, oldPropertyValue != null ? oldPropertyValue.toString() : null);
        }

        if(properties.get(WEB_APPLICATION_ENDPOINT_PORT) == null && properties.get(RESTFUL_APPLICATION_ENDPOINT_PORT) != null){
            Object oldPropertyValue = properties.get(RESTFUL_APPLICATION_ENDPOINT_PORT);
            properties.setProperty(WEB_APPLICATION_ENDPOINT_PORT, oldPropertyValue != null ? oldPropertyValue.toString() : null);
        }

        if(properties.get(WEB_APPLICATION_ENDPOINT_CONTEXT) == null && properties.get(RESTFUL_APPLICATION_ENDPOINT_CONTEXT) != null){
            Object oldPropertyValue = properties.get(RESTFUL_APPLICATION_ENDPOINT_CONTEXT);
            properties.setProperty(WEB_APPLICATION_ENDPOINT_CONTEXT, oldPropertyValue != null ? oldPropertyValue.toString() : null);
        }

    }

}

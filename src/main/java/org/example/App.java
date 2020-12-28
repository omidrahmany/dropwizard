package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.config.HelloWorldConfiguration;
import org.example.health.TemplateHealthCheck;
import org.example.resource.HelloWorldResource;

public class App extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

//    @Override
//    public String getName() {
//        return "hello-world";
//    }


    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {

        final HelloWorldResource resource =
                new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}

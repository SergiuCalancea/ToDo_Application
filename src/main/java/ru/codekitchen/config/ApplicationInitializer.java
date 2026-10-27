package ru.codekitchen.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WebConfig.class);
        servletContext.addListener(new ContextLoaderListener((WebApplicationContext) context));

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet((WebApplicationContext) context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

    }
}

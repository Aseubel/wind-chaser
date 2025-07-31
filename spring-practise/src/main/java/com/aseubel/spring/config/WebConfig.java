//package com.aseubel.spring.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
//import org.springframework.http.CacheControl;
//import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import org.springframework.web.reactive.config.ResourceHandlerRegistry;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//import org.springframework.web.reactive.resource.VersionResourceResolver;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author Aseubel
// * @date 2025/7/31 下午3:06
// */
//@Configuration
//public class WebConfig extends DelegatingWebFluxConfiguration {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("classpath:/static/")
//                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
//    }
//
////    @Override
////    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/resources/**")
////                .addResourceLocations("classpath:/static/")
////                .resourceChain(true)
////                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
////    }
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
//        registrar.setUseIsoFormat(true);
//        registrar.registerFormatters(registry);
//    }
//}
//
//

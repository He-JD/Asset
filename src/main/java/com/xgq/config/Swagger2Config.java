package com.xgq.config;


import com.fasterxml.classmate.TypeResolver;
import com.xgq.entity.SysUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: HeJD
 * @Date: 2018/9/14 17:40
 * @Description:Swagger2配置文件
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {

        //添加过滤规则：移除嵌套对象
        TypeResolver typeResolver = new TypeResolver();
        AlternateTypeRule typeRule = new AlternateTypeRule(typeResolver.resolve(SysUser.class),typeResolver.resolve(Object.class));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .alternateTypeRules(typeRule)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xgq.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Asset系统api文档")
                .description("Restful API文档")
                .termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                .build();
    }

}

package bootRest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by aaron on 3/31/17.
 * Find the results here:
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket publicRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .groupName("public-api")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/api/.*"))
            .build();
  }

  @Bean
  public Docket allApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .groupName("all-api")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
  }

  private ApiInfo apiInfo() {
    Contact primaryContact = new Contact(
            "LexSolSupport",
            "www.lexington-solutions.com",
            "support@lexington-solutions.com");

    return new ApiInfoBuilder()
            .title("SpringBootRest")
            .description("Test new springboot tools for REST APIs")
            .version("0.1.0")
            .termsOfServiceUrl("http://lexington-solutions.com")
            .contact(primaryContact)
            .license("License")
            .licenseUrl("support@lexington-solutions.com")
            .build();
  }
}

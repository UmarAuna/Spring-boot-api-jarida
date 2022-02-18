package com.jarida.server.jaridaserver;

import com.cloudinary.Cloudinary;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarida.server.jaridaserver.spring_security_2.entity.RoleTwos;
import com.jarida.server.jaridaserver.spring_security_2.repository.RoleRepositoryTwos;
import com.jarida.server.jaridaserver.upload_image.service.FilesStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import javax.annotation.Resource;
import java.util.*;

@SpringBootApplication
@EnableJpaAuditing //Enabling JPA Auditing
@Configuration
@EnableWebMvc
public class JaridaServerApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	@Resource
	FilesStorageService storageService;

	@Value("${cloudinary.cloud_name}")
	private String cloudName;

	@Value("${cloudinary.api_key}")
	private String apiKey;

	@Value("${cloudinary.api_secret}")
	private String apiSecret;

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(JaridaServerApplication.class, args);

		DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}

	@Autowired
	private RoleRepositoryTwos roleRepositoryTwos;

	public void run(String... arg) throws Exception {
		storageService.init();
		RoleTwos adminRole = new RoleTwos();
		adminRole.setName("ROLE_ADMIN");
		roleRepositoryTwos.save(adminRole);

		RoleTwos userRole = new RoleTwos();
		userRole.setName("ROLE_USER");
		roleRepositoryTwos.save(userRole);
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder){
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		return objectMapper;
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
				.displayRequestDuration(true)
				.validatorUrl("")
				.build();
	}

	@Bean
	public Cloudinary cloudinaryConfig() {
		Cloudinary cloudinary = null;
		Map config = new HashMap();
		config.put("dxrxviiv8", cloudName);
		config.put("855727313552936", apiKey);
		config.put("BEfHJes5oDvWM9QAfFoTyJlWMdo", apiSecret);
		cloudinary = new Cloudinary(config);
		return cloudinary;
	}


	// http://localhost:8080/swagger-ui/index.html
	public static final Set<String> consumes = new HashSet<>(Arrays.asList("application/json"));
	public static final Set<String> produces = new HashSet<>(Arrays.asList("application/json"));

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/documentation/v2/api-docs", "/v2/api-docs?group=restful-api");
		registry.addRedirectViewController("/documentation/swagger-resources/configuration/ui","/swagger-resources/configuration/ui");
		registry.addRedirectViewController("/documentation/swagger-resources/configuration/security","/swagger-resources/configuration/security");
		registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry
				.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket jaridaApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(consumes)
				.produces(produces)
				/*.protocols(Sets.newHashSet("http", "https"))*/
				.groupName("B Jarida - V1")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jarida.server.jaridaserver"))
				.paths(PathSelectors.ant("/api/v1/**"))
				.build()
				.apiInfo(apiJaridaInfo())
				.forCodeGeneration(true);
	}

	@Bean
	public Docket studentApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(consumes)
				.produces(produces)
				/*.protocols(Sets.newHashSet("http", "https"))*/
				.groupName("A Student - V2")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jarida.server.jaridaserver"))
				.paths(PathSelectors.ant("/api/v2/**"))
				.build()
				.apiInfo(apiStudentInfo())
				.forCodeGeneration(true);
	}

	@Bean
	public Docket securityApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(Collections.singletonList(securityContext()))
				.securitySchemes(Collections.singletonList(apiKeyToken()))
				.consumes(consumes)
				.produces(produces)
				/*.protocols(Sets.newHashSet("http", "https"))*/
				.groupName("A Security - V3")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.jarida.server.jaridaserver"))
				.paths(PathSelectors.ant("/api/v3/**"))
				.build()
				.apiInfo(apiSecurityInfo())
				.forCodeGeneration(true);
	}


	private ApiInfo apiStudentInfo() {
		return new ApiInfo(
				"Student Rest API",
				"This API is for getting, posting, deleting and updating Students.",
				"API TOS",
				"https://www.apache.org/licenses/LICENSE-2.0",
				new Contact("Umar Saidu Auna", "https://umarauna.bitbucket.io", "umarmanofpeace@gmail.com"),
				"License of API", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

	private ApiInfo apiJaridaInfo() {
		return new ApiInfo(
				"Jarida Rest API",
				"This API is for getting, posting, deleting and updating Jarida.",
				"API TOS",
				"https://www.apache.org/licenses/LICENSE-2.0",
				new Contact("Umar Saidu Auna", "https://umarauna.bitbucket.io", "umarmanofpeace@gmail.com"),
				"License of API", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

	private ApiInfo apiSecurityInfo() {
		return new ApiInfo(
				"Security Rest API",
				"This API is for Security.",
				"API TOS",
				"https://www.apache.org/licenses/LICENSE-2.0",
				new Contact("Umar Saidu Auna", "https://umarauna.bitbucket.io", "umarmanofpeace@gmail.com"),
				"License of API", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

	public ApiKey apiKeyToken() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext(){
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}



}

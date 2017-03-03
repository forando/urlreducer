package com.example.urlreducer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
open class ApiDocsConfiguration{
    companion object{
        /**
         * The project version.
         */
        val PROJECT_VERSION = "1.7.2"
        /**
         * The project contact name.
         */
        val PROJECT_CONTACT_NAME = "versiya.com"
        /**
         * The project contact URL.
         */
        val PROJECT_CONTACT_URL = "http://www.versiya.com/"
    }

    /**
     * Create a Contact class to be used by Springfox's Swagger API Documentation framework.

     * @return A Contact instance.
     */
    /*private fun contact(): Contact {
        return Contact(PROJECT_CONTACT_NAME, PROJECT_CONTACT_URL, null)
    }*/

    /**
     * Create an ApiInfo class to be used by Springfox's Swagger API Documentation framework.

     * @return An ApiInfo instance.
     */
    /*private fun apiInfo(): ApiInfo {

        // @formatter:off
        val apiInfo = ApiInfoBuilder().title("Project Skeleton for Spring Boot Web Services").description("The Spring Boot web services starter project provides a foundation " + "to rapidly construct a RESTful web services application.").contact(contact()).version(PROJECT_VERSION).build()
        // @formatter:on

        return apiInfo
    }*/

    /**
     * Create a Docket class to be used by Springfox's Swagger API Documentation framework. See
     * http://springfox.github.io/springfox/ for more information.

     * @return A Docket instance.
     */
    /*@Bean
    fun docket(): Docket {
        val paths = PathSelectors.ant("*//**")

        // @formatter:off
        val docket = Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(paths).build()
        // @formatter:on

        return docket
    }*/
}
package com.example.employee;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    classes = EmployeeApplication.class
)
public class EmployeeControllerTest {
    @Autowired
    private EmployeeRepository repository;

    private RequestSpecification basicRequest;

    @Before
    public void setUp() {
        basicRequest = given()
            .baseUri("http://localhost")
            .port(8080)
        ;

//        repository.deleteAll();
        repository.save(Employee.builder().lastName("jang").firstName("jaehoon").build());
        System.out.println(repository.findAll().toString());
    }

    @Test
    public void  shouldReturnDefaultMessageWhenLastNameNotFound(){
        String nonExistingLastName = "nonExistingLastName";
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";

        given().spec(basicRequest).basePath("/api/hello/" + nonExistingLastName)
               .when().get()
               .then().log().body()
               .statusCode(HttpStatus.OK.value())
               .body(is(expectedMessage));

    }

    @Test
    public void shouldReturnGreetingMessageWhenLastNameFound() {
        String existingLastName = "jang";
        String expectedMessage = "Hello jaehoon jang!";

        given().spec(basicRequest).basePath("/api/hello/" + existingLastName)
               .when().get()
               .then().log().body()
               .statusCode(HttpStatus.OK.value())
               .body(Is.is(expectedMessage));
    }

}

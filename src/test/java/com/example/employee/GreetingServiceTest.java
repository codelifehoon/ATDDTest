package com.example.employee;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest( classes = EmployeeApplication.class)
//@TestPropertySource(locations = "classpath:application-integration")
public class GreetingServiceTest {


    @Mock
    private  EmployeeRepository repository;

    @InjectMocks        //Service 내부의 repository를 mocking 하기 위해 Inject 추가
    @Autowired
    GreetingService greetingService;


    String nonExistingLastName = "nonExistingLastName";
    String existingLastName = "existingLastName";
    String firstName = "firstName";
    String lastName = "lastName";


    @Before
    public void setUp() throws Exception {

        //Service 내부의 repository-mocking
        MockitoAnnotations.initMocks(this);

        given(repository.findByLastName(nonExistingLastName))
            .willReturn(Optional.empty());

        given(repository.findByLastName(existingLastName))
            .willReturn(Optional.of(Employee.builder()
                                            .lastName(lastName)
                                            .firstName(firstName)
                                            .build()
                                    ));
    }

    @Test
    public void greet_with_noExisting_last_name_should_return_default_message() {

        String lastName = this.nonExistingLastName;
        String msg =  greetingService.greet(lastName);
        assertThat(msg).isEqualTo("Who is this " + lastName + " you're talking about?");


    }

    @Test
    public void greet_with_existsting_last_name_return_hello_message(){

        String lastName = this.existingLastName;
        String msg1 =  greetingService.greet(lastName);
        assertThat(msg1).isEqualTo(String.format("Hello %s %s!", firstName, this.lastName));
    }


}

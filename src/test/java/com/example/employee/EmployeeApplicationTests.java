package com.example.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
	,classes = EmployeeApplication.class)
@TestPropertySource(locations = "classpath:application-integration")
public class EmployeeApplicationTests {

	@Test
	public void contextLoads() {
	}

}

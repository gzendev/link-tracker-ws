package com.fierastudio.linktracker.ws.controller;

import com.fierastudio.linktracker.ws.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.IntegrationTest;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
//@IntegrationTest({"server.port:0",
        //"spring.datasource.url:jdbc:h2:mem:LinkTracker;DB_CLOSE_ON_EXIT=FALSE"})
public class LinkRestControllerTest {
    //@Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        //RestAssured.port = port;
    }
}
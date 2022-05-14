package com.shortnr.url;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shortnr.url.controller.UrlShortenerServiceController;
import com.shortnr.url.model.UrlObject;
import com.shortnr.url.model.UrlObjectRequest;
import com.shortnr.url.model.UrlObjectResponse;
import com.shortnr.url.services.UrlShortenerService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class UrlApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private MockRestServiceServer mockRestServiceServer;
    private ObjectMapper objectMapper;

    @InjectMocks
    UrlShortenerServiceController urlShortenerServiceController;

    @InjectMocks
    UrlShortenerService urlShortenerService;

    @Before
    public void urlShortenerSetup(){
        mockMvc = MockMvcBuilders.standaloneSetup(urlShortenerServiceController).build();
    }

    //Verify status codes
    @Test
    public void verifyStatusCode() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/generateShortURL")).andExpect(status().is(200));
    }

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getShortUrl() throws Exception{
        UrlObject urlObject = new UrlObject();


        UrlObjectRequest urlObjectRequest = new UrlObjectRequest("https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/util/Stack.java", "2022-05-20T01:30:00.000z");
        //ResponseEntity<?> urlObjectResponse = urlShortenerServiceController.generateShortUrl(urlObjectRequest);

        Mockito.when(urlShortenerService.generateShortUrl(urlObjectRequest)).thenReturn(urlObject);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/v1/generateShortURL")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(urlObjectRequest));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());



    }




}

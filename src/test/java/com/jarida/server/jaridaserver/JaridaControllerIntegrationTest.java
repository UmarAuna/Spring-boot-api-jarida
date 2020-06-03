package com.jarida.server.jaridaserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.jarida.server.jaridaserver.jarida.model.Jarida;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JaridaServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JaridaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllJarida() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/jarida",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetJaridaById() {
        Jarida jarida = restTemplate.getForObject(getRootUrl() + "/jarida/1", Jarida.class);
        System.out.println(jarida.getTitle());
        assertNotNull(jarida);
    }

    @Test
    public void testCreateJarida() {
        Jarida jarida = new Jarida();
        jarida.setTitle("Title 101");
        jarida.setContent("Content 101");
        jarida.setAuthor("Author 101");
        ResponseEntity<Jarida> postResponse = restTemplate.postForEntity(getRootUrl() + "/jarida", jarida, Jarida.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Jarida jarida = restTemplate.getForObject(getRootUrl() + "/jarida/" + id, Jarida.class);
        jarida.setTitle("Title 102");
        jarida.setContent("Content 102");
        jarida.setAuthor("Author 102");
        restTemplate.put(getRootUrl() + "/employees/" + id, jarida);
        Jarida updatedJarida = restTemplate.getForObject(getRootUrl() + "/jarida/" + id, Jarida.class);
        assertNotNull(updatedJarida);
    }

    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Jarida jarida = restTemplate.getForObject(getRootUrl() + "/jarida/" + id, Jarida.class);
        assertNotNull(jarida);
        restTemplate.delete(getRootUrl() + "/jarida/" + id);
        try {
            jarida = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Jarida.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}

package com.igm.igmtest.endpoint_3b;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({SpringExtension.class, WireMockExtension.class})
@SpringBootTest
public class EbServiceTest {

    @Autowired
    private EbService ebService;

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setUp() {
        wireMockServer = new WireMockServer(8081);
        wireMockServer.start();
        configureFor("localhost", 8081);
        stubFor(get(urlEqualTo("/endpoint"))
                .willReturn(aResponse()
                        .withStatus(429)
                        .withBody("Too many requests")));
    }

    @Test
    public void testGetData() {
        assertThrows(Exception.class, () -> ebService.getData());
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }
}
package com.example.demo;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {

    @LocalServerPort
    private int port;

    private BrowserWebDriverContainer container = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions());

    @Test
    public void shouldDisplayMessage() {
        this.container.getWebDriver().get("http://localhost:" + port + "/");
        WebElement messageElement = this.container.getWebDriver().findElementById("message");
        assertEquals("Integration Test with Selenium", messageElement.getText());
    }
}
package com.example.webframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class WebFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFrameworkApplication.class, args);
    }

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        String welcome = "<html xmlns=\"http://www.w3.org/1999/xhtml\"" +
                "<head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>" +
                "<title>Serverless Demo</title>" +
                "<link href=\"https://example-static.oss-cn-beijing.aliyuncs.com/web-framework/style.css\" rel=\"stylesheet\" type=\"text/css\"/>" +
                "</head>" +
                "<body>" +
                "<div class=\"website\">" +
                "<div class=\"ri-t\">" +
              
                "<h2>这是一个 Spring Boot 项目</h2>" +
                "<span>通过jenkins部署到alicloud serverless ask服务</span>" +
                "<br/><p>version： <br/>" +
                "• v222 <br/>" +
                "• 访问地址：http://10.85.184.151:9000/<br/>" +
                "Serverless Demo ATC cicd</p>" +
                "</div></div></body></html>";
        return new ResponseEntity<>(welcome, HttpStatus.OK);
    }

    @GetMapping("/fcheaders")
    public ResponseEntity<Map<String, String>> listHeaders(
            @RequestHeader Map<String, String> headers) {
        Map<String, String> fcHeaders = new HashMap<>();
        headers.forEach((key, value) -> {
            if (key.startsWith("x-fc")) {
                fcHeaders.put(key, value);
            }

        });

        return new ResponseEntity<>(fcHeaders, HttpStatus.OK);
    }
}

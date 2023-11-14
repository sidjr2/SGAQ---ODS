package br.edu.ufsj.dcefs.sgaq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SgaqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgaqApplication.class, args );
    }


}

package xyz.haixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.haixin.rent.mapper")
public class VedioSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VedioSourceApplication.class, args);
    }

}

package cn.zgyt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import cn.zgyt.util.xcx.XcxConfig;

@SpringBootApplication
public class StewedfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(StewedfoodApplication.class, args);
	}

	@Bean(name = "importConfig")
	@ConfigurationProperties(prefix="server")
	public XcxConfig xcxConfig(){
		return new XcxConfig();
	} 
}

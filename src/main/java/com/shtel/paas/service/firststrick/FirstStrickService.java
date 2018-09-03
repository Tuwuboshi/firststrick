package com.shtel.paas.service.firststrick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shtel.paas.sdk.core.PaasBaseResponse;
import com.shtel.paas.sdk.core.PaasDefaultObject;
import com.shtel.paas.sdk.core.RefreshableRestController;
import com.shtel.paas.sdk.core.log.PaasLogger;
import com.shtel.paas.sdk.core.web.client.PaasRestTemplate;

/**
 * 微服务返回json串示例
 * 
 * @author wjj
 *
 */
@RefreshableRestController
public class FirstStrickService {

	@Autowired
	private PaasRestTemplate restTemplate;
	@Autowired
	@Qualifier("externalRestTemplate")
	private PaasRestTemplate externalRestTemplate;

	@GetMapping("/test/{serv}/{ctx}")
	public String test(@PathVariable String serv, @PathVariable String ctx) {
		String result = "";
		result = this.restTemplate.getForObject("http://" + serv + "/" + ctx, String.class);
		PaasLogger.info("服务 /test 调用成功 ");
		return "test:" + result;
	}

	@GetMapping("/test1")
	public String test1() {
		String result = "";
		result = this.externalRestTemplate.getForObject("http://www.baidu.com/", String.class);
		PaasLogger.info("服务 /test1 调用成功 ");
		return "test1:" + result;
	}

	@GetMapping("/hello")
	public PaasBaseResponse<PaasDefaultObject> hello() {

		PaasDefaultObject paasDefaultObject = new PaasDefaultObject("hello world!");

		PaasBaseResponse<PaasDefaultObject> response = new PaasBaseResponse<>(paasDefaultObject);
		return response;
	}

	@GetMapping("/hello2")
	public String hello2() {
		return "world";
	}

}
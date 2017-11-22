package com.example.redisdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController
 *
 * @author Chenxing Li
 * @date 2017/11/22 21:13
 */
@Slf4j
@RequestMapping(path = "/v1/demo")
@RestController
public class DemoRestController {

    @Autowired
    private RedisTemplate<String, String> demoRedisTemplate;

    @RequestMapping(path = "/redis", method = RequestMethod.GET)
    public ApiResponse get(@RequestParam(name = "key") String key) {
        return new ApiResponse<>(demoRedisTemplate.opsForValue().get(key));
    }

    @RequestMapping(path = "/redis", method = RequestMethod.POST)
    public ApiResponse get(@RequestParam(name = "key") String key, @RequestParam(name = "value") String value) {
        demoRedisTemplate.opsForValue().set(key, value);
        return new ApiResponse<>();
    }

    @RequestMapping(path = "/redis", method = RequestMethod.DELETE)
    public ApiResponse delete(@RequestParam(name = "key") String key) {
        demoRedisTemplate.delete(key);
        return new ApiResponse<>();
    }

}

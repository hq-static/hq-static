package com.dmc.controller;

import com.dmc.util.VerifyCodeUtils;
import lombok.Data;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2022/10/9 0009.
 */
@RestController
public class CaptchaController {
    private Logger logger = LoggerFactory.getLogger(CaptchaController.class);
    /**
     * 验证码有效期（分钟）
     */
    final Integer CAPTCHA_EXPIRATION = 2;
    /**
     * 验证码 redis key
     */
    final String CAPTCHA_CODE_KEY = "captcha_codes:";

    private Map<String, String> captchaCache = new HashMap<String,String>();

    /**
     * 生成验证码
     */
    @GetMapping("/captcha")
    public Map<String, String> getCode(HttpServletResponse response) throws IOException {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = UUID.randomUUID().toString();

        // 保存验证码
        captchaCache.put(uuid,verifyCode);

        // 生成带验证码的图片
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //生成图片
        int w = 111, h = 36;
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);

        Map<String, String> result = new HashMap<>();
        try {
            //jdk1.8提供了新的Base64类 可以直接调用
            Base64.Encoder encoder = Base64.getEncoder();
            result.put("code", "200");
            result.put("uuid", uuid);
            result.put("verifyCode", verifyCode);
            result.put("img", encoder.encodeToString(stream.toByteArray()));
            result.put("msg", "获取验证码成功");
            return result;
        } catch (Exception e) {
            result.put("code", "500");
            result.put("meg", "获取验证码失败，请联系管理人员！");
            return result;
        } finally {
            stream.close();
        }
    }

    @RequestMapping("/checkCaptcha")
    public Map<String, String> check(@RequestBody JSONObject jsonObject) {
        String uuid = (String) jsonObject.get("uuid");
        String verify = (String) jsonObject.get("verify");
        String verifyServer = captchaCache.get(uuid);

        Map<String, String> result = new HashMap<>();
        if (verify != null && verify.toLowerCase().equals(verifyServer.toLowerCase())) {
            result.put("code","200");
            result.put("msg","校验码校验成功！");
        } else {
            result.put("code","500");
            result.put("msg","校验码校验不成功，请联系管理员！");
        }
        captchaCache.remove(uuid);
        return result;
    }

    @Data
    static class ImageCodeParams {
        /**
         * 验证码
         */
        private String code;
        /**
         * 唯一标识
         */
        private String uuid;
    }
}
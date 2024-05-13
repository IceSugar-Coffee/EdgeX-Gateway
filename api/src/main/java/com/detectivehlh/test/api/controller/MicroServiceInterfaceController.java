package com.detectivehlh.test.api.controller;

import com.detectivehlh.test.api.bean.APICache;
import com.detectivehlh.test.api.bean.Cache;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * RestController
 * 定义为Restful风格的API控制器
 */
@RestController
@CrossOrigin
public class MicroServiceInterfaceController {
    @PostMapping("/ImageMarkService/device{id:\\d+}/cameraImg")
    public String CachingImg(@RequestBody Base64ImgBody base64ImgBody, @PathVariable Integer id) {
        Cache.getImageCache().put(id, base64ImgBody.base64String);
        return "200 OK";
    }
    @PostMapping("/3DService/device{id:\\d+}/position")
    public String CachingPosition(@RequestBody PositionBody positionBody, @PathVariable Integer id) {
        Cache.getPositionCache().put(id, positionBody);
        return "200 OK";
    }
    @Getter
    @Setter
    public static class Base64ImgBody {
        String base64String;
    }

    @Getter
    @Setter
    public static class PositionBody {
        double x;
        double y;
        double z;
    }
}
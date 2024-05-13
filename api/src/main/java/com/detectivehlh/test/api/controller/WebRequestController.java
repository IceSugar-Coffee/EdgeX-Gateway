package com.detectivehlh.test.api.controller;
import com.detectivehlh.test.api.bean.APICache;
import com.detectivehlh.test.api.bean.Cache;
import com.detectivehlh.test.core.service.HelloService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * RestController
 * 定义为Restful风格的API控制器
 */
@RestController
@RequestMapping(value = "/device{id:\\d+}")
@CrossOrigin
public class WebRequestController {
    @GetMapping("/getVelocity")
    public double getVelocity(@PathVariable(name = "id") Integer id) {
        return 0.1;
    }

    @PostMapping("/setVelocity")
    public String setVelocity(@RequestBody velocityBody velocityBody, @PathVariable Integer id) {
        System.out.println("device" + id + "velocity has been set to " + velocityBody.velocity);
        return "200 OK";
    }
    @GetMapping("/getCameraImg")
    public String getCameraImg(@PathVariable Integer id) {
        String base64Img = Cache.getImageCache().get(id);
        if (base64Img != null) return base64Img;
        return "";
    }
    @Getter
    @Setter
    public static class velocityBody {
        double velocity;
    }
}
package com.detectivehlh.test.api.bean;

import org.springframework.stereotype.Component;

import java.awt.print.PrinterGraphics;
import java.util.ArrayList;

@Component
public class APICache {
    private static APICache ImageCache;
    private static APICache PositionCache;

    private final ArrayList<Object> data;

    private APICache() {
        this.data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.data.add("NULL");
        }
    }

    public void push(Integer id, Object o) {
        this.data.set(id, o);
    }
    public Object pop(Integer id) {
        return this.data.get(id);
    }

    public static APICache getImageCacheInstance() {
        if (ImageCache == null) {
            ImageCache = new APICache();
        }
        return ImageCache;
    }

    public static APICache getPositionCacheInstance() {
        if (PositionCache == null) {
            PositionCache = new APICache();
        }
        return PositionCache;
    }
}

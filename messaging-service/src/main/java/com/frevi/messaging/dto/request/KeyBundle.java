package com.frevi.messaging.dto.request;

import java.util.List;

public class KeyBundle {
    public String userId;
    public int deviceId = 0;
    public List<String> preKeysBase64;
}

package com.atlihao.springboot.ratelimiter;


import com.google.common.util.concurrent.RateLimiter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final Map<String, RateLimiter> rateLimiterMap = new HashMap<String, RateLimiter>();

}

package com.hejianzhang.zerocode.core.zzignored.trick;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHello {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.hejianzhang.zerocode.core.zzignored.trick.LogHello.class);

    public static void main(String[] args) {
        LOGGER.info("###Hello - " + com.hejianzhang.zerocode.core.zzignored.trick.LogHello.class.getName());
    }
}

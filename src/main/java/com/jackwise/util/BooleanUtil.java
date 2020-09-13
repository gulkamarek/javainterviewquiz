package com.jackwise.util;

import java.util.Objects;

public class BooleanUtil {

    public static boolean getBoolean(String isTrueAnsw0) {
        if (Objects.nonNull(isTrueAnsw0))
            return true;
        else
            return false;
    }
}

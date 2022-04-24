package ru.base.util.exception;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    private static final Logger LOG = getLogger(ExceptionUtil.class);

    public static void check(boolean found, int id){
        check(found,"id=" + id);
    }

    public static void check(boolean found, String msg ) {
        if (!found) throw new NotFoundException("Not found empty this " + msg);
    }

    public static <T> T check(T object, int id){
        return check(object,"id=" + id);
    }

    public static <T> T check(T object, String msg ) {
        if (object == null) throw new NotFoundException("Not found empty this " + msg);
        return object;
    }

}

package cn.xyz.chaos.orm.generator;

import java.util.Collection;

public class CollectionUtils {

    public static boolean isEmpty(Collection coll) {
        return ((coll == null) || coll.isEmpty());
    }

    public static boolean isNotEmpty(Collection coll) {
        return !CollectionUtils.isEmpty(coll);
    }

}

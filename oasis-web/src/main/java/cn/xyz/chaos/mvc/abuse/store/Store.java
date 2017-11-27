package cn.xyz.chaos.mvc.abuse.store;

/**
 * cn.xyz.chaos.mvc.abuse.store
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/4 10:54.
 */
public interface Store {

    String getName();

    void setName(String name);

    void save(String key, Object value, int expiration);

    <T> T get(String key);

    boolean exist(String key);

}

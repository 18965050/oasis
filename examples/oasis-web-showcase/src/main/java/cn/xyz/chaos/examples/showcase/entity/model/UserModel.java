package cn.xyz.chaos.examples.showcase.entity.model;

import cn.xyz.chaos.examples.showcase.entity.User;

/**
 * UserModel <br/>
 * User的扩展POJO
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月29日 下午2:21:58
 * @author lcg
 */
public class UserModel extends User {

    private String createUser;

    private String updateUser;

    /**
     * @return the createUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser the createUser to set
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return the updateUser
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser the updateUser to set
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}

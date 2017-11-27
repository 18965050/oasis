package cn.xyz.chaos.orm.mybatis.mysql.entity;

import cn.xyz.chaos.orm.annotations.Table;

/** lcg@2014-12-18 16:11:02 */
@Table(name = "cn.xyz.chaos.orm.mybatis.mysql.entity.SsTask", schema = "test")
public class SsTask {
    private Long id;

    private String title;

    private String description;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

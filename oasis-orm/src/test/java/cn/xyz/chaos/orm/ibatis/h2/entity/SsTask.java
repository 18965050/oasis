package cn.xyz.chaos.orm.ibatis.h2.entity;

import cn.xyz.chaos.orm.annotations.Table;

/** lcg@2014-12-18 11:09:31 */
@Table(name = "SS_TASK", schema = "test")
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
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

package cc.lq.blog.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 博客用户
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@TableName("blog_user")
public class UserDO extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码加密盐值
     */
    private String secretKey;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 锁定标识 1-是 0-否
     */
    @TableField("is_locked")
    private Boolean locked;

    /**
     * 删除标识 1-是 0-否
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                super.toString() +
                "username = " + username +
                ", secretKey = " + secretKey +
                ", password = " + password +
                ", nickName = " + nickName +
                ", phoneNum = " + phoneNum +
                ", email = " + email +
                ", locked = " + locked +
                ", deleted = " + deleted +
                "}";
    }
}

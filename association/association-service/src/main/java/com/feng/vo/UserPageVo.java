package com.feng.vo;



import com.feng.entity.User;
import com.github.pagehelper.PageInfo;
import lombok.Data;

/**
 * Created by rf on 2019/4/18.
 */
@Data
public class UserPageVo {
    private PageInfo<User> userPageInfo;

    public UserPageVo( PageInfo<User> userPageInfo) {
        this.userPageInfo = userPageInfo;
    }

    public UserPageVo() {
    }

}

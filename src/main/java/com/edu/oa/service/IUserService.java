package com.edu.oa.service;

import com.edu.oa.mdo.User;
import com.edu.oa.vo.UserVo;

public interface IUserService {
    int updatePersonalInformation(UserVo vo);
    UserVo completeUser(User user);
}

package cc.lq.blog.system.service.impl;

import cc.lq.blog.system.entity.UserDO;
import cc.lq.blog.system.mapper.UserMapper;
import cc.lq.blog.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客用户 服务实现类
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}

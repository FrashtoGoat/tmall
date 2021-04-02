package com.xiaoluban.demo.mybatisplus.service.impl;

import com.xiaoluban.demo.mybatisplus.entity.Person;
import com.xiaoluban.demo.mybatisplus.mapper.PersonMapper;
import com.xiaoluban.demo.mybatisplus.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author txb
 * @since 2021-04-02
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}

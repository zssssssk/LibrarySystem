package com.example.ems.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ems.bean.Administrator;
import com.example.ems.mapper.AdministratorMapper;
import com.example.ems.mapper.UserMapper;
import com.example.ems.service.AdministratorService;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService{

}

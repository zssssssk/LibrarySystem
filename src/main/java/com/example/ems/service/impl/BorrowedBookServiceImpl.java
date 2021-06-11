package com.example.ems.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ems.bean.BorrowedBook;
import com.example.ems.mapper.BorrowedBookMapper;
import com.example.ems.service.BorrowedBookService;
import org.springframework.stereotype.Service;

@Service
public class BorrowedBookServiceImpl extends ServiceImpl<BorrowedBookMapper, BorrowedBook> implements BorrowedBookService {
}

# 图书管理系统

Mysql+SpringBoot+Mybaties plus+Thymeleaf

> 数据库课程设计：LibrarySystem  (客户端入口: [godshuai](http://godshuai.cn:8080 "求star!") )
>
> username：userZ    
>
> password：123456

管理端界面设计

管理端主要实现书籍删除、书籍上传、书籍更新、借阅书籍、返还书籍等功能。主要界面设计如下：

-	登录界面：
  通过用户名和密码实现用户登录，并判断用户的权限
-	管理首页：
  根据用户的权限，进入首页，并在首页中展示此用户相应可以操作的权限功能。
  用户管理：
-	包括“用户列表”、“用户信息”、“修改用户信息”、“添加用户”和“删除用户” 、“用户权限设置”等页面。

客户端界面设计

- 客户端主要为用户提供网上借书的过程，客户端用户能够使用的主要功能包括：显示所有图书信息，查询图书信息和统计图书信息，借阅和归还图书.
- 首先进入图书管理界面，点击“Data Table”进入数据列表界面，包括“Administrator Table”、“Users Table”、“Book Table”、“Borrowed Table”共四个列表。点进去则是对应的信息。其中，用户可在“Book Table”界面中借阅图书，并在“Borrowed Table”界面中还回自己之前借的书籍。

## 参考资料

[1] *Mybaties Plus官方文档*

[2] Thymeleaf*官方文档*

[3] *SpringBoot官方文档*

[4] *bilibili视频*——借鉴了好多个，最多的是雷神springbboot

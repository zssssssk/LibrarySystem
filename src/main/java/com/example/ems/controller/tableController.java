package com.example.ems.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.ems.bean.Administrator;
import com.example.ems.bean.Book;
import com.example.ems.bean.BorrowedBook;
import com.example.ems.bean.User;
import com.example.ems.service.AdministratorService;
import com.example.ems.service.BookService;
import com.example.ems.service.BorrowedBookService;
import com.example.ems.service.UserService;
import com.sun.net.httpserver.HttpContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class tableController {

    @Autowired
    UserService userService;

    @Autowired
    AdministratorService administratorService;

    @Autowired
    BookService bookService;

    @Autowired
    BorrowedBookService borrowedBookService;

//------------------Administrator--------------------------------
    @GetMapping("/Administrator")
    public String basic_table(Model model,HttpSession session) {

        List<Administrator> list = administratorService.list();
        model.addAttribute("Administrators", list);

        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("privilege",loginUser.getId());

        return "table/Administrator";
    }


    //-----------------------------Users-----------------------------
    @GetMapping("/Users")
    public String dynamic_table(Model model,HttpSession session) {

        //从数据表中查出UserTable的数据进行展示
        List<User> list = userService.list();

        model.addAttribute("users", list);

        //id为0才是真正的系统管理员
        User loginUser = (User) session.getAttribute("loginUser");

        model.addAttribute("p", loginUser.getId());


        return "table/Users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){

        userService.removeById(id);
        return "redirect:/Users";
    }



//--------------------------Book--------------------------
    @GetMapping("/Book")
    public String responsive_table(Model model, HttpSession session) {


        User loginUser = (User) session.getAttribute("loginUser");
        boolean booleannn = false;
        if (loginUser.getId() == 0) {
            booleannn = true;
        }
        model.addAttribute("permission", booleannn);

        model.addAttribute("returnState", "0");

        List<Book> list = bookService.list();
        model.addAttribute("books", list);


        return "table/Book";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {

        bookService.removeById(id);

        return "redirect:/Book";
    }

    @GetMapping("/book/borrow/{id}")
    public String borrowBook(@PathVariable("id") Long id,
                             @RequestParam("ISBN") String ISBN,
                             @RequestParam("name") String name,
                             HttpSession session) {


        //更新Book
        UpdateWrapper<Book> bookUpdateWrapper = new UpdateWrapper<>();
        bookUpdateWrapper.eq("id", id).set("state", 1);
        bookService.update(bookUpdateWrapper);

        //更新BorrowedBook
        User loginUser = (User) session.getAttribute("loginUser");

        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setBorrowedTime(String.valueOf(new Date()));
        borrowedBook.setBookid(id);
        borrowedBook.setUserid(loginUser.getId());
        borrowedBook.setISBN(ISBN);
        borrowedBook.setName(name);

        borrowedBookService.save(borrowedBook);

        //更新Users
        Long borrowedQ = loginUser.getQuantity();
        borrowedQ+=1;
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id",loginUser.getId()).set("quantity",borrowedQ);
        userService.update(userUpdateWrapper);


        return "redirect:/Book";
    }

    @GetMapping("/book/update")
    public String updateBook() {

        return "table/formUpdate";
    }

    @PostMapping("/Book")
    public String Book(Book book) {
        bookService.saveOrUpdate(book);

        return "redirect:/Book";
    }

    //----------------------------BorrowedBook-----------------------------
    @GetMapping("/book/return/{id}")
    public String BookReturn(@PathVariable("id") Long id,HttpSession session) {

        QueryWrapper<BorrowedBook> borrowedBookQueryWrapper = new QueryWrapper<>();
        borrowedBookQueryWrapper.eq("bookid", id);

        borrowedBookService.remove(borrowedBookQueryWrapper);


        UpdateWrapper<Book> bookUpdateWrapper = new UpdateWrapper<>();
        bookUpdateWrapper.eq("id", id).set("state", 0);
        bookService.update(bookUpdateWrapper);

        //更新Users
        User loginUser = (User) session.getAttribute("loginUser");
        Long borrowedQ = loginUser.getQuantity();
        borrowedQ--;
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id",loginUser.getId()).set("quantity",borrowedQ);
        userService.update(userUpdateWrapper);

        return "redirect:/BorrowedBook";
    }

    @GetMapping("/BorrowedBook")
    public String editable_table(Model model, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");


        model.addAttribute("permission", loginUser.getId());

        List<BorrowedBook> list = borrowedBookService.list();
        model.addAttribute("borrowedBooks", list);

        return "table/BorrowedBook";
    }

}

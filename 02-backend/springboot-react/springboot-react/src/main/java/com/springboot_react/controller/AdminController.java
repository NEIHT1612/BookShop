package com.springboot_react.controller;

import com.springboot_react.requestmodels.RequestAddBook;
import com.springboot_react.service.AdminService;
import com.springboot_react.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/secure/add/book")
    public void postAddBook(@RequestHeader(value = "Authorization") String token,
                            @RequestBody RequestAddBook requestAddBook) throws Exception {
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if(!admin.equals("admin") || admin == null){
            throw new Exception("Administration page only");
        }
        adminService.postAddBook(requestAddBook);
    }

    @PutMapping("/secure/increase/book/quantity")
    public void increaseQuantity(@RequestHeader(value = "Authorization") String token,
                                 @RequestParam Long bookId) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if(!admin.equals("admin") || admin == null){
            throw new Exception("Administration page only");
        }
        adminService.increaseQuantity(bookId);
    }

    @PutMapping("/secure/decrease/book/quantity")
    public void decreaseQuantity(@RequestHeader(value = "Authorization") String token,
                                 @RequestParam Long bookId) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if(!admin.equals("admin") || admin == null){
            throw new Exception("Administration page only");
        }
        adminService.decreaseQuantity(bookId);
    }

    @DeleteMapping("/secure/delete/book")
    public void deleteBook(@RequestHeader(value = "Authorization") String token,
                           @RequestParam Long bookId) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if(!admin.equals("admin") || admin == null){
            throw new Exception("Administration page only");
        }
        adminService.deleteBook(bookId);
    }
}

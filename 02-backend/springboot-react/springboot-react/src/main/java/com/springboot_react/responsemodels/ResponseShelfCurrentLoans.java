package com.springboot_react.responsemodels;

import com.springboot_react.entity.Book;
import lombok.Data;

@Data
public class ResponseShelfCurrentLoans {
    public ResponseShelfCurrentLoans(Book book, int daysLeft){
        this.book = book;
        this.daysLeft = daysLeft;
    }

    private Book book;

    private int daysLeft;
}

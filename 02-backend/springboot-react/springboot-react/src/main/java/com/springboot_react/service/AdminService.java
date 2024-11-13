package com.springboot_react.service;

import com.springboot_react.dao.BookRepository;
import com.springboot_react.dao.CheckoutRepository;
import com.springboot_react.dao.ReviewRepository;
import com.springboot_react.entity.Book;
import com.springboot_react.requestmodels.RequestAddBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminService {
    private BookRepository bookRepository;
    private CheckoutRepository checkoutRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public AdminService(BookRepository bookRepository, CheckoutRepository checkoutRepository, ReviewRepository reviewRepository){
        this.bookRepository = bookRepository;
        this.checkoutRepository = checkoutRepository;
        this.reviewRepository = reviewRepository;
    }

    public void postAddBook(RequestAddBook requestAddBook){
        Book book = new Book();
        book.setTitle(requestAddBook.getTitle());
        book.setAuthor(requestAddBook.getAuthor());
        book.setDescription(requestAddBook.getDescription());
        book.setCopies(requestAddBook.getCopies());
        book.setCopiesAvailable(requestAddBook.getCopies());
        book.setCategory(requestAddBook.getCategory());
        book.setImg(requestAddBook.getImg());
        bookRepository.save(book);
    }

    public void increaseQuantity(Long bookId) throws Exception {
        Optional<Book> book = bookRepository.findById(bookId);
        if(!book.isPresent()){
            throw new Exception("Book is not exist");
        }
        book.get().setCopies(book.get().getCopies() + 1);
        book.get().setCopiesAvailable(book.get().getCopiesAvailable() + 1);
        bookRepository.save(book.get());
    }

    public void decreaseQuantity(Long bookId) throws Exception {
        Optional<Book> book = bookRepository.findById(bookId);
        if(!book.isPresent() || book.get().getCopies() <= 0 || book.get().getCopiesAvailable() <= 0){
            throw new Exception("Book is not exist or quantity locked");
        }
        book.get().setCopies(book.get().getCopies() - 1);
        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());
    }

    public void deleteBook(Long bookId) throws Exception{
        Optional<Book> book = bookRepository.findById(bookId);
        if(!book.isPresent()){
            throw new Exception("Book is not exist");
        }
        bookRepository.delete(book.get());
        checkoutRepository.deleteAllByBookId(bookId);
        reviewRepository.deleteAllByBookId(bookId);
    }
}

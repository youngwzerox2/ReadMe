package com.example.userdao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Book;

@Mapper
public interface UserBookDAO {

    List<Book> selectByTitle(String bookname);

    List<Book> selectByTitle10(String bookname);
    
    Book selectByNum(String bookIsbn13);

    List<Book> selectHotbook();
}

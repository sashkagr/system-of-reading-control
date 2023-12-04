package org.example.command.impl.page;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;
import org.example.helper.OpenLibraryApiHelper;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.service.impl.BookServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

public enum ShowMyLibraryPageCommand implements Command {
    INSTANCE;

    BookService bookService = BookServiceImpl.INSTANCE;

    public static final ResponseContext MAIN_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/pages/searchBook.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        Long userId = (Long) requestContext.getSession().getAttribute("id");
        List<Book> books = bookService.getAllUserBooks(userId.intValue());
        List<Book> booksReaded = books.stream().filter(Book::isFinished).collect(Collectors.toList());
        books = books.stream().filter(book -> !book.isFinished()).collect(Collectors.toList());
        requestContext.setAttribute("isMyLibrary", true);
        requestContext.setAttribute("books", books);
        requestContext.setAttribute("booksReaded", booksReaded);
        return MAIN_PAGE_RESPONSE;
    }
}

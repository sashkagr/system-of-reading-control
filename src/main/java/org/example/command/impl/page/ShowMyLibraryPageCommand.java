package org.example.command.impl.page;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;
import org.example.helper.OpenLibraryApiHelper;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.service.impl.BookServiceImpl;

import java.util.List;

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

        requestContext.setAttribute("books", books);
        return MAIN_PAGE_RESPONSE;
    }
}

package org.example.command.impl.page;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;
import org.example.helper.OpenLibraryApiHelper;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.service.impl.BookServiceImpl;

import java.util.List;

public enum ShowBookPageCommand implements Command {
    INSTANCE;

    BookService bookService = BookServiceImpl.INSTANCE;

    public static final ResponseContext MAIN_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/pages/bookDesc.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String showBook = String.valueOf(requestContext.getParameter("key"));
        Long userId = (Long) requestContext.getSession().getAttribute("id");
        Book book = OpenLibraryApiHelper.getBookByKeys(showBook);
        List<Book> allUserBooks = bookService.getAllUserBooks(userId.intValue());
        boolean isAlreadyAdded = allUserBooks.stream().anyMatch(bookInList -> bookInList.getId().equals(book.getId()));
        if (isAlreadyAdded)
        {
            Book bookFound = allUserBooks.stream().filter(bookInList -> bookInList.getId().equals(book.getId())).findFirst().get();
            book.setEndDate(bookFound.getEndDate());
            book.setStartDate(bookFound.getStartDate());
            book.setFinished(bookFound.isFinished());
        }
        requestContext.setAttribute("book", book);
        requestContext.setAttribute("isAdded", isAlreadyAdded);
        return MAIN_PAGE_RESPONSE;
    }



}

package org.example.command.impl.page;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;
import org.example.helper.OpenLibraryApiHelper;
import org.example.model.Book;

import java.util.List;

public enum ShowBookPageCommand implements Command {
    INSTANCE;

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
        Book book = OpenLibraryApiHelper.getBookByKeys(showBook);
        requestContext.setAttribute("book", book);
        return MAIN_PAGE_RESPONSE;
    }

//    public ResponseContext execute(RequestContext requestContext) {
//        final String searchQuery = String.valueOf(requestContext.getParameter("search_query"));
//        List<Book> books = OpenLibraryApiHelper.searchBookByName(searchQuery);
//        requestContext.setAttribute("books", books);
//        return MAIN_PAGE_RESPONSE;
//    }



}

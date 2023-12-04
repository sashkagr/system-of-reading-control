package org.example.command.impl.page;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.service.impl.BookServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public enum ShowBookPlanPageCommand implements Command {
    INSTANCE;

    private final BookService bookService = BookServiceImpl.INSTANCE;

    public static final ResponseContext MAIN_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/pages/bookPlan.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String dateStr = String.valueOf(requestContext.getParameter("date"));
        Date requestedDate;
        if (dateStr != null && !dateStr.isEmpty() && !"null".equals(dateStr)) {
             requestedDate = Date.valueOf(dateStr);
        }
        else
        {
            requestedDate = Date.valueOf(LocalDate.now());
        }
        Long userId = (Long) requestContext.getSession().getAttribute("id");
        List<Book> allUserBooks = bookService.getAllUserBooks(userId.intValue());
        List<Book> requestedBooks = allUserBooks.stream()
                .filter(bookFromList -> {
                    if (bookFromList.getStartDate() == null) {
                        return false;
                    }
                    if (bookFromList.getStartDate() != null &&
                            (bookFromList.getStartDate().before(requestedDate) || bookFromList.getStartDate().equals(requestedDate))) {
                        if (bookFromList.getEndDate() != null &&
                                (bookFromList.getEndDate().after(requestedDate) || bookFromList.getEndDate().equals(requestedDate))) {
                            return true;
                        }
                        else if (bookFromList.getEndDate() != null) return false;
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        requestContext.setAttribute("requestedDate", requestedDate);
        requestContext.setAttribute("books", requestedBooks);
        return MAIN_PAGE_RESPONSE;
    }
}

package org.example.command.impl;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;
import org.example.command.impl.page.ShowBookPageCommand;
import org.example.helper.OpenLibraryApiHelper;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.service.impl.BookServiceImpl;

import java.sql.Date;
import java.util.List;

public enum ManageBookCommand implements Command {
    INSTANCE;

    BookService bookService = BookServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        final String bookKey = String.valueOf(requestContext.getParameter("key"));
        final String edit = String.valueOf(requestContext.getParameter("edit"));
        Book book = OpenLibraryApiHelper.getBookByKeys(bookKey);
        Long userId = (Long) requestContext.getSession().getAttribute("id");
        List<Book> allUserBooks = bookService.getAllUserBooks(userId.intValue());

        boolean isBookAlreadyAdded = allUserBooks.stream()
                .anyMatch(bookInList -> bookInList.getId().equals(book.getId()));

        if ("true".equals(edit))
        {
            final String StartDate = String.valueOf(requestContext.getParameter("startDate"));
            final String endDate = String.valueOf(requestContext.getParameter("endDate"));
            final String isFinished = String.valueOf(requestContext.getParameter("finishedCheckbox"));
            Date endDateD = "null".equals(endDate) || endDate.isEmpty() ? null : Date.valueOf(endDate);
            Date startDateD = "null".equals(StartDate) || StartDate.isEmpty() ? null : Date.valueOf(StartDate);

            if (startDateD == null && endDateD != null)
            {
                requestContext.setAttribute("errorMessage", "Start date can't be empty if end date is set");
            }
            else if (startDateD != null && endDateD != null && endDateD.before(startDateD))
            {
                requestContext.setAttribute("errorMessage", "End date can't be before start date");
            }
            else {
                book.setEndDate(endDateD);
                book.setStartDate(startDateD);
                book.setFinished("on".equals(isFinished));
                bookService.editBook(book, userId.intValue());
            }
        }
        else {
            if (isBookAlreadyAdded) {
                bookService.removeBook(book, userId.intValue());
            } else {
                bookService.addBook(book, userId.intValue());
            }
        }
        return ShowBookPageCommand.INSTANCE.execute(requestContext);
    }
}

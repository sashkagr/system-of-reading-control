package org.example.command.impl.page;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;

import java.util.ArrayList;
import java.util.List;

public enum ShowMainPageCommand implements Command {
    INSTANCE;

    public static final ResponseContext MAIN_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/pages/main.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    public static final ResponseContext MAIN_PAGE_RESPONSE_REDIRECT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/pages/main.jsp";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public String getUrlToRedirect() {
            return "/";
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext context = MAIN_PAGE_RESPONSE;
        if (requestContext.getAttribute("redirect") != null) {
            context = MAIN_PAGE_RESPONSE_REDIRECT;
        }
        return context;
    }
}

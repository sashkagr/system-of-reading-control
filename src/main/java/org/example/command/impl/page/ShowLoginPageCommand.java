package org.example.command.impl.page;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;

public enum ShowLoginPageCommand implements Command {
    INSTANCE;

    public static final ResponseContext LOGIN_PAGE_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/pages/signIn.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext LOGIN_PAGE_REDIRECT = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/pages/signIn.jsp";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }

        @Override
        public String getUrlToRedirect() {
            return "/login";
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext response = LOGIN_PAGE_RESPONSE;
        if (requestContext.getParameter("redirect") != null) {
            response = LOGIN_PAGE_REDIRECT;
        }
        return response;
    }
}

package org.example.command.impl.page;

import org.example.command.Command;
import org.example.command.RequestContext;
import org.example.command.ResponseContext;

public enum ShowSignUpPageCommand implements Command {
    INSTANCE;


    public static final ResponseContext SIGN_UP_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/pages/signUp.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        return SIGN_UP_RESPONSE;
    }
}

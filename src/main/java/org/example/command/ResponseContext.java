package org.example.command;

public interface ResponseContext {
    /**
     * Method is used to get a JSP page path from response context
     *
     * @return JSP page path as a string
     */
    String getPage();

    /**
     * Method is used to detect response context with redirect
     *
     * @return true if redirect otherwise false
     */
    boolean isRedirect();

    /**
     * Method is used to get URL to redirect
     *
     * @return URL as a string
     */
    default String getUrlToRedirect() {
        return "/";
    }
}

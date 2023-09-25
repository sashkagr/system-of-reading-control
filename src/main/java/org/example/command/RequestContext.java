package org.example.command;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface RequestContext {

    /**
     * Method is used to set request attribute
     *
     */
    void setAttribute(String name, Object object);

    /**
     * Method is used to get request attribute
     *
     * @return requested attribute
     */
    Object getAttribute(String name);

    /**
     * Method is used to get request parameter
     *
     * @return requested parameter as a string
     */
    String getParameter(String name);

    /**
     * Method is used to invalidate request session
     *
     */
    void invalidateSession();

    /**
     * Method is used to set attribute within current session
     *
     */
    void setSessionAttribute(String name, Object value);

    /**
     * Method is used to get current request session
     *
     * @return current session
     */
    HttpSession getSession();

    /**
     * Method is used to get multiple request parameters
     *
     * @return Optional List of request parameters
     */
    Optional<List<String>> getParameterValues(String name);
}
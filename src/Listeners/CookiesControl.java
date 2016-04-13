package Listeners;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookiesControl class is used to handle getting/adding/deleting cookies through three public static methods.
 */
public class CookiesControl {

    /**
     * used to get the value of cookie.
     * @param request HttpServletRequest
     * @param name String key name of the cookie
     * @return value of the cookie as String or null if the cookie not found
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * used to add new cookie.
     * @param response HttpServletResponse
     * @param name String key name of the new cookie
     * @param value String value of the new cookie.
     * @param maxAge int the number of milliseconds which the cookie will be valid, after that the cookie will be destroyed.
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * used to remove a existing cookie by setting its value to null and maxAge to 0.
     * @param response HttpServletResponse
     * @param name String key name of the cookie
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        addCookie(response, name, null, 0);
    }

}

package au.org.ala.web;

import java.lang.annotation.*;

/**
 * Cut down version of the Spring Security @Secured annotation that will allow role based authorisation
 * on Grails controllers and controller actions *only*.
 *
 * @author Simon Bear (simon.bear@csiro.au)
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AlaSecured {
    /**
     * A list of roles that the user must have to have access to the method
     */
    public abstract String[] value();

    /**
     * Change the behaviour such that the user must have only one role from the roles list to have access to the method
     */
    public abstract boolean anyRole() default false;

    /**
     * Change the behaviour such that the user must *not* have any roles from the roles list to have access to the method
     */
    public abstract boolean notRoles() default false;

    public abstract String redirectController() default "";

    public abstract String redirectAction() default "index";

    public abstract String message() default "Permission denied";
}

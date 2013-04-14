package au.org.ala.web

import au.org.ala.cas.util.AuthenticationCookieUtils
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.springframework.web.context.request.RequestContextHolder

class AuthService {
    static transactional = false

    def getEmail() {
        def email = RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.email

        if (!email) {
            email  = AuthenticationCookieUtils.getUserName(RequestContextHolder.currentRequestAttributes().getRequest())
        }

        email
    }

    def getUserId() {
        def userId = RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.userid

        if (!userId) {
            userId = AuthenticationCookieUtils.getUserName(RequestContextHolder.currentRequestAttributes().getRequest())
        }

        userId
    }



    def getDisplayName() {
        if(RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.firstname){
            ((RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.firstname) +
                    " " + (RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.lastname))
        } else {
            null
        }
    }

    protected boolean userInRole(role) {
        log.debug("userInRole: " + RequestContextHolder.currentRequestAttributes()?.isUserInRole(role))
        return ConfigurationHolder.config.security.cas.bypass ||
                RequestContextHolder.currentRequestAttributes()?.isUserInRole(role) // || isAdmin()
    }

//    old version

    def email(){
        //println(RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes)
        (RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.email)?:null
    }

    def firstname(){
        (RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.firstname)?:null
    }

    def surname(){
        (RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes?.lastname)?:null
    }

    def isValidUserName(String username){
        //TODO check that the username is for a current CAS user
        true
    }

}
package au.org.ala.web

import au.org.ala.cas.util.AuthenticationCookieUtils
import au.org.ala.cas.util.AuthenticationUtils
import org.apache.commons.lang.StringUtils
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.context.request.RequestContextHolder

import javax.servlet.http.HttpServletRequest

class AuthService {

    static transactional = false

    def grailsApplication
    def userListService


    def getEmail() {
        return AuthenticationUtils.getEmailAddress(RequestContextHolder.currentRequestAttributes().getRequest())
    }

    def getUserId() {
        def request = RequestContextHolder.currentRequestAttributes().getRequest() as HttpServletRequest
        def userId = AuthenticationUtils.getUserId(request)
        if (!userId) {
            // try the email address, and working backwards from there
            def emailAddress = AuthenticationUtils.getEmailAddress(request)
            if (emailAddress) {
                def user = getUserForEmailAddress(emailAddress)
                if (user) {
                    userId = user.userId
                }
            }
        }
        return userId
    }

    def getDisplayName() {
        return AuthenticationUtils.getDisplayName(RequestContextHolder.currentRequestAttributes().getRequest())
    }

    public boolean userInRole(role) {

        def inRole = AuthenticationUtils.isUserInRole(RequestContextHolder.currentRequestAttributes().getRequest(), role)
        def bypass = grailsApplication.config.security.cas.bypass
        log.debug("userInRole(${role}) - ${inRole} (bypassing CAS - ${bypass})")
        return bypass || inRole
    }

    def userDetails() {
        def attr = RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes
        def details = null

        if (attr) {
            details = [
                userId:attr?.userid?.toString(),
                email: attr?.email?.toString()?.toLowerCase(),
                userDisplayName: "${attr?.firstname?:""} ${attr?.lastname?:""}".trim()
            ]
        }

        details
    }

    UserDetails getUserForUserId(String userId) {
        // TODO: replace this implementation with web service call
        Map<String, UserDetails> um = getAllUserNameMap()
        UserDetails ud = null;

        if (um && um.containsKey(userId)) {
            ud = um.get(userId)
        }

        return ud
    }

    UserDetails getUserForEmailAddress(String emailAddress) {
        // TODO: replace this implementation with web service call
        Map<String, UserDetails> um = getAllUserNameMap()

        return um.values().find {
            it.userName?.equalsIgnoreCase(emailAddress)
        }
    }

    Map<String, UserDetails> getAllUserNameMap() {
        def userListMap = [:]

        try {
            def userListJson = userListService.getFullUserList()
            if (userListJson instanceof JSONObject) {
                // works with path = getUserListWithIds
                userListJson.keySet().each { id ->
                    userListMap.put(String.valueOf(id), userListJson[id]);
                }
            } else if (userListJson instanceof JSONArray) {
                // works with path = getUserListFull
                userListJson.eachWithIndex { user, i ->
                    def userDetails = new UserDetails(userId: user.id, displayName: "${user.firstName} ${user.lastName}", userName: user.userName)
                    userListMap.put(String.valueOf(user.id), userDetails);
                }
            } else {
                log.info "error -  " + userListJson.getClass() + ": ${userListJson}"
            }
        } catch (Exception e) {
            log.error "Cache refresh error: " + e.message, e
        }

        return userListMap
    }


    def getAllUserNameList() {
        def userList = []
        try {
            def userListJson = userListService.getFullUserList()
            if (userListJson instanceof JSONObject) {
                // works with path = getUserListWithIds
                userListJson.keySet().each { id ->
                    userList.add(userListJson[id]);
                }
            } else if (userListJson instanceof JSONArray) {
                // works with path = getUserListFull
                userListJson.eachWithIndex { user, i ->
                    def userDetails = new UserDetails(userId: user.id, displayName: "${user.firstName} ${user.lastName}", userName: user.userName)
                    userList.add(userDetails);
                }
            } else {
                log.info "error -  " + userListJson.getClass() + ": ${userListJson}"
            }
        } catch (Exception e) {
            log.error "Cache refresh error: " + e.message, e
        }

        return userList
    }

}
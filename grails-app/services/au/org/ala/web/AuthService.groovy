package au.org.ala.web

import au.org.ala.cas.util.AuthenticationCookieUtils
import org.apache.commons.lang.StringUtils
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.context.request.RequestContextHolder

class AuthService {
    static transactional = false

    def httpWebService, grailsApplication

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
        return grailsApplication.config.security.cas.bypass ||
                RequestContextHolder.currentRequestAttributes()?.isUserInRole(role) // || isAdmin()
    }

    def userDetails() {
        def attr = RequestContextHolder.currentRequestAttributes()?.getUserPrincipal()?.attributes
        [
                userId:attr?.userid?.toString(),
                email: attr?.email?.toString()?.toLowerCase(),
                userDisplayName: "${attr?.firstname?:""} ${attr?.lastname?:""}".trim()
        ]
    }

    UserDetails getUserForUserId(String userId) {
        Map<String, UserDetails> um = getAllUserNameMap()
        UserDetails ud = null;

        if (um && um.containsKey(userId)) {
            ud = um.get(userId)
        }

        return ud
    }

    @Cacheable("userMapCache")
    Map<String, UserDetails> getAllUserNameMap() {
        def userListMap = [:]
        checkConfig()
        try {
            def userListJson = httpWebService.doJsonPost(grailsApplication.config.userDetails.url, grailsApplication.config.userDetails.path, "", "")

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
                log.info "error -  " + userListJson.getClass() + ":"+ StringUtils.abbreviate(userListJson, 100)
            }
        } catch (Exception e) {
            log.error "Cache refresh error: " + e.message, e
        }

        return userListMap
    }

    @Cacheable("userListCache")
    def getAllUserNameList() {
        def userList = []
        checkConfig()
        try {
            def userListJson = httpWebService.doJsonPost(grailsApplication.config.userDetails.url, grailsApplication.config.userDetails.path, "", "")

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
                log.info "error -  " + userListJson.getClass() + ":"+ StringUtils.abbreviate(userListJson, 100)
            }
        } catch (Exception e) {
            log.error "Cache refresh error: " + e.message, e
        }

        return userList
    }

    def checkConfig() {
        if (!grailsApplication.config.userDetails.url) {
            log.error "Required config not found: userDetails.url - please add to Config.groovy"
        }
        if (!grailsApplication.config.userDetails.path) {
            log.error "Required config not found: userDetails.path - please add to Config.groovy"
        }
    }
}
package au.org.ala.web

import org.codehaus.groovy.grails.commons.GrailsApplication
import spock.lang.Specification

/**
 * Created by bea18c on 1/04/2014.
 */
class SecurityPrimitivesSpec extends Specification {

    private static final String ROLE_POTATO = "ROLE_POTATO"

    AuthService mockAuthService
    GrailsApplication mockGrailsApplication
    ConfigObject mockConfig
    SecurityPrimitives secPrim

    def setup() {
        mockAuthService = Mock()
        mockGrailsApplication = Mock()
        mockConfig = new ConfigObject()
        mockGrailsApplication.config >> mockConfig

        secPrim = new SecurityPrimitives(mockAuthService, mockGrailsApplication)
    }


    def "test logged in user"() {
        given:
        mockAuthService.userDetails() >> [userId:"potato", email: "potato@potato.po", userDisplayName: "Mr Potato", ]

        when:
        def loggedIn = secPrim.loggedIn
        def notLoggedIn = secPrim.notLoggedIn

        then:
        loggedIn == true
        notLoggedIn == false
    }

    def "test anonymous user"() {
        given:
        mockAuthService.userDetails() >> null

        when:
        def loggedIn = secPrim.loggedIn
        def notLoggedIn = secPrim.notLoggedIn

        then:
        loggedIn == false
        notLoggedIn == true
    }

    def "test isAllGranted"() {
        given:
        mockAuthService.userInRole(ROLE_POTATO) >> false
        mockAuthService.userInRole(!ROLE_POTATO) >> true

        when:
        def noPotato = secPrim.isAllGranted([CASRoles.ROLE_ADMIN, CASRoles.ROLE_USER])
        def potato = secPrim.isAllGranted([CASRoles.ROLE_ADMIN, CASRoles.ROLE_USER, ROLE_POTATO])

        then:
        noPotato == true
        potato == false
    }

    def "test isAnyGranted"() {
        given:
        mockAuthService.userInRole(ROLE_POTATO) >> false
        mockAuthService.userInRole(!ROLE_POTATO) >> true

        when:
        def noPotato = secPrim.isAnyGranted([CASRoles.ROLE_ADMIN, CASRoles.ROLE_USER])
        def potato = secPrim.isAnyGranted([CASRoles.ROLE_ADMIN, CASRoles.ROLE_USER, ROLE_POTATO])
        def onlyPotato = secPrim.isAnyGranted([ROLE_POTATO])

        then:
        noPotato == true
        potato == true
        onlyPotato == false
    }

    def "test isNotGranted"() {
        given:
        mockAuthService.userInRole(ROLE_POTATO) >> false
        mockAuthService.userInRole(!ROLE_POTATO) >> true

        when:
        def noPotato = secPrim.isNotGranted([CASRoles.ROLE_ADMIN, CASRoles.ROLE_USER])
        def potato = secPrim.isNotGranted([CASRoles.ROLE_ADMIN, CASRoles.ROLE_USER, ROLE_POTATO])
        def onlyPotato = secPrim.isNotGranted([ROLE_POTATO])

        then:
        noPotato == false
        potato == false
        onlyPotato == true
    }

    def "test ROLE_ADMIN replacement"() {
        given:
        // security.cas.adminRole
        def cas = new ConfigObject()
        cas.put("adminRole", "ROLE_MAGIC")
        def security = new ConfigObject()
        security.put("cas", cas)
        mockConfig.put("security", security)
        mockAuthService.userInRole("ROLE_MAGIC") >> true
        mockAuthService.userInRole(!"ROLE_MAGIC") >> false

        when:
        def replaced = secPrim.isAllGranted([CASRoles.ROLE_ADMIN])
        def anyReplaced = secPrim.isAnyGranted([CASRoles.ROLE_ADMIN, ROLE_POTATO])
        def notReplacedPos = secPrim.isNotGranted([ROLE_POTATO])
        def notReplacedNeg = secPrim.isNotGranted([CASRoles.ROLE_ADMIN])

        then:
        replaced == true
        anyReplaced == true
        notReplacedPos == true
        notReplacedNeg == false
    }
}

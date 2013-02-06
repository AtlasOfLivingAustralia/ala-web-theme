/**
 * This file is included in the Grails config by the ala-web-theme plugin.
 *
 * Edit this file to suit your app's dev/test/prod environments
 *
 */

/******* ALA standard config ************/
headerAndFooter.baseURL = "http://www2.ala.org.au/commonui"
security.cas.casServerName = 'https://auth.ala.org.au'
security.cas.uriFilterPattern = ''
security.cas.uriExclusionFilterPattern = '/images.*,/css.*,/js.*'
security.cas.loginUrl = 'https://auth.ala.org.au/cas/login'
security.cas.logoutUrl = 'https://auth.ala.org.au/cas/logout'
security.cas.casServerUrlPrefix = 'https://auth.ala.org.au/cas'
security.cas.bypass = false
ala.baseURL = "http://www.ala.org.au"
bie.baseURL = "http://bie.ala.org.au"
bie.searchPath = "/search"
grails.project.groupId = au.org.ala // change this to alter the default package name and Maven publishing destination
/******* End of ALA standard config ************/

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
        grails.host = "http://localhost"
        grails.serverURL = "${grails.host}:8080/${appName}"
        security.cas.appServerName = "${grails.host}:8080"
        security.cas.contextPath = "${appName}"
        // cached-resources plugin - keeps original filenames but adds cache-busting params
        grails.resources.debug = true
    }
    test {
        grails.logging.jul.usebridge = false
        grails.host = "bie-test.ala.org.au"
        grails.serverURL = "http://bie-test.ala.org.au"
        security.cas.appServerName = grails.serverURL
        security.cas.contextPath = ""
        //log4j.appender.'errors.File'="/var/log/tomcat/biewebapp2-stacktrace.log"
    }
    production {
        grails.logging.jul.usebridge = false
        grails.host = "bie.ala.org.au"
        grails.serverURL = "http://bie.ala.org.au"
        security.cas.appServerName = grails.serverURL
        security.cas.contextPath = ""
        log4j.appender.'errors.File'="/var/log/tomcat6/biewebapp2-stacktrace.log"
    }
}
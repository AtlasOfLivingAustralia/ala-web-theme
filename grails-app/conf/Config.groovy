// configuration for plugin testing - will not be included in the plugin zip

/******************************************************************************\
 *  EXTERNAL SERVERS
 \******************************************************************************/
if (!bie.baseURL) {
    bie.baseURL = "http://bie.ala.org.au"
}
if (!bie.searchPath) {
    bie.searchPath = "/search"
}
if (!biocache.baseURL) {
    biocache.baseURL = "http://biocache.ala.org.au"
}
if (!spatial.baseURL) {
    spatial.baseURL = "http://spatial.ala.org.au"
}
if (!ala.baseURL) {
    ala.baseURL = "http://www.ala.org.au"
}
if (!collectory.baseURL) {
    collectory.baseURL = "http://collections.ala.org.au"
}
if (!bhl.baseURL) {
    bhl.baseURL = "http://bhlidx.ala.org.au"
}
if( !speciesList.baseURL){
    speciesList.baseURL ="http://lists.ala.org.au"
}
userDetails.url ="http://auth.ala.org.au/userdetails/userDetails/"
userDetails.path ="getUserList"
alerts.baseUrl = "http://alerts.ala.org.au/ws/"
//brds.guidUrl = "http://cs.ala.org.au/bdrs-ala/bdrs/user/atlas.htm?surveyId=1&guid="
brds.guidUrl = "http://sightings.ala.org.au/"
collectory.threatenedSpeciesCodesUrl = collectory.baseURL + "/public/showDataResource"
ranking.readonly = false

/******************************************************************************\
 *  SECURITY
 \******************************************************************************/
if (!security.cas.urlPattern) {
    security.cas.urlPattern = "/admin, /admin/.*"
}
if (!security.cas.urlExclusionPattern) {
    security.cas.urlExclusionPattern = "/images.*,/css.*,/js.*,.*json,.*xml"
}
if (!security.cas.authenticateOnlyIfLoggedInPattern) {
    security.cas.authenticateOnlyIfLoggedInPattern = "/species/.*"
}
if (!security.cas.casServerName) {
    security.cas.casServerName = "https://auth.ala.org.au"
}
if (!security.cas.loginUrl) {
    security.cas.loginUrl = "${security.cas.casServerName}/cas/login"
}
if (!security.cas.logoutUrl) {
    security.cas.logoutUrl = "${security.cas.casServerName}/cas/logout"
}
if (!security.cas.contextPath) {
    //security.cas.contextPath = "/workforce" //"""${appName}"
}
if (!security.cas.bypass) {
    security.cas.bypass = false
}

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
        grails.host = "http://localhost"
        grails.serverURL = "${grails.host}:8080/${appName}"
        security.cas.appServerName = "${grails.host}:8080"
        security.cas.contextPath = "/${appName}"
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

log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'

    environments {

        test {
            info   'grails.app'
        }
        development {
            // Override previous setting for 'grails.app'
            info   'grails.app'
            debug  'grails.app'
        }
    }
}

import grails.util.Environment
import grails.util.Holders

class AlaWebThemeGrailsPlugin {
    // the plugin version
    def version = "0.1.2"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Ala Web Theme Plugin" // Headline display name of the plugin
    def author = "Nick dos Remedios"
    def authorEmail = "nick.dosremedios@csiro.au"
    def description = '''\
This plugin provides a Sitemesh layout file (main.gsp) and associated resources files, based on Bootstrap.
It also provides a taglib for the ALA footer and navigation menu, login/logout links and a logout controller,
that invalidates the session. CSS files are generated from the Bootstrap LESS files, so any changes should be
made to `ala.less` and then CSS files generated with provided script (see README.md).
'''

    // URL to the plugin's documentation
    def documentation = "http://code.google.com/p/ala/wiki/AlaWebTheme"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Atlas of Living Australia", url: "http://www.ala.org.au/" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Nick dos Remedios", email: "nick.dosremedios@csiro.au" ], [ name: "Dave Martin", email: "david.martin@csiro.au" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "Google Code", url: "http://code.google.com/p/ala/issues/list" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "http://code.google.com/p/ala/source/browse/trunk/ala-web-theme" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
        mergeConfig(application) // gets values from ala-config.groovy in apps conf dir
        def mappingElement = xml.'context-param'
        def lastMapping = mappingElement[mappingElement.size()-1]
        lastMapping + {
            'context-param' {
                'param-name' ('serverName')
                'param-value' (Holders.config.security.cas.appServerName)
            }
            'context-param' {
                'param-name' ('casServerName')
                'param-value' (Holders.config.security.cas.casServerName)
            }
            'context-param' {
                'param-name' ('contextPath')
                'param-value' (Holders.config.security.cas.contextPath)
            }
            'context-param' {
                'param-name' ('uriFilterPattern')
                'param-value' (Holders.config.security.cas.uriFilterPattern)
            }
            'context-param' {
                'param-name' ('uriExclusionFilterPattern')
                'param-value' (Holders.config.security.cas.uriExclusionFilterPattern)
            }
            'context-param' {
                'param-name' ('authenticateOnlyIfLoggedInFilterPattern')
                'param-value' (Holders.config.security.cas.authenticateOnlyIfLoggedInPattern)
            }
        }

        mappingElement = xml.'filter'
        lastMapping = mappingElement[mappingElement.size()-1]
        lastMapping + {
            'filter' {
                'filter-name' ('CAS Single Sign Out Filter')
                'filter-class' ('org.jasig.cas.client.session.SingleSignOutFilter')
            }
            'filter' {
                'filter-name' ('CAS Authentication Filter')
                'filter-class' ('au.org.ala.cas.client.UriFilter')
                'init-param' {
                    'param-name' ('filterClass')
                    'param-value' ('org.jasig.cas.client.authentication.AuthenticationFilter')
                }
                'init-param' {
                    'param-name' ('casServerLoginUrl')
                    'param-value' (Holders.config.security.cas.loginUrl)
                }
                'init-param' {
                    'param-name' ('gateway')
                    'param-value' ('false')
                }
            }
            'filter' {
                'filter-name' ('CAS Validation Filter')
                'filter-class' ('au.org.ala.cas.client.UriFilter')
                'init-param' {
                    'param-name' ('filterClass')
                    'param-value' ('org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter')
                }
                'init-param' {
                    'param-name' ('casServerUrlPrefix')
                    'param-value' (Holders.config.security.cas.casServerName)
                }
            }
            'filter' {
                'filter-name' ('CAS HttpServletRequest Wrapper Filter')
                'filter-class' ('au.org.ala.cas.client.UriFilter')
                'init-param' {
                    'param-name' ('filterClass')
                    'param-value' ('au.org.ala.cas.client.AlaHttpServletRequestWrapperFilter')
                }
            }
            'filter-mapping' {
                'filter-name' ('CAS Single Sign Out Filter')
                'url-pattern' ('/*')
            }
            'filter-mapping' {
                'filter-name' ('CAS Authentication Filter')
                'url-pattern' ('/*')
            }
            'filter-mapping' {
                'filter-name' ('CAS Validation Filter')
                'url-pattern' ('/*')
            }
            'filter-mapping' {
                'filter-name' ('CAS HttpServletRequest Wrapper Filter')
                'url-pattern' ('/*')
            }
        }
    }

    def doWithSpring = {
        //System.println("Merging conf...")
        //mergeConfig(application)
    }

    protected mergeConfig(application) {
        application.config.merge(loadConfig(application))
        //System.println("Conf slurp: " + loadConfig(application))
    }

    protected loadConfig(application) {
        new ConfigSlurper(Environment.current.name).parse(application.classLoader.loadClass("ala-config"))
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
//        def config = application.config;
//        System.println("doWithApplicationContext config = " + config)
//        config.test.bar = 'set from within doWithApplicationContext'
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}

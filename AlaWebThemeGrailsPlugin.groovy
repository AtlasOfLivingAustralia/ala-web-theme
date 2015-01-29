import au.org.ala.web.SecurityPrimitives
import grails.util.Environment
import grails.util.Holders

class AlaWebThemeGrailsPlugin {
    // the plugin version
    def version = "1.1.0"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.4 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = ["grails-app/views/error.gsp"]

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

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "MPL2"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Atlas of Living Australia", url: "http://www.ala.org.au/" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Nick dos Remedios", email: "nick.dosremedios@csiro.au" ], [ name: "Dave Martin", email: "david.martin@csiro.au" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "Google Code", url: "http://code.google.com/p/ala/issues/list" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "http://code.google.com/p/ala/source/browse/trunk/ala-web-theme" ]

    // Note: ONLY evaluated at compile time (not run time)
    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }
}

class AlaWebThemeGrailsPlugin {
    // the plugin version
    def version = "0.1"
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
    def organization = [ name: "Atlas of Living Australia", url: "http://www.ala.org/" ]

    // Any additional developers beyond the author specified above.
    def developers = [ [ name: "Dave Martin", email: "david.martin@csiro.au" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "Google Code", url: "http://code.google.com/p/ala/issues/list" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "http://code.google.com/p/ala/source/browse/trunk/ala-web-theme" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
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

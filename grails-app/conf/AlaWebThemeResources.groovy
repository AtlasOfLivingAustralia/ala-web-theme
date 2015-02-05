modules = {
    bootstrap {
        dependsOn 'core'
        resource url:'/js/bootstrap.js'
        resource url:'/css/bootstrap.css', attrs:[media:'screen, projection, print']
        resource url:'/css/bootstrap-responsive.css', attrs:[media:'screen, projection, print']
    }

    core {
        dependsOn 'jquery', 'autocomplete'
        resource url: '/js/html5.js', wrapper: { s -> "<!--[if lt IE 9]>$s<![endif]-->" }
        resource url: '/js/application.js'
    }

    autocomplete {
        // This is a legacy component that do not work with latest versions of jQuery (1.11+, maybe others)
        dependsOn 'jquery-migration'
        // Important note!!: To use this component along side jQuery UI, you need to download a custom jQuery UI compilation that
        // do not include the autocommplete widget
        resource url: '/css/jquery.autocomplete.css'
        resource url: '/js/jquery.autocomplete.js'
    }

    'jquery-migration' {
        // Needed to support legacy js components that do not work with latest versions of jQuery
        dependsOn 'jquery'
        resource url: '/js/jquery-migrate-1.2.1.min.js'
    }

}
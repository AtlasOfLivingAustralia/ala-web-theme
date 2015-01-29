modules = {
    core {
        dependsOn 'jquery'
        resource url: [dir:'js', file:'html5.js', plugin: "ala-web-theme"], wrapper: { s -> "<!--[if lt IE 9]>$s<![endif]-->" }, disposition: 'head'
    }

    bootstrap {
        dependsOn 'core'
        resource url:[dir:'bootstrap/js', file:'bootstrap.js', plugin: 'ala-web-theme', disposition: 'head']
        resource url:[dir:'bootstrap/css', file:'bootstrap.css', plugin: 'ala-web-theme'], attrs:[media:'screen, projection, print']
        resource url:[dir:'bootstrap/css', file:'bootstrap-responsive.css', plugin: 'ala-web-theme'], attrs:[media:'screen', id:'responsiveCss']
    }

    jqueryui {
        dependsOn "jquery"
        resource url:'js/jquery-ui-1.9.2.custom.min.js'
        resource url:'css/jquery-ui/ui-lightness/jquery-ui-1.9.2.custom.min.css'
        resource url:'css/ala-autocomplete.css'
    }

    application {
        // implement in client app
    }

}
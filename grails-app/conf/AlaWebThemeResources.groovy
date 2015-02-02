modules = {
    core {
        dependsOn 'jquery'
        resource url: [dir:'js', file:'html5.js', plugin: "ala-web-theme"], wrapper: { s -> "<!--[if lt IE 9]>$s<![endif]-->" }, disposition: 'head'
    }

    bootstrap {
        dependsOn 'core'
        resource url:[dir:'bootstrap/js', file:'bootstrap.js', plugin: 'ala-web-theme']
        resource url:[dir:'bootstrap/css', file:'bootstrap.min.css', plugin: 'ala-web-theme'], attrs:[media:'screen, projection, print']
        resource url:[dir:'bootstrap/css', file:'bootstrap-responsive.min.css', plugin: 'ala-web-theme'], attrs:[media:'screen', id:'responsiveCss']
    }

    app_bootstrap {
        dependsOn 'application'
        resource url: 'bootstrap/js/bootstrap.min.js', plugin: 'ala-web-theme'
        resource url: 'bootstrap/css/bootstrap.min.css', plugin: 'ala-web-theme', attrs:[media:'screen,print']
        //resource url: 'bootstrap/less/bootstrap.less', plugin: 'ala-web-theme',attrs:[rel: "stylesheet/less", type:'css', media:'screen,print'], bundle:'bundle_ala-web-theme'
        //resource url: 'css/empty.css', plugin: 'ala-web-theme', bundle:'bundle_ala-web-theme' // needed for less-resources plugin ?
        //resource url: 'js/less-1.3.3.min.js', plugin: 'ala-web-theme', disposition: 'head'
        resource url: 'bootstrap/images/glyphicons-halflings-white.png', plugin: 'ala-web-theme'
        resource url: 'bootstrap/images/glyphicons-halflings.png', plugin: 'ala-web-theme'
    }

    app_bootstrap_responsive {
        dependsOn 'app_bootstrap'
        resource url: '/bootstrap/css/bootstrap-responsive.min.css', plugin: 'ala-web-theme', attrs:[media:'screen,print']
        //resource url: 'bootstrap/less/responsive.less', plugin: 'ala-web-theme',attrs:[rel: "stylesheet/less", type:'css', media:'screen,print'], bundle:'bundle_ala-web-theme'
        //resource url: 'css/empty.css', plugin: 'ala-web-theme', bundle:'bundle_ala-web-theme' // needed for less-resources plugin ?
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
modules = {
    core {
        dependsOn 'jquery'
        resource url: "${grailsApplication.config.ala.baseURL?:'http://www.ala.org.au'}/wp-content/themes/ala2011/css/jquery.autocomplete.css"
        resource url: "${grailsApplication.config.ala.baseURL?:'http://www.ala.org.au'}/wp-content/themes/ala2011/scripts/jquery.autocomplete.js"
        resource url: [dir:'js', file:'html5.js', plugin: "ala-web-theme"], wrapper: { s -> "<!--[if lt IE 9]>$s<![endif]-->" }
    }

    bootstrap {
        dependsOn 'core'
        resource url:[dir:'js', file:'bootstrap.js', plugin: 'ala-web-theme']
        resource url:[dir:'css', file:'bootstrap.css', plugin: 'ala-web-theme'], attrs:[media:'screen, projection, print']
        resource url:[dir:'css', file:'bootstrap-responsive.css', plugin: 'ala-web-theme'], attrs:[media:'screen', id:'responsiveCss']
    }

}
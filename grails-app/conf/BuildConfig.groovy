import grails.util.Environment

grails.servlet.version = "2.5"
grails.project.work.dir = "target"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolver = "maven"

grails.project.dependency.resolution = {
    //legacyResolve true // if using Grails > 2.2
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

    repositories {
        mavenLocal()
        mavenRepo ("http://nexus.ala.org.au/content/groups/public/") {
            updatePolicy 'always'
        }
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        // runtime 'mysql:mysql-connector-java:5.1.18'
        compile group: 'au.org.ala',
                name: 'ala-cas-client',
                version:'2.1-SNAPSHOT',
                transitive:false
        compile 'org.jasig.cas.client:cas-client-core:3.1.12'
        compile "org.tmatesoft.svnkit:svnkit:1.8.5"
        compile("org.codehaus.groovy.modules.http-builder:http-builder:0.7.1") {
            excludes "groovy"
        }
        compile 'com.yahoo.platform.yui:yuicompressor:2.4.8'
    }

    plugins {

        build(":release:3.0.1",
                ":rest-client-builder:1.0.3") {
            export = false
        }
        runtime ":jquery:1.11.1"
        runtime ':resources:1.2.14'
        if (Environment.current == Environment.PRODUCTION) {
            runtime ":zipped-resources:1.0.1"
            runtime ":cached-resources:1.1"
            compile ":cache-headers:1.1.7"
            runtime ":yui-minify-resources:0.1.5"
        }
        compile ":cache-ehcache:1.0.0"
        compile (":rest:0.7") {
            exclude "servlet-api"
            exclude "http-builder"
        }
        compile ":svn:1.0.2"
    }
}


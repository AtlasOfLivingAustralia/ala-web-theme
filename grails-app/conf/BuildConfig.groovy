grails.servlet.version = "2.5"
//grails.project.class.dir = "target/classes"
//grails.project.test.class.dir = "target/test-classes"
//grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
    //legacyResolve true // if using Grails > 2.2
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        mavenLocal()
        mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://maven.ala.org.au/repository"
        mavenRepo "http://maven.tmatesoft.com/content/repositories/releases/"
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
    }

    plugins {
        runtime ":jquery:1.7.1"
        runtime ":resources:1.2.1"
        compile(":tomcat:7.0.54",
                ":release:3.0.1") {
            export = false
        }
        compile ":cache-ehcache:1.0.0"
        compile ":rest:0.7"
        compile ":svn:1.0.2"
    }
}


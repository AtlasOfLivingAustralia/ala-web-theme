//
// This script is executed by Grails during application upgrade ('grails upgrade'
// command). This script is a Gant script so you can use all special variables
// provided by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//


try {
    File alaConfigFile = new File(new File(new File(basedir, "grails-app"), "conf"), "ala-config.groovy")
    def toConfFile =  "${basedir}/grails-app/conf/ala-config.groovy"
    File layoutFile = new File(new File(new File(basedir, "grails-app"), "views/layouts"), "main.gsp")
    def toLayoutFile =  "${basedir}/grails-app/views/layouts/main.gsp"
    File indexFile = new File(new File(new File(basedir, "grails-app"), "views"), "index.gsp")
    def toIndexFile =  "${basedir}/grails-app/views/index.gsp"

    if (alaConfigFile.exists() && layoutFile.exists() && indexFile.exists()) {
        // assume plugin has been installed previously or files are present via SVN
        ant.echo("Plugin files already present - appending with .new")
        toConfFile += ".new"
        toLayoutFile += ".new"
        toIndexFile += ".new"
    } else {
        // assume first time installation - backup original files first
        if (layoutFile.exists()) {
            ant.copy(file: "${basedir}/grails-app/views/layouts/main.gsp",
                    toFile: "${basedir}/grails-app/views/layouts/main.gsp.bak")
        }
        if (indexFile.exists()) {
            ant.copy(file: "${basedir}/grails-app/views/index.gsp",
                    toFile: "${basedir}/grails-app/views/index.gsp.bak")
        }
    }

//layout file
    ant.copy(file: "${pluginBasedir}/grails-app/views/layouts/main.gsp",
            toFile: toLayoutFile,
            overwrite: true)
// index file
    ant.copy(file: "${pluginBasedir}/grails-app/views/index.gsp",
            toFile: toIndexFile,
            overwrite: true)
// ala-config.groovy file
    ant.copy(file: "${pluginBasedir}/grails-app/conf/ala-config.groovy",
            toFile: toConfFile,
            overwrite: true)
} catch (Exception ex) {
    ant.echo("Error running install script: " + ex.message)
}
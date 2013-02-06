//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//   ant.mkdir(dir:"${basedir}/grails-app/jobs")
//
// backup original project files
//ant.echo("Backing-up project files: index.gsp and layouts/main.gsp")
ant.copy(file: "${basedir}/grails-app/views/layouts/main.gsp",
        toFile: "${basedir}/grails-app/views/layouts/main.gsp.bak")
ant.copy(file: "${basedir}/grails-app/views/index.gsp",
        toFile: "${basedir}/grails-app/views/index.gsp.bak")
// copy over plugin files
//ant.echo("Copying over plugin files: index.gsp and layouts/main.gsp")
ant.copy(file: "${pluginBasedir}/grails-app/views/layouts/main.gsp",
        toFile: "${basedir}/grails-app/views/layouts/main.gsp",
        overwrite: true)
ant.copy(file: "${pluginBasedir}/grails-app/views/index.gsp",
        toFile: "${basedir}/grails-app/views/index.gsp",
        overwrite: true)
ant.copy(file: "${pluginBasedir}/grails-app/conf/auth-config.groovy",
        toFile: "${basedir}/grails-app/conf/ala-config.groovy",
        overwrite: true)
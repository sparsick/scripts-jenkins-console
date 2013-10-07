package com.github.skosmalla.jenkins.scripts.subversion

def oldHostName = "old.hostname.com"
def newHostName = "new.hostname.com"

Hudson.instance.items.each { item ->

    if(item.scm instanceof hudson.scm.SubversionSCM) {
        println("JOB : "+item.name)

        def newLocations = new ArrayList<hudson.scm.SubversionSCM.ModuleLocation>()

        item.scm.locations.each {location ->

            println("SCM Location Remote : " + location.remote)
            def newRemoteUrl = location.remote.replaceFirst(oldHostName, newHostName)



            newLocations.add(new hudson.scm.SubversionSCM.ModuleLocation(newRemoteUrl, location.local, location.depthOption,location.ignoreExternalsOption))
        }

        def newScm = new hudson.scm.SubversionSCM(newLocations, item.scm.workspaceUpdater,
            item.scm.browser, item.scm.excludedRegions, item.scm.excludedUsers, item.scm.excludedRevprop, item.scm.excludedCommitMessages,
            item.scm.includedRegions, item.scm.ignoreDirPropChanges, item.scm.filterChangelog)

        newScm.locations.each { location ->
            println("New SCM Location Remote : " + location.remote)
        }

        item.scm = newScm
        item.save()
        println("\n=======\n")
    }
}

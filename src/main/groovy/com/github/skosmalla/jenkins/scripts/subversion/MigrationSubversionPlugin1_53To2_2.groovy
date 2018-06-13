package com.github.skosmalla.jenkins.scripts.subversion

import hudson.model.Hudson

/**
 * Tested with Jenkins version 1.555 and Subversion Plugin version 2.2
 */

def credentialId = '63c3793c-e3fb-49eb-b45b-f6f8e7364876'

Hudson.instance.items.each {item ->

    if(item.scm instanceof hudson.scm.SubversionSCM) {
        println("JOB : "+item.name)

        def newLocations = new ArrayList<hudson.scm.SubversionSCM.ModuleLocation>()

        item.scm.locations.each {location ->
            newLocations.add(new hudson.scm.SubversionSCM.ModuleLocation(location.remote, credentialId, location.local, location.depthOption,location.ignoreExternalsOption))
        }

        def newScm = new hudson.scm.SubversionSCM(newLocations, item.scm.workspaceUpdater,
            item.scm.browser, item.scm.excludedRegions, item.scm.excludedUsers, item.scm.excludedRevprop, item.scm.excludedCommitMessages,
            item.scm.includedRegions, item.scm.ignoreDirPropChanges, item.scm.filterChangelog, item.scm.additionalCredentials)
        
        item.scm = newScm
        item.save()
        println("\n=======\n")
    }

}
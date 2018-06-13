package com.github.skosmalla.jenkins.scripts.git

/**
 * Tested with Jenkins 2.121.1 and Git Plugin 3.9.1
 */

import hudson.plugins.git.GitSCM
import jenkins.model.Jenkins
import jenkins.triggers.SCMTriggerItem

Jenkins.get().items.each {item ->


    if (item instanceof SCMTriggerItem) {
        item.getSCMs().each { scm ->
            if (scm instanceof GitSCM) {
                println("=====================")
                println("Set GitRepositoryBrowser to (Auto) for JOB: " + item.name)
                scm.setBrowser(null)
            }
        }

        item.save()
    }
}
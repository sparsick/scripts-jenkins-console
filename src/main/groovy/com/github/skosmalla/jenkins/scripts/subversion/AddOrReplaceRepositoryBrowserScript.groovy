package com.github.skosmalla.jenkins.scripts.subversion

def newRepositoryBrowserRootUrl = new URL("http://root.url.to.your.sventon.instance")
def newRepositoryInstance = "repository-instance-name"
def newRepositoryBrowser = new  hudson.scm.browsers.Sventon2(newRepositoryBrowserRootUrl, newRepositoryInstance)

Hudson.instance.items.each { item ->

    if(item.scm instanceof hudson.scm.SubversionSCM) {
        println("JOB: " + item.name)

        def newScm = new hudson.scm.SubversionSCM(Arrays.asList(item.scm.locations), item.scm.workspaceUpdater,
            newRepositoryBrowser, item.scm.excludedRegions, item.scm.excludedUsers, item.scm.excludedRevprop, item.scm.excludedCommitMessages,
            item.scm.includedRegions, item.scm.ignoreDirPropChanges, item.scm.filterChangelog)

        item.scm = newScm
        item.save()

        println("New Repository Browser: " +  item.scm.browser.class)
        println("\n=================\n")


    }
}

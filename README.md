scripts-jenkins-console
=======================

Collection of Groovy scripts for Jenkins script console

Currently, following Groovy scripts exit:
* Rename Subversion host name in each job
* Add or replace the Subversion repository browser in each job (in this case Sventon 2.x is used as repository browser).
* Add or modify the setting for "old build disappear" in each job.
* Migration script from Subversion Plugin 1.5x to version 2.2


##Usage

Copy the script (exclude the package statement) and paste it ínto the Jenkins script console.


##Compatibility
All scripts are tested with Jenkins version 1.555 and Subversion Plugin version 2.2.

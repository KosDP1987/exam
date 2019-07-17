properties ([disableConcurrentBuilds()]) //only one task

pipeline {
    agent {
        label "master"
        //options { timeatamps() }
    }

    //triggers { pollSCM('* * * * *') }

    tools {
        // Note: this should match with the tool name configured in your jenkins instance (JENKINS_URL/configureTools/)
        jdk '1.8.0_212'
        maven '3'
    }

    stages {
        stage('Maven build') {
            buildInfo = rtMaven.run pom: './pom.xml', goals: 'clean install -q'
        }
    }

}

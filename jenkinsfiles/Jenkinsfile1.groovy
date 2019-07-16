properties ([disableConcurrentBuilds()]) //only one task

pipeline {
    agent {
        label "master"
        //options {timeatamps () }
    }

    //triggers { pollSCM('* * * * *') }

    tools {
        // Note: this should match with the tool name configured in your jenkins instance (JENKINS_URL/configureTools/)
        jdk '1.8.0_212'
        maven '3'
    }
    
    stage('Maven build') {
        buildInfo = rtMaven.run pom: './pom.xml', goals: 'clean install'
    }

    stages {
        stage("mvn compile") {
            steps {
                withMaven(maven: '3') {
                    sh 'chmod 755 ./gradlew'
                   // git url: 'https://github.com/KosDP1987/spring-petclinic.git'
                    sh "mvn clean compile"
                }
            }
        }

        stage("mvn depployment") {
            steps {
                withMaven(maven: '3') {
                    sh "mvn clean deploy"
                }
            }
        }


}

pipeline {

    environment {
        JAVA_TOOL_OPTIONS = "-Duser.home=/var/maven"
    }

    agent {
        docker {
            image "maven:3.3.3"
            // use local .m2 repo
            args "-v /tmp/maven:/var/maven/.m2 -e  MAVEN_CONFIG=/var/maven/.m2"
        }
    }

    stages {

        // is this stage necessary??
        // stage("Checkout Codebase") {
        //     steps {
        //         // ??
        //         cleanWS()
        //         checkout scm: [
        //             $class: "GitSCM",
        //             branches: [[name: '*/main']],
        //             userRemoteConfigs: [[credentialsId: 'github-ssh-key', url: 'git@github.com:horaciocrespo/JavaChallenge.git']]
        //         ]
        //     }
        // }

        stage("Build") {
            steps {
                sh "mvn clean compile"
            }
        }

        stage("Unit Tests") {
            steps {
                sh "mvn test"
            }

            // publish report
            post {
                always {
                    junit "**/target/surefire-reports/TEST-*.xml"
                }
            }
        }

        stage("deploy") {

        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
pipeline {

    agent {
        label "simple"
    }

    tools {

        jdk "Jdk-1.8"
        maven "Maven-3.6.1"
    }

    stages {

        stage ("Build") {

            steps {

                    sh 'mvn clean compile'
            }
        }
        stage ('Testing Stage') {

            agent {
                docker {
                  image "318578389648.dkr.ecr.eu-west-2.amazonaws.com/base-images/maven:latest"
                  label "simple"
                }
            }

            steps {

                    echo "tested in build step"
            }
        }
    }
}
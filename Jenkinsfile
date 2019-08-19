pipeline {

    agent any

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

            steps {

                    echo "tested in build step"
            }
        }
    }
}
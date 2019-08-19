pipeline {

    agent any

    tools {

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
pipeline {

    agent any

    stages {

        stage("Login to ECR"){
          steps {
            sh "`aws ecr get-login --no-include-email --region eu-west-2`"
          }
        }

        stage ("Build") {

            agent {
                    docker {
                          image "318578389648.dkr.ecr.eu-west-2.amazonaws.com/base-images/maven:latest"
                        }
            }

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
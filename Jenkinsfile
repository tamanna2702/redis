pipeline {

      agent {
        label "simple"
      }

    stages {

        stage ("Build") {

            agent {
                docker {
                  image "368130942539.dkr.ecr.eu-west-2.amazonaws.com/base-images/maven:latest"
                  label "simple"
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
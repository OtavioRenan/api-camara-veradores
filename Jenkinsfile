pipeline {
    agent any

    stages {

        stage('Initial') {
            steps {
                echo 'Verify plugins.'
                sh '''
                    docker info
                    docker version
                    docker-compose version
                '''
            }
        }

        stage('Build Application') {
            steps {
                echo 'Building application.'
                sh '''
                    docker-compose stop
                    cd application/
                    mvn clean install
                    cd ../
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building docker image.'
                sh 'docker-compose build'
            }
        }

        stage('Start Application') {
            steps {
                echo 'Start application.'
                // sh 'docker-compose up'
            }
        }
    }
}
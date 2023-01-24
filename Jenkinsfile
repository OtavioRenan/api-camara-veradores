pipeline {
    agent any

    stages {
        stage('Initial') {
            steps {
                echo 'Started pipeline.'
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
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building docker image.'
            }
        }

        stage('Start Application') {
            steps {
                echo 'Start application.'
            }
        }
    }
}
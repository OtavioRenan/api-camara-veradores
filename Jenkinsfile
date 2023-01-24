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
    }
}
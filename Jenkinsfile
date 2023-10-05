pipeline {
    agent 'none'
    stages {
        stage('Initial') {
            agent {
                docker { image 'maven' }
            }

            steps {
                echo 'Install e verify Maven.'
                sh '''
                    mvn package -Dmaven.test.skip=true
                '''
            }
        }
    //     stage('Initial') {
    //         steps {
    //             echo 'Verify plugins.'
    //             sh '''
    //                 docker info
    //                 docker version
    //                 docker-compose version
    //             '''
    //         }
    //     }

    //     stage('Build Application') {
    //         steps {
    //             sh '''
    //                 docker-compose stop
    //                 cd application/
    //                 mvn clean install
    //                 cd ../
    //             '''
    //         }
    //     }

    //     stage('Build Docker Image') {
    //         steps {
    //             sh 'docker-compose build'
    //         }
    //     }

    //     stage('Start Tests') {
    //         steps {
    //             sh '''
    //                 cd application/
    //                 mvn --batch-mode -Dmaven.test.failure.ignore=true test
    //                 cd ../
    //             '''
    //         }
    //     }
    }
}

// Test command
// mvn test -Dtest=SecondUnitTest
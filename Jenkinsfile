pipeline {
    agent none
    stages {
        stage('Maven Install') {
            agent {
                docker { image 'maven' }
            }

            steps {
                sh 'mvn clean install'
            }
        }
    }
}

// Test command
// mvn test -Dtest=SecondUnitTest
// mvn --batch-mode -Dmaven.test.failure.ignore=true test
pipeline {
    agent any

    stages {
     	stage('clean') {
                steps {
                    echo 'Destroy database on localhost'
                    sh 'mysql -u root -pPassword00 -e \'drop schema if exists webshop\''
                    echo 'Create database webshop on localhost'
                    sh 'mysql -u root -pPassword00 -e \'create schema webshop\''
                    sh './sbt clean'
                }
            }
        stage('Build') {
            steps {
                echo 'Building..'
                sh './sbt test'
                sh './sbt dist'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying....'
                echo 'Copy zip package to test server....'
                sh 'scp  target/universal/webshop-1.0-SNAPSHOT.zip docker@192.168.1.7:~/webshop.zip'
                echo 'Stop service....'
                sh 'ssh -t docker@192.168.1.7 \'rm -rf webshop\''
                echo 'Start service'
                sh 'ssh -t docker@192.168.1.7 \'unzip webshop.zip\''
                sh 'ssh -t docker@192.168.1.7 \'webshop/bin/webshop -Dplay.http.secret.key=hrwebshop & \''
            }
        }
    }
}


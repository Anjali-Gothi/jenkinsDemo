pipeline {
    agent { label 'winslave1' }
    environment {
        DOCKER_CREDENTIALS = credentials('DOCKER_CREDENTIALS')
        DOCKER_IMAGE_NAME = 'anjaligothi23/devops-integration:v1'
    }
    tools {
        maven 'maven_3_5_0'
    }
    stages {
        stage("Build Maven") {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Anjali-Gothi/jenkinsDemo']]])
                script {
                    if (isUnix()) {
                        sh 'mvn clean install'
                    } else {
                        bat 'mvn clean install'
                    }
                }
            }
        }
        stage("Build Docker Image") {
            steps {
                script {
                    if (isUnix()) {
                        sh "docker build -t ${DOCKER_IMAGE_NAME} ."
                    } else {
                        bat "docker build -t %DOCKER_IMAGE_NAME% ."
                    }
                }
            }
        }
        stage("Push Image to Hub") {
            steps {
                script {
                    // withCredentials([credentialsId: 'DOCKER_CREDENTIALS']) {
                        if (isUnix()) {
                            sh 'echo ${DOCKER_CREDENTIALS_PSW} | docker login --username ${DOCKER_CREDENTIALS_USR} --password=${DOCKER_CREDENTIALS_PSW}'
                            sh "docker push ${DOCKER_IMAGE_NAME}"
                        } else {
                            bat 'echo %DOCKER_CREDENTIALS_PSW% | docker login --username=%DOCKER_CREDENTIALS_USR% --password=%DOCKER_CREDENTIALS_PSW%'
                            bat "docker push %DOCKER_IMAGE_NAME%"
                        }
                    // }
                }
            }
        }
    }
}

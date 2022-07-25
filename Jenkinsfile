#!groovy

pipeline {
  agent any
  stages {
    stage('Maven Install') {
//       agent {
//         docker {
//           image 'maven:3.8.3'
//         }
//       }
      steps {
        bat 'mvn clean install -DskipTests'
      }
    }
    stage('Docker Build') {
//       agent any
      steps {
        bat 'docker build -t get-started/news-search-service:dev .'
      }
    }
    stage('Docker Push') {
          steps {
            withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
              sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
              sh 'docker push get-started/news-search-service:dev'
            }
          }
        }
  }
}
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
        sh 'mvn clean install -DskipTests'
      }
    }
    stage('Docker Build') {
//       agent any
      steps {
        sh 'docker build -t get-started/news-search-service:dev .'
      }
    }
  }
}
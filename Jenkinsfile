#!groovy

pipeline {
  agent none
  stages {
    stage('Maven Install') {
      agent {
        docker {
          image 'maven:3.8.3'
        }
      }
      steps {
        bat 'mvn clean install -DskipTests'
      }
    }
    stage('Docker Build') {
      agent any
      steps {
        bat 'docker build -t get-started/news-search-service:dev .'
      }
    }
  }
}
#!groovy

def dockerImage
pipeline {
  agent any
  stages {
    stage('Maven Install') {
      steps {
             withCredentials([string(credentialsId: 'GUARDIAN_API_KEY', variable: 'guardian_api_key'),
                string(credentialsId: 'NYTIMES_API_KEY', variable: 'nytimes_api_key')]) {
                bat "mvn clean install -DskipTests -DGUARDIAN_API_KEY=${guardian_api_key} -DNYTIMES_API_KEY=${nytimes_api_key}"
          }
      }
    }
    stage('Docker Build') {
      steps {
            script {
                dockerImage = docker.build("mukeshmande/news-search-service:${env.dockerBuildtag}")
            }
      }
    }
    stage('Docker Push') {
          steps {
            script{
              withDockerRegistry([ credentialsId: "docker-hub-credentials", url: "https://registry.hub.docker.com" ]){
                dockerImage.push();
              }
              }
          }
        }
  }
}
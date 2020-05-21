pipeline {
  agent any
  stages {
    stage('Fetch') {
      steps {
        git(url: 'https://github.com/pinkeshsagar-harptec/jenkins.git', branch: 'master', poll: true, changelog: true)
      }
    }

    stage('Build') {
      steps {
        bat(script: 'mvn clean install', encoding: 'UTF-8')
      }
    }

  }
}
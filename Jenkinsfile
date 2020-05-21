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

    stage('PreDeploy') {
      steps {
        bat(script: 'md run\\Configurations run\\Scripts run\\Services ', encoding: 'UTF-8')
        bat(script: '@echo This is jar file content of the file > run\\Scripts\\zRun_Standalone_MAA_Service.bat', encoding: 'UTF-8')
        bat(script: 'xcopy /s /i target\\JsonParser run\\Services\\', encoding: 'UTF-8')
      }
    }

    stage('Deploy') {
      steps {
        bat(script: 'START run\\Scripts\\zRun_Standalone_MAA_Service.bat', encoding: 'UTF-8')
      }
    }

  }
}
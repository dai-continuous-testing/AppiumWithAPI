pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build Server') {
          steps {
            sleep 2
          }
        }
        stage('Build Android App') {
          steps {
            sleep 3
          }
        }
        stage('Build iOS App') {
          steps {
            sleep 3
          }
        }
      }
    }
    stage('Deploy (QA)') {
      parallel {
        stage('Deploy Server') {
          steps {
            sleep 1
          }
        }
        stage('Upload Mobile Apps') {
          steps {
            sleep 1
          }
        }
      }
    }
    stage('Run Tests') {
      parallel {
        stage('Appium') {
          steps {
            powershell 'copy C:\\Users\\guyar\\Desktop\\postman1\\cloud.properties .'
            powershell 'set GRADLE_USER_HOME="c:\\Program Files (x86)\\gradle-4.4-rc-6";./gradlew cleanTest test'
          }
        }
        stage('Unit') {
          steps {
            sleep 10
          }
        }
      }
    }
    stage('Check Performance') {
      parallel {
        stage('Check Performance') {
          steps {
            powershell 'echo \'{"filters":["app_version":1.1,"app_name":"EriBank"]"compare_to_filter":["app_name":"EriBank","app_version","1.0,0.9,0.8"] [{"name":"*","matric":"durration","acceptade_change":"5%"},{"name":"Login","matric":"cpu_avg","acceptade_change":"5%"}]}\';echo \'https://invis.io/DXS4GF3QH9C\'; exit 0'
          }
        }
        stage('Check Visual') {
          steps {
            sleep 5
          }
        }
      }
    }
  }
  environment {
    APP_VERSION = '1.2'
  }
  post {
    always {
      junit 'build/test-results/**/*.xml'

    }

  }
}
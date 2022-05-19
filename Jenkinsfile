pipeline { 
    agent any
    options {
        skipStagesAfterUnstable()
    }
    tools{
        maven "maven-3.6.3"
    }
    stages {
        stage('Source code pull'){
            steps {
                checkout(
                    [$class: 'GitSCM', 
                    branches: [[name: '*/main']], 
                    extensions: [], 
                    userRemoteConfigs: [[credentialsId: '6f52a10f-bd05-44fc-9d47-1c127403797b', 
                    url: 'https://github.com/georgios-antoniadis/UltimateTrackers/']]]
                )
            }
        }
        stage('Clean') { 
            steps { 
                sh 'mvn clean'
            }
        }

        stage('Test') { 
            steps { 
                sh 'mvn test'
            }
        }

        stage('Build') { 
            steps { 
                sh 'mvn -Dmave.test.skip=True package'
            }
        }
        
        stage('Create and upload image to docker hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com/repository/docker/ge0rge21/ultimate_trackers','docker_hub') {
                        def customImage = docker.build("ge0rge21/ultimate_trackers:latest")
            
                        /* Push the container to the custom Registry */
                        customImage.push()// some block
                    }
                }
            }
        }

        stage('Placeholder'){
            steps{
                echo 'This is a placeholder!'
                echo 'Hello from local!'
            }
        }
    }
}
pipeline { 
    agent any

    environment {
        DOCKER_IMAGE = "jsmolak93/student-survey-app:latest"
        KUBECONFIG_CRED_ID = "kubeconfig"  // Replace with actual ID once you get the config
    }

    stages { 
        stage('Clone Repository') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'github-credentials', usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS')]) {
                    sh '''
                        rm -rf ContainerizedMicroservices
                        git clone https://$GIT_USER:$GIT_PASS@github.com/jsmolak93/ContainerizedMicroservices.git
                        cd ContainerizedMicroservices
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    cd ContainerizedMicroservices
                    docker build -t $DOCKER_IMAGE .
                '''
            }
        }

        stage('Push to DockerHub') {
            steps {
                withDockerRegistry([credentialsId: 'docker-credentials', url: '']) {
                    sh '''
                        docker push $DOCKER_IMAGE
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withCredentials([file(credentialsId: KUBECONFIG_CRED_ID, variable: 'KUBECONFIG')]) {
                    sh '''
                        cd ContainerizedMicroservices
                        kubectl apply -f deployment.yaml
                        kubectl apply -f service.yaml
                    '''
                }
            }
        }
    }

    post {
        success {
            echo '🚀 Deployment successful!'
        }
        failure {
            echo '❌ Deployment failed.'
        }
    }
}

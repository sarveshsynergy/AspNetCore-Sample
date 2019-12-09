node {
    def image1 = ''
    def image2 = ''

    stage('Checkout git repo') {
        checkout scm
    }

    stage('Build and push Docker image') {
        image1 = docker.build("${registry_url}/customersapi:${BUILD_NUMBER}", "-f ./src/CustomersAPI/Dockerfile .")
        image2 = docker.build("${registry_url}/customersmvc:${BUILD_NUMBER}", "-f ./src/CustomersMVC/Dockerfile .")
    }

    stage('Unit Tests') {
      sh 'echo test'
    }
    
    stage('Push Images') {
        image1.push('${BUILD_NUMBER}')
        image2.push('${BUILD_NUMBER}')
    }
}

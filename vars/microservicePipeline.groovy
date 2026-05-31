def call(Map config = [:]) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    git branch: 'main', url: 'https://github.com/JonathanBechar/prod-shared-library.git'
                }
            }

            stage('Code Quality') {
                steps {
                    echo "Running code quality checks..."
                }
            }

            stage('Security Scan') {
                steps {
                    scanSecurity(
                        failThreshold: config.failThreshold ?: 'HIGH',
                        scanTarget: config.scanTarget ?: '.'
                    )
                }
            }

            stage('Push Artifact') {
                steps {
                    withVaultSecrets(config.secretPath ?: 'secret/data/artifactory') {
                        pushArtifact(
                            artifactName: config.artifactName ?: 'microservice-app',
                            version: config.version ?: '1.0.0-SNAPSHOT'
                        )
                    }
                }
            }
        }

        post {
            always {
                echo "Cleaning workspace..."
                cleanWs()
            }
        }
    }
}
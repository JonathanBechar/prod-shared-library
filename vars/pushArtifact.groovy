def call(Map config = [:]) {
    def artifactName = config.artifactName ?: 'app'
    def version = config.version ?: '1.0.0-SNAPSHOT'

    def targetRepo = version.contains('-SNAPSHOT') 
        ? 'development-repository' 
        : 'production-repository'

    echo "Preparing artifact upload..."
    echo "Artifact: ${artifactName}"
    echo "Version: ${version}"
    echo "Uploading to: ${targetRepo}"
}
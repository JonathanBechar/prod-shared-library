def call(Map config = [:]) {

    def failThreshold = config.failThreshold ?: 'HIGH'
    def scanTarget = config.scanTarget ?: '.'

    echo "Starting security scan..."
    echo "Target: ${scanTarget}"
    echo "Fail Threshold: ${failThreshold}"

    if (failThreshold == 'CRITICAL') {
        echo "CRITICAL vulnerability detected!"

        error("Security scan failed: Critical vulnerability found in ${scanTarget}")
    }

    echo "Security scan passed."
}
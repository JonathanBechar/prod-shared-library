def call(String secretPath, Closure body) {
    echo "Fetching secrets from Vault path: ${secretPath}"

    withEnv(["VAULT_SECRET=simulated-secret-value"]) {
        try {
            echo "Temporary secret environment variable exposed"
            body()
        } finally {
            echo "Cleaning up temporary Vault secrets"
        }
    }
}
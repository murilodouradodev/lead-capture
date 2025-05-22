# LEAD CAPTURE

## Running the Application
You can run the docker compose at  `src/main/resource/dev-resources` that contain:

* Postgres 
* LocalStack 

After you run: `docker compose up -d` execute the install and follow the documentation to install awslocal:

https://docs.localstack.cloud/user-guide/integrations/aws-cli/#localstack-aws-cli-awslocal

*   `tflocal validate` command will validate the terraform script responsible to create the SNS 
*   `tflocal apply` commando will create the SNS topic

# Running locallyz
`./gradlew bootRun`




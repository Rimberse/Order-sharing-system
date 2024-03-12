# Order-sharing-system
Software Architectures Project

## Team Members
- Kerim HUDAYBERDIYEV
- Senhao JIANG
- Quang Viet NGUYEN
- Zain ZAFAR

## Requirements
- Java 17
- Gradle
- A PostgreSQL database or Docker (optional)

## How to run
1. Deploy a PostgreSQL database

You can use Docker to deploy a PostgreSQL database.
There is a `docker-compose.yml` file in the root directory of the project
that can be used to deploy a PostgreSQL database with Docker.
You can change the environment variables in the `docker-compose.yml` file to configure the database.
Run the following command at project's root directory to deploy a PostgreSQL database with Docker:
```bash
docker compose up -d
```

2. Set up the environment variables

You will need to set up your environment variables to connect to the database:
```bash
# Replace the variables marked with $ with your own values
export DB_URL=jdbc:postgresql://localhost:$DB_PORT/$DB_NAME?stringtype=unspecified
export DB_USERNAME=$YOUR_DB_USERNAME
export DB_PASSWORD=$YOUR_PASSWORD
```

3. Initialize the database

Scripts in the `db` directory can be used to initialize the database.
You can run the following command to initialize the database:
```bash
# Use this command to create the tables
psql -h localhost -p $DB_PORT -U $DB_USERNAME -d $DB_NAME -f db/create.sql
# Use this command to insert some data
psql -h localhost -p $DB_PORT -U $DB_USERNAME -d $DB_NAME -f db/insert.sql
```
4. Install dependencies and build the project
5. Run the application

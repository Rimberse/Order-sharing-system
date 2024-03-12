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
Run the following command to deploy a PostgreSQL database with Docker:
```bash
docker compose up -d
```

2. Set up the environment variables

You will need to set up your environment variables to connect to the database:
```bash
export DB_URL=jdbc:postgresql://localhost:$DB_PORT/$DB_NAME
export DB_USERNAME=$YOUR_DB_USERNAME
export DB_PASSWORD=$YOUR_PASSWORD
```

3. Install dependencies and build the project
4. Run the application

# Project PBO-24

This project is a part of the PBO (Pemrograman Berorientasi Objek) 24 course. It involves setting up a Docker container and connecting to a local database for development purposes. Below are the instructions to get started.

## Getting Started

These instructions will help you set up the project on your local machine for development and testing purposes.

### Prerequisites

- Docker
- Docker Compose
- A web browser (for accessing the database)

### Start Docker

To get the project running, first, make sure you have Docker installed on your machine. Then, run the following commands to start up the Docker containers.

```bash
docker compose down
docker compose up -d
```

- `docker compose down`: Stops the currently running containers and removes them.
- `docker compose up -d`: Starts the containers in detached mode.

### Database

Once the containers are up and running, you can access the database through the following URL:

```bash
http://localhost:8001/
```

This should open a web interface for managing the database locally.

## How to Contribute

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes.
4. Push to your forked repository.
5. Submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

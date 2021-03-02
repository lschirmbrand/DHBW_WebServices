# DHBW_WebServices

## Setup for Development

### Build and Start DB-Container

To build and start the MariaDB Container for the first time

```bash
cd database
docker build . -t db
docker run --name db -p 3306:3306 -d db
```

After that you can always stop and start the database with

```bash
docker stop db
docker start db
```

## Run and build with Docker Compose

To build and start containers run

`docker-compose up -d`

To rebuild run

`docker-compose build`
## External APIs

### The Movie Databse API

- Docs: <https://developers.themoviedb.org/>
- Beispiel Anfrage: <https://api.themoviedb.org/3/movie/550?api_key=27cfe88c59a7777abea0afba2eca5e68>

#### Auth

- API-Key: `27cfe88c59a7777abea0afba2eca5e68`
- Bearer-Token: `eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyN2NmZTg4YzU5YTc3NzdhYmVhMGFmYmEyZWNhNWU2OCIsInN1YiI6IjYwMjNhNGZmNDU4MTk5MDAzZmFjZjcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tn3gy3VlkVBw87KlnTEpzuMrGb1I50WaKoWBph3SPqc`

### Spotify API

#### Auth

- Client ID: `c38b4ae6e9044e959087ab9d468fdcdb`
- Client Secret: `9ea62490d4f241df9dae04bab128b618`

## Database

1. Create User with root credentials (username: Songguesser, password: songguesser)
2. Create database songguesser

(Or change application.properties)

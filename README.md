# DHBW_WebServices

[![Gitlab pipeline status](https://img.shields.io/gitlab/pipeline/123niel/soundtrack-guesser/main)](https://gitlab.com/123niel/soundtrack-guesser/-/pipelines)


## Setup for Development

### Build and Start DB-Container

To build and start the MariaDB Container run

```bash
docker-compose up db
```

## Run and build with Docker Compose

To build and start containers run

`docker-compose up -d`

To rebuild and start run

`docker-compose up -d --build`

## External APIs

### The Movie Databse API

- Docs: <https://developers.themoviedb.org/>
- API-Key: `27cfe88c59a7777abea0afba2eca5e68`
- Bearer-Token: `eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyN2NmZTg4YzU5YTc3NzdhYmVhMGFmYmEyZWNhNWU2OCIsInN1YiI6IjYwMjNhNGZmNDU4MTk5MDAzZmFjZjcwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tn3gy3VlkVBw87KlnTEpzuMrGb1I50WaKoWBph3SPqc`

### Spotify API

- Docs: <https://developer.spotify.com/documentation/web-api/>
- Client ID: `c38b4ae6e9044e959087ab9d468fdcdb`
- Client Secret: `9ea62490d4f241df9dae04bab128b618`

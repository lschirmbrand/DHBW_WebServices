FROM node:current-alpine AS build

WORKDIR /app

COPY package*.json /app/

RUN npm install

COPY . ./

ARG REACT_APP_SERVER_PORT

ENV REACT_APP_SERVER_PORT=${REACT_APP_SERVER_PORT}

RUN npm run build

FROM nginx:stable-alpine

COPY --from=build /app/build /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d

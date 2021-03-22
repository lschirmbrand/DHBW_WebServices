FROM node:current-alpine AS build

WORKDIR /app

COPY package*.json /app/

RUN npm install

COPY . ./

RUN npm run build

FROM nginx:stable-alpine

COPY --from=build /app/build /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d

EXPOSE 80

WORKDIR /usr/share/nginx/html

COPY ./env.sh .
COPY .env .

RUN apk add --no-cache bash

RUN chmod +x env.sh


CMD ["/bin/bash", "-c", "/usr/share/nginx/html/env.sh && nginx -g \"daemon off;\""]

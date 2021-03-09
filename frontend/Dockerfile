FROM node AS build

WORKDIR /app

COPY package*.json /app/

RUN npm install

COPY . ./

RUN npm run build

FROM nginx

COPY --from=build /app/build /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d

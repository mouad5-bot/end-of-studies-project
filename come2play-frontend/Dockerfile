FROM node:20.10.0 AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

RUN npm install -g @angular/cli

COPY . .

RUN ng build --configuration=production --stats-json

FROM nginx:latest

COPY --from=build /app/dist/come2play-frontend /usr/share/nginx/html

EXPOSE 80

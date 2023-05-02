# Stage 1


FROM node:latest as node


# RUN mkdir -p /app


WORKDIR /app


COPY . .


RUN npm install


# COPY . /app


RUN npm run build --prod


 


# Stage 2


FROM nginx:1.17.1-alpine


COPY --from=node /app/dist/reactive-form /usr/share/nginx/html
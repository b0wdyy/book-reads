FROM node:lts-alpine

WORKDIR /app

COPY package.json .

COPY yarn.lock .

RUN yarn

COPY . .

EXPOSE 4200

CMD ["yarn", "start"]

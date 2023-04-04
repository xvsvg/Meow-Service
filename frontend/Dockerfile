FROM node:latest
WORKDIR /frontend
COPY . .
RUN npm i
RUN npm run build
CMD ["npm", "start"]
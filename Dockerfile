FROM postgres:15-alpine

ENV POSTGRES_PASSWORD=admin

ENV POSTGRES_USER=bowdy

ENV POSTGRES_DB=bookreads

EXPOSE 5432

FROM postgres

ENV POSTGRES_DB gil-db
ENV POSTGRES_PASSWORD postgres

COPY schema.sql /docker-entrypoint-initdb.d/
COPY data.sql /docker-entrypoint-initdb.d/

EXPOSE 5432
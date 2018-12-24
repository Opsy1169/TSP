FROM postgres:9.5

RUN localedef -i ru_RU -c -f UTF-8 -A /usr/share/locale/locale.alias ru_RU.UTF-8

ENV POSTGRES_USER posohaka
ENV POSTGRES_PASSWORD 12345
ENV POSTGRES_DB blog

EXPOSE 5432

# COPY ./docker_docker-entrypoint-initdb.d /docker-entrypoint-initdb.d
COPY ./docker_docker-entrypoint-initdb.d /mydump

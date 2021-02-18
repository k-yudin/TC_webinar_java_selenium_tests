# docker build -t java_tc_counter .

FROM maven:3.6.3-ibmjava-8

WORKDIR /tests

COPY . .

RUN echo "Downloading maven dependencies..." && mvn dependency:resolve -q && echo "Download complete"

CMD mvn -Dtest=SimpleTest test
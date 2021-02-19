# docker build -t java_tc_counter .

FROM maven:3.6.3-ibmjava-8

WORKDIR /tests

COPY . .

CMD mvn -Dtest=SimpleTest test
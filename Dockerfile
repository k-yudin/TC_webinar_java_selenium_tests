FROM maven:3.6.3-ibmjava-8

WORKDIR /tests

COPY . .

RUN mvn install

CMD mvn -Dtest=SimpleTest test
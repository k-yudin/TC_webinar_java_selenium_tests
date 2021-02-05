# TeamCity webinar - Java Selenium tests

## Running from a terminal
```docker run --rm -p 4444:4444  selenium/standalone-chrome```

then

```mvn -Dtest=SimpleTest test```

## Report
To generate the report, run ```allure-results/```

More about Allure implementation for pytest is [here](https://docs.qameta.io/allure/#_testng).

## Running inside the Docker
```docker-compose up --build --abort-on-container-exit```

or

```docker-compose up --abort-on-container-exit```



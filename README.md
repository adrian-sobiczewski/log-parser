## ASSUMPTIONS
- each argument (ie. startDate can only have one value)

## FEATURES
- Argument accesslog is optional - default value is 'access.log' in current directory
  Also for the integration tests file 'access.log' is also in classpath.
- Tables are dropped and created at each start of the application

## DATABASE SETUP
PROD/DEV: In file application.yml in main/resources

TEST: In file application.yml in test/resources

## BUILD
mvn package -DskipTests

jar file in target dir

## RUN
access.log in  or provide argument --accesslog=/path/to/file

java -jar log-parser-1.0.0.jar --startDate=2017-01-01.15:00:00 --duration=hourly --threshold=20

## SQL
### DDL
src/main/resources/schema.sql

### QUERIES
Find IPs by arguments specified on startup
```roomsql
SELECT address, COUNT(address) FROM LOG 
WHERE date BETWEEN ? AND ? 
GROUP BY address 
HAVING COUNT(address) >= ?;
```

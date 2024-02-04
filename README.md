# Etech-SW for schools, studens, scholars, researchers, grants, and teachers (gil)

The goal of this project is to develop a solution for students, scholars, researchers and teachers in africa to have a single point of access to the information around education, training, competences exchange, scholarships and research. The information available at the site should be kept up-to-date with institute, universities and others partners to offer valid information. External partner should have the possibilities to access the differents services over open api if register to our platform.

# Wiki project page

Further descriptios of the service available here [wiki](https://etech-sw.atlassian.net/jira/software/projects/ESgil/boards/12).

## Configuration


## Execute locally

'''
mvn sping-boot:run -Dspring-boot.run.profiles=h2
'''

## Running With Docker Compose

'''
docker build .
docker-compose up
'''

## Access h2 console in browser

'''
http://localhost:8081/gil-api/h2-console
'''

## Access swagger openapi documentation

'''
http://localhost:8081/gil-api/swagger-ui.html
http://localhost:8081/gil-api/v3/api-docs
'''

## Use case flow

### Endpoints
### Request Parameters
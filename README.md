# Gameon-graph-map

This project has Game-map demostration using Graph database with Neo4J and JNoSQL

![Neo4J Project](https://jnosql.github.io/images/home_logo.png)


Recommendation system using Java EE 8 and Neo4J with JNoSQL.


![Neo4J Project](https://jnosql.github.io/img/logos/neo4j.png)

Neo4j is a graph database management system developed by Neo4j, Inc. Described by its developers as an ACID-compliant transactional database with native graph storage and processing, Neo4j is the most popular graph database according to DB-Engines ranking.

## Install Neo4J

### How To install


![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/neo4j
1. Run docker command
1. `docker rm -f neo4j || true && docker run -d --name neo4j -p 7474:7474 -p 7687:7687 -v $HOME/neo4j/data:/data neo4j`
1. configure Neo4J http://localhost:7474 - The default initial password is "neo4j". Set it to "admin".

# Run the Application
1. `mvn clean package`
1. Adds it in a Java EE 8 server


## Tests

1. `curl -H "Content-Type: application/json" -X POST -d '{"owner":"Leonardo Lima","doorDescription":{"east":"East description","south":"south description","north":"north description","west":"West description"},"name":"main room","fullName":" main room full name"}' http://localhost:8080/gameon-map-0.0.4/resource/sites/
`
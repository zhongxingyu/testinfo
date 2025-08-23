
# Understanding Query Optimization Bugs in Graph Database Systems

This repository contains the dataset, code, and supplementary material for our ASPLOS submission 
"Understanding Query Optimization Bugs in Graph Database Systems". Recent years have witnessed an ever-growing usage of graph
database management systems (GDBMSs) in various data-driven applications. Query optimization aims to improve
the performance of database queries by identifying the most efficient way to execute them, and is an important stage of GDBMS workflow.
Like other sophisticated systems, such as compilers, the query optimization process is complex and its implementation is prone to bugs. 
This work conducts the first characteristic study of query optimization bugs in GDBMSs, including root causes, manifestation methods, and
fix strategies, and delivers 10 novel and important findings about them. Based on the characteristic study, we also developed a testing tool
tailored to uncover GDBMS query optimization bugs, and the tool found 20 unique GDBMS bugs, 15 confirmed to be query optimization bugs. 

## GDBMS Issues Under Study

The [Issues](./Issues) folder contains the 112 issues used for conducting the characterstic study. These issues were selected from four popular GDBMSs, including Neo4j, Memgraph, RedisGraph, and Kuzu. We first search for the issue trackers of the four GDBMSs in to identify query 
optimization bugs. More specifically, in the issue tracker, we search for resolved and valid issues whose entire report contains optimization-related keywords, such as `optimize`, `planner`, `slow`, `fast`, `latency`, `throughput`, and `performance`. In total, we identify 763 issues that match our search criteria. For these matched issues, we then manually check them to make sure that they are query optimization
bugs according to our query optimization bug definition, and accordingly get 198 query optimization bugs. Finally, after manually inspecting
and analyzing the issue message, source code, commit message, and bug patch for each of the 198 bugs, we select 112 query optimization bugs that 
have clear information for us to understand at least the root causes. For the slected 112 issues, two authors separately performed the inspection and discussed disagreements, aiming to reach a consensus. Overall, 10 novel and important findings on the cause of query optimization bugs, the manifestation of query optimization bugs, and the fix of query optimization bugs are obtained at last. 

## Tool for Exposing Optimization Bugs

To demonstrate the value of our characteristic study, we develop a testing tool based on our finding about the manifestation of query optimization bug. 
The tool is implemented on top of [GDSmith](https://github.com/ddaa2000/GDsmith), and uses our findings to guide the generation of tests that can effecitively 
expose query optimization bugs. Our re-implementation consists of around 10K new non-comment lines of Java code, and the [src](./src) folder contains the source code. 
The packaged code is given in tool.jar.

## Installation

- Java 21
- Maven
- Docker (to run database instances conveniently)

------

## Running yu

### Example: Compare two versions of Neo4j

First, start two Neo4j instances:

```
docker run -d --name neo4j-5.26.1 -e NEO4J_AUTH=neo4j/password -e NEO4J_dbms_transaction_timeout="180s" -p 7474:7474 -p 7687:7687 neo4j:5.26.1
docker run -d --name neo4j-5.26.0 -e NEO4J_AUTH=neo4j/password -e NEO4J_dbms_transaction_timeout="180s" -p 7475:7474 -p 7688:7687 neo4j:5.26.0
```

Then create a `config.json` file:

```
{
  "neo4j@5.26.1": {
    "port": 7687,
    "host": "localhost",
    "username": "neo4j",
    "password": "password"
  },
  "neo4j@5.26.0": {
    "port": 7688,
    "host": "localhost",
    "username": "neo4j",
    "password": "password"
  }
}
```

Now run yu :

```
java -jar target\yu.jar --num-tries 500 --num-queries 1 
```

- yu will generate **500 graphs**,
- for each graph it will generate **1 queries**,
- queries will be executed across all configured databases,
- results — including **query outputs and performance** — will be compared to identify inconsistencies.
- crashes from any version will also be recorded in the `logs/` directory.

Notice that **yu** does not automatically create database users. You may need to manually create a user and grant the necessary privileges for remote connection, query execution, writing to databases, and creating or deleting databases.

------

### Configuration

- `--num-tries <N>` → number of graphs to generate
- `--num-queries <M>` → queries per graph
- connection details are defined in `config.json`.

The config file uses keys of the form `<engine>@<label>` where:

- `<engine>` is one of `neo4j`, `redisgraph`, or `memgraph`,
- `<label>` can be a version number or a custom tag.

------

## Supported Databases

- **neo4j**
- **memgraph**



## 🐛 Evaluation of the Tool

We evaluate the tool using the latest available release versions of Neo4j and Memgraph, the two most popular GDBMSs that use Cypher as the query language. During our testing
period, if a new version of the two tested GDBMSs is released, we set up the tool to test the updated versions. More specifically, we test Neo4j starting from version 5.26.3 and
Memgraph starting from version 3.1.0. The testing process lasts three months, which is a typical time period to evaluate the defectiveness of database testing methods.

Overall, the tool found 20 unique bugs, including 12 in Neo4j and 8 in Memgraph. Among them, 17 bugs were confirmed and 14 fixed. In particular, for the 17 confirmed bugs, 15 were 
confirmed to be query optimization bugs. These results demonstrate that the tool is practical and effective in detecting query optimization bugs in GDBMSs. When reporting the detected issues, 
we used our real-name accounts to facilate the communication. To keep the double-blind rules, we have removed the original links for issue reports here. The overall bug information can be found 
in `./found-bugs/overall.csv`.


Since all issues were submitted using real-name accounts, we are temporarily unable to share them during the review period. We will update them here afterward


*To keep the double-blind rules, we have removed the original links for bug reports in the artifacts submission.*

1. **GSlicer** detected 39 unique and previously unknown bugs, of which 34 have been fixed and confirmed by developers. The overall bug information can be found in `./found-bugs/overall.csv`.

2. Triggering test cases for logic bugs can be found in `./found-bugs/cases`.

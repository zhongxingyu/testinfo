
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

The "Issues" folder contains the 112 issues used for conducting the characterstic study. These issues were selected from four popular GDBMSs,
including Neo4j, Memgraph, RedisGraph, and Kuzu. We first search for the issue trackers of the four GDBMSs in to identify query 
optimization bugs. More specifically, in the issue tracker, we search for resolved and valid issues whose entire report contains optimization-related keywords, 
such as “optimize”, “planner”, “slow”, “fast”, “latency”, “throughput”, and “performance”. In total, we identify 763 issues that match
our search criteria. For these matched issues, we then manually check them to make sure that they are query optimization
bugs according to our query optimization bug definition, and accordingly get 198 query optimization bugs. Finally, after manually inspecting
and analyzing the issue message, source code, commit message, and bug patch for each of the 198 bugs, we select 112 query optimization bugs that 
have clear information for us to understand at least the root causes. For the slected 112 issues, two authors separately performed the inspection and
discussed disagreements, aiming to reach a consensus. Overall, 10 novel and important findings on the cause of query optimization bugs, the manifestation
of query optimization bugs, and the fix of query optimization bugs are obtained at last. 

## Tool for Exposing Optimization Bugs

To demonstrate the value of our characteristic study, we develop a testing tool based on our finding about the manifestation of query optimization bug. 
The tool is implemented on top of **GDSmith** (https://github.com/ddaa2000/GDsmith), and uses our findings to guide the generation of tests that can effecitively 
expose query optimization bugs. Our re-implementation consists of around 10K new non-comment lines of Java code,

# GSlicer

An automated testing tool for graph-processing systems via Graph-cutting. The codebase for the paper ***"Finding Logic Bugs in Graph-processing Systems via Graph-cutting"***

## 📰 Project Update

We plan to actively maintain GSlicer and extend support for more algorithms in NetworkX (see `/graphs/networks/algs`). We warmly welcome contributions—feel free to open a pull request if you’d like to be part of the project!


## 🚀 Quick Start

### 1. Environment Requirements

The code has been tested on a Linux (Ubuntu 22.04 LTS) workstation with Python 3.10. To set up the environment, follow these steps:

```bash
docker compose up
pip install neo4j
pip install networkx
pip install kuzu
pip install numpy
pip install pandas
```

### 2. Testing Graph-Processing Systems

- **Neo4j GDBMS**: To test Neo4j, simply run:
  ```bash
  python ./databases/neo4j/test.py
  ```

- **Neo4j-GDS (Graph Data Science Library)**: To test specific algorithms, navigate to the corresponding directory. For example, to test triangle counting:
  ```bash
  cd ./graphs/neo4j/algorithms/triangle_counting
  python triangle_counting.py
  ```

- **NetworkX**: To test using NetworkX, run:
  ```bash
  python ./graphs/networkx/entrance.py
  ```
  Note that for NetworkX, we do not provide instances for applying graph-cutting oracles other than those identified by Algorithm 1. Users may implement them and add to `./graphs/networkx/output.json` file before running the above command.
  You can get the basic `./graphs/networkx/output.json` file by reproducing the task coverage results (see below).

- **Kuzu**: To test using Kuzu, run:
  ```bash
  python ./graphs/kuzu/launcher.py
  ```

### Reproducing Task Coverage Results

To reproduce the task coverage results, run:
```bash
python ./graphs/networkx/sample.py
```

### Reproducing Code Coverage Results

To reproduce the code coverage results:
1. Manually compile **Kuzu v0.4.2** and install the **LCOV** tool.
   - See [Kuzu Developer Guide](https://docs.kuzudb.com/developer-guide/) and [LCOV Documentation](https://lcov.readthedocs.io/en/latest/).
   
2. Once Kuzu and LCOV are set up, run:
   ```bash
   python ./graphs/kuzu/launcher.py
   ```



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

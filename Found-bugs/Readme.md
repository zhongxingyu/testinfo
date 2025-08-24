Overall, the tool found 20 unique bugs, including 12 in Neo4j and 8 in Memgraph. The following table gives a summary of these bugs. 
In particular,  the column Opt Bugs


| Detected Bug ID | GDBMS| Confirmed?| Opt Bugs?| Status| Cypher Query Feature | 
|------|------|------|------|------|------|
| 1 | Neo4j| YES| YES| Fixed| Uses nested subquery clauses (nested CALL with CALL) and has the Single Clause Multiple Variables Interaction feature| 
| 2 | Neo4j| YES| YES| Fixed| Uses nested subquery clauses (nested COLLECT with CALL) and has the Single Clause Multiple Variables Interaction feature| 
| 3 | Neo4j| YES| YES| Fixed| Uses nested subquery clauses (nested CALL with EXISTS with COUNT) and has the Single Clause Multiple Variables Interaction feature| 
| 4| Neo4j| YES| YES| Fixed| Uses nested subquery clauses (nested COUNT with CALL)| 
| 5| Neo4j| YES| YES| Fixed| Uses nested subquery clauses (nested COUNT with EXISTS)| 
| 6| Neo4j| YES| YES| Fixed| Uses nested subquery clauses (nested CALL with CALL)| 
| 7| Neo4j| YES| YES| Fixed| Uses nested subquery clauses (nested COLLECT with EXISTS)| 
| 8| Neo4j| YES| YES| Fixed| Has the Single Clause Multiple Variables Interaction feature| 
| 9| Neo4j| YES| YES| NO| Has the Single Clause Multiple Variables Interaction feature| 
| 10| Neo4j| YES| NO| NO| None| 
| 11| Neo4j| NO| Unknown| NO| Uses nested subquery clauses (nested CALL with EXISTS)| 
| 12| Neo4j| NO| Unknown| NO| None| 
| 13| Memgraph| YES| YES| Fixed| Uses nested subquery clauses (nested CALL with COUNT) and has the Single Clause Multiple Variables Interaction feature| 
| 14| Memgraph| YES| YES| Fixed| Uses nested subquery clauses (nested CALL with COLLECT) and has the Single Clause Multiple Variables Interaction feature| 
| 15| Memgraph| YES| YES| Fixed| Uses nested subquery clauses (nested CALL with CALL)| 
| 16| Memgraph| YES| YES| Fixed| Uses nested subquery clauses (nested CALL with EXISTS)| 
| 17| Memgraph| YES| YES| Fixed| Has the Single Clause Multiple Variables Interaction feature| 
| 18| Memgraph| YES| YES| Fixed| None| 
| 19| Memgraph| YES| NO| NO| None | 
| 20| Memgraph| NO| Unknown| NO| Has the Single Clause Multiple Variables Interaction feature| 

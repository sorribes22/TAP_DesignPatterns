# Design patterns
This project aims to be a sandbox to apply concepts like **Design patterns**, **Code smells**, **Streams** and **testing**.
By axsor and Roger.

## Problem statement
The problem statement can be read at [exercice.pdf](docs/exercice.pdf).
The goal is to build a DataFrame library using design patterns.
We chose columnar design to store the data of readen DataFrame because its the more appropiate to resolve the problem statement.

## Design patterns
### Factory
### Composite
### Visitor
![](docs/img/visitor.png)

The Visitor design pattern allow us to implement new behaviors to a class, without adding them in the own class.
And we add Visitor to the previous Composite structure, and with it, we've implemented 4 new operations:
- Maxium
- Minium
- Average
- Summatory

We have to implement "accept" method in DataFrame class, and create the Visitor Abstract class (DataFrameVisitor) with the "visit" method, then we have 
4 classes that extends this abstract class. Each of them add a new operation. As we have directories and sons we have 2 visit methods, one for the directory (go through the sons) and one for the dataFrame.
### Observer + Dynamic proxy
![](docs/img/observer_dynamic-proxy.png)

## Code smells

## Streams + MapReduce

## Testing

## Extras
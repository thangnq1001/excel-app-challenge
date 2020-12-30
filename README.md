# excel-app-challenge
A solution for an excel app coding challenge
## Requirement
Implement a simple excel application:
### Input
input will be given via stdin
- The first line will contain a number N indicating the number of cells
- The rest 2*N lines will have the following structure:
  - The first line contains the cell name (e.g A1)
  - The second line contains the cell content. The content of each cell can be anumber (double), or a mathematic formula (consists of +-*/) and the formula can also refers to other cells. the formula is written in polish postfix notation
### Output
The final values of each cells, sorted alphabetically by the cell names, to stdout

### Important:
- The solution will be evaluated automatically, so the output must strictly adhere to
the above format
- The application must report for any circular dependencies

### Example 1:
Input:

3

A1

5

A2

A1 5 * B1 +

B1

6

Output:

A1

5

A2

31

B1

6

### Example 2
Input:

A1

A2 2 *

A2

A1 5 +

Output:
Circular dependency between A1 and A2 detected

# ReachableLand

## Task

Given a $n \times n$ grid with values 1 (land) or 0 (water), which land tiles are reachable from the borders of the grid.
Tiles are connected by Von Neumann neighborhood (up, down, left, right, not diagonal).

## Solution
  - in main: size -> n, rounds -> number of grids generated
  - uses BFS-search starting with every land border tile in queue
  - prints given grid and grid resulting from search
    - green/"1": land
    - colorless/"0": water
    - red number: was reached in search
  - prints searched tiles and percent of all tiles searched
  - when generating multiple grid prints stats for all grid combined

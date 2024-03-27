import java.util.*;

public class IslandsMain {
    int size;
    boolean[] marked;

    public IslandsMain(int[][] map) {
        size = map.length;
        marked = new boolean[size * size];
    }

    public int[][] Mapping(int[][] map) {

        LinkedList<Integer> inseln = new LinkedList<>();
        Queue<Integer> q = new LinkedList<>();
        int[][] newMap = new int[size][size];

        //the borders of the board are considered reachable from the start
        //adds border tiles with value 1 to searchqueue, marks value 0 as visited
        for (int i = 0; i < size; i++) {
            if (map[i][0] == 1) {
                q.add(Coordinates(i, 0));
            } else {
                marked[Coordinates(i, 0)] = true;
            }

            if (map[i][size - 1] == 1) {
                q.add(Coordinates(i, size - 1));
            } else {
                marked[Coordinates(i, size - 1)] = true;
            }
        }
        for (int i = 1; i < size - 1; i++) {
            if (map[0][i] == 1) {
                q.add(Coordinates(0, i));
            } else {
                marked[Coordinates(0, i)] = true;
            }

            if (map[size - 1][i] == 1) {
                q.add(Coordinates(size - 1, i));
            } else {
                marked[Coordinates(size - 1, i)] = true;
            }
        }

        while (!q.isEmpty()) {
            int current = q.remove();
            if (!marked[current]) {
                marked[current] = true;
                inseln.add(current);

                //check left
                if (!(current % size == 0)) {
                    if (!marked[current - 1]) {
                        if (map[current / size][(current % size) - 1] == 1) {
                            q.add(current - 1);
                        } else {
                            marked[current - 1] = true;
                        }
                    }
                }
                //check right
                if (!(current % size == size - 1)) {
                    if (!marked[current + 1])
                        if (map[current / size][(current % size) + 1] == 1) {
                            q.add(current + 1);
                        } else {
                            marked[current + 1] = true;
                        }
                }

                //check up
                if (!(current / size == 0)) {
                    if (!marked[current - size])
                        if (map[(current / size) - 1][(current % size)] == 1) {
                            q.add(current - size);
                        } else {
                            marked[current - size] = true;
                        }
                }

                //check down
                if (!(current / size == size - 1)) {
                    if (!marked[current + size])
                        if (map[(current / size) + 1][(current % size)] == 1) {
                            q.add(current + size);
                        } else {
                            marked[current + size] = true;
                        }
                }
            }
        }

        for (int i : inseln) {
            newMap[i / size][i % size] = 1;
        }
        //System.out.println("Size:" + size);
        return newMap;
    }

    public int Coordinates(int x, int y) {
        return x * size + y;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RED = "\u001B[31m";
    public static void main(String[] args) {
        int size = 24;
        int rounds = 1;
        int endTiles = 0;
        int endCounter = 0;
        for (int k = 0; k < rounds; k++) {

            int[][] test1 = new int[size][size];
            Random r = new Random();


            for (int[] row : test1) {
                for (int j = 0; j < row.length; j++) {
                    row[j] = r.nextBoolean() ? 1 : 0;
                }
            }
               

            System.out.println("Vorher:");
            for (int i= 0; i < test1.length; i++){
                for (int j = 0; j < test1.length; j++){
                    if(test1[i][j] == 0) {
                        System.out.print(test1[i][j] + " ");
                        //System.out.print("  ");
                    }
                    else{
                        System.out.print(ANSI_GREEN_BACKGROUND + test1[i][j] +  " " + ANSI_RESET );
                        //System.out.print(ANSI_GREEN_BACKGROUND +  "  " + ANSI_RESET );
                    }
                }
                System.out.println(ANSI_RESET);
            }

            IslandsMain p = new IslandsMain(test1);
            int[][] test2 = p.Mapping(test1);

            System.out.println("Nachher:");
            //System.out.println(test2.length);
            for (int i= 0; i < test2.length; i++){
                for (int j = 0; j < test2.length; j++){
                    if(test2[i][j] == 0 && !p.marked[i * size + j]) {
                        System.out.print(test2[i][j] + " ");
                        //System.out.print("  ");
                    }
                    else if(test2[i][j] == 0 && p.marked[i * size + j]){
                        System.out.print(ANSI_RED + test2[i][j] + " " + ANSI_RESET);
                        //System.out.print(ANSI_RED + "  " + ANSI_RESET);
                    }

                    else{
                        System.out.print(ANSI_GREEN_BACKGROUND+ ANSI_RED + test2[i][j] +  " " + ANSI_RESET );
                        //System.out.print(ANSI_GREEN_BACKGROUND+ ANSI_RED +  "  " + ANSI_RESET );
                    }
                }
                System.out.println(ANSI_RESET);
            }
            int counter = 0;
            for (int i = 0; i < p.marked.length; i++) {
                if (p.marked[i] == true) {
                    counter++;
                }
            }
            System.out.println("Gesamte Felder: " +size*size);
            System.out.println("Überprüfte Felder: " + counter);
            float anteil = (float)counter*100/(size*size);
            String ant = String.format("%.02f", anteil);
            System.out.println("Anteil überprüfte Felder: " + ant+"%");
            System.out.println();

            endTiles = endTiles + size*size;
            endCounter = endCounter + counter;
        }

        if(rounds > 1){
            System.out.println("Gesamtauswertung:");
            System.out.println();
            System.out.println("Gesamte Felder: " + endTiles);
            System.out.println("Überprüfte Felder: " + endCounter);
            float endanteil = (float)endCounter*100/(endTiles);
            String ant2 = String.format("%.02f", endanteil);
            System.out.println("Anteil überprüfte Felder: " + ant2+"%");
        }
        

    }
}
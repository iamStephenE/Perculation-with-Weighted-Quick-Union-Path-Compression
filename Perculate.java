public class Perculate {
    private int[][] grid;

    // true if open, false if blocked
    private boolean cells[][];

    // i use this variable to reduce using nested foor loops
    private int openCells;

    // class for the union find algorithm
    private WQUPC uf;
    private int topNode, botNode;

    public Perculate(int N) {
        this.grid = new int[N][N];
        this.cells = new boolean[N][N];
        this.openCells = 0;
        this.uf = new WQUPC(N);
        this.topNode = N * N;
        this.botNode = N * N + 1;

        int temp = 0;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                this.grid[r][c] = temp;
                temp += 1;
            }
        }
    }

    public void openRandomSite() {
        int r = (int)(Math.random() * this.grid.length);
        int c = (int)(Math.random() * this.grid.length);
        if(!this.cells[r][c]) {
            this.cells[r][c] = true;
            this.openCells++;
            this.connect(r, c);
        }

    }

    public boolean perculates() {
        return this.uf.isConnected(this.topNode, this.botNode);
    }

    public void connect(int r, int c) {
        // if its on the top row, connect to the top node
        // if its on the bot row, connect to the bottom node
        if(r == 0) {
            this.uf.union(this.grid[r][c], this.topNode);
        } else if (r == this.grid.length - 1) {
            this.uf.union(this.grid[r][c], this.botNode);
        }

        // check if the node can be connected to anything it is
        // adjacent to
        int[] adjacents = this.getAdjacents(r, c);
        if(adjacents[0] != -1) {
            this.uf.union(adjacents[0], this.grid[r][c]);
        }
        if(adjacents[1] != -1) {
            this.uf.union(adjacents[1], this.grid[r][c]);
        }
        if(adjacents[2] != -1) {
            this.uf.union(adjacents[2], this.grid[r][c]);
        }
        if(adjacents[3] != -1) {
            this.uf.union(adjacents[3], this.grid[r][c]);
        }

    }

    public int[] getAdjacents(int r, int c) {
        // {up, down, left, right}
        // -1 represents that it doesn't exist
        int[] result = new int[] {-1, -1, -1, -1};

        // up
        if (r > 0 && this.cells[r - 1][c])
            result[0] = this.grid[r - 1][c];
        // down
        if (r < this.grid.length - 1 && this.cells[r + 1][c])
            result[1] = this.grid[r + 1][c];
        // left
        if (c > 0 && this.cells[r][c - 1])
            result[2] = this.grid[r][c - 1];
        // right
        if (c < this.grid.length - 1 && this.cells[r][c + 1])
            result[3] = this.grid[r][c + 1];

        return result;
    }

    public double siteVacancyPercentage() {
        int total = this.cells.length * this.cells.length;
        System.out.println(this.openCells + " / " + total);
        return (double)(this.openCells) / total;
    }

    public void showGrid() {
        for(int r = 0; r < this.cells.length; r++) {
            for(int c = 0; c < this.cells.length; c++) {
                if(this.cells[r][c]) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[X]");
                }
            }
            System.out.println();
        }
    }

}

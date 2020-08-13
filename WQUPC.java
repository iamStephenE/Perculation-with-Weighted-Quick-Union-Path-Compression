public class WQUPC {

    private int parent[];
    private int size[];

    public WQUPC(int N) {
        // Add 2 to use a top now and bottom node to easily find if it perculates
        this.parent = new int[N * N + 2];
        this.size = new int[N * N + 2];
        for(int i = 0; i < this.parent.length; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    // trace back to the root or very first parent
    public int root(int p) {
        if(p != this.parent[p])
            this.parent[p] = this.root(this.parent[p]);
        return this.parent[p];

        // OR

        /*while(p != this.parent[p]) {
            this.parent[p] = this.parent[this.parent[p]];
            p = this.parent[p];
        }
        return p;*/
    }

    // check if 2 nodes are connected, in the same set, not disjoint
    public boolean isConnected(int p, int q) {
        return this.root(p) == this.root(q);
    }

    // add two trees by weight
    public void union(int p, int q) {
        int rootP = this.root(p);
        int rootQ = this.root(q);

        // finds the one with more weight and makes the smaller one point to it
        if(this.size[rootQ] < this.size[rootP]) {
            this.parent[rootQ] = rootP;
            this.size[rootP] += this.size[rootQ];
        } else {
            this.parent[rootP] = rootQ;
            this.size[rootQ] += this.size[rootP];
        }
    }

}

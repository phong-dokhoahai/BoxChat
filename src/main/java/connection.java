import java.util.*;

public class connection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();


        List<int[]> connections = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            connections.add(new int[]{u, v});
        }

        int result = findMinCables(N, connections);
        System.out.println(result);
    }

    public static int findMinCables(int N, List<int[]> connections) {
        UnionFind uf = new UnionFind(N);


        for (int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];
            uf.union(u, v);
        }


        int groupCount = uf.countGroups();

        int minCables = groupCount - 1;

        return minCables;
    }


    static class UnionFind {
        private int[] parent;
        private int[] size;
        private int groupCount;

        public UnionFind(int size) {
            parent = new int[size];
            this.size = new int[size];
            groupCount = size;
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                this.size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (size[rootX] < size[rootY]) {
                    parent[rootX] = rootY;
                    size[rootY] += size[rootX];
                } else {
                    parent[rootY] = rootX;
                    size[rootX] += size[rootY];
                }
                groupCount--;
            }
        }

        public int countGroups() {
            return groupCount;
        }
    }
}
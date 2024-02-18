import java.util.*;

public class NeuralNetwork {
    private List<List<Double>> weights;
    private int layerCount;

    public NeuralNetwork(int layerCount, List<Integer> nodesPerLayer) {
        this.layerCount = layerCount;
        this.weights = new ArrayList<>();

        for (int i = 0; i < layerCount - 1; i++) {
            List<Double> layerWeights = new ArrayList<>();
            for (int j = 0; j < nodesPerLayer.get(i); j++) {
                for (int k = 0; k < nodesPerLayer.get(i + 1); k++) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.printf("Enter the weight of edge between node %d in layer %d and node %d in layer %d: ", j + 1, i + 1, k + 1, i + 2);
                    double weight = scanner.nextDouble();
                    layerWeights.add(weight);
                }
            }
            weights.add(layerWeights);
        }
    }

    public double getWeight(int fromNode, int fromLayer, int toNode, int toLayer) {
        return weights.get(fromLayer - 1).get((fromNode - 1) * getNodesInLayer(fromLayer) + toNode - 1);
    }

    private int getNodesInLayer(int layer) {
        return weights.get(layer - 1).size() / (layer == 1 ? 1 : weights.get(layer - 2).size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of layers:");
        int layerCount = scanner.nextInt();

        List<Integer> nodesPerLayer = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            System.out.printf("Enter the number of nodes in layer %d: ", i + 1);
            int numNodes = scanner.nextInt();
            nodesPerLayer.add(numNodes);
        }

        NeuralNetwork neuralNetwork = new NeuralNetwork(layerCount, nodesPerLayer);

        System.out.println("Enter the nodes and layer to query the weight of an edge (e.g., 1 1 2 2):");
        int fromNode = scanner.nextInt();
        int fromLayer = scanner.nextInt();
        int toNode = scanner.nextInt();
        int toLayer = scanner.nextInt();

        double weight = neuralNetwork.getWeight(fromNode, fromLayer, toNode, toLayer);
        System.out.printf("The weight of the edge between node %d in layer %d and node %d in layer %d is %.2f\n", fromNode, fromLayer, toNode, toLayer, weight);
    }
}
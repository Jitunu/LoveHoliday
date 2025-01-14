package com.loveholiday;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    private static final Map<String, Integer> LOCATIONS = new HashMap<>() {{
        put("Castle Black", 0);
        put("Winterfell", 1);
        put("Riverrun", 2);
        put("King's Landing", 3);
    }};

    private static final int[][] COSTS = {
            {0, 15, 80, 90},
            {0, 0, 40, 50},
            {0, 0, 0, 70},
            {0, 0, 0, 0}
    };
    static Map<String, Integer> map = new HashMap<>();
    public static List<Object> findPaths(int source, int dest, int cost, List<String> paths) {

        List<Object> list = new ArrayList<>();
        paths.add(getLocation(source));
        if(source == dest) {
            String pathString = String.join("->", paths);
            System.out.println(pathString+" : "+ cost);
            list.add(pathString);
            list.add(cost);
            return list;
        }
        for (int index = source+1; index <= dest; index++) {
            if(COSTS[source][index] != 0) {

                List tempList = findPaths(index, dest, cost + COSTS[source][index], new ArrayList<>(paths));
                map.put((String) tempList.get(0), (Integer)tempList.get(1));
                list.add(tempList.get(0));
                list.add(tempList.get(1));
            }
        }
        return list;
    }

    private static String getLocation(int i) {
        return LOCATIONS.entrySet().stream().filter( entry -> entry.getValue() == i)
                .map( j -> j.getKey())
                .findFirst()
                .orElseGet(null);
    }

    public List<String> findPathUsingQueue(int source, int destination) {
//        Queue<Node> queue = new ArrayDeque<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        List<String> finalPath = new ArrayList<>();
        Node node = new Node(0, source, new ArrayList<>());
        pq.offer(node);
        
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            current.paths.add(getLocation(current.index));
            System.out.println(current);
            if(current.index == destination) {
                finalPath.add( current.paths+" : "+current.cost);
                break;
            }
            for (int i = current.index + 1; i < COSTS.length; i++) {
                List<String> newPath = new ArrayList<>(current.paths);
                pq.offer(new Node(current.cost + COSTS[current.index] [i], i, newPath));
                pq.offer(new Node(current.cost + COSTS[current.index] [i], i, newPath));

            }

        }
        return finalPath;
    }
    class Node {
        int cost;
        int index;
        List<String> paths;

        Node(int cost, int index, List<String> path) {
            this.cost = cost;
            this.index = index;
            this.paths = path;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "cost=" + cost +
                    ", index=" + index +
                    ", paths=" + paths +
                    '}';
        }
    }
    public static void main(String[] args) {

        String sourceLocation = "Castle Black";
        String destinationLocation = "King's Landing";
        List<String> paths = new ArrayList<>();
        int source = LOCATIONS.get(sourceLocation);
        int dest = LOCATIONS.get(destinationLocation);
        System.out.println(new Test().findPathUsingQueue(source, dest));
//        findPaths(source, dest, 0, paths);
//        String finalResult = map.entrySet().stream()
//                .min((k,v) -> k.getValue().compareTo(v.getValue()))
//                .map(Map.Entry::getKey).get();
//        System.out.println(map);
//        System.out.println(finalResult);

    }
}

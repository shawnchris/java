package cc189;

import java.util.*;

class BFSData {
	public Queue<PathNode> toVisit = new LinkedList<PathNode>();
	public HashMap<Integer, PathNode> visited = new HashMap<Integer, PathNode>();

	public BFSData(Person root) {
		PathNode sourcePath = new PathNode(root, null);
		toVisit.add(sourcePath);
		visited.put(root.getID(), sourcePath);	
	}
	
	public boolean isFinished() {
		return toVisit.isEmpty();
	}
}

class PathNode {
	private Person person = null;
	private PathNode previousNode = null;
	public PathNode(Person p, PathNode previous) {
		person = p;
		previousNode = previous;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public LinkedList<Person> collapse(boolean startsWithRoot) {
		LinkedList<Person> path = new LinkedList<Person>();
		PathNode node = this;
		while (node != null) {
			if (startsWithRoot) {
				path.addLast(node.person);
			} else {
				path.addFirst(node.person);
			}
			node = node.previousNode;
		}
		return path;
	}
}

class Person {
	private ArrayList<Integer> friends = new ArrayList<Integer>();
	private int personID;
	private String info;
	
	public String getInfo() { return info; }
	public void setInfo(String info) {
		this.info = info;
	}

	public ArrayList<Integer> getFriends() {
		return friends;
	}
	
	public int getID() { return personID; }
	public void addFriend(int id) { friends.add(id); }
	
	public Person(int id) {
		this.personID = id;
	}
}


public class C09Q02_Social_Network {
	class QuestionA {
		public LinkedList<Person> findPathBFS(HashMap<Integer, Person> people, int source, int destination) {
			Queue<PathNode> toVisit = new LinkedList<PathNode>();
			HashSet<Integer> visited = new HashSet<Integer>();
			toVisit.add(new PathNode(people.get(source), null));
			visited.add(source);
			while (!toVisit.isEmpty()) {
				PathNode node = toVisit.poll();
				Person person = node.getPerson();
				if (person.getID() == destination) {
					return node.collapse(false);
				}
				
				/* Search friends. */
				ArrayList<Integer> friends = person.getFriends();
				for (int friendId : friends) {
					if (!visited.contains(friendId)) {
						visited.add(friendId);
						Person friend = people.get(friendId);
						toVisit.add(new PathNode(friend, node));
					}
				}
			}
			return null;
		}
	}
	
	class QuestionB {

		public LinkedList<Person> mergePaths(BFSData bfs1, BFSData bfs2, int connection) {
			PathNode end1 = bfs1.visited.get(connection); // end1 -> source
			PathNode end2 = bfs2.visited.get(connection); // end2 -> dest
			LinkedList<Person> pathOne = end1.collapse(false); // forward: source -> connection
			LinkedList<Person> pathTwo = end2.collapse(true); // reverse: connection -> dest
			pathTwo.removeFirst(); // remove connection
			pathOne.addAll(pathTwo); // add second path
			return pathOne;
		}
		
		/* Search one level and return collision, if any. */
		public Person searchLevel(HashMap<Integer, Person> people, BFSData primary, BFSData secondary) {
			/* We only want to search one level at a time. Count how many nodes are currently in the primary's
			 * level and only do that many nodes. We'll continue to add nodes to the end. */
			int count = primary.toVisit.size(); 
			for (int i = 0; i < count; i++) {
				/* Pull out first node. */
				PathNode pathNode = primary.toVisit.poll();
				int personId = pathNode.getPerson().getID();
				
				/* Check if it's already been visited. */
				if (secondary.visited.containsKey(personId)) {
					return pathNode.getPerson();
				}				
				
				/* Add friends to queue. */
				Person person = pathNode.getPerson();
				ArrayList<Integer> friends = person.getFriends();
				for (int friendId : friends) {
					if (!primary.visited.containsKey(friendId)) {
						Person friend = people.get(friendId);
						PathNode next = new PathNode(friend, pathNode);
						primary.visited.put(friendId, next);
						primary.toVisit.add(next);
					}
				}
			}
			return null;
		}
		
		public LinkedList<Person> findPathBiBFS(HashMap<Integer, Person> people, int source, int destination) {
			BFSData sourceData = new BFSData(people.get(source));
			BFSData destData = new BFSData(people.get(destination));
			
			while (!sourceData.isFinished() && !destData.isFinished()) {
				/* Search out from source. */
				Person collision = searchLevel(people, sourceData, destData);
				if (collision != null) {
					return mergePaths(sourceData, destData, collision.getID());
				}
				
				/* Search out from destination. */
				collision = searchLevel(people, destData, sourceData);
				if (collision != null) {
					return mergePaths(sourceData, destData, collision.getID());
				}
			}
			return null;
		}
	}
	
	public static void printPeople(LinkedList<Person> path) {
		if (path == null) {
			System.out.println("No path");
		} else {
			for (Person p : path) {
				System.out.println(p.getID());
			}
		}		
	}
	
	public static boolean isEqual(LinkedList<Person> path1, LinkedList<Person> path2, boolean reverse) {
		if (path1 == null || path2 == null) {
			return path1 == null && path2 == null;
		}
		if (path1.size() != path2.size()) {
			return false;
		}
		
		for (int i = 0; i < path1.size(); i++) {
			int other = reverse ? path2.size() - i - 1 : i;
			if (path1.get(i) != path2.get(other)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isEquivalent(LinkedList<Person> path1, LinkedList<Person> path2) {
		boolean f1 = isEqual(path1, path2, false);
		boolean f2 = isEqual(path1, path2, true);
		return f1 || f2;
	}	
	
	public static void main(String[] args) {
		int nPeople = 11;
		HashMap<Integer, Person> people = new HashMap<Integer, Person>();
		for (int i = 0; i < nPeople; i++) {
			Person p = new Person(i);
			people.put(i, p);
		}
		
		int[][] edges = {{1, 4}, {1, 2}, {1, 3}, {3, 2}, {4, 6}, {3, 7}, {6, 9}, {9, 10}, {5, 10}, {2, 5}, {3, 7}};
		//int[][] edges = {{1, 4}, {1, 2}, {4, 6}, {6, 9}, {9, 10}, {5, 10}, {2, 5}};
		//int[][] edges = {{1, 2}, {1, 4}, {2, 3}, {3, 4}, {4, 6}, {5, 6}, {4, 5}}; 
		for (int[] edge : edges) {
			Person source = people.get(edge[0]);
			source.addFriend(edge[1]);
			
			Person destination = people.get(edge[1]);
			destination.addFriend(edge[0]);
		}
		
		/*int i = 1;
		int j = 10;
		LinkedList<Person> path1 = findPathBFS(people, i, j);
		LinkedList<Person> path2 = findPathBiBFS(people, i, j);
		System.out.println("Path 1");
		printPeople(path1);
		System.out.println("Path 2");
		printPeople(path2);*/
		
		C09Q02_Social_Network c09q02 = new C09Q02_Social_Network();
		for (int i = 0; i < nPeople; i++) {
			for (int j = 0; j < nPeople; j++) {
				LinkedList<Person> path1 = (c09q02.new QuestionA()).findPathBFS(people, i, j);
				LinkedList<Person> path2 = (c09q02.new QuestionB()).findPathBiBFS(people, i, j);
				if (!isEquivalent(path1, path2)) {
					System.out.println("Not equal: " + i + " to " + j);
					System.out.println("Path 1");
					printPeople(path1);
					System.out.println("Path 2");
					printPeople(path2);
					break;
				} else {
					System.out.println("Is equal: " + i + " to " + j);
				}
			}
		}
	}
}

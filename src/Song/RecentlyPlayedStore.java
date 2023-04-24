package Song;

import java.util.HashMap;
import java.util.Map;

public class RecentlyPlayedStore {
	 int capacity;
	    Map<String, Integer> counts;
	    Node head;
	    Node tail;
	    Map<String, Node> users;

	    public RecentlyPlayedStore(int capacity) {
	        this.capacity = capacity;
	        this.counts = new HashMap<>();
	        this.head = null;
	        this.tail = null;
	        this.users = new HashMap<>();
	    }

	    public void addSong(String song, String user) {
	        if (users.containsKey(user)) {
	            Node node = users.get(user);
	            node.song = song;
	            moveToHead(node);
	        } else {
	            Node node = new Node(song, user);
	            users.put(user, node);
	            addToHead(node);
	        }
	        adjustCounts(user);
	    }

	    private void moveToHead(Node node) {
	        if (node == head) {
	            return;
	        }
	        if (node == tail) {
	            tail = node.prev;
	        } else {
	            node.next.prev = node.prev;
	        }
	        node.prev.next = node.next;
	        addToHead(node);
	    }

	    private void addToHead(Node node) {
	        if (head == null) {
	            head = node;
	            tail = node;
	        } else {
	            node.next = head;
	            head.prev = node;
	            head = node;
	        }
	    }

	    private void adjustCounts(String user) {
	        int count = counts.getOrDefault(user, 0);
	        if (count == capacity) {
	            removeLast(user);
	        }
	        counts.put(user, count + 1);
	    }

	    private void removeLast(String user) {
	        Node node = tail;
	        if (node.prev == null) {
	            head = null;
	            tail = null;
	        } else {
	            node.prev.next = null;
	            tail = node.prev;
	        }
	        users.remove(node.user);
	        counts.put(user, counts.get(user) - 1);
	    }

	    public String[] getRecentlyPlayed(String user) {
	        if (!users.containsKey(user)) {
	            return new String[0];
	        }
	        Node node = users.get(user);
	        int count = counts.get(user);
	        String[] result = new String[count];
	        for (int i = 0; i < count; i++) {
	            result[i] = node.song;
	            node = node.next;
	        }
	        return result;
	    }

}

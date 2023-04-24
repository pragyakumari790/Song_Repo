package Song;

public class Node {
	String song;
    String user;
    Node prev;
    Node next;

    public Node(String song, String user) {
        this.song = song;
        this.user = user;
        this.prev = null;
        this.next = null;
    }
}

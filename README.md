To implement an in-memory store for recently played songs that can accommodate N songs per user, 
with a fixed initial capacity, we can use a combination of a hashmap and a doubly linked list.

The hashmap will store the user as the key and a pointer to the corresponding node in the doubly linked list as the value. 
The doubly linked list will store the song-user pairs, with each node representing a pair.

When a user plays a song, we will first check if the user is already in the hashmap. 
If the user is not present, we will add a new node to the front of the doubly linked list representing the song-user pair. 
We will also add an entry to the hashmap with the user as the key and a pointer to the new node as the value. 
If the user is present in the hashmap, we will move the corresponding node to the front of the doubly linked list.

To limit the size of the store to N songs per user, we can maintain a count of the number of songs for each user.
Whenever a user plays a new song and the count is already equal to N, we will remove the last node in the doubly linked list and decrement the count. 
We will then add a new node to the front of the list representing the new song-user pair and increment the count.

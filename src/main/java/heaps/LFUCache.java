package heaps;

import java.util.HashMap;

/*
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3
 */
class LFUCache {

    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        int freq=1;
        Node(int k,int v){
            key=k;
            val=v;
        }
    }
    class DoublyLinkedList{
        Node head;
        Node tail;
        DoublyLinkedList(){
            head=new Node(-1,-1);
            tail=new Node(-1,-1);
            head.next=tail;
            tail.prev=head;
        }
        void addNode(Node v){
            Node next=head.next;
            head.next=v;
            v.prev=head;
            v.next=next;
            next.prev=v;
        }
        Node removeNode(){
            Node node=tail.prev;
            node.prev.next=tail;
            tail.prev=node.prev;
            return node;
        }
        Node removeNode(Node v){
            Node prev=v.prev;
            Node next=v.next;
            prev.next=next;
            next.prev=prev;
            return v;
        }
        boolean isEmpty(){
            if(head.next==tail)
                return true;
            return false;
        }
    }


    HashMap<Integer,DoublyLinkedList> freqList=new HashMap<Integer,DoublyLinkedList>();
    HashMap<Integer,Node> lfuCache =new HashMap<Integer,Node>();
    int capacity;
    int minFreq;

    public LFUCache(int capacity) {
        this.capacity=capacity;
        minFreq=1;
    }
    public int get(int key) {
        if(lfuCache.get(key)==null)
            return -1;
        Node v=lfuCache.get(key);
        freqList.get(v.freq).removeNode(v);
        if(freqList.get(v.freq).isEmpty()){
            if(minFreq==v.freq){
                minFreq=v.freq+1;
            }
        }
        v.freq+=1;
        if(freqList.get(v.freq)==null){
            DoublyLinkedList d=new DoublyLinkedList();
            d.addNode(v);
            freqList.put(v.freq,d);
        }
        else{
            freqList.get(v.freq).addNode(v);
        }
        return v.val;
    }

    public void put(int key, int value) {
        if(capacity==0)
            return;
        if(lfuCache.get(key)!=null){
            Node v=lfuCache.get(key);
            freqList.get(v.freq).removeNode(v);
            if(freqList.get(v.freq).isEmpty()){
                if(minFreq==v.freq)
                    minFreq=v.freq+1;
            }
            v.freq+=1;
            if(freqList.get(v.freq)==null){
                DoublyLinkedList d=new DoublyLinkedList();
                d.addNode(v);
                freqList.put(v.freq,d);
            }
            else{
                freqList.get(v.freq).addNode(v);
            }
            v.val=value;
        }
        else{
            if(lfuCache.size()==capacity){
                Node v=freqList.get(minFreq).removeNode();
                lfuCache.remove(v.key);
            }
            Node newNode = new Node(key,value);
            lfuCache.put(key,newNode);
            if(freqList.get(1)!=null){
                freqList.get(1).addNode(newNode);
            }
            else{
                DoublyLinkedList d=new DoublyLinkedList();
                d.addNode(newNode);
                freqList.put(1,d);
            }
            minFreq=1;
        }
    }
}

package leetcode;
import java.util.*;

public class A355_Design_Twitter {
	// Pull mode implementation
    int sequence;
    
    class Tweet implements Comparable<Tweet>{
        int seq, id;
        Tweet next;
        public Tweet(int s, int t) {
            seq = s;
            id = t;
            next = null;
        }
        public int compareTo(Tweet other) {
            return other.seq - this.seq;
        }
    }
    
    class User {
        int id;
        Set<Integer> follows;
        Tweet tweetHead;
        public User(int id) {
            this.id = id;
            follows = new HashSet<Integer>();
            follows.add(id);
            tweetHead = null;
        }
        public void follows(int userId) {
            this.follows.add(userId);
        }
        public void unfollows(int userId) {
            if (userId != id) {
                this.follows.remove(userId);
            }
        }
        public void posts(int tweetId) {
            Tweet t = new Tweet(sequence++, tweetId);
            t.next = tweetHead;
            tweetHead = t;
        }
        public List<Integer> getNewsFeed() {
            List<Integer> result = new ArrayList<>();
            Queue<Tweet> pq = new PriorityQueue<Tweet>();
            for (Integer userId: follows) {
                if (users.get(userId) != null && 
                    users.get(userId).tweetHead != null) {
                    pq.add(users.get(userId).tweetHead);
                }
            }
            for (int i = 0; i < 10; i++) {
                if (pq.isEmpty()) break;
                Tweet t = pq.poll();
                if (t.next != null) {
                    pq.add(t.next);
                }
                result.add(t.id);
            }
            return result;
        }
    }
    
    Map<Integer, User> users;
    
    /** Initialize your data structure here. */
    public A355_Design_Twitter() {
        sequence = 0;
        users = new HashMap<Integer, User>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user = users.get(userId);
        if (user == null) {
            user = new User(userId);
            users.put(userId, user);
        }
        user.posts(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        User user = users.get(userId);
        if (user == null) {
            user = new User(userId);
            users.put(userId, user);
        }
        return user.getNewsFeed();
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        User user = users.get(followerId);
        if (user == null) {
            user = new User(followerId);
            users.put(followerId, user);
        }
        user.follows(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User user = users.get(followerId);
        if (user == null) {
            user = new User(followerId);
            users.put(followerId, user);
        }
        user.unfollows(followeeId);
    }
}

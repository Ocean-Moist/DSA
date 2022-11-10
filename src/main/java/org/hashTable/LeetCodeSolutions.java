package org.hashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCodeSolutions {

  public int[] intersection(int[] x, int[] y) {
    HashSet<Integer> set = new HashSet<>();
    Arrays.stream(x).forEach(set::add);
    HashSet<Integer> intersection = new HashSet<>();
    Arrays.stream(y).filter(set::contains).forEach(intersection::add);

    int[] result = new int[intersection.size()];
    AtomicInteger index = new AtomicInteger();
    intersection.forEach(i -> result[index.getAndIncrement()] = i);
    return result;
  }

  // this is O(n) because .charAt() is O(1) but no hashmap
  //   space is O(1) because we only need to store 26 characters
  public int firstUniqueChar(String s) {
    int[] c = new int[26];
    for (int i = 0; i < s.length(); i++) {
      c[s.charAt(i) - 'a']++;
    }
    for (int i = 0; i < s.length(); i++) {
      if (c[s.charAt(i) - 'a'] == 1) {
        return i;
      }
    }
    return -1;
  }

  // using hashmap, its literally the same thing, but worse (hashmap overhead)
  public int firstUniqueChar2(String S) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < S.length(); i++) {
      char c = S.charAt(i);
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    for (int i = 0; i < S.length(); i++) {
      if (map.get(S.charAt(i)) == 1) {
        return i;
      }
    }
    return -1;
  }

  // another O(n) solution that doesnt use a hashmap (but is functionally equiv)
  public int maxNumberOfBalloons(String s) {
    int[] c = new int[26];
    for (int i = 0; i < s.length(); i++) {
      c[s.charAt(i) - 'a']++;
    }

    int min = c['b' - 'a'];
    min = Math.min(min, c['a' - 'a']);
    min = Math.min(min, c['l' - 'a'] / 2);
    min = Math.min(min, c['o' - 'a'] / 2);
    min = Math.min(min, c['n' - 'a']);
    return min;
  }

  // O(n) solution using a hashmap
  public int maxNumberOfBalloons2(String s) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    int min = map.getOrDefault('b', 0);
    min = Math.min(min, map.getOrDefault('a', 0));
    min = Math.min(min, map.getOrDefault('l', 0) / 2);
    min = Math.min(min, map.getOrDefault('o', 0) / 2);
    min = Math.min(min, map.getOrDefault('n', 0));
    return min;
  }

  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int diff = target - nums[i];
      if (map.containsKey(diff)) {
        return new int[] {map.get(diff), i};
      }
      map.put(nums[i], i);
    }
    return new int[] {};
  }

  // another array instead of hashmap hack lol
  public boolean isIso(String s, String t) {
    int[] sMap = new int[128];
    int[] tMap = new int[128];
    char c, c1;
    for (int i = 0; i < s.length(); i++) {
      c = s.charAt(i);
      c1 = t.charAt(i);
      if (sMap[c] != tMap[c1]) {
        return false;
      }
      sMap[c] = i + 1;
      tMap[c1] = i + 1;
    }
    return true;
  }

  // heres me using hashmaps
  public boolean isIso2(String s, String t) {
    HashMap<Character, Character> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      char c1 = t.charAt(i);
      if (map.containsKey(c)) {
        if (map.get(c) != c1) {
          return false;
        }
      } else {
        if (map.containsValue(c1)) {
          return false;
        }
        map.put(c, c1);
      }
    }
    return true;
  }

  public int[] intersect(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> map = new HashMap<>();
    Arrays.stream(nums1).forEach(i -> map.put(i, map.getOrDefault(i, 0) + 1));
    int[] result = new int[nums1.length];
    final int[] index = {0};
    Arrays.stream(nums2)
        .filter(i -> map.containsKey(i) && map.get(i) > 0)
        .forEach(
            i -> {
              result[index[0]] = i;
              map.put(i, map.get(i) - 1);
              index[0]++;
            });
    return Arrays.copyOfRange(result, 0, index[0]);
  }
}

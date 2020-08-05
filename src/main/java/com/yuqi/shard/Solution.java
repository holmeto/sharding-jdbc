package com.yuqi.shard;

import java.util.*;

public class Solution {
    public boolean Find(int[][] array, int target) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        int row = 0;
        int col = array[0].length - 1;
        while (row <= array.length - 1 && col >= 0) {
            if (target == array[row][col])
                return true;
            else if (target > array[row][col])
                row++;
            else
                col--;
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(4,10,15,24,26));
        list.add(Arrays.asList(0,9,12,20));
        list.add(Arrays.asList(5,18,22,30));
        System.out.println(smallestRange(list));
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        if(nums == null || nums.size() == 0){
            return null;
        }
        Set<Integer> set = new HashSet<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i = 0; i < nums.size(); i++){
            for(int j = 0; j <  nums.get(i).size(); j++){
                int temp = nums.get(i).get(j);
                set.add(temp);
                if(map.get(temp) == null){
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(i);
                    map.put(temp, tempList);
                }else{
                    map.get(temp).add(i);
                }
            }
        }
        List<Integer> rank = new ArrayList<>(set);
        Collections.sort(rank);
        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        Set<Integer> tempSet = new HashSet<>();
        while(left <= right && right < rank.size()){
            tempSet.addAll(map.get(rank.get(right)));
            if(tempSet.size() == nums.size()){
                if(min > rank.get(right) - rank.get(left)){
                    res[0] = rank.get(left);
                    res[1] = rank.get(right);
                    min = rank.get(right) - rank.get(left);
                }
                tempSet.removeAll(map.get(rank.get(left++)));
            }else {
                right++;
            }
        }
        return res;

    }

}
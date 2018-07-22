//Dean Kieserman
//Given an array of size, and a value of C, 
//this determines whether or not k unique values of the subarray sum up to C

import java.util.*;

public class KsumtoC{
    public static void main(String[] args){
        int size = 10;
        int k = 4;
        
        int[] unsorted = genArray(size);
        System.out.println(Arrays.toString(unsorted));
        System.out.println(ksumtoc(unsorted, k, 12)); 
    }
    //K numbers in subset sum to C
    public static boolean ksumtoc(int[] arr, int k, int c){
        HashMap<Integer, int[]> hasher = new HashMap<>();
        //if even
        if(k%2 == 0){
            //get all possible combinations which are half the target size K
            List<int[]> combos = combinations(arr, k/2);
            //hash each combination and value
            for(int[] x : combos){
                hasher.put(getSum(x, arr), x);
            }
            //check if another combo matches
            for(int[] y: combos){
                int diff = c-getSum(y, arr);
                //check that the combo is different
                if(hasher.containsKey(diff)){
                    if(sameIndices(hasher.get(diff), y)){
                        System.out.println(Arrays.toString(hasher.get(diff)));
                        System.out.println(Arrays.toString(y));
                        return true;
                    }
                }
            }
        }
        //if odd
        else{
            List<int[]> combo1 = combinations(arr, (k+1)/2);
            List<int[]> combo2 = combinations(arr, (k+1)/2);
            //hash each combination and value
            for(int[] x : combo1){
                hasher.put(getSum(x, arr), x);
            }
            for(int[] y: combo2){
                int diff = c-getSum(y, arr);
                if(hasher.containsKey(diff)){
                    if(sameIndices(hasher.get(diff), y)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //get sum given initial array, and combination
    public static int getSum(int[] x, int[] arr){
        int sum = 0;
        for(int i = 0; i < x.length; i++){
            sum += arr[x[i]];
        }
        return sum;
    }
    //check to see if any indices, match, if so, not unique
    public static boolean sameIndices(int[] x, int[] y){
        for(int a : x){
            for(int b : y){
                if(a == b)
                    return false;
            }
        }
        return true;
    }
    //find all possible combinations
    public static List<int[]> combinations(int arr[], int k){
        List<int[]> subsets = new ArrayList<>();
        int[] s = new int[k]; 
        if (k <= arr.length) {
            // first index sequence: 0, 1, 2, ...
            for (int i = 0; (s[i] = i) < k - 1; i++);
            subsets.add(s.clone());
            for(;;) {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && s[i] == arr.length - k + i; i--); 
                if (i < 0) {
                    break;
                }
                s[i]++;                    // increment this item
                for (++i; i < k; i++) {    // fill up remaining items
                    s[i] = s[i - 1] + 1; 
                }  
                subsets.add(s.clone());
            }
        }
        return subsets;
    }
    //generate random array
    public static int[] genArray(int s){
        Random rand = new Random();
        int[] arr = new int[s];
        for(int i = 0; i < s; i++){
            arr[i] = rand.nextInt(10);
        }
        return arr;
    }
}
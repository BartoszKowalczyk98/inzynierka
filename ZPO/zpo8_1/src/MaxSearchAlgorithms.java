import java.util.ArrayList;
import java.util.List;

public class MaxSearchAlgorithms {
    protected List<Integer> leftToRightScanForMax(int [] arr){
        List<Integer> result = new ArrayList<>();
        int temp = arr[0];
        result.add(temp);
        for (int value : arr) {
            if (value > temp) {
                temp = value;
                result.add(temp);
            }
        }
        return result;
    }

    protected List<Integer> rightToLeftScanForMax(int [] arr){
        List<Integer> result = new ArrayList<>();
        int temp = arr[arr.length-1];
        result.add(temp);
        for(int i =arr.length-1;i>=0;i--){
            if(arr[i]>temp){
                temp=arr[i];
                result.add(temp);
            }
        }
        return result;
    }
    protected List<Integer> evenOrNotScanForMax(int [] arr){
        List<Integer> result = new ArrayList<>();
        int temp = arr[0];
        result.add(temp);
        for(int i =0;i<arr.length;i+=2){
            if(arr[i]>temp){
                temp=arr[i];
                result.add(temp);
            }
        }
        for(int i =1;i<arr.length;i+=2){
            if(arr[i]>temp){
                temp=arr[i];
                result.add(temp);
            }
        }

        return result;
    }

}

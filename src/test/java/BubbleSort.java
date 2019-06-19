/**
 * @author Jackson
 * @description 冒泡排序算法
 * @date 2019/6/20
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {10, 20, 15, 0, 6, 7, 2, 1, -5, 55};
        boolean flag = true;
        for (int i = 0; i < arr.length-1; i++) {
            flag = true;
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
    }

}

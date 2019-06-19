/**
 * @author Jackson
 * @description java实现快速排序算法
 * @date 2019/6/20
 */
public class QuickSorted {
    public static void main(String[] args) {
        int[] arr = {10, 20, 15, 0, 6, 7, 2, 1, -5, 55};

        if (arr.length > 0) {
            quickSort(arr, 0, arr.length - 1);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int index = getIndex(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
    }

    public static int getIndex(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            while (low < high && arr[high] > temp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] < temp) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }
}

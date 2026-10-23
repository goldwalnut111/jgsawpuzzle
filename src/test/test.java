package test;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        int[] arr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random random=new Random();
        for (int i = 0; i < arr.length; i++) {
            int index=random.nextInt(16);
            int temp=arr[index];
            arr[index]=arr[i];
            arr[i]=temp;

        }

        int[][]a=new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j]=arr[i*4+j];
                System.out.print(a[i][j]+" ");

            }
            System.out.println();

        }


    }
}

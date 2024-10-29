package application;
public class MaxSubarraySum2 {

    // دالة لإيجاد أكبر مجموع يعبر المنتصف
    public static int maxCrossingSum(int[] arr, int L, int M, int U) {
        // إيجاد أكبر مجموع في الجزء الأيسر
        int sum = 0;
        int maxToLeft = Integer.MIN_VALUE;
        for (int i = M; i >= L; i--) {
            sum += arr[i];
            if (sum > maxToLeft) {
                maxToLeft = sum;
            }
        }

        // إيجاد أكبر مجموع في الجزء الأيمن
        sum = 0;
        int maxToRight = Integer.MIN_VALUE;
        for (int i = M + 1; i <= U; i++) {
            sum += arr[i];
            if (sum > maxToRight) {
                maxToRight = sum;
            }
        }

        // إرجاع المجموع الذي يعبر المنتصف
        return maxToLeft + maxToRight;
    }

    // الدالة التكرارية التي تستخدم التقسيم والتحليل
    public static int maxSum(int[] arr, int L, int U) {
        // حالة القاعدة إذا كان L أكبر من U
        if (L > U) {
            return 0;
        }

        // حالة القاعدة إذا كان L يساوي U (عنصر واحد)
        if (L == U) {
            return Math.max(0, arr[L]);
        }

        // حساب منتصف المصفوفة
        int M = (L + U) / 2;

        // حساب أكبر مجموع فرعي في النصف الأيسر، النصف الأيمن، وعبر المنتصف
        int maxInA = maxSum(arr, L, M); // في الجزء الأيسر
        int maxInB = maxSum(arr, M + 1, U); // في الجزء الأيمن
        int maxCrossing = maxCrossingSum(arr, L, M, U); // يعبر المنتصف

        // إرجاع أكبر قيمة من الثلاثة
        return Math.max(Math.max(maxInA, maxInB), maxCrossing);
    }

    public static void main(String[] args) {
        // مثال على مصفوفة
        int[] arr = {5, 2, -1, -3, -5, 6, 4, -1, 2, 5, -3, 1, -4, 5};
        int n = arr.length;

        // استدعاء الدالة لحساب أكبر مجموع فرعي
        int maxSumResult = maxSum(arr, 0, n - 1);

        // طباعة النتيجة
        System.out.println("أكبر مجموع فرعي هو: " + maxSumResult);
    }
}

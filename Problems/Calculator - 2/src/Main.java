/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        // write your code here
        switch (operator) {
            case "SUM":
                int sum = 0;
                for (int i = 1; i < args.length; i++) {
                    sum += Integer.parseInt(args[i]);
                }
                System.out.println(sum);
                break;
            case "MAX":
                int max = Integer.parseInt(args[1]);
                for (int i = 2; i < args.length; i++) {
                    max = Math.max(max, Integer.parseInt(args[i]));
                }
                System.out.println(max);
                break;
            case "MIN":
                int min = Integer.parseInt(args[1]);
                for (int i = 2; i < args.length; i++) {
                    min = Math.min(min, Integer.parseInt(args[i]));
                }
                System.out.println(min);
                break;
            default:System.out.println("Unknown operation");
        }
    }
}
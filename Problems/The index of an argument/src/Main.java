class Problem {

    public static void main(String[] args) {
        int i = 0;
        for (String arg : args) {
            if ("test".equals(arg)) {
                System.out.println(i);
                return;
            }
            i++;
        }
        System.out.println(-1);
    }
}
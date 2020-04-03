import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        double a = Double.parseDouble(scanner.nextLine());
        double b = Double.parseDouble(scanner.nextLine());
        double c = Double.parseDouble(scanner.nextLine());

        double b24ac = Math.sqrt(Math.pow(b, 2) - (4 * a * c));

        double y = (-b + b24ac) / (2 * a);
        double x = (-b - b24ac) / (2 * a);

        double small = Math.min(x, y);
        double big = Math.max(x, y);

        System.out.println(small + " " + big);

    }
}
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double distance = Double.parseDouble(scanner.nextLine());
        double time = Double.parseDouble(scanner.nextLine());
        System.out.println(distance / time);
    }
}
import java.util.ArrayList;
import java.util.Scanner;

public class password {
    public static void main(String[] args) {
        ArrayList<String> passwords = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < T; i++) {
            String firstLine = scanner.nextLine();
            String secondLine = scanner.nextLine();

            String password = buildPassword(firstLine, secondLine);
            passwords.add(password);
        }
        for (int i = 0; i < T; i++) {
            System.out.println(passwords.get(i));
        }
    }

    //AABABACABACABA
    static String buildPassword(String firstLine, String secondLine) {
        StringBuilder password = new StringBuilder();
        int firstIndex = 0;
        int secondIndex = 0;
        int i = 0;
        int j = 0;

        while (firstIndex < firstLine.length() && secondIndex < secondLine.length()) {

            while (firstLine.charAt(firstIndex) == secondLine.charAt(secondIndex)) {
                i = 1;
                j = 1;
                if (firstIndex + i == firstLine.length()) i = 0;
                if (secondIndex + j == secondLine.length()) j = 0;
                while (firstLine.charAt(firstIndex + i) == secondLine.charAt(secondIndex + j)) {

                    i++;
                    j++;
                    if (firstIndex + i == firstLine.length() && secondIndex + j == secondLine.length()) {
                        password.append(firstLine.charAt(firstIndex));
                        if (firstIndex + i >= firstLine.length()) i = firstIndex+1;
                        if (secondIndex + j >= secondLine.length()) j = secondIndex;
                        break;
                    }
                    if (firstIndex + i >= firstLine.length()) i = 0;
                    if (secondIndex + j >= secondLine.length()) j = 0;

                }
                if (firstLine.charAt(firstIndex + i) < secondLine.charAt(secondIndex + j)) {
                    password.append(firstLine.charAt(firstIndex+i));
                    firstIndex++;
                } else {
                    password.append(secondLine.charAt(secondIndex));
                    secondIndex++;
                }
            }

            while (firstIndex < firstLine.length()) {
                if (secondIndex == secondLine.length()) {
                    password.append(firstLine, firstIndex, firstLine.length());
                    break;
                }
                if (firstLine.charAt(firstIndex) < secondLine.charAt(secondIndex))
                    password.append(firstLine.charAt(firstIndex++));
                else break;
            }
            while (secondIndex < secondLine.length()) {
                if (firstIndex == firstLine.length()) {
                    password.append(secondLine, secondIndex, secondLine.length());
                    break;
                }
                if (secondLine.charAt(secondIndex) < firstLine.charAt(firstIndex))
                    password.append(secondLine.charAt(secondIndex++));
                else break;
            }
        }
        return password.toString();
    }
}

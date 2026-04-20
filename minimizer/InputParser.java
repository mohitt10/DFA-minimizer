package minimizer;

import java.util.*;

public class InputParser {
    public static ProgramState parse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter total number of states (labeled 0 to n-1): ");
        int numStates = scanner.nextInt();
        System.out.print("Enter the start state (e.g., 0): ");
        int startState = scanner.nextInt();
        System.out.print("Enter number of alphabet symbols: ");
        int numAlphabets = scanner.nextInt();
        Set<Character> alphabet = new HashSet<>();
        System.out.print("Enter alphabet symbols separated by space (e.g., a b): ");
        for (int i = 0; i < numAlphabets; i++) {
            alphabet.add(scanner.next().charAt(0));
        }
        System.out.print("Enter number of accepting states: ");
        int numAccepting = scanner.nextInt();
        Set<Integer> acceptingStates = new HashSet<>();
        if (numAccepting > 0) {
            System.out.print("Enter accepting states separated by space: ");
            for (int i = 0; i < numAccepting; i++) {
                acceptingStates.add(scanner.nextInt());
            }
        }
        ProgramState dfa = new ProgramState(numStates, startState, alphabet, acceptingStates);
        System.out.println("\n--- Enter transitions ---");
        for (int i = 0; i < numStates; i++) {
            for (char c : alphabet) {
                System.out.print("Transition for state " + i + " on symbol '" + c + "': ");
                int toState = scanner.nextInt();
                dfa.addTransition(i, c, toState);
            }
        }
        return dfa;
    }
}

package minimizer;

import java.util.*;

public class Utils {
    public static void printDFA(ProgramState dfa) {
        System.out.println("Total States: " + dfa.numStates);
        System.out.println("Start State: " + dfa.startState);
        System.out.println("Accepting States: " + dfa.acceptingStates);
        System.out.println("Alphabet: " + dfa.alphabet);
        
        List<Character> alphaList = new ArrayList<>(dfa.alphabet);
        Collections.sort(alphaList);
        
        System.out.println("\nTransition Table:");
        System.out.print("State\t|");
        for (char c : alphaList) {
            System.out.print(" " + c + "\t|");
        }
        System.out.println();
        
        for (int i = 0; i < dfa.numStates; i++) {
            String mark = dfa.acceptingStates.contains(i) ? "*" : "";
            mark += (i == dfa.startState) ? "->" : "";
            System.out.print(mark + i + "\t|");
            for (char c : alphaList) {
                int toState = dfa.transitions.get(i).get(c);
                System.out.print(" " + toState + "\t|");
            }
            System.out.println();
        }
    }
}

import java.util.*;
import minimizer.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== DFA Minimizer Interactive Setup ===");
        ProgramState dfa = InputParser.parse();
        ProgramState minimizedDfa = Minimizer.minimize(dfa);
        
        System.out.println("\n--- Original DFA ---");
        Utils.printDFA(dfa);

        System.out.println("\n--- Minimized DFA ---");
        Utils.printDFA(minimizedDfa);
    }
}

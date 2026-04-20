package minimizer;

import java.util.*;

public class ProgramState {
    public int numStates;
    public int startState;
    public Set<Character> alphabet;
    public Set<Integer> acceptingStates;
    public Map<Integer, Map<Character, Integer>> transitions
    public Map<Integer, Map<Character, Set<Integer>>> reverseTransitions;
    public ProgramState(int numStates, int startState, Set<Character> alphabet, Set<Integer> acceptingStates) {
        this.numStates = numStates;
        this.startState = startState;
        this.alphabet = alphabet;
        this.acceptingStates = acceptingStates;
        this.transitions = new HashMap<>();
        this.reverseTransitions = new HashMap<>();
        
        for (int i = 0; i < numStates; i++) {
            transitions.put(i, new HashMap<>());
            reverseTransitions.put(i, new HashMap<>());
            for (char c : alphabet) {
                reverseTransitions.get(i).put(c, new HashSet<>());
            }
        }
    }

    public void addTransition(int fromState, char symbol, int toState) {
        transitions.get(fromState).put(symbol, toState);
        reverseTransitions.get(toState).get(symbol).add(fromState);
    }
}

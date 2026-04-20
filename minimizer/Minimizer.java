package minimizer;

import java.util.*;

public class Minimizer {
    public static ProgramState minimize(ProgramState dfa) {
        Set<Set<Integer>> P = new HashSet<>();
        Set<Integer> F = new HashSet<>(dfa.acceptingStates);
        Set<Integer> Qf = new HashSet<>();
        
        for (int i = 0; i < dfa.numStates; i++) {
            if (!F.contains(i)) {
                Qf.add(i);
            }
        }
        if (!F.isEmpty()) P.add(F);
        if (!Qf.isEmpty()) P.add(Qf);
        Queue<Set<Integer>> W = new LinkedList<>();
        if (!F.isEmpty()) W.add(F);
        if (!Qf.isEmpty()) W.add(Qf);
        while (!W.isEmpty()) {
            Set<Integer> A = W.poll();
            for (char c : dfa.alphabet) {
                Set<Integer> X = new HashSet<>();
                for (int targetState : A) {
                    X.addAll(dfa.reverseTransitions.get(targetState).get(c));
                }
                Set<Set<Integer>> newP = new HashSet<>();
                for (Set<Integer> Y : P) {
                    Set<Integer> intersection = new HashSet<>(Y);
                    intersection.retainAll(X);
                    Set<Integer> difference = new HashSet<>(Y);
                    difference.removeAll(X);
                    if (!intersection.isEmpty() && !difference.isEmpty()) {
                        newP.add(intersection);
                        newP.add(difference);
                        boolean wasInW = false;
                        for (Set<Integer> wSet : W) {
                            if (wSet.equals(Y)) {
                                W.remove(Y);
                                W.add(intersection);
                                W.add(difference);
                                wasInW = true;
                                break;
                            }
                        }
                        if (!wasInW) {
                            if (intersection.size() <= difference.size()) {
                                W.add(intersection);
                            } else {
                                W.add(difference);
                            }
                        }
                    } else {
                        newP.add(Y);
                    }
                }
                P = newP;
            }
        }
        List<Set<Integer>> partitionList = new ArrayList<>(P);
        int newNumStates = partitionList.size();
        Set<Integer> newAcceptingStates = new HashSet<>();
        int newStartState = -1;
        Map<Integer, Integer> stateMap = new HashMap<>();
        for (int i = 0; i < partitionList.size(); i++) {
            Set<Integer> group = partitionList.get(i);
            for (int state : group) {
                stateMap.put(state, i);
                if (dfa.acceptingStates.contains(state)) {
                    newAcceptingStates.add(i);
                }
                if (state == dfa.startState) {
                    newStartState = i;
                }
            }
        }
        
        ProgramState minimized = new ProgramState(newNumStates, newStartState, dfa.alphabet, newAcceptingStates);
        for (int i = 0; i < partitionList.size(); i++) {
            Set<Integer> group = partitionList.get(i);
            int representativeState = group.iterator().next(); 
            for (char c : dfa.alphabet) {
                int oldTarget = dfa.transitions.get(representativeState).get(c);
                int newTarget = stateMap.get(oldTarget);
                minimized.addTransition(i, c, newTarget);
            }
        }
        return minimized;
    }
}

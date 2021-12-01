package com.company;

import com.company.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.BitSet;

public class Combination {
    BitSet cards_mask = new BitSet(52);
    int value;

    private Combination(Collection<Card> cards, int value) {
        for (Card card : cards) {
            cards_mask.set(card.index());
        }
        this.value = value;
    }

    public boolean isSubSet(BitSet st) {
        BitSet nd = (BitSet)st.clone();
        nd.and(cards_mask);
        return nd.equals(cards_mask);
    }

    static int max_val = 1;
    static public ArrayList<Combination> allCombinations = new ArrayList<>();
    static void addCombination(Collection<Card> cards) {
        allCombinations.add(new Combination(cards, max_val));
        max_val += 1;
    }

    static {
        for (Card card : Card.allCards) {
            addCombination(Arrays.asList(card));
        }
        for (Card card : Card.allCards) {
            addCombination(Arrays.asList(card, card));
        }

    }
}
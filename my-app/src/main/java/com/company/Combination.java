package com.company;

import com.company.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.BitSet;

public class Combination {
    BitSet cards_mask;
    int value;

    static BitSet getCardsBitset(Collection<Card> cards) {
        BitSet mask = new BitSet(52);
        for (Card card : cards) {
            mask.set(card.index());
        }
        return mask;
    }

    private Combination(Collection<Card> cards, int value) {
        cards_mask = getCardsBitset(cards);
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

    public static int getBestCombination(Collection<Card> cards) {
        BitSet st = getCardsBitset(cards);
        int max_val = 0;
        for (Combination comb : allCombinations) {
            if (!comb.isSubSet(st)) {
                continue;
            }
            max_val = Math.max(max_val, comb.value);
        }
        return max_val;
    }
}
package com.company;

import com.company.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Combination {
    List<Card> cards;
    int value;

    private Combination(Collection<Card> cards, int value) {
        this.cards = List.copyOf(cards);
        this.value = value;
    }

    public boolean isInHand(Collection<Card> hand) {
        for (Card card : cards) {
            if (!hand.contains(card)) {
                return false;
            }
        }
        return true;
    }

    static public ArrayList<Combination> allCombinations = new ArrayList<>();

    static {
        for (Card card : Card.allCards) {
            allCombinations.add(new Combination(List.of(card, card), 1));
        }
    }
}
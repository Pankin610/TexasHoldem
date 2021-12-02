package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

enum Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}

enum Name {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);
    // najsilniejsza karta karta [2; 14]
    // para 15
    // (2, 2) < (A, A),    (8, 8, 8, 2, 3, A, K) > (2, 2, 4, 4, A, K, J)
    int value;
    Name(int x) {
        value = x;
    }
}

public class Card {
    Suit suit;
    Name name;
    int index;

    private Card(Suit suit, Name name, int index) {
        this.suit = suit;
        this.name = name;
        this.index = index;
    }

    public int index() {
        return index;
    }

    public Suit getSuit() {
        return suit;
    }

    public Name getName() {
        return name;
    }

    static public ArrayList<Card> allCards = new ArrayList<>();

    static {
        int ind = 0;
        for (Suit suit : Suit.values()) {
            for (Name name : Name.values()) {
                allCards.add(new Card(suit, name, ind));
                ind++;
            }
        }
    }

    public static Stack<Card> getNewDeck() {
        Stack<Card> deck = new Stack<>();
        for (Card card : allCards) {
            deck.push(card);
        }

        Collections.shuffle(deck);
        return deck;
    }

    public int compareByValue(Card other) {
        return Integer.compare(name.value, other.name.value);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
            return ((Card)o).name.equals(name) && ((Card)o).suit.equals(suit);
        }
        return false;
    }
}
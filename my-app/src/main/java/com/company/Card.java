package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

enum Suit {
    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts"),
    SPADES("Spades");

    String text;
    Suit(String text) {
        this.text = text;
    }
}

enum Name {
    TWO(2, "two"),
    THREE(3, "three"),
    FOUR(4, "four"),
    FIVE(5, "five"),
    SIX(6, "six"),
    SEVEN(7, "seven"),
    EIGHT(8, "eight"),
    NINE(9, "nine"),
    TEN(10, "ten"),
    JACK(11, "Jack"),
    QUEEN(12, "Queen"),
    KING(13, "King"),
    ACE(14, "Ace");
    // najsilniejsza karta karta [2; 14]
    // para 15
    // (2, 2) < (A, A),    (8, 8, 8, 2, 3, A, K) > (2, 2, 4, 4, A, K, J)
    int value;
    String text;
    Name(int x, String text) {
        value = x;
        this.text = text;
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

    @Override
    public String toString() {
        return suit.text + " " + name.text;
    } 
}
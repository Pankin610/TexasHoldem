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
        switch (suit.text) {
            case "Clubs":
                switch (name.text) {
                    case "King":
                        return "\uD83C\uDCDE";
                    case "Queen":
                        return "\uD83C\uDCDD";
                    case "Jack":
                        return "\uD83C\uDCDB";
                    case "ten":
                        return "\uD83C\uDCDA";
                    case "nine":
                        return "\uD83C\uDCD9";
                    case "eight":
                        return "\uD83C\uDCD8";
                    case "seven":
                        return "\uD83C\uDCD7";
                    case "six":
                        return "\uD83C\uDCD6";
                    case "five":
                        return "\uD83C\uDCD5";
                    case "four":
                        return "\uD83C\uDCD4";
                    case "three":
                        return "\uD83C\uDCD3";
                    case "two":
                        return "\uD83C\uDCD2";
                    case "Ace":
                        return "\uD83C\uDCD1";
                }
                break;
            case "Hearts":
                switch (name.text) {
                    case "King":
                        return "\uD83C\uDCBE";
                    case "Queen":
                        return "\uD83C\uDCBD";
                    case "Jack":
                        return "\uD83C\uDCBB";
                    case "ten":
                        return "\uD83C\uDCBA";
                    case "nine":
                        return "\uD83C\uDCB9";
                    case "eight":
                        return "\uD83C\uDCB8";
                    case "seven":
                        return "\uD83C\uDCB7";
                    case "six":
                        return "\uD83C\uDCB6";
                    case "five":
                        return "\uD83C\uDCB5";
                    case "four":
                        return "\uD83C\uDCB4";
                    case "three":
                        return "\uD83C\uDCB3";
                    case "two":
                        return "\uD83C\uDCB2";
                    case "Ace":
                        return "\uD83C\uDCB1";
                }
                break;
            case "Spades":
                switch (name.text) {
                    case "King":
                        return "\uD83C\uDCAE";
                    case "Queen":
                        return "\uD83C\uDCAD";
                    case "Jack":
                        return "\uD83C\uDCAB";
                    case "ten":
                        return "\uD83C\uDCAA";
                    case "nine":
                        return "\uD83C\uDCA9";
                    case "eight":
                        return "\uD83C\uDCA8";
                    case "seven":
                        return "\uD83C\uDCA7";
                    case "six":
                        return "\uD83C\uDCA6";
                    case "five":
                        return "\uD83C\uDCA5";
                    case "four":
                        return "\uD83C\uDCA4";
                    case "three":
                        return "\uD83C\uDCA3";
                    case "two":
                        return "\uD83C\uDCA2";
                    case "Ace":
                        return "\uD83C\uDCA1";
                }
                break;
            case "Diamonds":
                switch (name.text) {
                    case "King":
                        return "\uD83C\uDCCE";
                    case "Queen":
                        return "\uD83C\uDCCD";
                    case "Jack":
                        return "\uD83C\uDCCB";
                    case "ten":
                        return "\uD83C\uDCCA";
                    case "nine":
                        return "\uD83C\uDCC9";
                    case "eight":
                        return "\uD83C\uDCC8";
                    case "seven":
                        return "\uD83C\uDCC7";
                    case "six":
                        return "\uD83C\uDCC6";
                    case "five":
                        return "\uD83C\uDCC5";
                    case "four":
                        return "\uD83C\uDCC4";
                    case "three":
                        return "\uD83C\uDCC3";
                    case "two":
                        return "\uD83C\uDCC2";
                    case "Ace":
                        return "\uD83C\uDCC1";
                }
                break;
        }
        return "";
    } 
}
package com.company;

import com.company.Card;

import java.util.*;

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
    static void addCombination(Collection<Card> cards, int value) {
        allCombinations.add(new Combination(cards, max_val + value));
    }

    static {
        // adding all combinations in the increasing order of strength

        // strong card
        for (Card card : Card.allCards) {
            addCombination(Arrays.asList(card), card.name.value);
        }
        // pair
        max_val = 1000000;
        for (Card card1 : Card.allCards) {
            for (Card card2 : Card.allCards) {
                if (card2.name.value < card1.name.value) {
                    continue;
                }
                if (card1.name.equals(card2.name) && !card1.equals(card2)) {
                    addCombination(Arrays.asList(card1, card2), card1.name.value);
                }
            }
        }
        // two pair
        max_val = 2000000;
        for (Card card1 : Card.allCards) {
            for (Card card2 : Card.allCards) {
                if (card2.name.value < card1.name.value) {
                    continue;
                }
                for (Card card3 : Card.allCards) {
                    if (card3.name.value < card2.name.value) {
                        continue;
                    }
                    for (Card card4 : Card.allCards) {
                        if (card4.name.value < card3.name.value) {
                            continue;
                        }
                        if (!card1.equals(card2) && card1.name.equals(card2.name)) {
                            if (!card3.equals(card4) && card3.name.equals(card4.name)) {
                                if (!card1.name.equals(card3.name)) {
                                    addCombination(Arrays.asList(card1, card2, card3, card4),(card3.name.value * 100 + card1.name.value));
                                }
                            }
                        }
                    }
                }
            }
        }
        // three of kind
        max_val = 3000000;
        for (Card card1 : Card.allCards) {
            for (Card card2 : Card.allCards) {
                if (card2.name.value < card1.name.value) {
                    continue;
                }
                for (Card card3 : Card.allCards) {
                    if (card3.name.value < card2.name.value) {
                        continue;
                    }
                    if (card1.name.equals(card2.name) && card2.name.equals(card3.name)) {
                        if (!card1.equals(card2) && !card1.equals(card3) && !card2.equals(card3)) {
                            addCombination(Arrays.asList(card1, card2, card3), card1.name.value);
                        }
                    }
                }
            }
        }
        // street
        max_val = 4000000;
        for (Card card1 : Card.allCards) {
            for (Card card2 : Card.allCards) {
                if (card2.name.value < card1.name.value) {
                    continue;
                }
                for (Card card3 : Card.allCards) {
                    if (card3.name.value < card2.name.value) {
                        continue;
                    }
                    for (Card card4 : Card.allCards) {
                        if (card4.name.value < card3.name.value) {
                            continue;
                        }
                        for (Card card5 : Card.allCards) {
                            if (card5.name.value < card4.name.value) {
                                continue;
                            }
                            if (card1.name.value + 1 == card2.name.value) {
                                if (card2.name.value + 1 == card3.name.value) {
                                    if (card3.name.value + 1 == card4.name.value) {
                                        if (card4.name.value + 1 == card5.name.value) {
                                            HashSet<Suit> suitesss = new HashSet<>();
                                            suitesss.add(card1.suit);
                                            suitesss.add(card2.suit);
                                            suitesss.add(card3.suit);
                                            suitesss.add(card4.suit);
                                            suitesss.add(card5.suit);
                                            if (suitesss.size() > 1) {
                                                Combination.addCombination(Arrays.asList(card1, card2, card3, card4, card5), card1.name.value);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // flush - kolor
        max_val = 5000000;
        for (Card card1 : Card.allCards) {
            for (Card card2 : Card.allCards) {
                if (card2.name.value < card1.name.value) {
                    continue;
                }
                for (Card card3 : Card.allCards) {
                    if (card3.name.value < card2.name.value) {
                        continue;
                    }
                    for (Card card4 : Card.allCards) {
                        if (card4.name.value < card3.name.value) {
                            continue;
                        }
                        for (Card card5 : Card.allCards) {
                            if (card5.name.value < card4.name.value) {
                                continue;
                            }
                            HashSet<Card> sett = new HashSet<>();
                            sett.add(card1);
                            sett.add(card2);
                            sett.add(card3);
                            sett.add(card4);
                            sett.add(card5);

                            HashSet<Suit> suiteess = new HashSet<>();
                            suiteess.add(card1.suit);
                            suiteess.add(card2.suit);
                            suiteess.add(card3.suit);
                            suiteess.add(card4.suit);
                            suiteess.add(card5.suit);

                            if (sett.size() == 5 && suiteess.size() == 5) {
                                Combination.addCombination(Arrays.asList(card1, card2, card3, card4, card5), (card5.name.value * 10000 + card4.name.value * 1000 + card3.name.value * 100 + card2.name.value * 10 + card1.name.value));
                            }
                        }
                    }
                }
            }
        }
        // full house
        max_val = 6000000;
        for (Card card1 : Card.allCards) {
            for (Card card2 : Card.allCards) {
                if (card2.name.value < card1.name.value) {
                    continue;
                }
                for (Card card3 : Card.allCards) {
                    if (card3.name.value < card2.name.value) {
                        continue;
                    }
                    for (Card card4 : Card.allCards) {
                        if (card4.name.value < card3.name.value) {
                            continue;
                        }
                        for (Card card5 : Card.allCards) {
                            if (card5.name.value < card4.name.value) {
                                continue;
                            }
                            HashSet<Card> sett = new HashSet<>();
                            sett.add(card1);
                            sett.add(card2);
                            sett.add(card3);
                            sett.add(card4);
                            sett.add(card5);

                            if (sett.size() < 5) {
                                continue;
                            }

                            if (card1.name.equals(card2.name) && card2.name.equals(card3.name)) {
                                if (card4.name.equals(card5.name)) {
                                    Combination.addCombination(Arrays.asList(card1, card2, card3, card4, card5), (card1.name.value * 100 + card2.name.value));
                                }
                            }
                        }
                    }
                }
            }
        }
        // four of kind
        max_val = 7000000;
        for (Card card1 : Card.allCards) {
            for (Card card2 : Card.allCards) {
                if (card2.name.value < card1.name.value) {
                    continue;
                }
                for (Card card3 : Card.allCards) {
                    if (card3.name.value < card2.name.value) {
                        continue;
                    }
                    for (Card card4 : Card.allCards) {
                        if (card4.name.value < card3.name.value) {
                            continue;
                        }
                        HashSet<Card> sett = new HashSet<>();
                        sett.add(card1);
                        sett.add(card2);
                        sett.add(card3);
                        sett.add(card4);

                        if (sett.size() < 4) {
                            continue;
                        }

                        HashSet<String> strings = new HashSet<>();
                        strings.add(card1.name.text);
                        strings.add(card2.name.text);
                        strings.add(card3.name.text);
                        strings.add(card4.name.text);

                        if (strings.size() == 1) {
                            Combination.addCombination(Arrays.asList(card1, card2, card3, card4), card1.name.value);
                        }
                    }
                }
            }
        }
        // poker
        max_val = 8000000;
        for (Card card1 : Card.allCards) {
            for (Card card2 : Card.allCards) {
                if (card2.name.value < card1.name.value) {
                    continue;
                }
                for (Card card3 : Card.allCards) {
                    if (card3.name.value < card2.name.value) {
                        continue;
                    }
                    for (Card card4 : Card.allCards) {
                        if (card4.name.value < card3.name.value) {
                            continue;
                        }
                        for (Card card5 : Card.allCards) {
                            if (card5.name.value < card4.name.value) {
                                continue;
                            }
                            if (card1.name.value + 1 == card2.name.value) {
                                if (card2.name.value + 1 == card3.name.value) {
                                    if (card3.name.value + 1 == card4.name.value) {
                                        if (card4.name.value + 1 == card5.name.value) {
                                            HashSet<Suit> suitesss = new HashSet<>();
                                            suitesss.add(card1.suit);
                                            suitesss.add(card2.suit);
                                            suitesss.add(card3.suit);
                                            suitesss.add(card4.suit);
                                            suitesss.add(card5.suit);
                                            if (suitesss.size() == 1) {
                                                Combination.addCombination(Arrays.asList(card1, card2, card3, card4, card5), card1.name.value);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // threes of the same value
        /*Map<Name, Card[]> cards_by_name = Card.allCards.stream().
            collect(Collectors.groupingBy(
                card Card.getName(), Collectors.toArray()
            ));*/
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
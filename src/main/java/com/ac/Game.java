package com.ac;

public class Game {

    private Deck deck;
    private Player dealer;
    private Player[] players;

    public Game(int playerCount) {
        deck = new Deck();
        players = new Player[playerCount];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
            players[i].setName("Player " + (i + 1));
        }
        dealer = new Player();
        dealer.setName("Computer");
        //Shuffle the deck
        deck.shuffle();

        startPlay();
    }


    private void startPlay() {

        //Give each player one card
        for (int i = 0; i < players.length; i++) {
            players[i].addCard(deck.draw());
            System.out.println(
                    new StringBuilder("Dealing to ")
                            .append(players[i].getName())
                            .append(", card: ")
                            .append(players[i]._getHand())
                            .toString());
        }


        // Give dealer one card
        dealer.addCard(deck.draw());
        System.out.println("Dealing to computer, card: face down");

        // Give each player second card
        for (int i = 0; i < players.length; i++) {
            players[i].addCard(deck.draw());
            System.out.println(
                    new StringBuilder("Dealing to ")
                            .append(players[i].getName())
                            .append(", card: ")
                            .append(players[i]._getHand())
                            .toString());
            players[i].makeDecision(deck);
            if (players[i].calculatedValue() > 21) {
                System.out.println("Busted over 21");
            }
        }

        //Give the dealer second card
        dealer.addCard(deck.draw());


        System.out.println("Dealing to " + dealer.getName() + ", card: " + dealer._getHand());
        while (dealer.calculatedValue() < 17) {
            dealer.hit(deck);
        }

        // Print Scores for each player
        for (int i = 0; i < players.length; i++) {
            if (dealer.calculatedValue() > 21) {

                System.out.println(
                        new StringBuilder("Scoring ")
                                .append(players[i].getName())
                                .append(" has ")
                                .append(players[i].calculatedValue())
                                .append(", dealer has ")
                                .append(dealer.calculatedValue())
                                .append(" Dealer busts")
                );

            } else if (dealer.calculatedValue() >= players[i].calculatedValue()) {
                System.out.println(
                        new StringBuilder("Scoring ")
                                .append(players[i].getName())
                                .append(" has ")
                                .append(players[i].calculatedValue())
                                .append(", dealer has ")
                                .append(dealer.calculatedValue())
                                .append(" Dealer wins.")
                );
            } else if (players[i].calculatedValue() > 21) {
                System.out.println(
                        new StringBuilder("Scoring ")
                                .append(players[i].getName())
                                .append(" busted ")
                );
            } else if (players[i].calculatedValue() > dealer.calculatedValue()) {
                System.out.println(
                        new StringBuilder("Scoring ")
                                .append(players[i].getName())
                                .append(" has ")
                                .append(players[i].calculatedValue())
                                .append(", dealer has ")
                                .append(dealer.calculatedValue())
                                .append(" ")
                                .append(players[i].getName())
                                .append(" wins.")
                );
            } else {
                System.out.println("");
            }
        }

    }

}

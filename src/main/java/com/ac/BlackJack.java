package com.ac;

public class BlackJack {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("usage: blackjack 3");
            System.exit(1);
        }
            int noOfPlayers = Integer.parseInt(args[0]);
            System.out.println("Starting game with "+ noOfPlayers +" players.");

            //start the game of Blackjack
            Game blackjack = new Game(noOfPlayers);
    }
}

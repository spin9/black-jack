package com.ac;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    Scanner input = new Scanner(System.in);

    private  String name;

    private  ArrayList<Card> hand;

    public Player() {
        this.hand = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public String _getHand(){
        String output = "";
        for(Card card: hand){
            output += card + " ";
        }
        return output;
    }

    public int calculatedValue(){
        int value = 0;
        int aceCount = 0;
        for(Card card: hand){
            value += card.getRank().rankValue;
            if (card.getRank().rankValue == 11){
                aceCount ++;
            }
        }
        if (value > 21 && aceCount > 0){
            while(aceCount > 0 && value > 21){
                aceCount --;
                value -= 10;
            }
        }
        return value;
    }

    public void hit(Deck deck){
        hand.add(deck.draw());
        System.out.println("Dealing to " + this.name + ", card: " + this._getHand());

    }

    public void makeDecision(Deck deck) {

        String  decision = "";
        boolean getInput = true;

        while(getInput){
            try{
                System.out.println("Hit or Stand?");
                decision = input.next();
                getInput = false;

            }
            //catch any exceptions and try again
            catch(Exception e){
                System.out.println("Invalid");
                input.next();
            }
        }

        if (decision.equalsIgnoreCase("hit")) {
            this.hit(deck);
            if(this.calculatedValue()>20){
                return;
            }
            else{
                this.makeDecision(deck);
            }

        } else {
            //System.out.println("You stand.");
        }


    }

}

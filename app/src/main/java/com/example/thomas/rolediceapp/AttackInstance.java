package com.example.thomas.rolediceapp;

/**
 * Created by Thomas on 2/1/2015.
 */
public class AttackInstance {

    private int AttackingPlayers;
    private int DefendingPlayers;
    private RollDiceActivity rollDiceActivity;
    private int a1;
    private int a2;
    private int a3;
    private int d1;
    private int d2;

    public AttackInstance(int defendingPlayers, int attackingPlayers, RollDiceActivity rda){
        AttackingPlayers = attackingPlayers;
        DefendingPlayers = defendingPlayers;
        rollDiceActivity = rda;
        PrintPlayersLeft();
    }

    public void PrintPlayersLeft(){
        rollDiceActivity.PrintToActivity("Attacking players: " + Integer.toString(AttackingPlayers) + " Defending players: " + Integer.toString(DefendingPlayers));
    }

    public void Roll(){
        a1 = rollDiceActivity.NextDiceRoll();
        a2 = rollDiceActivity.NextDiceRoll();
        a3 = rollDiceActivity.NextDiceRoll();
        d1 = rollDiceActivity.NextDiceRoll();
        d2 = rollDiceActivity.NextDiceRoll();
        SortDice();
        rollDiceActivity.PrintToActivity("Attack rolled: " + Integer.toString(a1) + ", " + Integer.toString(a2) + ", " + Integer.toString(a3));
        rollDiceActivity.PrintToActivity("Defence rolled: " + Integer.toString(d1) + ", " + Integer.toString(d2));
        CompareDice();
        PrintPlayersLeft();
    }

    public boolean CanAttack(){
        if (AttackingPlayers > 1 && DefendingPlayers > 0){
            return true;
        } else {
            return false;
        }
    }

    private void CompareDice(){
        int attackToLose = 0;
        int defenceToLose = 0;
        if (a1 > d1){
            defenceToLose++;
        } else {
            attackToLose++;
        }
        if (a2 > d2){
            defenceToLose++;
        } else {
            attackToLose++;
        }
        if(attackToLose > 0){
            rollDiceActivity.PrintToActivity("Attack to lose " + Integer.toString(attackToLose));
        }
        if(defenceToLose > 0){
            rollDiceActivity.PrintToActivity("Defence to lose " + Integer.toString(defenceToLose));
        }
        AttackingPlayers -= attackToLose;
        DefendingPlayers -= defenceToLose;
    }

    private void SortDice(){
        if (d1 < d2){
            int i = d1;
            d1 = d2;
            d2 = i;
        }
        if (a2 < a3){
            int i = a2;
            a2 = a3;
            a3 = i;
        }
        if (a1 < a2){
            int i = a1;
            a1 = a2;
            a2 = i;
        }
        if (a2 < a3){
            int i = a2;
            a2 = a3;
            a3 = i;
        }
    }
}

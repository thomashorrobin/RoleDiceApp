package com.tomhorrobinapps.thomas.rolldiceapp;

import android.graphics.Color;

public class AttackInstance {

    private int AttackingPlayers;
    private int DefendingPlayers;
    private RollDiceActivity rollDiceActivity;
    private int a1;
    private int a2;
    private int a3;
    private int d1;
    private int d2;

    public AttackInstance(int defendingPlayers, int attackingPlayers, RollDiceActivity rda) {
        AttackingPlayers = attackingPlayers;
        DefendingPlayers = defendingPlayers;
        rollDiceActivity = rda;
        PrintPlayersLeft();
    }

    public void PrintPlayersLeft() {
        rollDiceActivity.PrintToActivity("Attacking players: " + Integer.toString(AttackingPlayers) + " Defending players: " + Integer.toString(DefendingPlayers), Color.BLACK);
    }

    public void Roll(int NumberOfPlayersAttacking, int NumberOfPlayersDefending) {
        if (AttackingPlayers - 1 < NumberOfPlayersAttacking) {
            NumberOfPlayersAttacking = AttackingPlayers - 1;
        }
        if (DefendingPlayers < NumberOfPlayersDefending) {
            NumberOfPlayersDefending = DefendingPlayers;
        }
        rollDiceActivity.PrintToActivity("Attacking with " + Integer.toString(NumberOfPlayersAttacking) + " players");
        rollDiceActivity.PrintToActivity("Defending with " + Integer.toString(NumberOfPlayersDefending) + " players");
        a1 = rollDiceActivity.NextDiceRoll();
        a2 = rollDiceActivity.NextDiceRoll();
        a3 = rollDiceActivity.NextDiceRoll();
        d1 = rollDiceActivity.NextDiceRoll();
        d2 = rollDiceActivity.NextDiceRoll();
        SortDice(NumberOfPlayersAttacking, NumberOfPlayersDefending);
        if (NumberOfPlayersAttacking == 3) {
            rollDiceActivity.PrintToActivity("Attack rolled: " + Integer.toString(a1) + ", " + Integer.toString(a2) + ", " + Integer.toString(a3));
        } else if (NumberOfPlayersAttacking == 2) {
            rollDiceActivity.PrintToActivity("Attack rolled: " + Integer.toString(a1) + ", " + Integer.toString(a2));
        } else if (NumberOfPlayersAttacking == 1) {
            rollDiceActivity.PrintToActivity("Attack rolled: " + Integer.toString(a1));
        }
        if (NumberOfPlayersDefending == 2) {
            rollDiceActivity.PrintToActivity("Defence rolled: " + Integer.toString(d1) + ", " + Integer.toString(d2));
        } else if (NumberOfPlayersDefending == 1) {
            rollDiceActivity.PrintToActivity("Defence rolled: " + Integer.toString(d1));
        }
        CompareDice(NumberOfPlayersAttacking, NumberOfPlayersDefending);
        PrintPlayersLeft();
        if (!CanAttack()) {
            if (AttackingPlayers > 1) {
                rollDiceActivity.PrintToActivity("Attack wins, attack must move " + Integer.toString(NumberOfPlayersAttacking) + " peaces into the conquered territory");
            } else {
                rollDiceActivity.PrintToActivity("The attacking player can no longer attack");
            }
        }
    }

    public boolean CanAttack() {
        if (AttackingPlayers > 1 && DefendingPlayers > 0) {
            return true;
        }
        return false;
    }

    private void CompareDice(int NumberOfPlayersAttacking, int NumberOfPlayersDefending) {
        int attackToLose = 0;
        int defenceToLose = 0;
        if (a1 > d1) {
            defenceToLose++;
        } else {
            attackToLose++;
        }
        if (NumberOfPlayersAttacking > 1 && NumberOfPlayersDefending > 1) {
            if (a2 > d2) {
                defenceToLose++;
            } else {
                attackToLose++;
            }
        }
        if (attackToLose > 0) {
            rollDiceActivity.PrintToActivity("Attack to lose " + Integer.toString(attackToLose));
        }
        if (defenceToLose > 0) {
            rollDiceActivity.PrintToActivity("Defence to lose " + Integer.toString(defenceToLose));
        }
        AttackingPlayers -= attackToLose;
        DefendingPlayers -= defenceToLose;
    }

    private void SortDice(int NumberOfPlayersAttacking, int NumberOfPlayersDefending) {
        if (NumberOfPlayersDefending > 2 && d1 < d2) {
            int i = d1;
            d1 = d2;
            d2 = i;
        }
        if (NumberOfPlayersAttacking > 2 && a2 < a3) {
            int i = a2;
            a2 = a3;
            a3 = i;
        }
        if (NumberOfPlayersAttacking >= 2 && a1 < a2) {
            int i = a1;
            a1 = a2;
            a2 = i;
        }
        if (NumberOfPlayersAttacking > 2 && a2 < a3) {
            int i = a2;
            a2 = a3;
            a3 = i;
        }
    }
}

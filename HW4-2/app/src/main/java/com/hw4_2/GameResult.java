package com.hw4_2;



public class GameResult {
    private int playerChoice;
    private int comChoice;
    private int result;

    // 0 為輸，1 為贏，2 為平手
    private int rule[][] = {{2, 0, 1}, {1, 2, 0}, {0, 1, 2}};

    private int generateRandom() {
        return (int)(Math.random() * 3 + 1);
    }

    public void setPlayerChoice(int iMyChoice) {
        this.playerChoice = iMyChoice;
    }

    public void setComChoice(int iComChoice) {
        this.comChoice = iComChoice;
    }

    public int getComChoice() {
        return this.comChoice;
    }

    public void judge() {
        // 決定誰輸誰贏
        this.result = rule[this.playerChoice - 1][this.comChoice - 1];
    }

    public void playGame(int iMyChoice) {
        this.setPlayerChoice(iMyChoice);
        this.setComChoice(this.generateRandom());
        this.judge();
    }

    public int getResult() {
        return this.result;
    }
}

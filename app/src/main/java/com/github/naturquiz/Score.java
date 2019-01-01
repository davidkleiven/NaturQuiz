package com.github.naturquiz;

class Score extends Object{
    private int numCorrect = 0;
    private int numTotal = 0;

    public void update(boolean correct){
        if (correct){
            numCorrect += 1;
        }
        numTotal += 1;
    }

    public String get(){
        int percent = 0;

        if (numTotal > 0){
            percent = numCorrect*100/numTotal;
        }
        return numCorrect + " av " + numTotal + " (" + percent + " %)";
    }

    public void reset(){
        numCorrect = 0;
        numTotal = 0;
    }
}
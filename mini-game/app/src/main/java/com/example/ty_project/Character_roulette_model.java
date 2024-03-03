package com.example.ty_project;

public class Character_roulette_model {

    private int imageId;
    private boolean isFaceUp;
    private boolean isMatched;

    public Character_roulette_model(int imageId, boolean isFaceUp, boolean isMatched) {
        this.imageId = imageId;
        this.isFaceUp = isFaceUp;
        this.isMatched = isMatched;
    }

    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId){
        this.imageId =imageId;
    }
    public boolean isFaceUp() {
        return isFaceUp;
    }
    public void setFaceUp(boolean faceUp){
        isFaceUp = faceUp;
    }
    public boolean isMatched() {
        return isMatched;
    }
    public void setMatched(boolean matched){
        isMatched = matched;
    }
}

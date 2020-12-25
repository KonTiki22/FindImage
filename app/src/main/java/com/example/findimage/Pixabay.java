package com.example.findimage;

public class Pixabay {
    int total, totalHits;
    Hit[] hits;

    class Hit {
        int id;
        String previewURL;
    }
    
    @Override
    public String toString() {
        return "totalHits = " + totalHits;
    }
}

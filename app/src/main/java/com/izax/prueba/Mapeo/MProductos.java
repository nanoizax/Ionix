package com.izax.prueba.Mapeo;

public class MProductos {
    String  id, title, releaseState, image, plot, stars;

    public MProductos(){

    }

    public MProductos(String id, String title, String releaseState, String image, String plot, String stars){
        this.id = id;
        this.title = title;
        this.releaseState = releaseState;
        this.image = image;
        this.plot = plot;
        this.stars = stars;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseState() {
        return releaseState;
    }

    public void setReleaseState(String releaseState) {
        this.releaseState = releaseState;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }


}

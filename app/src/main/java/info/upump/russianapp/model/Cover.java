package info.upump.russianapp.model;

/**
 * Created by explo on 29.01.2018.
 */

public class Cover {
    private long id;
    private String title;
    private int rate;
    private boolean favorite;
    private boolean read;
    private Author author;
    private Tale tale;
    private String img;

    public Cover(long id) {
        this.id = id;
    }

    public Cover() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Tale getTale() {
        return tale;
    }

    public void setTale(Tale tale) {
        this.tale = tale;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Cover{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", favorite=" + favorite +
                ", read=" + read +
                ", author=" + author.getId() +

                ", img='" + img + '\'' +
                '}';
    }
}

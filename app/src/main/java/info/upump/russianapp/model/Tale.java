package info.upump.russianapp.model;

/**
 * Created by explo on 29.01.2018.
 */

public class Tale {
    private long id;
    private String text;
    private Cover cover;
    private String imgChar;

    public Tale(long id) {
        this.id = id;
    }

    public Tale() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Tale{" +
                "id=" + id +
                ", cover=" + cover.getTitle() +
                '}';
    }

    public String getImgChar() {
        return imgChar;
    }

    public void setImgChar(String imgChar) {
        this.imgChar = imgChar;
    }
}

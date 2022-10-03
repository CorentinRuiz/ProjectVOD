public class Movie {
    private String isbn;
    private String content;

    public Movie() {

    }

    public Movie(String isbn, String content) {
        this.isbn = isbn;
        this.content = content;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

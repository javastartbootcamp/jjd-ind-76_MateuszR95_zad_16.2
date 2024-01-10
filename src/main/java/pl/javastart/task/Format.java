package pl.javastart.task;

public enum Format {

    FORMAT_1("yyyy-MM-dd HH:mm:ss"),
    FORMAT_2("yyyy-MM-dd"),
    FORMAT_3("dd.MM.yyyy HH:mm:ss");

    private String description;

    Format(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package org.example.strengir;

public enum View {
    Strengir("strengir-view.fxml"),
    ASKRIFANDI("askrifandi-view.fxml");

    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

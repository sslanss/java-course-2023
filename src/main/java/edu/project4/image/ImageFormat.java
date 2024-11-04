package edu.project4.image;

public enum ImageFormat {

    PNG("png"),
    BMP("bmp"),
    JPEG("jpeg"),
    PCX("pcx");

    private final String formatName;

    ImageFormat(String formatName) {
        this.formatName = formatName;
    }

    public String getFormatName() {
        return formatName;
    }

}

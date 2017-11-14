package org.impstack.awt;

public class Resolution {

    private final int width;
    private final int height;
    private final int bpp;

    public Resolution(int width, int height, int bpp) {
        this.width = width;
        this.height = height;
        this.bpp = bpp;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBpp() {
        return bpp;
    }

    @Override
    public String toString() {
        return width + "x" + height + " (" + bpp + " bit)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resolution that = (Resolution) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        return bpp == that.bpp;

    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + bpp;
        return result;
    }
}

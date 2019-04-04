package com.example.localviewer.logic.entity;

import java.util.Arrays;
import java.util.Objects;

public class Album {
    String name;
    String [] paths;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(name, album.name) &&
                Arrays.equals(paths, album.paths);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(paths);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public Album(String name, String[] paths) {
        this.name = name;
        this.paths = paths;
    }

    public Album() {
    }
}

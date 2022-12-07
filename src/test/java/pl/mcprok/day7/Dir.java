package pl.mcprok.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dir {
    private final String name;
    private final List<File> files = new ArrayList<>();
    private final List<Dir> dirs = new ArrayList<>();
    private final Dir parent;

    public Dir(String name, Dir parent) {
        this.name = name;
        this.parent = parent;
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addDir(Dir dir) {
        dirs.add(dir);
    }

    public Dir getParent() {
        return parent;
    }

    public Dir findDir(String dirName) {
        return dirs.stream().filter(dir -> dir.name.equals(dirName)).findFirst().orElseThrow();
    }

    public int getDirSize() {
        return this.getFilesSize() +  dirs.stream().mapToInt(Dir::getDirSize).sum();
    }

    private int getFilesSize() {
        return this.files.stream().mapToInt(File::size).sum();
    }

    @Override
    public String toString() {
        return "Dir{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dir dir = (Dir) o;

        if (!name.equals(dir.name)) return false;
        return Objects.equals(parent, dir.parent);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }
}

package com.taskCodeInside.Task1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Task1 {
    public static String extension = "txt";

    public static void main(String[] args) {
        WalkFiles walkFiles = new WalkFiles(Paths.get(args[0]), "text");
        ForkJoinPool fp = new ForkJoinPool();
        fp.invoke(walkFiles);
    }


    private static class WalkFiles extends RecursiveAction {
        private final Path dir;
        private final String text;

        public WalkFiles(Path dir, String text) {
            this.dir = dir;
            this.text = text;
        }

        @Override
        protected void compute() {
            List<WalkFiles> walkFiles = new ArrayList<>();
            try {
                Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        if (!dir.equals(WalkFiles.this.dir)) {
                            WalkFiles w = new WalkFiles(dir, text);
                            w.fork();
                            walkFiles.add(w);
                            return FileVisitResult.SKIP_SUBTREE;
                        } else {
                            return FileVisitResult.CONTINUE;
                        }
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (Files.isReadable(file) && getExtension(file).equals(extension)) {
                            boolean match = Files.lines(file, StandardCharsets.ISO_8859_1).parallel().anyMatch(l -> l.contentEquals(text));
                            if (match) {
                                System.out.println(file.toString());
                            }
                        }
                       return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        if (exc instanceof AccessDeniedException) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        return super.visitFileFailed(file, exc);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (WalkFiles w: walkFiles) {
                w.join();
            }
        }
    }

    public static String getExtension(Path file) {
        String fileName = file.toFile().getName();
        Optional<String> optionalS = Optional.of(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));

        return optionalS.orElse("");
    }
}

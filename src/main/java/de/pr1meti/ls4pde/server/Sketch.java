package de.pr1meti.ls4pde.server;

import org.eclipse.lsp4j.InitializeParams;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Acts as a project in which all code of the current project is stored
 */
public class Sketch {

    private String projectName;

    /**
     * Path to the sketch root
     */
    private URI sketchRoot;

    /**
     * Contains every sketch code in the project except the main file. Only SketchCodes which have the project root as
     * their parent file will be stored in this array.
     */
    private List<SketchCode> sketchCodes;

    /**
     * Points to the main file which has the same name as the project
     */
    private SketchCode mainSketchCode;

    public void initialize(InitializeParams initializeParams) {

        this.sketchRoot = URI.create(initializeParams.getRootUri());

        this.projectName = Path.of(sketchRoot).getFileName().toString();

        try {
            this.sketchCodes = Files
                    .find(Path.of(sketchRoot), 1, (path, basicFileAttributes) -> path.toString().endsWith(".pde"))
                    .filter(path -> !path.endsWith(this.projectName))
                    .map(path -> {
                        String sketchCodeText;

                        try {
                            sketchCodeText = Files.readString(path);
                        } catch (IOException e) {
                            sketchCodeText = "";
                            e.printStackTrace();
                        }
                        return new SketchCode(path.toUri(), "pde", 0, sketchCodeText);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            this.sketchCodes = new ArrayList<>();
            e.printStackTrace();
        }
    }
}
package de.pr1meti.ls4pde.server;

import java.net.URI;

/**
 * Contains code of a single .pde file. See <a href="https://microsoft.github.io/language-server-protocol/specifications/specification-current/#textDocumentItem">LSP specification</a>
 */
public class SketchCode {

    private URI uri;

    private String languageId;

    private int version;

    private String text;

    public SketchCode(URI uri, String languageId, int version, String text) {
        this.uri = uri;
        this.languageId = languageId;
        this.version = version;
        this.text = text;
    }
}
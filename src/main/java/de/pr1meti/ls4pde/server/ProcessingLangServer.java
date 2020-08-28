package de.pr1meti.ls4pde.server;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;

import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.DocumentFormattingParams;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.ServerInfo;
import org.eclipse.lsp4j.TextEdit;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

import de.pr1meti.ls4pde.server.formatting.AutoFormatter;

public class ProcessingLangServer
        implements LanguageClientAware, WorkspaceService, TextDocumentService, LanguageServer {

    LanguageClient client;

    @Override
    public void connect(LanguageClient client) {
        this.client = client;
    }

    // LanguageServer
    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
        return CompletableFuture.supplyAsync(() -> {
            InitializeResult result = new InitializeResult();

            ServerInfo info = new ServerInfo("J-LS4P", "v0.1.0-alpha.1");

            ServerCapabilities capabilities = new ServerCapabilities();
            capabilities.setDocumentFormattingProvider(true);

            result.setCapabilities(capabilities);
            result.setServerInfo(info);
            return result;
        });
    }

    @Override
    public CompletableFuture<Object> shutdown() {
        return null;
    }

    @Override
    public void exit() {

    }

    @Override
    public TextDocumentService getTextDocumentService() {
        return this;
    }

    @Override
    public WorkspaceService getWorkspaceService() {
        return this;
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {
    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {

    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {

    }

    @Override
    public void didSave(DidSaveTextDocumentParams params) {

    }

    @Override
    public void didChangeConfiguration(DidChangeConfigurationParams params) {

    }

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {

    }

    @Override
    public CompletableFuture<List<? extends TextEdit>> formatting(DocumentFormattingParams params) {
        return CompletableFuture.supplyAsync(() -> {
            TextEdit textEdit = new TextEdit();
            try {

                String fileUri = params.getTextDocument().getUri();
                Path filePath = Path.of(URI.create(fileUri));

                String before = Files.readString(filePath);
                AutoFormatter autoFormatter = new AutoFormatter(params.getOptions().getTabSize());
                String after = autoFormatter.format(before);

                textEdit.setNewText(after);

                Range selectAll = new Range();
                selectAll.setStart(new Position(0, 0));
                selectAll.setEnd(new Position(Integer.MAX_VALUE, Integer.MAX_VALUE));

                textEdit.setRange(selectAll);

            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<TextEdit>(0);
            }

            return Collections.singletonList(textEdit);
        });
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}

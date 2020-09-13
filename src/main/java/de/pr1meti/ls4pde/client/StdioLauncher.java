package de.pr1meti.ls4pde.client;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;

import de.pr1meti.ls4pde.server.ProcessingLangServer;

/**
 * Launches the Processing Language Server. stdiolauncher
 */
public class StdioLauncher {

    public static String rootPath = Paths.get(
            System.getProperty("user.home"),
            "projects",
            "vscode-ls4pde",
            "server_logs"
    ).toString();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        if (args.length >= 1) {
            rootPath = args[0];
        }
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(Level.OFF);
        startServer(System.in, System.out);
    }

    private static void startServer(InputStream in, OutputStream out) throws ExecutionException, InterruptedException {

        ProcessingLangServer server = new ProcessingLangServer();
        Launcher<LanguageClient> launcher;
        try {
            String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss.SSS")) + "-ls4pde-debug.log";
            PrintWriter writer = new PrintWriter(Paths.get(rootPath, fileName).toString());
            launcher = LSPLauncher.createServerLauncher(server, in, out, true, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            launcher = LSPLauncher.createServerLauncher(server, in, out);
        }

        LanguageClient client = launcher.getRemoteProxy();

        server.connect(client);

        Future<Void> startListening = launcher.startListening();

        startListening.get();
    }

}

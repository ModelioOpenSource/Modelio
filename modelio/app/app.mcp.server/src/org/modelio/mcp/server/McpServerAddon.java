/*
 * Copyright 2026 MEDA
 *
 * This file is part of Modelio.
 *
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package org.modelio.mcp.server;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.eclipse.e4.core.contexts.IEclipseContext;

/**
 * E4 addon starting the MCP server with the Modelio application.
 * <p>
 * The port defaults to {@value #DEFAULT_PORT} and may be changed with the <code>modelio.mcp.port</code>
 * system property or the <code>MODELIO_MCP_PORT</code> environment variable. A port of 0 or less disables the server.
 */
public class McpServerAddon {
    public static final int DEFAULT_PORT = 7341;

    private McpHttpServer server;

    @PostConstruct
    void start(IEclipseContext context) {
        int port = configuredPort();
        if (port <= 0) {
            McpPlugin.LOG.info("Modelio MCP server disabled.");
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        McpProtocol protocol = new McpProtocol(new ModelQueries(mapper, context));
        this.server = new McpHttpServer(port, protocol);
        try {
            this.server.start();
            McpPlugin.LOG.info("Modelio MCP server listening on http://127.0.0.1:%d/mcp", port);
        } catch (IOException e) {
            McpPlugin.LOG.warning("Modelio MCP server could not listen on port %d (another Modelio running?): %s", port, e.getMessage());
            this.server = null;
        }
    }

    @PreDestroy
    void stop() {
        if (this.server != null) {
            this.server.stop();
            this.server = null;
        }
    }

    private static int configuredPort() {
        String v = System.getProperty("modelio.mcp.port");
        if (v == null || v.isBlank()) {
            v = System.getenv("MODELIO_MCP_PORT");
        }
        if (v == null || v.isBlank()) {
            return DEFAULT_PORT;
        }
        try {
            return Integer.parseInt(v.trim());
        } catch (NumberFormatException e) {
            return DEFAULT_PORT;
        }
    }

}

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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Minimal HTTP/1.1 server implementing the MCP "Streamable HTTP" transport in its
 * simplest form: every JSON-RPC POST gets a single <code>application/json</code> answer.
 * <p>
 * Binds only to the loopback interface and rejects cross-origin browser requests.
 */
public class McpHttpServer {
    private static final int MAX_BODY = 8 * 1024 * 1024;

    private final int port;

    private final McpProtocol protocol;

    private ServerSocket serverSocket;

    private ExecutorService workers;

    private Thread acceptThread;

    public McpHttpServer(int port, McpProtocol protocol) {
        this.port = port;
        this.protocol = protocol;
    }

    public void start() throws IOException {
        this.serverSocket = new ServerSocket();
        this.serverSocket.setReuseAddress(true);
        this.serverSocket.bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), this.port));
        this.workers = Executors.newFixedThreadPool(4, r -> {
            Thread t = new Thread(r, "Modelio MCP worker");
            t.setDaemon(true);
            return t;
        });
        this.acceptThread = new Thread(this::acceptLoop, "Modelio MCP server");
        this.acceptThread.setDaemon(true);
        this.acceptThread.start();
    }

    public void stop() {
        try {
            if (this.serverSocket != null) {
                this.serverSocket.close();
            }
        } catch (IOException e) {
            // ignore
        }
        if (this.workers != null) {
            this.workers.shutdownNow();
        }
    }

    private void acceptLoop() {
        while (!this.serverSocket.isClosed()) {
            try {
                Socket s = this.serverSocket.accept();
                this.workers.execute(() -> handleConnection(s));
            } catch (SocketException e) {
                return; // server closed
            } catch (IOException e) {
                McpPlugin.LOG.warning(e);
            }
        }
    }

    private void handleConnection(Socket s) {
        try (s) {
            s.setSoTimeout(120_000);
            InputStream in = new BufferedInputStream(s.getInputStream());
            OutputStream out = s.getOutputStream();
            boolean keepAlive = true;
            while (keepAlive) {
                String requestLine = readLine(in);
                if (requestLine == null || requestLine.isEmpty()) {
                    return;
                }
                String[] parts = requestLine.split(" ");
                if (parts.length < 2) {
                    writeResponse(out, 400, "text/plain", "Bad request", false);
                    return;
                }
                String method = parts[0];
                String path = parts[1];
                Map<String, String> headers = new HashMap<>();
                String line;
                while ((line = readLine(in)) != null && !line.isEmpty()) {
                    int idx = line.indexOf(':');
                    if (idx > 0) {
                        headers.put(line.substring(0, idx).trim().toLowerCase(Locale.ROOT), line.substring(idx + 1).trim());
                    }
                }
                keepAlive = !"close".equalsIgnoreCase(headers.get("connection"));

                byte[] body = readBody(in, headers);
                if (body == null) {
                    writeResponse(out, 411, "text/plain", "Length required", false);
                    return;
                }

                if (!isAllowedOrigin(headers.get("origin"))) {
                    writeResponse(out, 403, "text/plain", "Forbidden origin", keepAlive);
                    continue;
                }

                int q = path.indexOf('?');
                if (q >= 0) {
                    path = path.substring(0, q);
                }
                if (!path.equals("/mcp") && !path.equals("/mcp/")) {
                    writeResponse(out, 404, "text/plain", "Not found, use /mcp", keepAlive);
                    continue;
                }

                switch (method) {
                case "POST":
                    String answer = this.protocol.handle(new String(body, StandardCharsets.UTF_8));
                    if (answer == null) {
                        writeResponse(out, 202, null, "", keepAlive);
                    } else {
                        writeResponse(out, 200, "application/json", answer, keepAlive);
                    }
                    break;
                case "DELETE":
                    writeResponse(out, 200, null, "", keepAlive);
                    break;
                default:
                    // No server initiated SSE stream.
                    writeResponse(out, 405, "text/plain", "Method not allowed", keepAlive);
                    break;
                }
            }
        } catch (IOException e) {
            // connection dropped
        } catch (RuntimeException e) {
            McpPlugin.LOG.error(e);
        }
    }

    private static boolean isAllowedOrigin(String origin) {
        if (origin == null || origin.isEmpty() || origin.equals("null")) {
            return true;
        }
        String o = origin.toLowerCase(Locale.ROOT);
        return o.matches("https?://(localhost|127\\.0\\.0\\.1|\\[::1\\])(:\\d+)?/?");
    }

    private static byte[] readBody(InputStream in, Map<String, String> headers) throws IOException {
        String te = headers.get("transfer-encoding");
        if (te != null && te.toLowerCase(Locale.ROOT).contains("chunked")) {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            while (true) {
                String sizeLine = readLine(in);
                if (sizeLine == null) {
                    throw new IOException("Unexpected end of chunked body");
                }
                int semi = sizeLine.indexOf(';');
                int size = Integer.parseInt((semi >= 0 ? sizeLine.substring(0, semi) : sizeLine).trim(), 16);
                if (size == 0) {
                    String l;
                    while ((l = readLine(in)) != null && !l.isEmpty()) {
                        // skip trailers
                    }
                    return buf.toByteArray();
                }
                if (buf.size() + size > MAX_BODY) {
                    throw new IOException("Body too large");
                }
                buf.write(in.readNBytes(size));
                readLine(in);
            }
        }
        String cl = headers.get("content-length");
        if (cl == null) {
            return new byte[0];
        }
        int len = Integer.parseInt(cl.trim());
        if (len < 0 || len > MAX_BODY) {
            throw new IOException("Invalid content length");
        }
        byte[] body = in.readNBytes(len);
        if (body.length != len) {
            throw new IOException("Unexpected end of body");
        }
        return body;
    }

    private static String readLine(InputStream in) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int c;
        while ((c = in.read()) != -1) {
            if (c == '\n') {
                break;
            }
            if (c != '\r') {
                buf.write(c);
            }
            if (buf.size() > 16384) {
                throw new IOException("Line too long");
            }
        }
        if (c == -1 && buf.size() == 0) {
            return null;
        }
        return buf.toString(StandardCharsets.ISO_8859_1);
    }

    private static void writeResponse(OutputStream out, int status, String contentType, String body, boolean keepAlive) throws IOException {
        byte[] data = body.getBytes(StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ").append(status).append(' ').append(reason(status)).append("\r\n");
        if (contentType != null) {
            sb.append("Content-Type: ").append(contentType).append("; charset=utf-8\r\n");
        }
        sb.append("Content-Length: ").append(data.length).append("\r\n");
        sb.append("Cache-Control: no-store\r\n");
        sb.append("Connection: ").append(keepAlive ? "keep-alive" : "close").append("\r\n");
        sb.append("\r\n");
        out.write(sb.toString().getBytes(StandardCharsets.ISO_8859_1));
        out.write(data);
        out.flush();
    }

    private static String reason(int status) {
        switch (status) {
        case 200:
            return "OK";
        case 202:
            return "Accepted";
        case 400:
            return "Bad Request";
        case 403:
            return "Forbidden";
        case 404:
            return "Not Found";
        case 405:
            return "Method Not Allowed";
        case 411:
            return "Length Required";
        default:
            return "Status";
        }
    }

}

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

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * JSON-RPC 2.0 / MCP message handling: lifecycle, tools/list and tools/call.
 */
public class McpProtocol {
    private static final List<String> SUPPORTED_VERSIONS = List.of("2025-06-18", "2025-03-26", "2024-11-05");

    private static final String SERVER_VERSION = "6.2.0";

    private final ObjectMapper mapper = new ObjectMapper();

    private final ModelQueries queries;

    public McpProtocol(ModelQueries queries) {
        this.queries = queries;
    }

    /**
     * Handle a raw POST body.
     *
     * @param body a JSON-RPC message or batch
     * @return the JSON answer, or <code>null</code> when the message needs no answer (notifications)
     */
    public String handle(String body) {
        JsonNode msg;
        try {
            msg = this.mapper.readTree(body);
        } catch (Exception e) {
            return toJson(error(null, -32700, "Parse error: " + e.getMessage()));
        }
        if (msg == null) {
            return toJson(error(null, -32600, "Empty request"));
        }
        if (msg.isArray()) {
            ArrayNode answers = this.mapper.createArrayNode();
            for (JsonNode m : msg) {
                ObjectNode a = handleMessage(m);
                if (a != null) {
                    answers.add(a);
                }
            }
            return answers.isEmpty() ? null : toJson(answers);
        }
        ObjectNode a = handleMessage(msg);
        return a == null ? null : toJson(a);
    }

    private ObjectNode handleMessage(JsonNode msg) {
        JsonNode id = msg.get("id");
        String method = msg.path("method").asText(null);
        if (method == null) {
            // A response sent by the client, or garbage: nothing to answer.
            return null;
        }
        boolean isNotification = id == null || id.isNull();
        JsonNode params = msg.path("params");
        try {
            ObjectNode result;
            switch (method) {
            case "initialize":
                result = initialize(params);
                break;
            case "ping":
                result = this.mapper.createObjectNode();
                break;
            case "tools/list":
                result = this.mapper.createObjectNode();
                result.set("tools", McpTools.describe(this.mapper));
                break;
            case "tools/call":
                result = callTool(params);
                break;
            case "resources/list":
                result = this.mapper.createObjectNode();
                result.putArray("resources");
                break;
            case "prompts/list":
                result = this.mapper.createObjectNode();
                result.putArray("prompts");
                break;
            default:
                if (method.startsWith("notifications/")) {
                    return null;
                }
                return isNotification ? null : error(id, -32601, "Method not found: " + method);
            }
            if (isNotification) {
                return null;
            }
            ObjectNode answer = this.mapper.createObjectNode();
            answer.put("jsonrpc", "2.0");
            answer.set("id", id);
            answer.set("result", result);
            return answer;
        } catch (RuntimeException e) {
            McpPlugin.LOG.error(e);
            return isNotification ? null : error(id, -32603, "Internal error: " + e);
        }
    }

    private ObjectNode initialize(JsonNode params) {
        String requested = params.path("protocolVersion").asText("");
        ObjectNode result = this.mapper.createObjectNode();
        result.put("protocolVersion", SUPPORTED_VERSIONS.contains(requested) ? requested : SUPPORTED_VERSIONS.get(0));
        ObjectNode caps = result.putObject("capabilities");
        caps.putObject("tools").put("listChanged", false);
        ObjectNode info = result.putObject("serverInfo");
        info.put("name", "modelio");
        info.put("title", "Modelio model server");
        info.put("version", SERVER_VERSION);
        result.put("instructions",
                "Access to the model currently opened in the running Modelio application. "
                        + "Start with get_project_info, then browse with list_children/get_element or search with find_elements. "
                        + "Elements are identified by their Modelio UUID. Use get_diagram_image with a diagram UUID to inspect its current appearance. "
                        + "create_element, update_element and delete_elements modify the model; each call is one Modelio transaction "
                        + "that the user can undo in Modelio (Edit > Undo). Changes are saved when the user saves the project.");
        return result;
    }

    private ObjectNode callTool(JsonNode params) {
        String name = params.path("name").asText("");
        JsonNode args = params.path("arguments");
        if (args.isMissingNode() || args.isNull()) {
            args = this.mapper.createObjectNode();
        }
        ObjectNode result = this.mapper.createObjectNode();
        ArrayNode content = result.putArray("content");
        try {
            if (McpTools.GET_DIAGRAM_IMAGE.equals(name)) {
                content.add(this.queries.getDiagramImage(args));
            } else {
                JsonNode value = this.queries.call(name, args);
                addText(content, this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value));
            }
            result.put("isError", false);
        } catch (ToolException e) {
            addText(content, e.getMessage());
            result.put("isError", true);
        } catch (Exception e) {
            McpPlugin.LOG.error(e);
            addText(content, "Error: " + e);
            result.put("isError", true);
        }
        return result;
    }

    private static void addText(ArrayNode content, String value) {
        ObjectNode text = content.addObject();
        text.put("type", "text");
        text.put("text", value);
    }

    private ObjectNode error(JsonNode id, int code, String message) {
        ObjectNode answer = this.mapper.createObjectNode();
        answer.put("jsonrpc", "2.0");
        if (id == null) {
            answer.putNull("id");
        } else {
            answer.set("id", id);
        }
        ObjectNode err = answer.putObject("error");
        err.put("code", code);
        err.put("message", message);
        return answer;
    }

    private String toJson(JsonNode node) {
        try {
            return this.mapper.writeValueAsString(node);
        } catch (Exception e) {
            return "{\"jsonrpc\":\"2.0\",\"id\":null,\"error\":{\"code\":-32603,\"message\":\"serialization failure\"}}";
        }
    }

    /**
     * Error reported to the MCP client as a tool error (isError=true).
     */
    public static class ToolException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ToolException(String message) {
            super(message);
        }
    }

}

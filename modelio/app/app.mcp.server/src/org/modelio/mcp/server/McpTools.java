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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Declaration of the MCP tools (name, description, JSON input schema).
 */
final class McpTools {
    static final String GET_PROJECT_INFO = "get_project_info";

    static final String LIST_CHILDREN = "list_children";

    static final String GET_ELEMENT = "get_element";

    static final String FIND_ELEMENTS = "find_elements";

    static final String GET_MODEL_TREE = "get_model_tree";

    static final String GET_RELATIONSHIPS = "get_relationships";

    static final String LIST_METACLASSES = "list_metaclasses";

    static final String GET_SELECTION = "get_selection";

    static final String CREATE_ELEMENT = "create_element";

    static final String UPDATE_ELEMENT = "update_element";

    static final String DELETE_ELEMENTS = "delete_elements";

    static final String GET_DIAGRAM_IMAGE = "get_diagram_image";
    static final String GET_DIAGRAM_CONTENTS = "get_diagram_contents";

    static final String ADD_TO_DIAGRAM = "add_to_diagram";

    static final String MOVE_IN_DIAGRAM = "move_in_diagram";

    static final String ALIGN_IN_DIAGRAM = "align_in_diagram";

    static final String REMOVE_FROM_DIAGRAM = "remove_from_diagram";

    private McpTools() {
    }

    static ArrayNode describe(ObjectMapper mapper) {
        ArrayNode tools = mapper.createArrayNode();

        ObjectNode t = tool(tools, GET_PROJECT_INFO,
                "Get the project currently opened in Modelio: name, path, model fragments (with their root elements) and modules. Call this first.");

        t = tool(tools, LIST_CHILDREN,
                "List the direct composition children (owned elements) of a model element. Without 'id', list the root elements of every model fragment.");
        prop(t, "id", "string", "UUID of the parent element. Omit to list fragment roots.");
        prop(t, "offset", "integer", "Index of the first child to return (default 0).");
        prop(t, "limit", "integer", "Maximum number of children to return (default 200).");

        t = tool(tools, GET_ELEMENT,
                "Get the full description of one element: metaclass, owner path, all attribute values, stereotypes, tagged values, notes/documentation and every non-empty reference to other elements.");
        prop(t, "id", "string", "UUID of the element.");
        prop(t, "max_refs", "integer", "Maximum number of referenced elements listed per reference (default 50).");
        required(t, "id");

        t = tool(tools, FIND_ELEMENTS,
                "Search model elements by name and/or metaclass. Name matching is a case-insensitive substring unless 'exact' is true. "
                        + "Metaclass may be simple ('Class', 'BusinessActor') or qualified ('Standard.Class', 'Archimate.BusinessActor'); subclasses are included. "
                        + "By default only the project's own model fragments are searched (not modules/libraries).");
        prop(t, "name", "string", "Text to look for in element names.");
        prop(t, "metaclass", "string", "Metaclass name to restrict the search (default: any element).");
        prop(t, "exact", "boolean", "Require an exact (case-sensitive) name match (default false).");
        prop(t, "stereotype", "string", "Only keep elements carrying a stereotype with this name.");
        prop(t, "fragment", "string", "Only keep elements stored in the model fragment with this id.");
        prop(t, "include_libraries", "boolean", "Also search module and library (RAMC) fragments (default false).");
        prop(t, "limit", "integer", "Maximum number of results (default 100).");

        t = tool(tools, GET_MODEL_TREE,
                "Get an indented outline of the composition tree under an element (or under all fragment roots when 'id' is omitted).");
        prop(t, "id", "string", "UUID of the starting element. Omit to start from fragment roots.");
        prop(t, "depth", "integer", "Maximum depth (default 3).");
        prop(t, "max_nodes", "integer", "Maximum number of nodes in the outline (default 500).");
        prop(t, "include_libraries", "boolean", "When starting from fragment roots, include module and library fragments (default false).");

        t = tool(tools, GET_RELATIONSHIPS,
                "List the relationships (links such as associations, dependencies, ArchiMate relationships, BPMN flows...) connected to an element, with their sources and targets.");
        prop(t, "id", "string", "UUID of the element.");
        required(t, "id");

        t = tool(tools, LIST_METACLASSES,
                "List the metaclasses known by the opened project's metamodel, optionally filtered, with optional instance counts.");
        prop(t, "filter", "string", "Case-insensitive substring of the qualified metaclass name (e.g. 'Archimate.', 'Diagram').");
        prop(t, "with_counts", "boolean", "Count instances (subclasses excluded) in the project's model fragments; can be slow on big models (default false).");

        tool(tools, GET_SELECTION, "Get the elements currently selected in the Modelio user interface (model browser, diagram...).");

        t = tool(tools, CREATE_ELEMENT,
                "Create a model element (or a relationship) in the opened project, in one undoable Modelio transaction. "
                        + "To create a UML association between classes or between an actor and a use case, use 'Standard.Association' with source_id and target_id. "
                        + "Omit owner_id and dependency for UML associations. Set multiplicities with source_multiplicity and target_multiplicity. "
                        + "Pass its returned element ID to add_to_diagram once both participants are shown. "
                        + "For a relationship metaclass (e.g. 'Archimate.Serving', 'Standard.Dependency') give source_id and target_id; its owner defaults to the source. "
                        + "The composition relation from the owner is chosen automatically unless 'dependency' is given. "
                        + "The element is not drawn in any diagram: use add_to_diagram for that.");
        prop(t, "metaclass", "string", "Metaclass of the new element, simple or qualified (e.g. 'BusinessActor', 'Archimate.ApplicationService', 'Standard.Class').");
        prop(t, "owner_id", "string", "UUID of the owner (package, folder, class...). Optional for relationships (defaults to the source).");
        prop(t, "name", "string", "Name of the new element (default: a unique generated name).");
        prop(t, "dependency", "string", "Composition relation of the owner that will hold the element (default: chosen by the metamodel).");
        prop(t, "source_id", "string", "Relationships only: UUID of the source element.");
        prop(t, "target_id", "string", "Relationships only: UUID of the target element.");
        prop(t, "source_multiplicity", "string", "UML association multiplicity at the source participant, e.g. '1'.");
        prop(t, "target_multiplicity", "string", "UML association multiplicity at the target participant, e.g. '1..*'.");
        prop(t, "composition", "boolean", "UML association only. True makes the source the whole (filled diamond) and the target the part. Source multiplicity defaults to '1' for compositions.");
        editProps(t);
        prop(t, "stereotypes", "array", "Stereotypes to apply, by name or 'Module:Name'.");
        required(t, "metaclass");
        writable(t, false);

        t = tool(tools, UPDATE_ELEMENT,
                "Modify an element in one undoable Modelio transaction: rename, set attributes, add/remove stereotypes, set notes and tagged values, "
                        + "add/remove references (non-composition relations such as 'Represented', 'Type', 'Target') and move it under another owner.");
        prop(t, "id", "string", "UUID of the element to modify.");
        prop(t, "name", "string", "New name.");
        editProps(t);
        prop(t, "add_stereotypes", "array", "Stereotypes to apply, by name or 'Module:Name'.");
        prop(t, "remove_stereotypes", "array", "Stereotype names to remove.");
        prop(t, "remove_references", "object", "Map relation name -> UUID or array of UUIDs to remove from that relation.");
        prop(t, "owner_id", "string", "UUID of a new owner to move the element under.");
        required(t, "id");
        writable(t, false);

        t = tool(tools, DELETE_ELEMENTS,
                "Delete elements from the model (undoable in Modelio). Owned elements are deleted with their owner; their graphics are first removed "
                        + "from the other diagrams showing them (e.g. a view's thumbnail in a viewpoint diagram), and relationships pointing to them "
                        + "are cleaned up by Modelio. Fragment roots cannot be deleted.");
        prop(t, "ids", "array", "UUIDs of the elements to delete.");
        required(t, "ids");
        writable(t, true);

        t = tool(tools, GET_DIAGRAM_IMAGE,
                "Render the whole current diagram as a PNG image, including changes made since the last project save. "
                        + "Use this to visually inspect layout, labels and links before or after diagram edits. "
                        + "The tool returns an MCP image. Use get_diagram_contents for element IDs and coordinates.");
        prop(t, "diagram_id", "string", "UUID of the diagram to render.");
        prop(t, "max_size", "integer", "Maximum width and height in pixels, from 256 to 4096 (default 2048). Aspect ratio is preserved.");
        required(t, "diagram_id");

        t = tool(tools, GET_DIAGRAM_CONTENTS,
                "List what a diagram (ArchimateView, ClassDiagram, any AbstractDiagram...) actually draws: its nodes with bounds (diagram coordinates) and parent, and its links. "
                        + "Graphics whose element no longer exists are flagged \"missing\": true (remove them with remove_from_diagram).");
        prop(t, "diagram_id", "string", "UUID of the diagram.");
        required(t, "diagram_id");

        t = tool(tools, ADD_TO_DIAGRAM,
                "Draw (unmask) existing model elements in a diagram, in one undoable Modelio transaction. This is how an element is shown in an ArchimateView "
                        + "or any other diagram; setting model references such as 'Represented' does not draw anything. "
                        + "Elements already drawn are skipped. Coordinates are diagram coordinates (independent of the editor zoom). "
                        + "A node is never dropped inside another one unless 'parent_id' asks for it: an occupied spot is replaced by the next free one to its right. "
                        + "By default the relationships between the drawn elements and those already shown are drawn too; "
                        + "relationships given in element_ids are drawn when both ends are shown.");
        prop(t, "diagram_id", "string", "UUID of the diagram.");
        prop(t, "element_ids", "array", "UUIDs of the elements (or relationships) to draw.");
        prop(t, "x", "integer", "X of the first element (default 50); following elements are placed to its right.");
        prop(t, "y", "integer", "Y of the elements (default: below the current diagram content).");
        prop(t, "width", "integer", "Width to give each drawn node (default: Modelio's default size).");
        prop(t, "height", "integer", "Height to give each drawn node (default: Modelio's default size).");
        prop(t, "parent_id", "string", "UUID of an element already drawn as a container node (Grouping, Location, Node...) to draw inside; x and y are then relative to its top-left corner.");
        prop(t, "with_relationships", "boolean", "Also draw the relationships linking the new elements to elements already shown (default true).");
        required(t, "diagram_id", "element_ids");
        writable(t, false);

        t = tool(tools, MOVE_IN_DIAGRAM,
                "Move and/or resize nodes already drawn in a diagram, in one undoable Modelio transaction. "
                        + "Coordinates are diagram coordinates (independent of the editor zoom); omitted ones keep their current value. Links follow their ends.");
        prop(t, "diagram_id", "string", "UUID of the diagram.");
        prop(t, "moves", "array", "[{\"element_id\": \"<uuid>\", \"x\": 100, \"y\": 50, \"width\": 120, \"height\": 60}] (x, y, width, height and label_width optional). label_width resizes a separate name label, such as an actor label, using the node element_id.");
        objectItems(t, "moves");
        prop(t, "reset_routes", "boolean", "Recalculate bent connector routes attached to the requested nodes after moving or resizing (default false).");
        required(t, "diagram_id", "moves");
        writable(t, false);

        t = tool(tools, ALIGN_IN_DIAGRAM,
                "Align and/or distribute nodes already drawn in a diagram, in one undoable Modelio transaction. "
                        + "'align' lines the nodes up on the anchor node: left/center/right share an X (a column, i.e. vertical alignment), "
                        + "top/middle/bottom share a Y (a row). 'distribute' spaces the nodes along an axis, keeping their current order on that axis. "
                        + "To stack nodes in a vertical column use align=center with distribute=vertical.");
        prop(t, "diagram_id", "string", "UUID of the diagram.");
        prop(t, "element_ids", "array", "UUIDs of the drawn elements to arrange (at least 2).");
        prop(t, "align", "string", "One of left, center, right (same X) or top, middle, bottom (same Y). Optional.");
        prop(t, "distribute", "string", "One of horizontal, vertical. Optional.");
        prop(t, "spacing", "integer", "Gap in pixels between consecutive nodes when distributing (default: keep the first and last node in place and make gaps equal).");
        prop(t, "anchor_id", "string", "UUID of the node the others align on (default: the first of element_ids).");
        prop(t, "reset_routes", "boolean", "Recalculate bent connector routes attached to the requested nodes after alignment (default false).");
        required(t, "diagram_id", "element_ids");
        writable(t, false);

        t = tool(tools, REMOVE_FROM_DIAGRAM,
                "Remove (mask) graphics from a diagram without deleting any model element, in one undoable Modelio transaction. "
                        + "With missing=true, also remove the graphics whose element no longer exists (shown as <<missing>>).");
        prop(t, "diagram_id", "string", "UUID of the diagram.");
        prop(t, "element_ids", "array", "UUIDs of the elements whose graphics are removed from the diagram.");
        prop(t, "missing", "boolean", "Remove every graphic of the diagram that draws a missing element (default false).");
        required(t, "diagram_id");
        writable(t, false);
        return tools;
    }

    private static ObjectNode tool(ArrayNode tools, String name, String description) {
        ObjectNode t = tools.addObject();
        t.put("name", name);
        t.put("description", description);
        ObjectNode schema = t.putObject("inputSchema");
        schema.put("type", "object");
        schema.putObject("properties");
        ObjectNode annotations = t.putObject("annotations");
        annotations.put("readOnlyHint", true);
        annotations.put("openWorldHint", false);
        return t;
    }

    private static void editProps(ObjectNode t) {
        prop(t, "attributes", "object", "Map attribute name -> value (string, number, boolean or enum literal), e.g. {\"Visibility\": \"PRIVATE\", \"IsAbstract\": true}.");
        prop(t, "notes", "array", "Notes to set: [{\"type\": \"description\", \"content\": \"...\", \"module\": \"ModelerModule\"}] (module defaults to ModelerModule).");
        prop(t, "tags", "array", "Tagged values to set: [{\"type\": \"...\", \"value\": \"...\", \"module\": \"ModelerModule\"}].");
        objectItems(t, "notes");
        objectItems(t, "tags");
        prop(t, "add_references", "object", "Map relation name -> UUID or array of UUIDs to add to that relation (e.g. {\"Represented\": [\"<uuid>\"]}).");
    }

    private static void objectItems(ObjectNode tool, String name) {
        ((ObjectNode) tool.get("inputSchema").get("properties").get(name)).putObject("items").put("type", "object");
    }

    private static void writable(ObjectNode tool, boolean destructive) {
        ObjectNode annotations = (ObjectNode) tool.get("annotations");
        annotations.put("readOnlyHint", false);
        annotations.put("destructiveHint", destructive);
        annotations.put("idempotentHint", false);
    }

    private static void prop(ObjectNode tool, String name, String type, String description) {
        ObjectNode p = ((ObjectNode) tool.get("inputSchema").get("properties")).putObject(name);
        p.put("type", type);
        p.put("description", description);
        if ("array".equals(type)) {
            p.putObject("items").put("type", "string");
        }
    }

    private static void required(ObjectNode tool, String... names) {
        ArrayNode req = ((ObjectNode) tool.get("inputSchema")).putArray("required");
        for (String n : names) {
            req.add(n);
        }
    }

}

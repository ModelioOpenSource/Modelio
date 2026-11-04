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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.IModelioServicesRegistry;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.mcp.server.McpProtocol.ToolException;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Diagram contents: list what a diagram shows and unmask (draw) model elements in it.
 * <p>
 * Setting a model reference such as <code>ArchimateView.Represented</code> does not draw anything: the graphics of a
 * diagram live in its own data and are edited through the Modelio diagram API ({@link IDiagramHandle}).
 * Everything runs in the SWT UI thread (the caller ensures it).
 */
class DiagramEdits {
    /** Horizontal step between elements unmasked in one call. */
    private static final int STEP_X = 180;

    private static final int MARGIN = 50;

    /** Gaps smaller than this when distributing (nodes overlapping) are replaced by {@link #DEFAULT_GAP}. */
    private static final int MIN_GAP = 10;

    private static final int DEFAULT_GAP = 40;

    private final ObjectMapper mapper;

    private final IEclipseContext context;

    private final ModelQueries queries;

    DiagramEdits(ObjectMapper mapper, IEclipseContext context, ModelQueries queries) {
        this.mapper = mapper;
        this.context = context;
        this.queries = queries;
    }

    // ------------------------------------------------------------------ tools

    JsonNode getDiagramContents(JsonNode args) {
        AbstractDiagram diagram = requireDiagram(args);
        ObjectNode r = this.mapper.createObjectNode();
        r.set("diagram", this.queries.summary(diagram));
        ArrayNode nodes = r.putArray("nodes");
        ArrayNode links = r.putArray("links");
        try (IDiagramHandle dh = diagramService().getDiagramHandle(diagram)) {
            IDiagramDG root = dh.getDiagramNode();
            Coords coords = new Coords(dh);
            for (IDiagramNode n : root.getNodes()) {
                addNode(nodes, n, null, coords);
            }
            for (IDiagramLink l : root.getLinks()) {
                ObjectNode o = graphic(links, l);
                if (l.getFrom() != null && l.getFrom().getElement() != null) {
                    o.put("from_id", l.getFrom().getElement().getUuid());
                }
                if (l.getTo() != null && l.getTo().getElement() != null) {
                    o.put("to_id", l.getTo().getElement().getUuid());
                }
            }
        } catch (ToolException e) {
            throw e;
        } catch (Exception e) {
            throw new ToolException("Cannot read diagram " + describe(diagram) + ": " + e);
        }
        return r;
    }

    JsonNode addToDiagram(JsonNode args) {
        AbstractDiagram diagram = requireDiagram(args);
        if (!diagram.isModifiable()) {
            throw new ToolException(describe(diagram) + " is not modifiable (read-only fragment or library?).");
        }
        List<MObject> elements = new ArrayList<>();
        for (JsonNode id : args.path("element_ids")) {
            elements.add(this.queries.requireElement(id.asText()));
        }
        if (elements.isEmpty()) {
            throw new ToolException("Missing required argument 'element_ids'.");
        }
        // Nodes first, so that relationships listed with them find both of their ends drawn (stable sort).
        elements.sort(Comparator.comparing((MObject e) -> e.getMClass().isLinkMetaclass()));
        boolean explicitPos = args.has("x") || args.has("y");
        int x0 = args.path("x").asInt(MARGIN);
        int y0 = args.path("y").asInt(MARGIN);
        int width = args.path("width").asInt(-1);
        int height = args.path("height").asInt(-1);
        boolean withRelationships = args.path("with_relationships").asBoolean(true);
        MObject parentEl = args.hasNonNull("parent_id") ? this.queries.requireElement(args.get("parent_id").asText()) : null;

        ICoreSession session = this.queries.requireProject().getSession();
        IDiagramService ds = diagramService();
        ArrayNode added = this.mapper.createArrayNode();
        ArrayNode skipped = this.mapper.createArrayNode();
        ArrayNode relAdded = this.mapper.createArrayNode();
        try (ITransaction t = session.getTransactionSupport().createTransaction("MCP: add to diagram " + diagram.getName())) {
            try (IDiagramHandle dh = ds.getDiagramHandle(diagram)) {
                Coords coords = new Coords(dh);
                // Coordinates are diagram coordinates; inside a container they are relative to its top-left corner.
                IDiagramNode parentNode = null;
                int ox = 0;
                int oy = 0;
                if (parentEl != null) {
                    parentNode = mainNode(dh, parentEl);
                    if (parentNode == null) {
                        throw new ToolException("The container " + describe(parentEl) + " is not drawn as a node in " + describe(diagram) + ".");
                    }
                    Rectangle pb = coords.toModel(parentNode.getBounds());
                    ox = pb.x;
                    oy = pb.y;
                    if (!explicitPos) {
                        x0 = 20;
                        y0 = 30;
                    }
                } else if (!explicitPos) {
                    y0 = belowContent(dh.getDiagramNode(), coords);
                }
                List<Rectangle> occupied = topLevelBounds(dh.getDiagramNode(), coords);
                Set<MObject> nodesAdded = new LinkedHashSet<>();
                int x = x0;
                for (MObject el : elements) {
                    if (!dh.getDiagramGraphics(el).isEmpty()) {
                        skipped.add(reason(el, "already shown in the diagram"));
                        continue;
                    }
                    boolean link = el.getMClass().isLinkMetaclass();
                    int px = ox + x;
                    int py = oy + y0;
                    String shifted = null;
                    if (!link && parentNode == null) {
                        // Dropping on an existing node would nest the new one inside it: take the next free spot.
                        int fx = freeX(occupied, px, py);
                        if (fx != px) {
                            shifted = "moved right from x=" + px + " to avoid dropping it inside another node";
                            px = fx;
                        }
                    }
                    Rectangle drop = coords.toViewer(new Rectangle(px, py, 1, 1));
                    List<IDiagramGraphic> gs = dh.unmask(el, drop.x, drop.y);
                    if (gs == null || gs.isEmpty()) {
                        skipped.add(reason(el, link ? "both ends must already be shown in the diagram" : "this diagram cannot show it"));
                        continue;
                    }
                    ObjectNode o = this.queries.summary(el);
                    if (link) {
                        o.put("link", true);
                    } else {
                        nodesAdded.add(el);
                        IDiagramNode n = mainNode(dh, el);
                        if (n != null) {
                            if (width > 0 || height > 0) {
                                Rectangle cur = coords.toModel(n.getBounds());
                                Rectangle v = coords.toViewer(new Rectangle(cur.x, cur.y, width > 0 ? width : cur.width, height > 0 ? height : cur.height));
                                n.setSize(v.width, v.height);
                            }
                            Rectangle b = coords.toModel(n.getBounds());
                            putBounds(o, b);
                            if (n.getParent() instanceof IDiagramNode p && p != dh.getDiagramNode() && !(p instanceof IDiagramDG)
                                    && p.getElement() != null) {
                                o.put("parent_id", p.getElement().getUuid());
                            }
                            if (parentNode == null) {
                                occupied.add(b);
                            }
                        }
                        if (shifted != null) {
                            o.put("note", shifted);
                        }
                        x = px - ox + Math.max(STEP_X, (n != null ? coords.toModel(n.getBounds()).width : 0) + MIN_GAP * 2);
                    }
                    added.add(o);
                }
                if (withRelationships) {
                    for (MObject rel : relationshipsToDraw(dh, nodesAdded)) {
                        List<IDiagramGraphic> gs = dh.unmask(rel, drop0(coords).x, drop0(coords).y);
                        if (gs != null && !gs.isEmpty()) {
                            relAdded.add(this.queries.summary(rel));
                        }
                    }
                }
                dh.save();
            }
            t.commit();
        } catch (ToolException e) {
            throw e;
        } catch (Exception e) {
            throw new ToolException("Cannot add to diagram " + describe(diagram) + ": " + e);
        }
        ObjectNode r = this.mapper.createObjectNode();
        r.set("diagram", this.queries.summary(diagram));
        r.put("added_count", added.size());
        r.set("added", added);
        r.set("skipped", skipped);
        r.put("relationships_added_count", relAdded.size());
        r.set("relationships_added", relAdded);
        return r;
    }

    /**
     * Relationships of the given elements that are not drawn yet but whose two ends are shown in the diagram, so
     * drawing an element also draws its links to what the diagram already shows.
     */
    private static Collection<MObject> relationshipsToDraw(IDiagramHandle dh, Collection<MObject> elements) {
        Set<MObject> result = new LinkedHashSet<>();
        for (MObject el : elements) {
            for (MDependency dep : el.getMClass().getDependencies(true)) {
                if (!dep.getTarget().isLinkMetaclass()) {
                    continue;
                }
                List<MObject> links;
                try {
                    links = el.mGet(dep);
                } catch (RuntimeException e) {
                    continue;
                }
                for (MObject link : links) {
                    if (result.contains(link) || !dh.getDiagramGraphics(link).isEmpty()) {
                        continue;
                    }
                    if (endsShown(dh, link)) {
                        result.add(link);
                    }
                }
            }
        }
        return result;
    }

    private static boolean endsShown(IDiagramHandle dh, MObject link) {
        List<MObject> ends = new ArrayList<>();
        for (MDependency d : link.getMClass().getLinkMetaclassSources()) {
            ends.addAll(link.mGet(d));
        }
        for (MDependency d : link.getMClass().getLinkMetaclassTargets()) {
            ends.addAll(link.mGet(d));
        }
        if (ends.isEmpty()) {
            return false;
        }
        for (MObject end : ends) {
            if (dh.getDiagramGraphics(end).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static Rectangle drop0(Coords coords) {
        return coords.toViewer(new Rectangle(0, 0, 1, 1));
    }

    /** First X at or right of <code>x</code> where (x, y) is not inside one of the occupied rectangles. */
    private static int freeX(List<Rectangle> occupied, int x, int y) {
        boolean moved = true;
        while (moved) {
            moved = false;
            for (Rectangle r : occupied) {
                if (x >= r.x - MIN_GAP && x <= r.right() + MIN_GAP && y >= r.y - MIN_GAP && y <= r.bottom() + MIN_GAP) {
                    x = r.right() + MIN_GAP * 2;
                    moved = true;
                }
            }
        }
        return x;
    }

    private static List<Rectangle> topLevelBounds(IDiagramDG root, Coords coords) {
        List<Rectangle> l = new ArrayList<>();
        for (IDiagramNode n : root.getNodes()) {
            Rectangle b = n.getBounds();
            if (b != null) {
                l.add(coords.toModel(b));
            }
        }
        return l;
    }

    private static void putBounds(ObjectNode o, Rectangle b) {
        o.put("x", b.x);
        o.put("y", b.y);
        o.put("width", b.width);
        o.put("height", b.height);
    }

    JsonNode moveInDiagram(JsonNode args) {
        AbstractDiagram diagram = requireModifiableDiagram(args);
        JsonNode moves = args.path("moves");
        if (!moves.isArray() || moves.isEmpty()) {
            throw new ToolException("Missing required argument 'moves'.");
        }
        Map<MObject, JsonNode> requested = new LinkedHashMap<>();
        for (JsonNode m : moves) {
            requested.put(this.queries.requireElement(ModelQueries.requiredText(m, "element_id")), m);
        }
        return arrange(diagram, "move in", requested.keySet(), (nodes, current) -> {
            Map<IDiagramNode, Rectangle> targets = new LinkedHashMap<>();
            for (Map.Entry<MObject, IDiagramNode> e : nodes.entrySet()) {
                JsonNode m = requested.get(e.getKey());
                Rectangle b = current.get(e.getValue());
                targets.put(e.getValue(), new Rectangle(m.path("x").asInt(b.x), m.path("y").asInt(b.y),
                        m.path("width").asInt(b.width), m.path("height").asInt(b.height)));
            }
            return targets;
        });
    }

    JsonNode alignInDiagram(JsonNode args) {
        AbstractDiagram diagram = requireModifiableDiagram(args);
        List<MObject> elements = new ArrayList<>();
        for (JsonNode id : args.path("element_ids")) {
            elements.add(this.queries.requireElement(id.asText()));
        }
        if (elements.size() < 2) {
            throw new ToolException("'element_ids' needs at least 2 elements.");
        }
        String align = args.path("align").asText("").toLowerCase();
        String distribute = args.path("distribute").asText("").toLowerCase();
        if (!align.isEmpty() && !List.of("left", "center", "right", "top", "middle", "bottom").contains(align)) {
            throw new ToolException("Invalid 'align': " + align + " (expected left, center, right, top, middle or bottom).");
        }
        if (!distribute.isEmpty() && !List.of("horizontal", "vertical").contains(distribute)) {
            throw new ToolException("Invalid 'distribute': " + distribute + " (expected horizontal or vertical).");
        }
        if (align.isEmpty() && distribute.isEmpty()) {
            throw new ToolException("Give 'align' and/or 'distribute'.");
        }
        MObject anchorEl = args.hasNonNull("anchor_id") ? this.queries.requireElement(args.get("anchor_id").asText()) : elements.get(0);
        if (!elements.contains(anchorEl)) {
            elements.add(0, anchorEl);
        }
        Integer spacing = args.hasNonNull("spacing") ? args.get("spacing").asInt() : null;

        return arrange(diagram, "align in", elements, (nodes, current) -> {
            Map<IDiagramNode, Rectangle> targets = new LinkedHashMap<>();
            for (IDiagramNode n : nodes.values()) {
                targets.put(n, current.get(n).getCopy());
            }
            if (!nodes.containsKey(anchorEl)) {
                throw new ToolException("The anchor " + describe(anchorEl) + " is not drawn as a node in the diagram.");
            }
            Rectangle anchor = targets.get(nodes.get(anchorEl));
            for (Rectangle r : targets.values()) {
                switch (align) {
                case "left" -> r.x = anchor.x;
                case "center" -> r.x = anchor.x + (anchor.width - r.width) / 2;
                case "right" -> r.x = anchor.right() - r.width;
                case "top" -> r.y = anchor.y;
                case "middle" -> r.y = anchor.y + (anchor.height - r.height) / 2;
                case "bottom" -> r.y = anchor.bottom() - r.height;
                default -> {
                    // no alignment requested
                }
                }
            }
            if (!distribute.isEmpty()) {
                distribute(new ArrayList<>(targets.values()), "vertical".equals(distribute), spacing);
            }
            return targets;
        });
    }

    JsonNode removeFromDiagram(JsonNode args) {
        AbstractDiagram diagram = requireModifiableDiagram(args);
        List<MObject> elements = new ArrayList<>();
        for (JsonNode id : args.path("element_ids")) {
            elements.add(this.queries.requireElement(id.asText()));
        }
        boolean missing = args.path("missing").asBoolean(false);
        if (elements.isEmpty() && !missing) {
            throw new ToolException("Give 'element_ids' and/or 'missing': true.");
        }
        ICoreSession session = this.queries.requireProject().getSession();
        ArrayNode removed = this.mapper.createArrayNode();
        ArrayNode skipped = this.mapper.createArrayNode();
        try (ITransaction t = session.getTransactionSupport().createTransaction("MCP: remove from diagram " + diagram.getName())) {
            try (IDiagramHandle dh = diagramService().getDiagramHandle(diagram)) {
                for (MObject el : elements) {
                    List<IDiagramGraphic> gs = new ArrayList<>(dh.getDiagramGraphics(el));
                    if (gs.isEmpty()) {
                        skipped.add(reason(el, "not shown in the diagram"));
                        continue;
                    }
                    for (IDiagramGraphic g : gs) {
                        dh.mask(g);
                    }
                    removed.add(this.queries.summary(el));
                }
                if (missing) {
                    List<IDiagramGraphic> ghosts = new ArrayList<>();
                    collectMissing(dh.getDiagramNode(), ghosts);
                    for (IDiagramGraphic g : ghosts) {
                        MRef ref = missingRef(g);
                        // Modelio's delete edit policies refuse graphics without a represented element (unless they
                        // are ghost edit parts, which e.g. diagram thumbnails are not): remove the graphic model itself.
                        gmModel(g).delete();
                        ObjectNode o = removed.addObject();
                        o.put("id", ref.uuid);
                        o.put("name", ref.name);
                        o.put("metaclass", ref.mc);
                        o.put("missing", true);
                    }
                }
                dh.save();
            }
            t.commit();
        } catch (ToolException e) {
            throw e;
        } catch (Exception e) {
            throw new ToolException("Cannot remove from diagram " + describe(diagram) + ": " + e);
        }
        ObjectNode r = this.mapper.createObjectNode();
        r.set("diagram", this.queries.summary(diagram));
        r.put("removed_count", removed.size());
        r.set("removed", removed);
        r.set("skipped", skipped);
        return r;
    }

    /**
     * Remove the graphics of elements about to be deleted from the other diagrams showing them, so the deletion does
     * not leave "missing" references behind (e.g. the thumbnail of a deleted view in a viewpoint diagram).
     * Diagrams that are deleted too are left alone.
     */
    void unmaskFromOtherDiagrams(Collection<MObject> toDelete) {
        Set<MObject> deleted = new LinkedHashSet<>();
        for (MObject o : toDelete) {
            collectSubtree(o, deleted);
        }
        Map<AbstractDiagram, List<MObject>> byDiagram = new LinkedHashMap<>();
        for (MObject o : deleted) {
            if (o instanceof Element e) {
                for (AbstractDiagram d : e.getDiagramElement()) {
                    if (!deleted.contains(d) && d.isValid() && d.isModifiable()) {
                        byDiagram.computeIfAbsent(d, k -> new ArrayList<>()).add(o);
                    }
                }
            }
        }
        if (byDiagram.isEmpty()) {
            return;
        }
        ICoreSession session = this.queries.requireProject().getSession();
        try (ITransaction t = session.getTransactionSupport().createTransaction("MCP: unmask elements before deletion")) {
            for (Map.Entry<AbstractDiagram, List<MObject>> e : byDiagram.entrySet()) {
                try (IDiagramHandle dh = diagramService().getDiagramHandle(e.getKey())) {
                    for (MObject o : e.getValue()) {
                        for (IDiagramGraphic g : new ArrayList<>(dh.getDiagramGraphics(o))) {
                            dh.mask(g);
                        }
                    }
                    dh.save();
                }
            }
            t.commit();
        } catch (Exception e) {
            // Best effort: the deletion itself must still happen; remove_from_diagram with missing=true cleans up.
            if (McpPlugin.LOG != null) {
                McpPlugin.LOG.warning("MCP: cannot unmask elements before deletion: " + e);
            }
        }
    }

    private static void collectSubtree(MObject o, Set<MObject> out) {
        if (out.add(o)) {
            for (MObject c : o.getCompositionChildren()) {
                collectSubtree(c, out);
            }
        }
    }

    /** Outermost nodes and links drawing a missing element (nodes nested in them go with them). */
    private static void collectMissing(IDiagramNode node, List<IDiagramGraphic> out) {
        for (IDiagramNode n : node.getNodes()) {
            if (missingRef(n) != null) {
                out.add(n);
            } else {
                collectMissing(n, out);
            }
        }
        if (node instanceof IDiagramDG root) {
            for (IDiagramLink l : root.getLinks()) {
                if (missingRef(l) != null) {
                    out.add(l);
                }
            }
        }
    }

    private static GmModel gmModel(IDiagramGraphic g) {
        Object model = null;
        if (g instanceof DiagramNode n) {
            model = n.getModel();
        } else if (g instanceof DiagramLink l) {
            model = l.getModel();
        }
        return model instanceof GmModel gm ? gm : null;
    }

    /** The reference of the element a graphic draws when that element no longer exists, else <code>null</code>. */
    private static MRef missingRef(IDiagramGraphic g) {
        GmModel gm = gmModel(g);
        if (gm != null) {
            MRef ref = gm.getRepresentedRef();
            MObject el = gm.getRepresentedElement();
            if (ref != null && (el == null || !el.isValid())) {
                return ref;
            }
        }
        return null;
    }

    // ------------------------------------------------------------------ helpers

    /** Spread the rectangles along one axis, keeping their current order on it. */
    private static void distribute(List<Rectangle> rects, boolean vertical, Integer spacing) {
        rects.sort(Comparator.comparingInt(r -> vertical ? r.y : r.x));
        int count = rects.size();
        int start = vertical ? rects.get(0).y : rects.get(0).x;
        int gap;
        if (spacing != null) {
            gap = spacing;
        } else {
            Rectangle last = rects.get(count - 1);
            int span = (vertical ? last.bottom() : last.right()) - start;
            int used = rects.stream().mapToInt(r -> vertical ? r.height : r.width).sum();
            gap = (span - used) / (count - 1);
            if (gap < MIN_GAP) {
                gap = DEFAULT_GAP;
            }
        }
        int pos = start;
        for (Rectangle r : rects) {
            if (vertical) {
                r.y = pos;
                pos += r.height + gap;
            } else {
                r.x = pos;
                pos += r.width + gap;
            }
        }
    }

    /**
     * Find the top level node drawing each element, let <code>layout</code> compute their new bounds, apply them in one
     * transaction and report the resulting bounds.
     */
    private JsonNode arrange(AbstractDiagram diagram, String verb, Collection<MObject> elements,
            BiFunction<Map<MObject, IDiagramNode>, Map<IDiagramNode, Rectangle>, Map<IDiagramNode, Rectangle>> layout) {
        ICoreSession session = this.queries.requireProject().getSession();
        IDiagramService ds = diagramService();
        ArrayNode moved = this.mapper.createArrayNode();
        ArrayNode skipped = this.mapper.createArrayNode();
        try (ITransaction t = session.getTransactionSupport().createTransaction("MCP: " + verb + " diagram " + diagram.getName())) {
            try (IDiagramHandle dh = ds.getDiagramHandle(diagram)) {
                Coords coords = new Coords(dh);
                Map<MObject, IDiagramNode> nodes = new LinkedHashMap<>();
                Map<IDiagramNode, Rectangle> current = new LinkedHashMap<>();
                for (MObject el : elements) {
                    IDiagramNode n = mainNode(dh, el);
                    if (n == null) {
                        skipped.add(reason(el, "not drawn as a node in the diagram"));
                    } else {
                        nodes.put(el, n);
                        current.put(n, coords.toModel(n.getBounds()));
                    }
                }
                if (nodes.isEmpty()) {
                    throw new ToolException("None of the given elements is drawn as a node in " + describe(diagram) + ".");
                }
                // Layouts work in diagram coordinates, independent of the zoom and scrolling of an opened editor.
                Map<IDiagramNode, Rectangle> targets = layout.apply(nodes, current);
                for (Map.Entry<MObject, IDiagramNode> e : nodes.entrySet()) {
                    IDiagramNode n = e.getValue();
                    Rectangle before = n.getBounds().getCopy();
                    Rectangle target = targets.get(n);
                    Rectangle vt = coords.toViewer(target);
                    Map<IDiagramNode, Rectangle> satellites = new LinkedHashMap<>();
                    for (IDiagramNode s : n.getNodes(IDiagramNode.Role.SATELLITE)) {
                        if (s.getBounds() != null) {
                            satellites.put(s, s.getBounds().getCopy());
                        }
                    }
                    Rectangle cur = current.get(n);
                    if (target.width != cur.width || target.height != cur.height) {
                        n.setSize(vt.width, vt.height);
                    }
                    n.setLocation(vt.x, vt.y);
                    Rectangle afterViewer = n.getBounds();
                    // Make satellite labels (e.g. the name under an icon) follow their node when the move left them behind.
                    int dx = afterViewer.x - before.x;
                    int dy = afterViewer.y - before.y;
                    if (dx != 0 || dy != 0) {
                        for (Map.Entry<IDiagramNode, Rectangle> s : satellites.entrySet()) {
                            if (s.getKey().getBounds().getTopLeft().equals(s.getValue().getTopLeft())) {
                                s.getKey().setLocation(s.getValue().x + dx, s.getValue().y + dy);
                            }
                        }
                    }
                    Rectangle after = coords.toModel(afterViewer);
                    ObjectNode o = this.queries.summary(e.getKey());
                    putBounds(o, after);
                    if (!close(after, target)) {
                        o.put("note", "Modelio adapted the requested bounds " + target.x + "," + target.y + " " + target.width + "x" + target.height);
                    }
                    moved.add(o);
                }
                dh.save();
            }
            t.commit();
        } catch (ToolException e) {
            throw e;
        } catch (Exception e) {
            throw new ToolException("Cannot " + verb + " diagram " + describe(diagram) + ": " + e);
        }
        ObjectNode r = this.mapper.createObjectNode();
        r.set("diagram", this.queries.summary(diagram));
        r.set("nodes", moved);
        r.set("skipped", skipped);
        return r;
    }

    /** The node drawing the element, preferring the outermost one (not a label or port). */
    private static IDiagramNode mainNode(IDiagramHandle dh, MObject el) {
        IDiagramNode best = null;
        for (IDiagramGraphic g : dh.getDiagramGraphics(el)) {
            if (g instanceof IDiagramNode n && n.getBounds() != null) {
                if (best == null || !(n.getParent() instanceof IDiagramNode p && el.equals(p.getElement()))) {
                    best = n;
                }
            }
        }
        return best;
    }

    private AbstractDiagram requireModifiableDiagram(JsonNode args) {
        AbstractDiagram diagram = requireDiagram(args);
        if (!diagram.isModifiable()) {
            throw new ToolException(describe(diagram) + " is not modifiable (read-only fragment or library?).");
        }
        return diagram;
    }

    private AbstractDiagram requireDiagram(JsonNode args) {
        MObject obj = this.queries.requireElement(ModelQueries.requiredText(args, "diagram_id"));
        if (!(obj instanceof AbstractDiagram)) {
            throw new ToolException(describe(obj) + " is not a diagram (use find_elements with metaclass 'AbstractDiagram').");
        }
        return (AbstractDiagram) obj;
    }

    private IDiagramService diagramService() {
        IModelioServices services = null;
        IModuleContext mc = this.context.get(IModuleContext.class);
        if (mc != null) {
            services = mc.getModelioServices();
        }
        if (services == null && this.context.get(IModelioServicesRegistry.class) instanceof IModelioServices s) {
            services = s;
        }
        IDiagramService ds = services != null ? services.getDiagramService() : null;
        if (ds == null) {
            throw new ToolException("The Modelio diagram service is not available (is a project opened?).");
        }
        return ds;
    }

    /** Y coordinate (diagram coordinates) just below the top level nodes of the diagram. */
    private static int belowContent(IDiagramDG root, Coords coords) {
        int bottom = 0;
        for (Rectangle b : topLevelBounds(root, coords)) {
            bottom = Math.max(bottom, b.bottom());
        }
        return bottom == 0 ? MARGIN : bottom + MARGIN;
    }

    /** Equal within one pixel: zoom conversions may round. */
    private static boolean close(Rectangle a, Rectangle b) {
        return Math.abs(a.x - b.x) <= 1 && Math.abs(a.y - b.y) <= 1 && Math.abs(a.width - b.width) <= 1 && Math.abs(a.height - b.height) <= 1;
    }

    private void addNode(ArrayNode out, IDiagramNode n, String parentId, Coords coords) {
        ObjectNode o = graphic(out, n);
        Rectangle b = n.getBounds();
        if (b != null) {
            putBounds(o, coords.toModel(b));
        }
        if (parentId != null) {
            o.put("parent_id", parentId);
        }
        String id = n.getElement() != null ? n.getElement().getUuid() : parentId;
        for (IDiagramNode c : n.getNodes()) {
            addNode(out, c, id, coords);
        }
    }

    /**
     * Converts between diagram coordinates (those stored in the diagram, the same whatever the zoom) and the viewer
     * coordinates used by the Modelio diagram API, which follow the zoom and scrolling of the diagram editor when the
     * diagram is opened. Without an opened editor both are the same.
     */
    private static final class Coords {
        private final IFigure content;

        Coords(IDiagramHandle dh) {
            IFigure f = null;
            try {
                if (dh instanceof DiagramHandle h && dh.getDiagramNode() instanceof DiagramNode root) {
                    GraphicalEditPart p = h.getEditPart(root.getModel());
                    if (p != null) {
                        f = p.getFigure();
                    }
                }
            } catch (RuntimeException e) {
                f = null;
            }
            this.content = f;
        }

        Rectangle toModel(Rectangle viewer) {
            if (viewer == null) {
                return null;
            }
            if (this.content == null) {
                return viewer.getCopy();
            }
            PrecisionRectangle r = new PrecisionRectangle(viewer);
            this.content.translateToRelative(r);
            return round(r);
        }

        Rectangle toViewer(Rectangle model) {
            if (this.content == null) {
                return model.getCopy();
            }
            PrecisionRectangle r = new PrecisionRectangle(model);
            this.content.translateToAbsolute(r);
            return round(r);
        }

        private static Rectangle round(PrecisionRectangle r) {
            return new Rectangle((int) Math.round(r.preciseX()), (int) Math.round(r.preciseY()),
                    (int) Math.round(r.preciseWidth()), (int) Math.round(r.preciseHeight()));
        }
    }

    private ObjectNode graphic(ArrayNode out, IDiagramGraphic g) {
        MRef missing = missingRef(g);
        if (missing != null) {
            // The drawn element no longer exists (deleted, or in a fragment that is not loaded).
            ObjectNode o = this.mapper.createObjectNode();
            o.put("id", missing.uuid);
            o.put("name", missing.name);
            o.put("metaclass", missing.mc);
            o.put("missing", true);
            out.add(o);
            return o;
        }
        MObject el = g.getElement();
        ObjectNode o = el != null ? this.queries.summary(el) : this.mapper.createObjectNode();
        if (el == null) {
            o.put("name", g.getName());
            o.put("ghost", true);
        }
        out.add(o);
        return o;
    }

    private ObjectNode reason(MObject el, String why) {
        ObjectNode o = this.queries.summary(el);
        o.put("reason", why);
        return o;
    }

    private static String describe(MObject o) {
        return "'" + o.getName() + "' [" + o.getMClass().getName() + "]";
    }

}

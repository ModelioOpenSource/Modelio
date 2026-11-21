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
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPart;
import org.modelio.gproject.core.IGProject;
import org.modelio.mcp.server.McpProtocol.ToolException;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Queries on the model of the project currently opened in Modelio. Modifications are delegated to {@link ModelEdits}.
 * <p>
 * All model accesses are run in the SWT UI thread, like the rest of Modelio does.
 */
public class ModelQueries {
    private static final long TIMEOUT_SECONDS = 60;

    private static final int MAX_STRING = 4000;

    /** Attributes that only hold diagram graphic data: useless and huge. */
    private static final Set<String> SKIPPED_ATTRIBUTES = Set.of("UiData", "PreviewData", "JsStructure", "UiDataVersion");

    /** Dependencies already reported in a dedicated section of get_element. */
    private static final Set<String> SKIPPED_DEPENDENCIES = Set.of("Descriptor", "Tag", "Extension");

    private final ObjectMapper mapper;

    private final IEclipseContext context;

    /** Elements already returned to the client, by UUID: allows fast lookup without knowing the metaclass. */
    private final Map<String, MObject> knownElements = Collections.synchronizedMap(new LinkedHashMap<>(1024, 0.75f, true) {
        private static final long serialVersionUID = 1L;

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, MObject> eldest) {
            return size() > 20000;
        }
    });

    private IGProject cachedProject;

    private final ModelEdits edits;

    private final DiagramEdits diagrams;

    DiagramEdits diagrams() {
        return this.diagrams;
    }

    public ModelQueries(ObjectMapper mapper, IEclipseContext context) {
        this.mapper = mapper;
        this.context = context;
        this.edits = new ModelEdits(mapper, context, this);
        this.diagrams = new DiagramEdits(mapper, context, this);
    }

    /**
     * Run a tool.
     */
    public JsonNode call(String tool, JsonNode args) {
        return runInUi(() -> {
            switch (tool) {
            case McpTools.GET_PROJECT_INFO:
                return getProjectInfo();
            case McpTools.LIST_CHILDREN:
                return listChildren(args);
            case McpTools.GET_ELEMENT:
                return getElement(args);
            case McpTools.FIND_ELEMENTS:
                return findElements(args);
            case McpTools.GET_MODEL_TREE:
                return getModelTree(args);
            case McpTools.GET_RELATIONSHIPS:
                return getRelationships(args);
            case McpTools.LIST_METACLASSES:
                return listMetaclasses(args);
            case McpTools.GET_SELECTION:
                return getSelection();
            case McpTools.CREATE_ELEMENT:
                return this.edits.createElement(args);
            case McpTools.UPDATE_ELEMENT:
                return this.edits.updateElement(args);
            case McpTools.DELETE_ELEMENTS:
                return this.edits.deleteElements(args);
            case McpTools.GET_DIAGRAM_CONTENTS:
                return this.diagrams.getDiagramContents(args);
            case McpTools.ADD_TO_DIAGRAM:
                return this.diagrams.addToDiagram(args);
            case McpTools.MOVE_IN_DIAGRAM:
                return this.diagrams.moveInDiagram(args);
            case McpTools.ALIGN_IN_DIAGRAM:
                return this.diagrams.alignInDiagram(args);
            case McpTools.REMOVE_FROM_DIAGRAM:
                return this.diagrams.removeFromDiagram(args);
            default:
                throw new ToolException("Unknown tool: " + tool);
            }
        });
    }

    JsonNode getDiagramImage(JsonNode args) {
        return runInUi(() -> this.diagrams.getDiagramImage(args));
    }

    private JsonNode runInUi(Supplier<JsonNode> task) {
        Display display = Display.getDefault();
        if (display == null || display.isDisposed()) {
            throw new ToolException("Modelio user interface is not available.");
        }
        if (display.getThread() == Thread.currentThread()) {
            return task.get();
        }
        CompletableFuture<JsonNode> future = new CompletableFuture<>();
        display.asyncExec(() -> {
            try {
                future.complete(task.get());
            } catch (Throwable e) {
                future.completeExceptionally(e);
            }
        });
        try {
            return future.get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            }
            throw new RuntimeException(e.getCause());
        } catch (TimeoutException e) {
            throw new ToolException("Modelio did not answer within " + TIMEOUT_SECONDS + "s (user interface busy?).");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ToolException("Interrupted");
        }
    }

    // ------------------------------------------------------------------ tools

    private JsonNode getProjectInfo() {
        IGProject project = requireProject();
        ObjectNode r = this.mapper.createObjectNode();
        r.put("name", project.getName());
        r.put("path", String.valueOf(project.getPfs().getProjectPath()));
        r.put("type", String.valueOf(project.getType()));
        r.put("dirty", project.getSession().isDirty());
        ArrayNode fragments = r.putArray("fragments");
        for (IGModelFragment f : project.getParts(IGModelFragment.class)) {
            ObjectNode fn = fragments.addObject();
            fn.put("id", f.getId());
            fn.put("type", String.valueOf(f.getType()));
            fn.put("state", String.valueOf(f.getState().getValue()));
            fn.put("is_library", !isUserFragment(f));
            ArrayNode roots = fn.putArray("roots");
            for (MObject root : safeRoots(f)) {
                roots.add(summary(root));
            }
        }
        ArrayNode others = r.putArray("other_parts");
        for (IGPart p : project.getParts()) {
            if (!(p instanceof IGModelFragment)) {
                ObjectNode pn = others.addObject();
                pn.put("id", p.getId());
                pn.put("type", String.valueOf(p.getType()));
                pn.put("version", String.valueOf(p.getVersion()));
                pn.put("state", String.valueOf(p.getState().getValue()));
            }
        }
        return r;
    }

    private JsonNode listChildren(JsonNode args) {
        IGProject project = requireProject();
        int offset = Math.max(0, args.path("offset").asInt(0));
        int limit = positive(args.path("limit").asInt(200), 200);
        List<? extends MObject> children;
        ObjectNode r = this.mapper.createObjectNode();
        if (args.hasNonNull("id")) {
            MObject parent = requireElement(args.path("id").asText());
            r.set("parent", summary(parent));
            children = parent.getCompositionChildren();
        } else {
            List<MObject> roots = new ArrayList<>();
            for (IGModelFragment f : project.getParts(IGModelFragment.class)) {
                roots.addAll(safeRoots(f));
            }
            children = roots;
        }
        r.put("total", children.size());
        r.put("offset", offset);
        ArrayNode items = r.putArray("children");
        for (int i = offset; i < children.size() && i < offset + limit; i++) {
            MObject c = children.get(i);
            ObjectNode s = summary(c);
            s.put("child_count", c.getCompositionChildren().size());
            items.add(s);
        }
        if (offset + limit < children.size()) {
            r.put("next_offset", offset + limit);
        }
        return r;
    }

    private JsonNode getElement(JsonNode args) {
        requireProject();
        MObject obj = requireElement(requiredText(args, "id"));
        int maxRefs = positive(args.path("max_refs").asInt(50), 50);

        ObjectNode r = summary(obj);
        r.put("metaclass_qualified", obj.getMClass().getQualifiedName());
        IGModelFragment frag = requireProject().getFragment(obj);
        if (frag != null) {
            r.put("fragment", frag.getId());
        }
        r.put("modifiable", obj.isModifiable());
        r.set("owner_path", ownerPath(obj));
        if (obj instanceof AssociationEnd end && end.getOpposite() != null && end.getOpposite().getOwner() != null) {
            // Non-navigable roles have no Target reference but still describe the opposite participant.
            r.put("participant_id", end.getOpposite().getOwner().getUuid());
        }

        ObjectNode attrs = r.putObject("attributes");
        for (MAttribute att : obj.getMClass().getAttributes(true)) {
            if (SKIPPED_ATTRIBUTES.contains(att.getName())) {
                continue;
            }
            Object v;
            try {
                v = obj.mGet(att);
            } catch (RuntimeException e) {
                continue;
            }
            putValue(attrs, att.getName(), v);
        }

        if (obj instanceof ModelElement) {
            ModelElement me = (ModelElement) obj;
            ArrayNode notes = r.putArray("notes");
            for (Note n : me.getDescriptor()) {
                ObjectNode nn = notes.addObject();
                nn.put("type", n.getModel() != null ? n.getModel().getName() : null);
                nn.put("mime_type", n.getMimeType());
                nn.put("content", truncate(n.getContent()));
            }
            ArrayNode tags = r.putArray("tagged_values");
            for (TaggedValue tv : me.getTag()) {
                ObjectNode tn = tags.addObject();
                tn.put("type", tv.getDefinition() != null ? tv.getDefinition().getName() : null);
                ArrayNode values = tn.putArray("values");
                for (TagParameter p : tv.getActual()) {
                    values.add(p.getValue());
                }
            }
        }

        ObjectNode refs = r.putObject("references");
        for (MDependency dep : obj.getMClass().getDependencies(true)) {
            if (SKIPPED_DEPENDENCIES.contains(dep.getName())) {
                continue;
            }
            List<MObject> targets;
            try {
                targets = obj.mGet(dep);
            } catch (RuntimeException e) {
                continue;
            }
            if (targets == null || targets.isEmpty()) {
                continue;
            }
            ObjectNode dn = refs.putObject(dep.getName());
            dn.put("composition", dep.isComposition());
            dn.put("count", targets.size());
            ArrayNode list = dn.putArray("elements");
            for (int i = 0; i < targets.size() && i < maxRefs; i++) {
                list.add(summary(targets.get(i)));
            }
        }
        return r;
    }

    private JsonNode findElements(JsonNode args) {
        IGProject project = requireProject();
        ICoreSession session = project.getSession();
        String name = args.path("name").asText(null);
        boolean exact = args.path("exact").asBoolean(false);
        String stereotype = args.path("stereotype").asText(null);
        String fragmentId = args.path("fragment").asText(null);
        boolean includeLibraries = args.path("include_libraries").asBoolean(false);
        int limit = positive(args.path("limit").asInt(100), 100);

        MClass cls = args.hasNonNull("metaclass") ? requireMetaclass(session, args.path("metaclass").asText())
                : session.getMetamodel().getMClass(Element.class);

        Collection<? extends MObject> candidates;
        // The common Element metaclass has no Name attribute. Its subclasses
        // can still be named, so only use the indexed lookup when supported.
        if (name != null && exact && cls.getAttribute("Name") != null) {
            candidates = session.getModel().findByName(cls, true, name);
        } else {
            candidates = session.getModel().findByClass(cls, true);
        }
        String needle = name == null ? null : name.toLowerCase(Locale.ROOT);

        List<MObject> hits = new ArrayList<>();
        int total = 0;
        for (MObject o : candidates) {
            if (o.isDeleted() || o.isShell()) {
                continue;
            }
            if (needle != null) {
                String n = o.getName();
                if (n == null || (exact ? !n.equals(name) : !n.toLowerCase(Locale.ROOT).contains(needle))) {
                    continue;
                }
            }
            if (stereotype != null && !hasStereotype(o, stereotype)) {
                continue;
            }
            IGModelFragment f = project.getFragment(o);
            if (fragmentId != null && (f == null || !fragmentId.equals(f.getId()))) {
                continue;
            }
            if (fragmentId == null && !includeLibraries && (f == null || !isUserFragment(f))) {
                continue;
            }
            total++;
            if (hits.size() < limit) {
                hits.add(o);
            }
        }
        hits.sort(Comparator.comparing((MObject o) -> o.getMClass().getName()).thenComparing(o -> String.valueOf(o.getName())));

        ObjectNode r = this.mapper.createObjectNode();
        r.put("total_matches", total);
        r.put("returned", hits.size());
        ArrayNode items = r.putArray("elements");
        for (MObject o : hits) {
            ObjectNode s = summary(o);
            s.put("path", pathString(o));
            items.add(s);
        }
        return r;
    }

    private JsonNode getModelTree(JsonNode args) {
        IGProject project = requireProject();
        int depth = positive(args.path("depth").asInt(3), 3);
        int maxNodes = positive(args.path("max_nodes").asInt(500), 500);
        boolean includeLibraries = args.path("include_libraries").asBoolean(false);
        List<MObject> starts = new ArrayList<>();
        if (args.hasNonNull("id")) {
            starts.add(requireElement(args.path("id").asText()));
        } else {
            for (IGModelFragment f : project.getParts(IGModelFragment.class)) {
                if (includeLibraries || isUserFragment(f)) {
                    starts.addAll(safeRoots(f));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int[] count = { 0 };
        for (MObject s : starts) {
            outline(s, 0, depth, maxNodes, count, sb);
        }
        if (count[0] >= maxNodes) {
            sb.append("... (truncated at ").append(maxNodes).append(" nodes)\n");
        }
        ObjectNode r = this.mapper.createObjectNode();
        r.put("format", "indent | name [Metaclass] <<stereotypes>> {uuid} (+N children not shown)");
        r.put("tree", sb.toString());
        return r;
    }

    private JsonNode getRelationships(JsonNode args) {
        requireProject();
        MObject obj = requireElement(requiredText(args, "id"));
        ObjectNode r = this.mapper.createObjectNode();
        r.set("element", summary(obj));
        ArrayNode rels = r.putArray("relationships");
        for (MDependency dep : obj.getMClass().getDependencies(true)) {
            if (!dep.getTarget().isLinkMetaclass()) {
                continue;
            }
            List<MObject> links;
            try {
                links = obj.mGet(dep);
            } catch (RuntimeException e) {
                continue;
            }
            for (MObject link : links) {
                ObjectNode ln = summary(link);
                ln.put("via", dep.getName());
                ArrayNode sources = ln.putArray("sources");
                for (MDependency sd : link.getMClass().getLinkMetaclassSources()) {
                    for (MObject s : link.mGet(sd)) {
                        sources.add(summary(s));
                    }
                }
                ArrayNode targets = ln.putArray("targets");
                for (MDependency td : link.getMClass().getLinkMetaclassTargets()) {
                    for (MObject t : link.mGet(td)) {
                        targets.add(summary(t));
                    }
                }
                rels.add(ln);
            }
        }
        r.put("count", rels.size());
        return r;
    }

    private JsonNode listMetaclasses(JsonNode args) {
        IGProject project = requireProject();
        ICoreSession session = project.getSession();
        String filter = args.path("filter").asText("").toLowerCase(Locale.ROOT);
        boolean withCounts = args.path("with_counts").asBoolean(false);
        List<MClass> classes = new ArrayList<>();
        for (MClass c : session.getMetamodel().getRegisteredMClasses()) {
            if (c.isFake()) {
                continue;
            }
            if (filter.isEmpty() || c.getQualifiedName().toLowerCase(Locale.ROOT).contains(filter)) {
                classes.add(c);
            }
        }
        classes.sort(Comparator.comparing(MClass::getQualifiedName));
        ObjectNode r = this.mapper.createObjectNode();
        r.put("count", classes.size());
        ArrayNode items = r.putArray("metaclasses");
        for (MClass c : classes) {
            ObjectNode n = items.addObject();
            n.put("name", c.getQualifiedName());
            n.put("abstract", c.isAbstract());
            n.put("link", c.isLinkMetaclass());
            if (c.getSuper() != null) {
                n.put("super", c.getSuper().getQualifiedName());
            }
            if (withCounts && !c.isAbstract()) {
                int nb = 0;
                for (MObject o : session.getModel().findByClass(c, false)) {
                    IGModelFragment f = project.getFragment(o);
                    if (f != null && isUserFragment(f) && !o.isDeleted()) {
                        nb++;
                    }
                }
                n.put("instances", nb);
            }
        }
        return r;
    }

    private JsonNode getSelection() {
        requireProject();
        IEclipseContext active = this.context.getActiveLeaf();
        Object sel = active != null ? active.get(IServiceConstants.ACTIVE_SELECTION) : null;
        List<Object> items = new ArrayList<>();
        if (sel instanceof IStructuredSelection) {
            items.addAll(((IStructuredSelection) sel).toList());
        } else if (sel instanceof Collection) {
            items.addAll((Collection<?>) sel);
        } else if (sel != null) {
            items.add(sel);
        }
        ObjectNode r = this.mapper.createObjectNode();
        ArrayNode elems = r.putArray("selected");
        for (Object o : items) {
            MObject m = null;
            if (o instanceof MObject) {
                m = (MObject) o;
            } else if (o instanceof IAdaptable) {
                m = ((IAdaptable) o).getAdapter(MObject.class);
            }
            if (m != null && m.isValid()) {
                ObjectNode s = summary(m);
                s.put("path", pathString(m));
                elems.add(s);
            }
        }
        r.put("count", elems.size());
        return r;
    }

    // ------------------------------------------------------------------ helpers

    IGProject requireProject() {
        IProjectService ps = this.context.get(IProjectService.class);
        IGProject project = ps != null ? ps.getOpenedProject() : null;
        if (project == null || project.getSession() == null) {
            throw new ToolException("No project is currently opened in Modelio. Open a project and retry.");
        }
        if (project != this.cachedProject) {
            this.knownElements.clear();
            this.cachedProject = project;
        }
        return project;
    }

    MObject requireElement(String id) {
        MObject obj = findElement(id);
        if (obj == null) {
            throw new ToolException("No element with id '" + id + "' in the opened project.");
        }
        return obj;
    }

    private MObject findElement(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        id = id.trim();
        MObject known = this.knownElements.get(id);
        if (known != null && known.isValid() && !known.isDeleted()) {
            return known;
        }
        ICoreSession session = requireProject().getSession();
        MMetamodel mm = session.getMetamodel();
        MObject found = session.getModel().findById(mm.getMClass(Element.class), id);
        if (found == null) {
            // The store looks elements up by exact metaclass: try every concrete one.
            for (MClass c : mm.getRegisteredMClasses()) {
                if (c.isAbstract() || c.isFake()) {
                    continue;
                }
                found = session.getModel().findById(c, id);
                if (found != null) {
                    break;
                }
            }
        }
        if (found != null && found.isValid() && !found.isDeleted()) {
            this.knownElements.put(id, found);
            return found;
        }
        return null;
    }

    MClass requireMetaclass(ICoreSession session, String name) {
        MMetamodel mm = session.getMetamodel();
        MClass c = mm.getMClass(name);
        if (c != null && !c.isFake()) {
            return c;
        }
        List<MClass> candidates = new ArrayList<>();
        for (MClass mc : mm.getRegisteredMClasses()) {
            if (!mc.isFake() && (mc.getName().equalsIgnoreCase(name) || mc.getQualifiedName().equalsIgnoreCase(name))) {
                candidates.add(mc);
            }
        }
        if (candidates.size() == 1) {
            return candidates.get(0);
        }
        if (candidates.isEmpty()) {
            throw new ToolException("Unknown metaclass '" + name + "'. Use list_metaclasses to see the available ones.");
        }
        StringBuilder sb = new StringBuilder("Ambiguous metaclass '" + name + "', use a qualified name among:");
        for (MClass mc : candidates) {
            sb.append(' ').append(mc.getQualifiedName());
        }
        throw new ToolException(sb.toString());
    }

    private static boolean isUserFragment(IGModelFragment f) {
        return String.valueOf(f.getType()).endsWith("FRAGMENT");
    }

    private static Collection<MObject> safeRoots(IGModelFragment f) {
        try {
            Collection<MObject> roots = f.getRoots();
            return roots != null ? roots : List.of();
        } catch (RuntimeException e) {
            return List.of();
        }
    }

    ObjectNode summary(MObject o) {
        this.knownElements.put(o.getUuid(), o);
        ObjectNode n = this.mapper.createObjectNode();
        n.put("id", o.getUuid());
        n.put("name", o.getName());
        n.put("metaclass", o.getMClass().getName());
        List<String> st = stereotypes(o);
        if (!st.isEmpty()) {
            ArrayNode a = n.putArray("stereotypes");
            st.forEach(a::add);
        }
        return n;
    }

    private static List<String> stereotypes(MObject o) {
        if (!(o instanceof ModelElement)) {
            return List.of();
        }
        List<String> res = new ArrayList<>();
        try {
            for (Stereotype s : ((ModelElement) o).getExtension()) {
                res.add(s.getName());
            }
        } catch (RuntimeException e) {
            // ignore broken references
        }
        return res;
    }

    private static boolean hasStereotype(MObject o, String name) {
        for (String s : stereotypes(o)) {
            if (s.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private ArrayNode ownerPath(MObject o) {
        List<MObject> chain = new ArrayList<>();
        for (MObject p = o.getCompositionOwner(); p != null; p = p.getCompositionOwner()) {
            chain.add(0, p);
        }
        ArrayNode a = this.mapper.createArrayNode();
        for (MObject p : chain) {
            a.add(summary(p));
        }
        return a;
    }

    static String pathString(MObject o) {
        StringBuilder sb = new StringBuilder();
        for (MObject p = o.getCompositionOwner(); p != null; p = p.getCompositionOwner()) {
            sb.insert(0, "/").insert(0, p.getName());
        }
        return sb.toString();
    }

    private void outline(MObject o, int level, int maxDepth, int maxNodes, int[] count, StringBuilder sb) {
        if (count[0] >= maxNodes) {
            return;
        }
        count[0]++;
        List<? extends MObject> children = o.getCompositionChildren();
        sb.append("  ".repeat(level)).append(o.getName()).append(" [").append(o.getMClass().getName()).append(']');
        List<String> st = stereotypes(o);
        if (!st.isEmpty()) {
            sb.append(" <<").append(String.join(",", st)).append(">>");
        }
        sb.append(" {").append(o.getUuid()).append('}');
        this.knownElements.put(o.getUuid(), o);
        if (level + 1 >= maxDepth && !children.isEmpty()) {
            sb.append(" (+").append(children.size()).append(" children not shown)");
        }
        sb.append('\n');
        if (level + 1 < maxDepth) {
            for (MObject c : children) {
                outline(c, level + 1, maxDepth, maxNodes, count, sb);
            }
        }
    }

    private void putValue(ObjectNode target, String key, Object v) {
        if (v == null) {
            target.putNull(key);
        } else if (v instanceof Boolean) {
            target.put(key, (Boolean) v);
        } else if (v instanceof Integer) {
            target.put(key, (Integer) v);
        } else if (v instanceof Long) {
            target.put(key, (Long) v);
        } else if (v instanceof Number) {
            target.put(key, ((Number) v).doubleValue());
        } else if (v instanceof Enum) {
            target.put(key, ((Enum<?>) v).name());
        } else {
            target.put(key, truncate(String.valueOf(v)));
        }
    }

    private static String truncate(String s) {
        if (s == null || s.length() <= MAX_STRING) {
            return s;
        }
        return s.substring(0, MAX_STRING) + "... [truncated, " + s.length() + " chars]";
    }

    static String requiredText(JsonNode args, String name) {
        String v = args.path(name).asText(null);
        if (v == null || v.isBlank()) {
            throw new ToolException("Missing required argument '" + name + "'.");
        }
        return v;
    }

    private static int positive(int value, int def) {
        return value > 0 ? value : def;
    }

}

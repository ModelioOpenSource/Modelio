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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.mcp.server.McpProtocol.ToolException;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Model modifications: create, update and delete elements.
 * <p>
 * Each tool call runs in its own Modelio transaction, so it can be undone from the Modelio user interface.
 * Like {@link ModelQueries}, everything runs in the SWT UI thread (the caller ensures it).
 */
class ModelEdits {
    /** Attributes the client must never write directly. */
    private static final Set<String> READONLY_ATTRIBUTES = Set.of("status", "UiData", "PreviewData", "JsStructure", "UiDataVersion");

    private final ObjectMapper mapper;

    private final IEclipseContext context;

    private final ModelQueries queries;

    ModelEdits(ObjectMapper mapper, IEclipseContext context, ModelQueries queries) {
        this.mapper = mapper;
        this.context = context;
        this.queries = queries;
    }

    // ------------------------------------------------------------------ tools

    JsonNode createElement(JsonNode args) {
        ICoreSession session = this.queries.requireProject().getSession();
        MExpert expert = session.getMetamodel().getMExpert();
        MClass cls = this.queries.requireMetaclass(session, ModelQueries.requiredText(args, "metaclass"));
        if (cls.isAbstract()) {
            throw new ToolException("Metaclass '" + cls.getQualifiedName() + "' is abstract: choose a concrete one (see list_metaclasses).");
        }

        boolean isLink = cls.isLinkMetaclass();
        MObject source = args.hasNonNull("source_id") ? this.queries.requireElement(args.path("source_id").asText()) : null;
        MObject target = args.hasNonNull("target_id") ? this.queries.requireElement(args.path("target_id").asText()) : null;
        if (isLink && (source == null || target == null)) {
            throw new ToolException("'" + cls.getName() + "' is a relationship metaclass: 'source_id' and 'target_id' are required.");
        }
        if (!isLink && (source != null || target != null)) {
            throw new ToolException("'" + cls.getName() + "' is not a relationship metaclass: 'source_id'/'target_id' are not allowed.");
        }
        if (isLink && !expert.canLink(cls, source, target)) {
            throw new ToolException("The metamodel does not allow a '" + cls.getName() + "' from " + describe(source) + " to " + describe(target) + ".");
        }

        MObject owner;
        if (args.hasNonNull("owner_id")) {
            owner = this.queries.requireElement(args.path("owner_id").asText());
        } else if (isLink) {
            owner = source;
        } else {
            throw new ToolException("Missing required argument 'owner_id'.");
        }
        MDependency dep = compositionDependency(expert, owner, cls, args.path("dependency").asText(null));
        if (!owner.isModifiable()) {
            throw new ToolException(describe(owner) + " is not modifiable (read-only fragment or library?).");
        }

        return inTransaction("create " + cls.getName(), () -> {
            MObject obj = newElement(session, cls, owner, dep);
            if (isLink) {
                setLinkEnd(obj, cls.getLinkMetaclassSources(), source, "source");
                setLinkEnd(obj, cls.getLinkMetaclassTargets(), target, "target");
            }
            for (JsonNode st : args.path("stereotypes")) {
                addStereotype(obj, st.asText());
            }
            String name = args.path("name").asText(null);
            if (name != null) {
                obj.setName(name);
            } else {
                IElementNamer namer = namer();
                obj.setName(namer != null ? namer.getUniqueName(obj) : cls.getName());
            }
            setAttributes(obj, args.path("attributes"));
            putNotes(obj, args.path("notes"));
            putTags(obj, args.path("tags"));
            changeReferences(obj, args.path("references"), true);
            return obj;
        }, obj -> {
            ObjectNode r = this.mapper.createObjectNode();
            r.put("created", true);
            ObjectNode e = this.queries.summary(obj);
            e.put("path", ModelQueries.pathString(obj));
            e.put("composition_dependency", dep.getName());
            r.set("element", e);
            return r;
        });
    }

    JsonNode updateElement(JsonNode args) {
        ICoreSession session = this.queries.requireProject().getSession();
        MObject obj = this.queries.requireElement(ModelQueries.requiredText(args, "id"));
        if (!obj.isModifiable()) {
            throw new ToolException(describe(obj) + " is not modifiable (read-only fragment or library?).");
        }
        MObject newOwner = args.hasNonNull("owner_id") ? this.queries.requireElement(args.path("owner_id").asText()) : null;
        if (newOwner != null && newOwner != obj.getCompositionOwner() && !MTools.getAuthTool().canMove(obj, newOwner)) {
            throw new ToolException(describe(obj) + " cannot be moved under " + describe(newOwner) + ".");
        }

        return inTransaction("update " + obj.getName(), () -> {
            if (args.hasNonNull("name")) {
                obj.setName(args.path("name").asText());
            }
            setAttributes(obj, args.path("attributes"));
            for (JsonNode st : args.path("remove_stereotypes")) {
                removeStereotype(obj, st.asText());
            }
            for (JsonNode st : args.path("add_stereotypes")) {
                addStereotype(obj, st.asText());
            }
            putNotes(obj, args.path("notes"));
            putTags(obj, args.path("tags"));
            changeReferences(obj, args.path("remove_references"), false);
            changeReferences(obj, args.path("add_references"), true);
            if (newOwner != null && newOwner != obj.getCompositionOwner()) {
                MTools.getModelTool().moveElement(obj, newOwner, obj.getCompositionOwner());
            }
            return obj;
        }, o -> {
            ObjectNode r = this.mapper.createObjectNode();
            r.put("updated", true);
            ObjectNode e = this.queries.summary(o);
            e.put("path", ModelQueries.pathString(o));
            r.set("element", e);
            return r;
        });
    }

    JsonNode deleteElements(JsonNode args) {
        this.queries.requireProject();
        List<String> ids = new ArrayList<>();
        if (args.path("ids").isArray()) {
            args.path("ids").forEach(n -> ids.add(n.asText()));
        }
        if (args.hasNonNull("id")) {
            ids.add(args.path("id").asText());
        }
        if (ids.isEmpty()) {
            throw new ToolException("Missing required argument 'ids'.");
        }

        Set<MObject> toDelete = new LinkedHashSet<>();
        for (String id : ids) {
            MObject obj = this.queries.requireElement(id);
            MObject owner = obj.getCompositionOwner();
            if (owner == null) {
                throw new ToolException(describe(obj) + " is a model fragment root and cannot be deleted.");
            }
            if (!obj.isModifiable() || !MTools.getAuthTool().canRemoveFrom(obj, owner)) {
                throw new ToolException(describe(obj) + " cannot be deleted (read-only or protected element).");
            }
            toDelete.add(obj);
        }

        ArrayNode deleted = this.mapper.createArrayNode();
        for (MObject obj : toDelete) {
            ObjectNode s = this.queries.summary(obj);
            s.put("path", ModelQueries.pathString(obj));
            s.put("owned_elements_deleted", countDescendants(obj));
            deleted.add(s);
        }
        // Remove them from the diagrams that show them first, so no "missing" graphics are left behind.
        this.queries.diagrams().unmaskFromOtherDiagrams(toDelete);
        return inTransaction("delete " + toDelete.size() + " element(s)", () -> {
            for (MObject obj : toDelete) {
                obj.delete();
            }
            return deleted;
        }, d -> {
            ObjectNode r = this.mapper.createObjectNode();
            r.put("deleted_count", d.size());
            r.set("deleted", d);
            return r;
        });
    }

    // ------------------------------------------------------------------ helpers

    private <T> JsonNode inTransaction(String name, Supplier<T> body, Function<T, JsonNode> result) {
        ICoreSession session = this.queries.requireProject().getSession();
        T value;
        try (ITransaction t = session.getTransactionSupport().createTransaction("MCP: " + name)) {
            value = body.get();
            t.commit();
        }
        return result.apply(value);
    }

    private IMModelServices modelServices() {
        return this.context.get(IMModelServices.class);
    }

    private IElementNamer namer() {
        IMModelServices s = modelServices();
        return s != null ? s.getElementNamer() : null;
    }

    private MObject newElement(ICoreSession session, MClass cls, MObject owner, MDependency dep) {
        IMModelServices s = modelServices();
        if (s != null) {
            return s.getModelFactory().createElement(cls, owner, dep);
        }
        return session.getModel().getGenericFactory().create(cls, owner, dep);
    }

    private MDependency compositionDependency(MExpert expert, MObject owner, MClass cls, String depName) {
        MDependency dep;
        if (depName != null && !depName.isBlank()) {
            dep = owner.getMClass().getDependency(depName);
            if (dep == null || !dep.isComposition()) {
                throw new ToolException("'" + depName + "' is not a composition relation of " + owner.getMClass().getName()
                        + ". Available: " + compositionNames(owner.getMClass()));
            }
        } else {
            dep = expert.getDefaultCompositionDep(owner.getMClass(), cls);
            if (dep == null) {
                throw new ToolException("A " + cls.getName() + " cannot be owned by " + describe(owner)
                        + ". Choose another owner_id, or give 'dependency' among: " + compositionNames(owner.getMClass()));
            }
        }
        if (!expert.canCompose(owner, cls, dep.getName())) {
            throw new ToolException("The metamodel does not allow a " + cls.getName() + " under " + describe(owner) + " through '" + dep.getName() + "'.");
        }
        return dep;
    }

    private static String compositionNames(MClass cls) {
        List<String> names = new ArrayList<>();
        for (MDependency d : cls.getDependencies(true)) {
            if (d.isComposition()) {
                names.add(d.getName() + " (" + d.getTarget().getName() + ")");
            }
        }
        return String.join(", ", names);
    }

    private static void setLinkEnd(MObject link, Collection<MDependency> deps, MObject end, String role) {
        for (MDependency d : deps) {
            if (!end.getMClass().hasBase(d.getTarget())) {
                continue;
            }
            List<MObject> current = link.mGet(d);
            if (current.contains(end)) {
                return;
            }
            if (d.getMaxCardinality() == 1 && !current.isEmpty()) {
                current.set(0, end);
            } else {
                current.add(end);
            }
            return;
        }
        throw new ToolException(describe(end) + " cannot be the " + role + " of a " + link.getMClass().getName() + ".");
    }

    private void setAttributes(MObject obj, JsonNode attrs) {
        if (!attrs.isObject()) {
            return;
        }
        for (Iterator<Map.Entry<String, JsonNode>> it = attrs.fields(); it.hasNext();) {
            Map.Entry<String, JsonNode> e = it.next();
            MAttribute att = obj.getMClass().getAttribute(e.getKey());
            if (att == null) {
                throw new ToolException("Unknown attribute '" + e.getKey() + "' on " + obj.getMClass().getName() + ". Use get_element to see the attributes.");
            }
            if (READONLY_ATTRIBUTES.contains(att.getName())) {
                throw new ToolException("Attribute '" + att.getName() + "' cannot be modified.");
            }
            obj.mSet(att, convert(att, e.getValue()));
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Object convert(MAttribute att, JsonNode v) {
        Class<?> type = att.getType();
        if (v == null || v.isNull()) {
            if (type.isPrimitive()) {
                throw new ToolException("Attribute '" + att.getName() + "' cannot be null.");
            }
            return null;
        }
        if (type == String.class) {
            return v.asText();
        }
        if (type == boolean.class || type == Boolean.class) {
            if (!v.isBoolean() && !"true".equalsIgnoreCase(v.asText()) && !"false".equalsIgnoreCase(v.asText())) {
                throw new ToolException("Attribute '" + att.getName() + "' expects a boolean.");
            }
            return v.isBoolean() ? v.booleanValue() : Boolean.parseBoolean(v.asText());
        }
        try {
            if (type == int.class || type == Integer.class) {
                return v.isNumber() ? v.intValue() : Integer.parseInt(v.asText().trim());
            }
            if (type == long.class || type == Long.class) {
                return v.isNumber() ? v.longValue() : Long.parseLong(v.asText().trim());
            }
            if (type == double.class || type == Double.class) {
                return v.isNumber() ? v.doubleValue() : Double.parseDouble(v.asText().trim());
            }
            if (type == float.class || type == Float.class) {
                return v.isNumber() ? v.floatValue() : Float.parseFloat(v.asText().trim());
            }
        } catch (NumberFormatException e) {
            throw new ToolException("Attribute '" + att.getName() + "' expects a number, got '" + v.asText() + "'.");
        }
        if (type.isEnum()) {
            String s = v.asText().trim();
            for (Object c : type.getEnumConstants()) {
                if (((Enum) c).name().equalsIgnoreCase(s) || c.toString().equalsIgnoreCase(s)) {
                    return c;
                }
            }
            List<String> names = new ArrayList<>();
            for (Object c : type.getEnumConstants()) {
                names.add(((Enum) c).name());
            }
            throw new ToolException("Attribute '" + att.getName() + "' expects one of " + names + ".");
        }
        throw new ToolException("Attribute '" + att.getName() + "' of type " + type.getSimpleName() + " cannot be set through MCP.");
    }

    private Stereotype findStereotype(MObject obj, String spec) {
        IMModelServices s = modelServices();
        if (s == null) {
            throw new ToolException("Stereotype services are not available.");
        }
        // Accept "Module:Name" to disambiguate.
        String module = null;
        String name = spec.trim();
        int sep = name.indexOf(':');
        if (sep > 0) {
            module = Pattern.quote(name.substring(0, sep));
            name = name.substring(sep + 1);
        }
        List<Stereotype> found = s.findStereotypes(module, Pattern.quote(name), obj.getMClass());
        if (found.isEmpty()) {
            throw new ToolException("No stereotype '" + spec + "' applicable to " + obj.getMClass().getName() + ".");
        }
        if (found.size() > 1) {
            List<String> names = new ArrayList<>();
            for (Stereotype st : found) {
                names.add(ModelQueries.pathString(st) + st.getName());
            }
            throw new ToolException("Ambiguous stereotype '" + spec + "', use 'Module:Name' among " + names + ".");
        }
        return found.get(0);
    }

    private void addStereotype(MObject obj, String spec) {
        ModelElement me = requireModelElement(obj, "stereotypes");
        Stereotype st = findStereotype(obj, spec);
        if (!me.getExtension().contains(st)) {
            me.getExtension().add(st);
        }
    }

    private static void removeStereotype(MObject obj, String spec) {
        ModelElement me = requireModelElement(obj, "stereotypes");
        String name = spec.contains(":") ? spec.substring(spec.indexOf(':') + 1) : spec;
        boolean removed = me.getExtension().removeIf(st -> name.equalsIgnoreCase(st.getName()));
        if (!removed) {
            throw new ToolException(describe(obj) + " has no stereotype '" + spec + "'.");
        }
    }

    @SuppressWarnings("deprecation")
    private static void putNotes(MObject obj, JsonNode notes) {
        if (!notes.isArray() || notes.isEmpty()) {
            return;
        }
        ModelElement me = requireModelElement(obj, "notes");
        for (JsonNode n : notes) {
            String module = n.path("module").asText("ModelerModule");
            String type = ModelQueries.requiredText(n, "type");
            try {
                me.putNoteContent(module, type, n.path("content").asText(""));
            } catch (IllegalArgumentException e) {
                throw new ToolException("Cannot set note '" + module + ":" + type + "' on " + describe(obj) + ": " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("deprecation")
    private static void putTags(MObject obj, JsonNode tags) {
        if (!tags.isArray() || tags.isEmpty()) {
            return;
        }
        ModelElement me = requireModelElement(obj, "tagged values");
        for (JsonNode n : tags) {
            String module = n.path("module").asText("ModelerModule");
            String type = ModelQueries.requiredText(n, "type");
            try {
                me.putTagValue(module, type, n.hasNonNull("value") ? n.path("value").asText() : null);
            } catch (IllegalArgumentException e) {
                throw new ToolException("Cannot set tagged value '" + module + ":" + type + "' on " + describe(obj) + ": " + e.getMessage());
            }
        }
    }

    private void changeReferences(MObject obj, JsonNode refs, boolean add) {
        if (!refs.isObject()) {
            return;
        }
        MExpert expert = obj.getMClass().getMetamodel().getMExpert();
        for (Iterator<Map.Entry<String, JsonNode>> it = refs.fields(); it.hasNext();) {
            Map.Entry<String, JsonNode> e = it.next();
            MDependency dep = obj.getMClass().getDependency(e.getKey());
            if (dep == null) {
                throw new ToolException("Unknown relation '" + e.getKey() + "' on " + obj.getMClass().getName() + ". Use get_element to see the references.");
            }
            if (dep.isComposition()) {
                throw new ToolException("'" + dep.getName() + "' is a composition: create the element under its owner, or move it with update_element/owner_id.");
            }
            List<MObject> current = obj.mGet(dep);
            JsonNode ids = e.getValue().isArray() ? e.getValue() : this.mapper.createArrayNode().add(e.getValue());
            for (JsonNode idNode : ids) {
                MObject ref = this.queries.requireElement(idNode.asText());
                if (add) {
                    if (current.contains(ref)) {
                        continue;
                    }
                    if (!expert.canDep(obj, ref, dep.getName())) {
                        throw new ToolException("The metamodel does not allow " + describe(ref) + " in '" + dep.getName() + "' of " + describe(obj) + ".");
                    }
                    if (dep.getMaxCardinality() == 1 && !current.isEmpty()) {
                        current.set(0, ref);
                    } else {
                        current.add(ref);
                    }
                } else if (!current.remove(ref)) {
                    throw new ToolException(describe(ref) + " is not in '" + dep.getName() + "' of " + describe(obj) + ".");
                }
            }
        }
    }

    private static ModelElement requireModelElement(MObject obj, String what) {
        if (!(obj instanceof ModelElement)) {
            throw new ToolException(describe(obj) + " cannot carry " + what + ".");
        }
        return (ModelElement) obj;
    }

    private static int countDescendants(MObject obj) {
        int n = 0;
        for (MObject c : obj.getCompositionChildren()) {
            n += 1 + countDescendants(c);
        }
        return n;
    }

    private static String describe(MObject o) {
        return "'" + o.getName() + "' [" + o.getMClass().getName() + "]";
    }

}

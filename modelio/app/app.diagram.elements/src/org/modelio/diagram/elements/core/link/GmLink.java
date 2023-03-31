/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.diagram.elements.core.link;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.extensions.GmConnectionEndpoinLocator;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.link.migration.LinkPathFixer;
import org.modelio.diagram.elements.core.link.migration.OrthogonalPathFixer3_7;
import org.modelio.diagram.elements.core.link.migration.OrthogonalPathFixer5_0_2;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.GmReference;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmNode;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.model.IGmReference;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a link between 2 nodes in the diagram.
 */
@objid ("80100e01-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmLink extends GmModel implements IGmLink {
    @objid ("80127039-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     * <ul>
     * <li>0 : initial version
     * <li>1 : extensions role must now be filled
     * <li>2 : source and target are now IGmReferences. Modelio 3.7 migration.
     * <li>3 : handle new orthogonal router. Modelio 5.0.2 migration.
     * </ul>
     */
    @objid ("80127036-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 3;

    /**
     * Tells the source element changed and is inconsistent from the source node.
     */
    @objid ("8fb061e4-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROP_SOURCE_EL = "Source element model changed";

    /**
     * Tells the source node changed.
     */
    @objid ("8fadff89-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROP_SOURCE_GM = "Source graphic model changed";

    /**
     * Tells the target element changed and is inconsistent from the terget node.
     */
    @objid ("8fb2c43a-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROP_TARGET_EL = "Target element model changed";

    /**
     * Tells the target node changed.
     */
    @objid ("8fb061de-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROP_TARGET_GM = "Target graphic model changed";

    @objid ("80100e0d-1dec-11e2-8cad-001ec947c8cc")
    private final List<GmReference<IGmLink>> endingLinks = new ArrayList<>();

    @objid ("80100e05-1dec-11e2-8cad-001ec947c8cc")
    private final Map<GmNodeModel, IGmLocator> extensions = new HashMap<>();

    @objid ("80100e03-1dec-11e2-8cad-001ec947c8cc")
    private GmReference<IGmLinkable> from;

    @objid ("2784caea-f36c-4afd-a76e-e877b572b2d6")
    private final PropertyChangeListener fromReferenceListener;

    @objid ("80100e09-1dec-11e2-8cad-001ec947c8cc")
    private final List<GmReference<IGmLink>> startingLinks = new ArrayList<>();

    @objid ("80100e04-1dec-11e2-8cad-001ec947c8cc")
    private GmReference<IGmLinkable> to;

    @objid ("9085bea6-3cb3-4756-ae99-ca5f38c79f9e")
    private final PropertyChangeListener toReferenceListener;

    /**
     * Initialize a new GmLink.
     * @param diagram The diagram containing the link.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("8012703b-1dec-11e2-8cad-001ec947c8cc")
    protected  GmLink(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.fromReferenceListener = this::fromReferenceChanged;
        this.toReferenceListener = this::toReferenceChanged;
        initGmLink();
        
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("80127040-1dec-11e2-8cad-001ec947c8cc")
    protected  GmLink() {
        this.fromReferenceListener = this::fromReferenceChanged;
        this.toReferenceListener = this::toReferenceChanged;
        initGmLink();
        
    }

    @objid ("80127043-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void addEndingLink(final IGmLink link) {
        this.endingLinks.add(new GmReference<>(this, link));
        link.setTo(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, null, link);
        
    }

    /**
     * Add a link extension.
     * @param extension The link extension.
     * @param role the role of the extension
     * @param constraint The extension layout constraint.
     */
    @objid ("80127048-1dec-11e2-8cad-001ec947c8cc")
    public void addExtension(GmNodeModel extension, String role, IGmLocator constraint) {
        extension.setRoleInComposition(role);
        extension.setParentLink(this);
        this.extensions.put(extension, constraint);
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, extension);
        
    }

    /**
     * Add a link extension.
     * @param locationKey extension key
     * @param role the role of the extension
     * @param extension the extension to add.
     */
    @objid ("8012704d-1dec-11e2-8cad-001ec947c8cc")
    public void addExtension(String locationKey, String role, GmNodeModel extension) {
        extension.setRoleInComposition(role);
        extension.setParentLink(this);
        this.extensions.put(extension, makeLocator(locationKey));
        if (getRelatedElement() != null) {
            firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, getRelatedElement().getName());
        }
        
    }

    @objid ("80127052-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void addStartingLink(final IGmLink link) {
        this.startingLinks.add(new GmReference<>(this, link));
        link.setFrom(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, null, link);
        
    }

    /**
     * By default a link can unmask nothing.
     */
    @objid ("80127057-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    /**
     * By default a link can unmask nothing.
     */
    @objid ("80127060-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Delete the link from the diagram.
     * <p>
     * Delete its links, extensions and then detach from its source and destination.
     */
    @objid ("8014d293-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void delete() {
        // delete outgoing links
        for (IGmReference<IGmLink> l : new ArrayList<>(this.startingLinks)) {
            l.delete();
        }
        // delete incoming links
        for (IGmReference<IGmLink> l : new ArrayList<>(this.endingLinks)) {
            l.delete();
        }
        
        // delete extensions
        for (GmNodeModel extension : new ArrayList<>(this.extensions.keySet())) {
            extension.delete();
        }
        
        // detach from source
        if (this.from != null) {
            this.from.releaseGmReference();
            final IGmLinkable resolvedFrom = this.from.getReferencedModel();
            if (resolvedFrom != null) {
                resolvedFrom.removeStartingLink(this);
            }
        }
        
        // detach from target
        if (this.to != null) {
            this.to.releaseGmReference();
            final IGmLinkable resolvedTo = this.to.getReferencedModel();
            if (resolvedTo != null) {
                resolvedTo.removeEndingLink(this);
            }
        }
        
        // now I can die
        super.delete();
        
    }

    /**
     * Called by the anchor when its location changes.
     * @param gmLinkAnchor The moved anchor.
     */
    @objid ("8019974a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void fireAnchorMoved(GmAbstractLinkAnchor gmLinkAnchor) {
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, gmLinkAnchor);
    }

    /**
     * Fires a {@link org.modelio.diagram.elements.core.model.IGmObject#PROPERTY_CHILDREN PROPERTY_CHILDREN} property change.
     * <p>
     * To be called when the result of {@link GmNodeModel#isVisible()} on the given link extension changes.
     * @param child The link extension node whose visibility changed.
     */
    @objid ("8014d297-1dec-11e2-8cad-001ec947c8cc")
    public final void fireChildVisibilityChanged(GmNodeModel child) {
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, child);
    }

    @objid ("8014d29b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void firePathChanged(final IGmPath path) {
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, path);
    }

    @objid ("8014d2a0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final List<IGmLink> getEndingLinks() {
        return this.endingLinks
                .stream()
                .filter(IGmReference<IGmLink>::isReferencedModelValid)
                .map(IGmReference<IGmLink>::getReferencedModel)
                .collect(Collectors.toList());
        
    }

    /**
     * Get all link extensions.
     * <p>
     * Link extensions are roundly all labels related to the link, eg: association role name and cardinality.
     * @return all link extensions.
     */
    @objid ("8014d2a7-1dec-11e2-8cad-001ec947c8cc")
    public final Collection<GmNodeModel> getExtensions() {
        return this.extensions.keySet();
    }

    @objid ("e45f5363-1e2d-45c5-a827-c553512c8d35")
    @Override
    public final Collection<IGmNode> getExtensions(String role) {
        Collection<GmNodeModel> exts = getExtensions();
        Collection<IGmNode> ret = new ArrayList<>(exts.size());
        
        for (GmNodeModel n : exts) {
            if (n.getRoleInComposition().equals(role)) {
                ret.add(n);
            }
        }
        return ret;
    }

    @objid ("28b3a69f-3e34-418e-b01d-74f76211c490")
    @Override
    public final IGmNode getFirstExtension(String role) {
        for (GmNodeModel n : getExtensions()) {
            if (n.getRoleInComposition().equals(role)) {
                return n;
            }
        }
        return null;
    }

    /**
     * @return The link source
     */
    @objid ("8014d2ae-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final IGmLinkable getFrom() {
        return resolveRef(this.from);
    }

    /**
     * Returns the element being the source of the represented link (in the Ob model).
     * <p>
     * May return <code>null</code> if {@link #getRelatedElement()} returns <code>null</code>.
     * <p>
     * <em>This method must <strong>NOT</strong> return "<code>this.getFrom().getElement();</code>" but instead must read the actual source of the link returned by {@link #getRelatedElement()}.</em>
     * @return the element being the source of the represented link (in the Ob model).
     */
    @objid ("8014d2b4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public abstract MObject getFromElement();

    /**
     * Get the locator model used to layout the given extension.
     * @param extension A link extension.
     * @return The locator model.
     */
    @objid ("8014d2b8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final IGmLocator getLayoutContraint(IGmObject extension) {
        return this.extensions.get(extension);
    }

    @objid ("8019976a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmLink.MAJOR_VERSION;
    }

    @objid ("8014d2be-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final GmModel getParent() {
        // links have no parent at the Gm graphic model level
        return null;
    }

    @objid ("8014d2c3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final IGmPath getPath() {
        return (IGmPath) getLayoutData();
    }

    /**
     * Get the element representation mode.
     * <p>
     * Return {@link RepresentationMode#STRUCTURED} , no mode is applicable.
     */
    @objid ("8014d2c8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    /**
     * Get the source anchor model.
     * @return the source anchor.
     */
    @objid ("8014d2ce-1dec-11e2-8cad-001ec947c8cc")
    public final Object getSourceAnchor() {
        return getPath().getSourceAnchor();
    }

    /**
     * Get the links starting from this node.
     * @return the links starting from this node.
     */
    @objid ("8014d2d3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final List<IGmLink> getStartingLinks() {
        return this.startingLinks
                .stream()
                .filter(r -> r.isReferencedModelValid())
                .map(r -> r.getReferencedModel())
                .collect(Collectors.toList());
        
    }

    /**
     * Get the target anchor model.
     * @return the target anchor.
     */
    @objid ("8014d2db-1dec-11e2-8cad-001ec947c8cc")
    public final Object getTargetAnchor() {
        return getPath().getTargetAnchor();
    }

    /**
     * @return the link destination
     */
    @objid ("8014d2e0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final IGmLinkable getTo() {
        return resolveRef(this.to);
    }

    /**
     * Returns the element being the target of the represented link (in the Ob model).
     * <p>
     * <em>This methods must <strong>NOT</strong> return "<code>this.getTo().getElement();</code>" but instead must read the actual target of the link returned by {@link #getRelatedElement()}.</em>
     * <p>
     * May return <code>null</code> if {@link #getRelatedElement()} returns <code>null</code>.
     * @return the element being the target of the represented link (in the Ob model).
     */
    @objid ("801734ec-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public abstract MObject getToElement();

    /**
     * <p>
     * Get the extensions nodes currently visible.
     * </p>
     * <p>
     * The returned list is a copy and may be freely modified.
     * </p>
     * <p>
     * Default implementation returns a list of all extensions for which the isVisible method returns <code>true</code>. This method may be overridden to dynamically filter the extensions list, based on current representation mode for example.<br>
     * In this case you must ensure that {@link #styleChanged(StyleKey, Object)} fires a {@link IGmObject#PROPERTY_CHILDREN} property change event in order for the EditParts to be informed of the change.<br>
     * </p>
     * @return The visible link extension nodes.
     */
    @objid ("801734f0-1dec-11e2-8cad-001ec947c8cc")
    public Collection<GmNodeModel> getVisibleExtensions() {
        final Collection<GmNodeModel> allExtensions = getExtensions();
        final Collection<GmNodeModel> ret = new ArrayList<>(allExtensions.size());
        
        for (final GmNodeModel childNode : allExtensions) {
            if (childNode.isVisible()) {
                ret.add(childNode);
            }
        }
        return ret;
    }

    /**
     * Redefined to refresh also link extensions who relates the same element.
     */
    @objid ("801734fc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void obElementResolved(MObject ev) {
        super.obElementResolved(ev);
        
        for (GmNodeModel m : getExtensions()) {
            if (m.getRepresentedElement() == null) {
                m.obElementResolved(ev);
            }
        }
        
    }

    /**
     * This method is final.
     * <p>
     * Subclasses must override {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("80173501-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLink.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        case 2: {
            read_2(in);
            break;
        }
        case 3: {
            read_3(in);
            break;
        }
        default: {
            assert false : "version number not covered!";
            // reading as last handled version: 3
            read_3(in);
            break;
        }
        }
        
    }

    @objid ("80173506-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void removeEndingLink(final IGmLink gmLink) {
        GmReference.removeFrom(this.endingLinks, gmLink);
        gmLink.setTo(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, gmLink, null);
        
    }

    /**
     * Remove a link extension.
     * @param gmNodeModel the link extension to remove.
     * @throws IllegalArgumentException if the link does not own this node.
     */
    @objid ("8017350b-1dec-11e2-8cad-001ec947c8cc")
    public void removeExtension(GmNodeModel gmNodeModel) throws IllegalArgumentException {
        assert this.extensions.containsKey(gmNodeModel);
        
        this.extensions.remove(gmNodeModel);
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, gmNodeModel, null);
        
        gmNodeModel.setParentLink(null);
        
    }

    @objid ("8017350f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void removeStartingLink(final IGmLink gmLink) {
        GmReference.removeFrom(this.startingLinks, gmLink);
        gmLink.setFrom(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, gmLink, null);
        
    }

    /**
     * Update the link origin.
     * <p>
     * This method is intended to be called only by {@link IGmLinkable#addEndingLink(IGmLink)}. It does fire {@link #PROP_SOURCE_GM} change event.
     * @param from The new link origin
     */
    @objid ("80173514-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setFrom(IGmLinkable from) {
        IGmReference<IGmLinkable> oldFrom = this.from;
        if (from != IGmReference.resolve(oldFrom)) {
            this.from = from == null ? null : new GmReference<>(this, from);
        
            updateFromReferenceListeners(oldFrom, this.from);
            firePropertyChange(GmLink.PROP_SOURCE_GM, oldFrom, from);
        }
        
    }

    /**
     * Change the given extension location.
     * @param extension The link extension.
     * @param layoutData The extension layout constraint.
     */
    @objid ("80173519-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void setLayoutConstraint(IGmObject extension, IGmLocator layoutData) {
        this.extensions.put((GmNodeModel) extension, layoutData);
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, extension, layoutData);
        
    }

    @objid ("8017351e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void setLayoutData(final Object layoutData) {
        final IGmPath oldPath = getPath();
        final IGmPath newPath = (IGmPath) layoutData;
        
        IStyle style = getDisplayedStyle();
        if (newPath != null && style != null) {
            if (newPath.getRouterKind() == null) {
                // Set the router kind from the style if not specified.
        
                newPath.setRouterKind(getRouterFromStyle(style));
            } else {
                // Update the connection router in the style.
        
                // Inhibit style update notification
                ConnectionRouterId oldRouterKind = oldPath.getRouterKind();
                oldPath.setRouterKind(newPath.getRouterKind());
        
                // Update the connection router in the style.
                final StyleKey routerStyleKey = getStyleKey(MetaKey.CONNECTIONROUTER);
                if (routerStyleKey != null && style.getProperty(routerStyleKey) != newPath.getRouterKind()) {
                    style.setProperty(routerStyleKey, newPath.getRouterKind());
                }
        
                // Restore the old router to properly trigger the layout changed property
                oldPath.setRouterKind(oldRouterKind);
            }
        }
        
        // FIXME: Update anchors connected links
        Object oldSrcAnchor = null;
        Object oldDestAnchor = null;
        Object newSrcAnchor = null;
        Object newDestAnchor = null;
        
        if (oldPath != null) {
            oldSrcAnchor = oldPath.getSourceAnchor();
            oldDestAnchor = oldPath.getTargetAnchor();
        }
        if (newPath != null) {
            newSrcAnchor = newPath.getSourceAnchor();
            newDestAnchor = newPath.getTargetAnchor();
        }
        
        if (newSrcAnchor != oldSrcAnchor) {
            if (oldSrcAnchor instanceof GmAbstractLinkAnchor) {
                ((GmAbstractLinkAnchor) oldSrcAnchor).removeLink(this);
            }
            if (newSrcAnchor instanceof GmAbstractLinkAnchor) {
                ((GmAbstractLinkAnchor) newSrcAnchor).addLink(this);
            }
        }
        
        if (newDestAnchor != oldDestAnchor) {
            if (oldDestAnchor instanceof GmAbstractLinkAnchor) {
                ((GmAbstractLinkAnchor) oldDestAnchor).removeLink(this);
            }
        
            if (newDestAnchor instanceof GmAbstractLinkAnchor ) {
                ((GmAbstractLinkAnchor) newDestAnchor).addLink(this);
            }
        }
        
        // Change the path
        super.setLayoutData(layoutData);
        
    }

    /**
     * Update the link destination.
     * <p>
     * This method is intended to be called only by {@link IGmLinkable#addEndingLink(IGmLink)}. It does fire {@link #PROP_TARGET_GM} change event.
     * @param to The new destination
     */
    @objid ("8017352f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTo(IGmLinkable to) {
        IGmReference<IGmLinkable> oldTo = this.to;
        if (to != IGmReference.resolve(oldTo)) {
            this.to = to == null ? null : new GmReference<>(this, to);
        
            updateToReferenceListeners(oldTo, this.to);
            firePropertyChange(GmLink.PROP_TARGET_GM, oldTo, to);
        }
        
    }

    @objid ("80199746-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        if (this.from == null || this.to == null) {
            String msg = MessageFormat.format(
                    "This {0} representing {1} from {2} to {3} has <{4}> as source and <{5}> as target in the diagram.",
                    getClass().getSimpleName(),
                    getRelatedElement(),
                    getFromElement(),
                    getToElement(),
                    this.from, this.to);
            throw new IllegalStateException(msg);
        }
        
        out.writeProperty("Source", this.from);
        out.writeProperty("Dest", this.to);
        out.writeProperty("extensions", this.extensions);
        
        // Write version of this Gm
        writeMinorVersion(out, "GmLink.", GmLink.MINOR_VERSION);
        
    }

    /**
     * Get the connection router id stored in the given style. If no StyleKey is found, the default value for the router is DIRECT.
     * @param style a style
     * @return the connection router.
     */
    @objid ("8019974e-1dec-11e2-8cad-001ec947c8cc")
    final ConnectionRouterId getRouterFromStyle(final IStyle style) {
        final StyleKey routerStyleKey = getStyleKey(MetaKey.CONNECTIONROUTER);
        if (routerStyleKey != null) {
            final ConnectionRouterId newRouter = style.getProperty(routerStyleKey);
            return newRouter;
        }
        return ConnectionRouterId.DIRECT;
    }

    /**
     * Helper method to find an extension from its java class.
     * @param cls the java class of the extension to find
     * @return the found node or null.
     */
    @objid ("627db114-7294-4698-9ba4-b873091122ce")
    protected final <T extends IGmLinkable> T findExtension(Class<T> cls) {
        for (GmNodeModel n : getExtensions()) {
            if (cls.isInstance(n)) {
                return cls.cast(n);
            }
        }
        return null;
    }

    /**
     * Redefinable method called by {@link GmLink#read(IDiagramReader)} before adding the link to the diagram.
     * <p>
     * Subclasses should redefine this method instead of {@link #read(IDiagramReader)}.
     * <p>
     * The default implementation does nothing.
     * @param in a reader to build the graphic model from.
     */
    @objid ("8019975d-1dec-11e2-8cad-001ec947c8cc")
    protected void readLink(IDiagramReader in) {
        // Nothing to do
    }

    /**
     * This method must guess the link extension role from whatever is available from a GmLink with 0 as minor version.
     * <p>
     * It is called by {@link GmLink#read(IDiagramReader)} for V0 {@link GmLink} , when link extension role was not filled, to ask subclasses to fill them.
     */
    @objid ("27eed107-63db-4413-a6c3-bc2e2025e698")
    protected abstract void read_GmLinkV0_roles();

    /**
     * Convenience implementation to call from {@link #read_GmLinkV0_roles()} when the link has only one main label.
     */
    @objid ("7afb9eca-8a9e-4e47-af9c-5713e60e035e")
    protected final void read_GmLinkV0_roles_one_main_label() {
        for (GmNodeModel n : getExtensions()) {
            n.setRoleInComposition(IGmLink.ROLE_MAIN_LABEL);
        }
        
    }

    @objid ("80199761-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromObModel() {
        if (this.from == null && this.to == null) {
            // Orphan GmLink:
            // - it will never have EditPart so will never recover their source and target.
            // - won't be deleted in GmAbstractdiagram#clear()
            // The best workaround for now is to delete them now.
            DiagramElements.LOG.warning(
                    "refreshAllFromObModel(): Deleting %s representing %s from %s to %s that has no source nor target in the diagram.",
                    getClass().getSimpleName(), getRelatedElement(), getFromElement(), getToElement());
            delete();
            return; // fast exit
        }
        
        MObject relatedElement = getRelatedElement();
        if (relatedElement != null && !relatedElement.isShell() && !relatedElement.isDeleted()) {
            MObject graphicSourceElement = getFromElement();
            IGmLinkable gmFrom = getFrom();
            if (gmFrom == null) {
                // source changed: let edit part know and handle it.
                firePropertyChange(GmLink.PROP_SOURCE_EL, null, graphicSourceElement);
            } else if (gmFrom instanceof GmModel) {
                MObject modelSourceElement = ((GmModel) gmFrom).getRelatedElement();
        
                if (!Objects.equals(modelSourceElement, graphicSourceElement)) {
                    // source changed: let edit part know and handle it.
                    firePropertyChange(GmLink.PROP_SOURCE_EL, modelSourceElement, graphicSourceElement);
                } else if (gmFrom instanceof GmNodeModel && !((GmNodeModel) gmFrom).isVisible()) {
                    // source not visible anymore, delete the link
                    delete();
                    return;
                }
            }
        
            MObject graphicTargetElement = getToElement();
            IGmLinkable graphicTo = getTo();
            if (graphicTo == null) {
                // target changed: let edit part know and handle it.
                firePropertyChange(GmLink.PROP_TARGET_EL, null, graphicTargetElement);
            } else if (graphicTo instanceof GmModel) {
                MObject modelTargetElement = ((GmModel) graphicTo).getRelatedElement();
                if (!Objects.equals(modelTargetElement, graphicTargetElement)) {
                    // target changed: let edit part know and handle it.
                    firePropertyChange(GmLink.PROP_TARGET_EL, modelTargetElement, graphicTargetElement);
                } else if (graphicTo instanceof GmNodeModel && !((GmNodeModel) graphicTo).isVisible()) {
                    // target not visible anymore, delete the link
                    delete();
                    return;
                }
            }
        
        }
        
    }

    @objid ("180a7067-4147-4bce-a7b2-fab33dbb82d2")
    protected static final IGmLinkable resolveRef(IGmReference<IGmLinkable> ref) {
        if (ref == null) {
            return null;
        }
        return ref.getReferencedModel();
    }

    @objid ("9f36e75e-aefd-4eca-8927-aa73f6119334")
    private void fromReferenceChanged(PropertyChangeEvent ev) {
        assert this.from == ev.getSource() : this.to + " != " + ev.getSource();
        
        if (this.from != null) {
            final IGmLinkable node = this.from.getReferencedModel();
            if (!node.getStartingLinks().contains(this)) {
                node.addStartingLink(this);
            }
        }
        
    }

    /**
     * To be called by all constructors.
     */
    @objid ("80199764-1dec-11e2-8cad-001ec947c8cc")
    private void initGmLink() {
        GmPath path = new GmPath();
        path.setPathData(new ArrayList<>());
        setLayoutData(path);
        
    }

    @objid ("41c31379-90a0-4494-a0d2-103269748dfe")
    private static IGmLocator makeLocator(String locationKey) {
        switch (locationKey) {
        
        case ExtensionLocation.SourceNW:
            final GmConnectionEndpoinLocator constraint = new GmConnectionEndpoinLocator();
            constraint.setEnd(false);
            constraint.setVDistance(-5);
            constraint.setUDistance(5);
            return constraint;
        
        case ExtensionLocation.SourceSE:
            final GmConnectionEndpoinLocator constraint1 = new GmConnectionEndpoinLocator();
            constraint1.setEnd(false);
            constraint1.setVDistance(5);
            constraint1.setUDistance(5);
            return constraint1;
        
        case ExtensionLocation.TargetNW:
            final GmConnectionEndpoinLocator constraint2 = new GmConnectionEndpoinLocator();
            constraint2.setEnd(true);
            constraint2.setVDistance(-5);
            constraint2.setUDistance(5);
            return constraint2;
        
        case ExtensionLocation.TargetSE:
            final GmConnectionEndpoinLocator constraint3 = new GmConnectionEndpoinLocator();
            constraint3.setEnd(true);
            constraint3.setVDistance(5);
            constraint3.setUDistance(5);
            return constraint3;
        
        case ExtensionLocation.MiddleSE:
            /*
             * final GmConnectionLocator constraint = new GmConnectionLocator(); constraint.setAlignment(ConnectionLocator.MIDDLE); constraint.setRelativePosition(PositionConstants.SOUTH_EAST); constraint.setGap(5);
             */
            final GmFractionalConnectionLocator constraint4 = new GmFractionalConnectionLocator();
            constraint4.setFraction(0.5);
            constraint4.setUDistance(0);
            constraint4.setVDistance(-20);
            return constraint4;
        
        case ExtensionLocation.MiddleNW:
            /*
             * final GmConnectionLocator constraint = new GmConnectionLocator(); constraint.setAlignment(ConnectionLocator.MIDDLE); constraint.setRelativePosition(PositionConstants.NORTH_WEST); constraint.setGap(5);
             */
            final GmFractionalConnectionLocator constraint5 = new GmFractionalConnectionLocator();
            constraint5.setFraction(0.5);
            constraint5.setUDistance(0);
            constraint5.setVDistance(20);
            return constraint5;
        
        case ExtensionLocation.OnSourceThird:
            final GmFractionalConnectionLocator constraint6 = new GmFractionalConnectionLocator();
            constraint6.setFraction(0.25);
            constraint6.setUDistance(0);
            constraint6.setVDistance(0);
            return constraint6;
        
        case ExtensionLocation.OnTargetThird:
            final GmFractionalConnectionLocator constraint7 = new GmFractionalConnectionLocator();
            constraint7.setFraction(0.75);
            constraint7.setUDistance(0);
            constraint7.setVDistance(0);
            return constraint7;
        
        default:
            throw new IllegalArgumentException("'" + locationKey + "' is not supported");
        }
        
    }

    /**
     * This method is final. Subclasses should override {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("c7cd5444-9fd8-424c-a8ec-6ec6055dbd0f")
    private final void read_0(IDiagramReader in) {
        read_1(in);
        
        // Ask subclasses to fill extension roles.
        read_GmLinkV0_roles();
        
    }

    /**
     * This method is final. Subclasses should override {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("80199766-1dec-11e2-8cad-001ec947c8cc")
    private final void read_1(IDiagramReader in) {
        read_3(in);
        
        // Make sure the link belongs to the proper diagram
        IGmPath path = getPath();
        if (updateDiagram()) {
            // Link is now in an embedded diagram, fix its path in a post load action, because it needs the figure
            if (path != null && path.getRouterKind() != ConnectionRouterId.DIRECT) {
                getDiagram().addPostLoadAction(new LinkPathFixer(this));
            }
        }
        
        // Handle orthogonal router changes as a post load action, because it needs the figure to exist
        if (path != null && path.getRouterKind() == ConnectionRouterId.ORTHOGONAL) {
            getDiagram().addPostLoadAction(new OrthogonalPathFixer3_7(this));
            getDiagram().addPostLoadAction(new OrthogonalPathFixer5_0_2(this));
        }
        
    }

    @objid ("abc625fd-3461-42c9-b17f-79a33a6c14bd")
    private void toReferenceChanged(PropertyChangeEvent ev) {
        assert this.to == ev.getSource() : this.to + " != " + ev.getSource();
        
        if (this.to != null) {
            final IGmLinkable node = this.to.getReferencedModel();
            if (!node.getEndingLinks().contains(this)) {
                node.addEndingLink(this);
            }
        }
        
    }

    @objid ("b1b94718-47e5-4dc4-9c26-e2544415e3d8")
    private void updateFromReferenceListeners(IGmReference<IGmLinkable> oldFrom, IGmReference<IGmLinkable> newFrom) {
        if (oldFrom != null) {
            oldFrom.removeReferenceChangeListener(this.fromReferenceListener);
            oldFrom.releaseGmReference();
        }
        if (newFrom != null) {
            newFrom.addReferenceResolvedListener(this.fromReferenceListener);
        }
        
    }

    @objid ("1ce8b1e4-8ff7-41e2-8faf-87412de5cf16")
    private void updateToReferenceListeners(IGmReference<IGmLinkable> oldTo, IGmReference<IGmLinkable> newTo) {
        if (oldTo != null) {
            oldTo.removeReferenceChangeListener(this.toReferenceListener);
            oldTo.releaseGmReference();
        }
        if (newTo != null) {
            newTo.addReferenceResolvedListener(this.toReferenceListener);
        }
        
    }

    /**
     * This method is final. Subclasses should override {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("1afa293a-3071-45db-b66c-9967cea02cb9")
    private final void read_2(IDiagramReader in) {
        read_3(in);
        
        IGmPath path = getPath();
        
        // Handle orthogonal router changes as a post load action, because it needs the figure to exist
        if (path != null && path.getRouterKind() == ConnectionRouterId.ORTHOGONAL) {
            getDiagram().addPostLoadAction(new OrthogonalPathFixer5_0_2(this));
        }
        
    }

    /**
     * This method is final. Subclasses should override {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("818e89ec-e7c9-4e9a-b3e7-0e5f1b67cb21")
    private final void read_3(IDiagramReader in) {
        super.read(in);
        this.from = GmReference.read(in, "Source");
        this.to = GmReference.read(in, "Dest");
        this.extensions.putAll(in.readMapProperty("extensions"));
        
        final Object sourceAnchor = getPath().getSourceAnchor();
        if (sourceAnchor instanceof GmAbstractLinkAnchor) {
            ((GmAbstractLinkAnchor) sourceAnchor).addLink(this);
        }
        final Object targetAnchor = getPath().getTargetAnchor();
        if (targetAnchor instanceof GmAbstractLinkAnchor) {
            ((GmAbstractLinkAnchor) targetAnchor).addLink(this);
        }
        
        // Read child classes data
        readLink(in);
        
        // Connect extensions
        for (GmNodeModel ext : this.extensions.keySet()) {
            ext.setParentLink(this);
        }
        
        // Connect extremities
        if (this.from != null && this.from.isReferencedModelValid()) {
            this.from.getReferencedModel().addStartingLink(this);
        }
        if (this.to != null && this.to.isReferencedModelValid()) {
            this.to.getReferencedModel().addEndingLink(this);
        }
        
        updateFromReferenceListeners(null, this.from);
        updateToReferenceListeners(null, this.to);
        
    }

    @objid ("a5883e81-65bb-4f52-a8a8-97ce31b1a145")
    @Override
    public boolean updateDiagram() {
        IGmDiagram myDiagram = getDiagram();
        IGmDiagram myNewDiagram = myDiagram;
        IGmLinkable lSrc = getFrom();
        IGmLinkable lTarget = getTo();
        IGmDiagram sourceDiagram = lSrc != null ? lSrc.getDiagram() : null;
        IGmDiagram targetDiagram = lTarget != null ? lTarget.getDiagram() : null;
        
        if (sourceDiagram == null || targetDiagram == null) {
            return false;
        }
        
        if (sourceDiagram == targetDiagram) {
            myNewDiagram = sourceDiagram;
        } else {
            myNewDiagram = IGmDiagram.getCommonDiagramOwner(sourceDiagram, targetDiagram);
        }
        
        if (myNewDiagram == myDiagram || myNewDiagram == null) {
            return false;
        }
        
        // Change the diagram
        moveToDiagram(myNewDiagram);
        
        // Propagate to source and target refs
        if (this.from != null) {
            this.from.setOwnerDiagram(myNewDiagram);
        }
        if (this.to != null) {
            this.to.setOwnerDiagram(myNewDiagram);
        }
        
        // Propagate to extensions
        for (GmNodeModel gm : getExtensions()) {
            gm.updateDiagram();
        }
        
        // Propagate to links on this link
        final IGmDiagram lNewDiagram = myNewDiagram;
        if (this.startingLinks != null) {
            this.startingLinks.forEach(r -> {
                r.setOwnerDiagram(lNewDiagram);
                if (r.isReferencedModelValid()) {
                    r.getReferencedModel().updateDiagram();
                }
            });
        }
        if (this.endingLinks != null) {
            this.endingLinks.forEach(r -> {
                r.setOwnerDiagram(lNewDiagram);
                if (r.isReferencedModelValid()) {
                    r.getReferencedModel().updateDiagram();
                }
            });
        }
        return true;
    }

}

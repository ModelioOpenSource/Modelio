/* 
 * Copyright 2013-2019 Modeliosoft
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.HandleBounds;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.routers.OrthogonalRouter;
import org.modelio.diagram.elements.core.link.extensions.GmConnectionEndpoinLocator;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.GmReference;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmNode;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.model.IGmReference;
import org.modelio.diagram.elements.core.model.IPostLoadAction;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
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
     * <li>2 : source and target are now IGmReferences
     * </ul>
     */
    @objid ("80127036-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 2;

    /**
     * Tells the source element changed and is inconsistent from the source
     * node.
     */
    @objid ("8fb061e4-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROP_SOURCE_EL = "Source element model changed";

    /**
     * Tells the source node changed.
     */
    @objid ("8fadff89-1e83-11e2-8cad-001ec947c8cc")
    public static final String PROP_SOURCE_GM = "Source graphic model changed";

    /**
     * Tells the target element changed and is inconsistent from the terget
     * node.
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
     * 
     * @param diagram The diagram containing the link.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("8012703b-1dec-11e2-8cad-001ec947c8cc")
    public GmLink(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.fromReferenceListener = this::fromReferenceChanged;
        this.toReferenceListener = this::toReferenceChanged;
        initGmLink();
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("80127040-1dec-11e2-8cad-001ec947c8cc")
    public GmLink() {
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
     * 
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
     * 
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
     * Delete its links, extensions and then detach from its source and
     * destination.
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
     * 
     * @param gmLinkAnchor The moved anchor.
     */
    @objid ("8019974a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void fireAnchorMoved(GmAbstractLinkAnchor gmLinkAnchor) {
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, gmLinkAnchor);
    }

    /**
     * Fires a
     * {@link org.modelio.diagram.elements.core.model.IGmObject#PROPERTY_CHILDREN
     * PROPERTY_CHILDREN} property change.
     * <p>
     * To be called when the result of {@link GmNodeModel#isVisible()} on the
     * given link extension changes.
     * 
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
     * Link extensions are roundly all labels related to the link, eg:
     * association role name and cardinality.
     * 
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
     * Returns the element being the source of the represented link (in the Ob
     * model).
     * <p>
     * May return <code>null</code> if {@link #getRelatedElement()} returns
     * <code>null</code>.
     * <p>
     * <em>This method must <strong>NOT</strong> return
     * "<code>this.getFrom().getElement();</code>" but instead must read the
     * actual source of the link returned by {@link #getRelatedElement()}.</em>
     * 
     * @return the element being the source of the represented link (in the Ob
     * model).
     */
    @objid ("8014d2b4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public abstract MObject getFromElement();

    /**
     * Get the locator model used to layout the given extension.
     * 
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
     * 
     * @return the source anchor.
     */
    @objid ("8014d2ce-1dec-11e2-8cad-001ec947c8cc")
    public final Object getSourceAnchor() {
        return getPath().getSourceAnchor();
    }

    /**
     * Get the links starting from this node.
     * 
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
     * 
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
     * Returns the element being the target of the represented link (in the Ob
     * model).
     * <p>
     * <em>This methods must <strong>NOT</strong> return
     * "<code>this.getTo().getElement();</code>" but instead must read the
     * actual target of the link returned by {@link #getRelatedElement()}.</em>
     * <p>
     * May return <code>null</code> if {@link #getRelatedElement()} returns
     * <code>null</code>.
     * 
     * @return the element being the target of the represented link (in the Ob
     * model).
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
     * Default implementation returns a list of all extensions for which the
     * isVisible method returns <code>true</code>. This method may be overridden
     * to dynamically filter the extensions list, based on current
     * representation mode for example.<br>
     * In this case you must ensure that {@link #styleChanged(StyleKey, Object)}
     * fires a {@link IGmObject#PROPERTY_CHILDREN} property change event in
     * order for the EditParts to be informed of the change.<br>
     * </p>
     * 
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
    @objid ("801734f7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void obElementAdded(MObject addedEl) {
        super.obElementAdded(addedEl);
        
        for (GmNodeModel m : getExtensions()) {
            if (m.getRepresentedElement() == null) {
                m.obElementAdded(addedEl);
            }
        }
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
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 2
            read_2(in);
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
     * 
     * @param gmNodeModel the link extension to remove.
     * @throws java.lang.IllegalArgumentException if the link does not own this node.
     */
    @objid ("8017350b-1dec-11e2-8cad-001ec947c8cc")
    public void removeExtension(GmNodeModel gmNodeModel) throws IllegalArgumentException {
        assert (this.extensions.containsKey(gmNodeModel));
        
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
     * This method is intended to be called only by
     * {@link IGmLinkable#addEndingLink(IGmLink)}.
     * It does fire {@link #PROP_SOURCE_GM} change event.
     * 
     * @param from The new link origin
     */
    @objid ("80173514-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setFrom(IGmLinkable from) {
        IGmReference<IGmLinkable> oldFrom = this.from;
        if (from != IGmReference.resolve(oldFrom)) {
            this.from = (from == null) ? null : new GmReference<>(this, from);
        
            updateFromReferenceListeners(oldFrom, this.from);
            firePropertyChange(GmLink.PROP_SOURCE_GM, oldFrom, from);
        }
    }

    /**
     * Change the given extension location.
     * 
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
                getPath().setRouterKind(newPath.getRouterKind());
        
                // Update the connection router in the style.
                final StyleKey routerStyleKey = getStyleKey(MetaKey.CONNECTIONROUTER);
                if (routerStyleKey != null && style.getProperty(routerStyleKey) != newPath.getRouterKind()) {
                    style.setProperty(routerStyleKey, newPath.getRouterKind());
                }
            }
        }
        
        // FIXME: Update anchors connected links
        Object oldSrcAnchor = null;
        Object oldDestAnchor = null;
        
        if (oldPath != null) {
            oldSrcAnchor = oldPath.getSourceAnchor();
            oldDestAnchor = oldPath.getTargetAnchor();
        
            if (oldSrcAnchor instanceof GmAbstractLinkAnchor) {
                ((GmAbstractLinkAnchor) oldSrcAnchor).removeLink(this);
            }
        
            if (oldDestAnchor instanceof GmAbstractLinkAnchor) {
                ((GmAbstractLinkAnchor) oldDestAnchor).removeLink(this);
            }
        }
        
        if (newPath != null) {
            final Object newSrcAnchor = newPath.getSourceAnchor();
            final Object newDestAnchor = newPath.getTargetAnchor();
        
            if (newSrcAnchor instanceof GmAbstractLinkAnchor && newSrcAnchor != oldSrcAnchor) {
                ((GmAbstractLinkAnchor) newSrcAnchor).addLink(this);
            }
        
            if (newDestAnchor instanceof GmAbstractLinkAnchor && newDestAnchor != oldDestAnchor) {
                ((GmAbstractLinkAnchor) newDestAnchor).addLink(this);
            }
        }
        
        // Change the path
        super.setLayoutData(layoutData);
    }

    /**
     * Update the link destination.
     * <p>
     * This method is intended to be called only by
     * {@link IGmLinkable#addEndingLink(IGmLink)}.
     * It does fire {@link #PROP_TARGET_GM} change event.
     * 
     * @param to The new destination
     */
    @objid ("8017352f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTo(IGmLinkable to) {
        IGmReference<IGmLinkable> oldTo = this.to;
        if (to != IGmReference.resolve(oldTo)) {
            this.to = (to == null) ? null : new GmReference<>(this, to);
        
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
     * Get the connection router id stored in the given style. If no StyleKey is
     * found, the default value for the router is DIRECT.
     * 
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
     * 
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
     * Redefinable method called by {@link GmLink#read(IDiagramReader)} before
     * adding the link to the diagram.
     * <p>
     * Subclasses should redefine this method instead of
     * {@link #read(IDiagramReader)}.
     * <p>
     * The default implementation does nothing.
     * 
     * @param in a reader to build the graphic model from.
     */
    @objid ("8019975d-1dec-11e2-8cad-001ec947c8cc")
    protected void readLink(IDiagramReader in) {
        // Nothing to do
    }

    /**
     * This method must guess the link extension role from whatever is available
     * from a GmLink with 0 as minor version.
     * <p>
     * It is called by {@link GmLink#read(IDiagramReader)} for V0 {@link GmLink}
     * , when link extension role was not filled, to ask subclasses to fill
     * them.
     */
    @objid ("27eed107-63db-4413-a6c3-bc2e2025e698")
    protected abstract void read_GmLinkV0_roles();

    /**
     * Convenience implementation to call from {@link #read_GmLinkV0_roles()}
     * when the link has only one main label.
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
        assert (this.from == ev.getSource()) : this.to + " != " + ev.getSource();
        
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
             * final GmConnectionLocator constraint = new GmConnectionLocator();
             * constraint.setAlignment(ConnectionLocator.MIDDLE);
             * constraint.setRelativePosition(PositionConstants.SOUTH_EAST);
             * constraint.setGap(5);
             */
            final GmFractionalConnectionLocator constraint4 = new GmFractionalConnectionLocator();
            constraint4.setFraction(0.5);
            constraint4.setUDistance(0);
            constraint4.setVDistance(-20);
            return constraint4;
        
        case ExtensionLocation.MiddleNW:
            /*
             * final GmConnectionLocator constraint = new GmConnectionLocator();
             * constraint.setAlignment(ConnectionLocator.MIDDLE);
             * constraint.setRelativePosition(PositionConstants.NORTH_WEST);
             * constraint.setGap(5);
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
     * This method is final. Subclasses should override
     * {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("c7cd5444-9fd8-424c-a8ec-6ec6055dbd0f")
    private final void read_0(IDiagramReader in) {
        read_1(in);
        
        // Ask subclasses to fill extension roles.
        read_GmLinkV0_roles();
    }

    /**
     * This method is final. Subclasses should override
     * {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("80199766-1dec-11e2-8cad-001ec947c8cc")
    private final void read_1(IDiagramReader in) {
        read_2(in);
        
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
            getDiagram().addPostLoadAction(new OrthogonalPathFixer(this));
        }
    }

    @objid ("abc625fd-3461-42c9-b17f-79a33a6c14bd")
    private void toReferenceChanged(PropertyChangeEvent ev) {
        assert (this.to == ev.getSource()) : this.to + " != " + ev.getSource();
        
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
     * This method is final. Subclasses should override
     * {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("1afa293a-3071-45db-b66c-9967cea02cb9")
    private final void read_2(IDiagramReader in) {
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

    /**
     * With Modelio 3.7, the orthogonal router changed a little, making it sometimes necessary to adapt the layout data before using the new router.
     * <p>
     * This class compares the point list gotten from {@link OrthogonalRouter} and {@link OldOrthogonalRouter}, and updates the
     * {@link GmPath} of the {@link GmLink} if needed to keep the same looks for old orthogonal links.
     * </p>
     */
    @objid ("1b9c1d23-45ce-4831-a052-0296d9ba4939")
    private static final class OrthogonalPathFixer implements IPostLoadAction {
        @objid ("96b4a2bf-91b6-4f89-a887-b29688936686")
        private GmLink gmLink;

        @objid ("330a7be9-8749-422b-8be8-b69a724d8931")
        public OrthogonalPathFixer(GmLink gmLink) {
            this.gmLink = gmLink;
        }

        @objid ("d25bf6fc-ed49-4246-9c39-85a1f3b02d77")
        @Override
        public void run(EditPartViewer viewer) {
            if (this.gmLink.isValid()) {
                GraphicalEditPart linkEditPart = (GraphicalEditPart) viewer.getEditPartRegistry().get(this.gmLink);
                if (linkEditPart != null && linkEditPart.getFigure() instanceof Connection) {
                    Connection connection = (Connection) linkEditPart.getFigure();
                    List<Point> pointList = computeFixedPointList(connection);
                    if (pointList != null) {
                        applyLayoutData(pointList);
                    }
                }
            }
        }

        /**
         * Compares the point list gotten from {@link OrthogonalRouter} and {@link OldOrthogonalRouter}.
         * 
         * @param connection the connection being routed.
         * @return a list of points when a layout fix is needed, <code>null</code> otherwise.
         */
        @objid ("766715e6-b676-46df-9583-d83238ce755c")
        private static List<Point> computeFixedPointList(Connection connection) {
            if (!(connection.getConnectionRouter() instanceof OrthogonalRouter)) {
                return null;
            }
            
            OrthogonalRouter newRouter = (OrthogonalRouter) connection.getConnectionRouter();
            PointList newPointList = newRouter.computePointList(connection);
            PointList oldPointList = new OldOrthogonalRouter().computePointList(connection, newRouter);
            
            if (oldPointList.size() >= 2 && newPointList.size() >= 2) {
                // Keep point list unchanged when anchors are not properly initialized (aka both equals to (0, 0))
                if (newPointList.getFirstPoint().equals(new Point()) && newPointList.getFirstPoint().equals(newPointList.getLastPoint())) {
                    return null;
                }
            
                // Remove first and last points, handled by anchors
                oldPointList.removePoint(oldPointList.size() - 1);
                newPointList.removePoint(newPointList.size() - 1);
                oldPointList.removePoint(0);
                newPointList.removePoint(0);
            
                // Check at least one bendpoint is different
                if (isContentDifferent(newPointList, oldPointList)) {
                    // Set old router points as bend points for new router
                    List<Point> newPathData = new ArrayList<>(oldPointList.size());
                    for (int i = 0; i < oldPointList.size(); i++) {
                        Point point = oldPointList.getPoint(i);
                        newPathData.add(point);
                    }
                    return newPathData;
                }
            }
            // No fix needed
            return null;
        }

        /**
         * Update the layout data of the {@link #gmLink}.
         * 
         * @param newPathData a list of points.
         */
        @objid ("1a5abd92-eaaa-4782-8c7e-47bf3a2efce4")
        private void applyLayoutData(List<Point> newPathData) {
            GmPath newGmPath = new GmPath(this.gmLink.getPath());
            newGmPath.setPathData(newPathData);
            this.gmLink.setLayoutData(newGmPath);
        }

        @objid ("207bedcb-73ac-4887-9043-74a66fc1c526")
        private static boolean isContentDifferent(PointList newPointList, PointList oldPointList) {
            if (oldPointList.size() != newPointList.size()) {
                return true;
            }
            return !Arrays.equals(newPointList.toIntArray(), oldPointList.toIntArray());
        }

        /**
         * Pre-Modelio 3.7 of the orthogonal router, which has been replaced with {@link OrthogonalRouter}.
         * <p>
         * Routes {@link Connection}s through a <code>List</code> of {@link Bendpoint Bendpoints} that make an orthogonal path.
         * </p>
         * <p>
         * The route constraint is modified to be made orthogonal.
         * </p>
         */
        @objid ("3ac3753f-cbf9-40a7-9ed0-e06be14414cb")
        private static final class OldOrthogonalRouter {
            /**
             * Temporary point used to avoid Point allocations.
             */
            @objid ("ab8db3b4-32d3-4405-8e1a-301a0d997d1f")
            private static final PrecisionPoint A_POINT = new PrecisionPoint();

            /**
             * Compute a list of points to use when routing the connection.
             * 
             * @param connection an orthogonal connection.
             * @param orthogonalRouter the actual orthogonal router holding the connection's constraint.
             * @return a List of Points
             */
            @objid ("58f4f08d-27af-40c0-8675-4f39222ddcfa")
            public PointList computePointList(Connection connection, OrthogonalRouter orthogonalRouter) {
                final ConnectionAnchor sourceAnchor = connection.getSourceAnchor();
                final ConnectionAnchor targetAnchor = connection.getTargetAnchor();
                
                final List<Bendpoint> allPoints = computeInitialBendpointsList(connection, sourceAnchor, targetAnchor, orthogonalRouter);
                
                // Source and target locations are now fixed, we are not allowed to move them anymore.
                Point sourceLocation = allPoints.get(0).getLocation();
                Point targetLocation = allPoints.get(allPoints.size() - 1).getLocation();
                
                // Now the tricky part: fix the first and last bend points to form an orthogonal path.
                final Rectangle sourceRelativeBounds = getAnchorOwnerAbsoluteBounds(sourceAnchor).expand(1, 1);
                connection.translateToRelative(sourceRelativeBounds);
                final Rectangle targetRelativeBounds = getAnchorOwnerAbsoluteBounds(targetAnchor).expand(1, 1);
                connection.translateToRelative(targetRelativeBounds);
                Direction sourceAnchorOrientation = GeomUtils.getDirection(sourceLocation, sourceRelativeBounds);
                Direction targetAnchorOrientation = GeomUtils.getDirection(targetLocation, targetRelativeBounds);
                if (allPoints.size() == 2) {
                    fixNoBendpointsLink(allPoints, sourceLocation, targetLocation, sourceAnchorOrientation, targetAnchorOrientation);
                } else if (allPoints.size() == 3) {
                    fixOneBendpointLink(allPoints, sourceLocation, targetLocation, sourceAnchorOrientation, targetAnchorOrientation);
                
                } else {
                    fixSeveralBendpointsLink(allPoints, sourceLocation, targetLocation, sourceAnchorOrientation, targetAnchorOrientation);
                }
                
                // Some cleanup of useless allPoints.
                cleanup(allPoints);
                
                // Clear the old points list
                final PointList points = new PointList(allPoints.size());
                for (int i = 0; i < allPoints.size(); i++) {
                    Bendpoint bp = allPoints.get(i);
                    points.addPoint(bp.getLocation());
                }
                return points;
            }

            /**
             * convenience method to get the constraint as a list of bend points.
             * 
             * @param connection a connection figure
             * @return The list of bend points.
             */
            @objid ("21569050-7d2a-481b-a948-21435abc521f")
            @SuppressWarnings ("unchecked")
            private List<Bendpoint> getBendpoints(Connection connection, OrthogonalRouter orthogonalRouter) {
                return (List<Bendpoint>) orthogonalRouter.getConstraint(connection);
            }

            /**
             * Get the anchor owner (handle)bounds in absolute coordinates. If the anchor is not attached to a figure, returns a
             * 1x1 sized rectangle located at the anchor reference point.
             * 
             * @param anchor The anchor.
             * @return The anchor owner bounds.
             */
            @objid ("7d7cce30-44d2-4200-9876-d35a0664f228")
            private Rectangle getAnchorOwnerAbsoluteBounds(ConnectionAnchor anchor) {
                final IFigure f = anchor.getOwner();
                if (f == null) {
                    Point p = anchor.getReferencePoint();
                    return new Rectangle(p.x, p.y, 1, 1);
                } else {
                    PrecisionRectangle bounds = new PrecisionRectangle(f instanceof HandleBounds
                            ? ((HandleBounds) f).getHandleBounds() : f.getBounds());
                    f.translateToAbsolute(bounds);
                
                    return bounds;
                }
            }

            /**
             * @param allPoints point list to clean unnecessary bend points from.
             */
            @objid ("2e45e580-401f-4a3f-8dae-7aa28491d1e1")
            private void cleanup(final List<Bendpoint> allPoints) {
                // Finish by removing unnecessary points:
                // 1: overlapping points.
                List<Integer> indexesToRemove = new ArrayList<>();
                for (int i = 1; i < allPoints.size() - 2; ++i) {
                    Point p1 = allPoints.get(i).getLocation();
                    Point p2 = allPoints.get(i + 1).getLocation();
                
                    if (p1.getDistance(p2) < 1) {
                        indexesToRemove.add(i);
                    }
                }
                for (int i = indexesToRemove.size() - 1; i >= 0; --i) {
                    allPoints.remove(indexesToRemove.get(i).intValue());
                }
                // 2: allPoints not bending
                indexesToRemove.clear();
                for (int i = 1; i < allPoints.size() - 1; ++i) {
                    if (allPoints.get(i - 1).getLocation().x == allPoints.get(i + 1).getLocation().x ||
                            allPoints.get(i - 1).getLocation().y == allPoints.get(i + 1).getLocation().y) {
                        indexesToRemove.add(i);
                    }
                }
                for (int i = indexesToRemove.size() - 1; i >= 0; --i) {
                    allPoints.remove(indexesToRemove.get(i).intValue());
                }
            }

            @objid ("338eb760-f58d-43fc-8b87-6deab878bd95")
            private void fixSeveralBendpointsLink(final List<Bendpoint> bendpoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
                // If there are at least 2 intermediary bend points, fix them to get orthogonal segments
                Point fixedPoint = bendpoints.get(1).getLocation();
                Point nextPoint = bendpoints.get(2).getLocation();
                Orientation nextSegmentOrientation = Orientation.NONE;
                if (fixedPoint.x == nextPoint.x) {
                    nextSegmentOrientation = Orientation.VERTICAL;
                } else if (fixedPoint.y == nextPoint.y) {
                    nextSegmentOrientation = Orientation.HORIZONTAL;
                } else {
                    assert (false) : "impossible to determine orientation of start segment, something is wrong with the provided list of bendpoints!";
                }
                if (sourceAnchorOrientation == Direction.NONE) {
                    if (nextSegmentOrientation == Orientation.VERTICAL) {
                        // next segment is vertical, so first was horizontal
                        fixedPoint.y = sourceLocation.y;
                    } else if (nextSegmentOrientation == Orientation.HORIZONTAL) {
                        // next segment is horizontal so first is vertical
                        fixedPoint.x = sourceLocation.x;
                    }
                } else if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
                    // First segment is vertical: align the X coordinates
                    // check that we don't need an additional bend point (next segment must be horizontal) first
                    if (nextSegmentOrientation != Orientation.HORIZONTAL) {
                        // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                        bendpoints.add(1, null);
                        fixedPoint = new Point(fixedPoint);
                    }
                    fixedPoint.x = sourceLocation.x;
                } else {
                    // First segment is horizontal: align the Y coordinates
                    // check that we don't need an additional bend point (next segment must be vertical) first
                    if (nextSegmentOrientation != Orientation.VERTICAL) {
                        // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                        bendpoints.add(1, null);
                        fixedPoint = new Point(fixedPoint);
                    }
                    fixedPoint.y = sourceLocation.y;
                }
                AbsoluteBendpoint fixedBendpoint = new AbsoluteBendpoint(fixedPoint);
                bendpoints.set(1, fixedBendpoint);
                
                int lastBendpointIndex = bendpoints.size() - 2;
                fixedPoint = bendpoints.get(lastBendpointIndex).getLocation();
                nextPoint = bendpoints.get(lastBendpointIndex - 1).getLocation();
                Orientation previousSegmentOrientation = Orientation.NONE;
                if (fixedPoint.x == nextPoint.x) {
                    previousSegmentOrientation = Orientation.VERTICAL;
                } else if (fixedPoint.y == nextPoint.y) {
                    previousSegmentOrientation = Orientation.HORIZONTAL;
                } else {
                    assert (false) : "impossible to determine orientation of last segment, something is wrong with the provided list of bendpoints!";
                }
                if (targetAnchorOrientation == Direction.NONE) {
                    // Target anchor is not oriented, deduct orientation from previous segment if possible.
                    if (previousSegmentOrientation == Orientation.VERTICAL) {
                        // previous segment is vertical, so first was horizontal
                        fixedPoint.y = targetLocation.y;
                    } else if (previousSegmentOrientation == Orientation.HORIZONTAL) {
                        // previous segment is horizontal so first is vertical
                        fixedPoint.x = targetLocation.x;
                    }
                } else if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
                    // Last segment is vertical: align the X coordinates
                    // Check that we don't need an additional bend point (previous segment must be horizontal) first
                    if (previousSegmentOrientation != Orientation.HORIZONTAL) {
                        // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                        ++lastBendpointIndex;
                        bendpoints.add(lastBendpointIndex, null);
                        fixedPoint = new Point(fixedPoint);
                    }
                    fixedPoint.x = targetLocation.x;
                } else {
                    // Last segment is horizontal: align the Y coordinates
                    // Check that we don't need an additional bend point (previous segment must be vertical) first
                    if (previousSegmentOrientation != Orientation.VERTICAL) {
                        // Add an additional bendpoint (null is allright, it will be replaced later anyway).
                        ++lastBendpointIndex;
                        bendpoints.add(lastBendpointIndex, null);
                        fixedPoint = new Point(fixedPoint);
                    }
                    fixedPoint.y = targetLocation.y;
                }
                fixedBendpoint = new AbsoluteBendpoint(fixedPoint);
                bendpoints.set(lastBendpointIndex, fixedBendpoint);
            }

            @objid ("7ae932ef-40bf-4c17-8758-c17339982d2a")
            private void fixNoBendpointsLink(final List<Bendpoint> allPoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
                // If there is no intermediary bend point, check whether the anchors location are aligned, and add bend point(s) if not.
                if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
                    if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
                        if (sourceLocation.x != targetLocation.x) {
                            // No luck: not aligned, we need 2 additional bend points.
                            OldOrthogonalRouter.A_POINT.setLocation(sourceLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                            allPoints.add(1, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                            OldOrthogonalRouter.A_POINT.setLocation(targetLocation.x, (sourceLocation.y + targetLocation.y) / 2);
                            allPoints.add(2, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                        }
                        // else: good luck: both anchors are aligned, nothing to do!
                    } else {
                        // We need an additional bend point.
                        OldOrthogonalRouter.A_POINT.setLocation(sourceLocation.x, targetLocation.y);
                        allPoints.add(1, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                    }
                } else {
                    if (targetAnchorOrientation == Direction.NONE) {
                        // Not oriented target anchor: we might need an additional bend point.
                        if (sourceLocation.y != targetLocation.y) {
                            // No luck, anchors are not aligned, we need a bend point.
                            OldOrthogonalRouter.A_POINT.setLocation(targetLocation.x, sourceLocation.y);
                            allPoints.add(1, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                        }
                        // else: good luck, both anchors are aligned, nothing to do!
                    } else if (targetAnchorOrientation == Direction.SOUTH ||
                            targetAnchorOrientation == Direction.NORTH) {
                        // We need an additional bend point
                        OldOrthogonalRouter.A_POINT.setLocation(targetLocation.x, sourceLocation.y);
                        allPoints.add(1, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                    } else {
                        if (sourceLocation.y != targetLocation.y) {
                            // No luck: not aligned, we need 2 additional bend points.
                            OldOrthogonalRouter.A_POINT.setLocation((sourceLocation.x + targetLocation.x) / 2, sourceLocation.y);
                            allPoints.add(1, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                            OldOrthogonalRouter.A_POINT.setLocation((sourceLocation.x + targetLocation.x) / 2, targetLocation.y);
                            allPoints.add(2, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                        }
                        // else: good luck: both anchors are aligned, nothing to do!
                    }
                }
            }

            @objid ("c52991f4-1c4a-49af-bf9d-f13dee0237df")
            private List<Bendpoint> computeInitialBendpointsList(final Connection connection, final ConnectionAnchor sourceAnchor, final ConnectionAnchor targetAnchor, OrthogonalRouter orthogonalRouter) {
                List<Bendpoint> origBendpoints = getBendpoints(connection, orthogonalRouter);
                if (origBendpoints == null) {
                    origBendpoints = Collections.emptyList();
                }
                final List<Bendpoint> allPoints = new ArrayList<>();
                
                // Let's assume the first point is the source anchor reference point (This may be modified later).
                OldOrthogonalRouter.A_POINT.setLocation(sourceAnchor.getReferencePoint());
                connection.translateToRelative(OldOrthogonalRouter.A_POINT);
                allPoints.add(new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                // Now assume the given allPoints are good (we'll fix them later if needed)
                for (Bendpoint bendpoint : origBendpoints) {
                    allPoints.add(new AbsoluteBendpoint(bendpoint.getLocation()));
                }
                // End with the target anchor reference point
                OldOrthogonalRouter.A_POINT.setLocation(targetAnchor.getReferencePoint());
                connection.translateToRelative(OldOrthogonalRouter.A_POINT);
                allPoints.add(new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                
                final Rectangle srcBounds = getAnchorOwnerAbsoluteBounds(sourceAnchor).expand(1, 1);
                connection.translateToRelative(srcBounds);
                final Rectangle targetBounds = getAnchorOwnerAbsoluteBounds(targetAnchor).expand(1, 1);
                connection.translateToRelative(targetBounds);
                
                // Cleanup some useless points if needed at the beginning
                boolean sourceContainsTarget = srcBounds.contains(targetBounds);
                if (!sourceContainsTarget) {
                    // Remove from the beginning of the list all allPoints until the first outside the source bounds.
                    // We want to keep at least 2 points (source and target anchor reference point)
                    while (allPoints.size() > 2 && srcBounds.contains(allPoints.get(1).getLocation())) {
                        allPoints.remove(1);
                    }
                }
                
                // Cleanup some useless points if needed at the end
                boolean targetContainsSource = targetBounds.contains(srcBounds);
                if (!targetContainsSource) {
                    // Remove from the end of the list all allPoints until the first outside the target bounds.
                    // We want to keep at least 2 points (source and target anchor reference point)
                    while (allPoints.size() > 2 &&
                            targetBounds.contains(allPoints.get(allPoints.size() - 2).getLocation())) {
                        allPoints.remove(allPoints.size() - 2);
                    }
                }
                
                // Now compute the actual location of the source anchor, based on the next bendpoint (might be the target anchor reference point).
                OldOrthogonalRouter.A_POINT.setLocation(allPoints.get(1).getLocation());
                connection.translateToAbsolute(OldOrthogonalRouter.A_POINT);
                OldOrthogonalRouter.A_POINT.setLocation(sourceAnchor.getLocation(OldOrthogonalRouter.A_POINT));
                connection.translateToRelative(OldOrthogonalRouter.A_POINT);
                // Use that value in the list, instead of the reference point.
                allPoints.set(0, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                
                // Now compute the actual location of the target anchor, based on the previous bendpoint (might be the source anchor location point).
                int index = allPoints.size() - 1;
                OldOrthogonalRouter.A_POINT.setLocation(allPoints.get(index - 1).getLocation());
                connection.translateToAbsolute(OldOrthogonalRouter.A_POINT);
                OldOrthogonalRouter.A_POINT.setLocation(targetAnchor.getLocation(OldOrthogonalRouter.A_POINT));
                connection.translateToRelative(OldOrthogonalRouter.A_POINT);
                // Use that value in the list, instead of the reference point.
                allPoints.set(index, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                return allPoints;
            }

            @objid ("ef351125-9298-4a31-812f-eb5ca4b26715")
            private void fixOneBendpointLink(final List<Bendpoint> bendpoints, final Point sourceLocation, final Point targetLocation, final Direction sourceAnchorOrientation, final Direction targetAnchorOrientation) {
                // If there is only 1 intermediary bend point, try to fix it or add another bend point if needed
                Point fixedPoint = bendpoints.get(1).getLocation();
                if (sourceAnchorOrientation == Direction.NORTH || sourceAnchorOrientation == Direction.SOUTH) {
                    fixedPoint.x = sourceLocation.x;
                    if (targetAnchorOrientation == Direction.NORTH || targetAnchorOrientation == Direction.SOUTH) {
                        // Unless the 3 points are aligned on the X axis, we are gonna need an additional bend point.
                        if (targetLocation.x != fixedPoint.x) {
                            OldOrthogonalRouter.A_POINT.setLocation(targetLocation.x, fixedPoint.y);
                            bendpoints.add(2, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                        }
                        // else: do nothing, the 3 points are aligned, the intermediary bendpoint will be removed during the cleanup phase.
                    } else {
                        fixedPoint.y = targetLocation.y;
                    }
                } else {
                    fixedPoint.y = sourceLocation.y;
                    if (targetAnchorOrientation == Direction.NORTH ||
                            targetAnchorOrientation == Direction.SOUTH ||
                            targetAnchorOrientation == Direction.NONE) {
                        fixedPoint.x = targetLocation.x;
                    } else {
                        // Unless the 3 points are aligned on the Y axis, we are gonna need an additional bend point.
                        if (targetLocation.y != fixedPoint.y) {
                            OldOrthogonalRouter.A_POINT.setLocation(fixedPoint.x, targetLocation.y);
                            bendpoints.add(2, new AbsoluteBendpoint(OldOrthogonalRouter.A_POINT));
                        }
                        // else: do nothing, the 3 points are aligned, the intermediary bendpoint will be removed during the cleanup phase.
                    }
                }
                AbsoluteBendpoint fixedBendpoint = new AbsoluteBendpoint(fixedPoint);
                bendpoints.add(1, fixedBendpoint);
                bendpoints.remove(2);
            }

        }

    }

    @objid ("dbfa292f-29a4-45a6-8257-f7d55b3ed510")
    private static class LinkPathFixer implements IPostLoadAction {
        @objid ("9ab1790a-3890-4b26-a080-c456eab24cbe")
        private GmLink gmLink;

        @objid ("9a55e16e-5b69-4025-8774-bfea42af1160")
        public LinkPathFixer(GmLink gmLink) {
            this.gmLink = gmLink;
        }

        @objid ("1f036c46-5cf1-4601-af03-7be3428aa9a9")
        @Override
        public void run(EditPartViewer viewer) {
            GraphicalEditPart linkEditPart = (GraphicalEditPart) viewer.getEditPartRegistry().get(this.gmLink);
            if (linkEditPart != null && linkEditPart.getFigure() instanceof Connection) {
                Connection connection = (Connection) linkEditPart.getFigure();
                List<Bendpoint> routingConstraint = (List<Bendpoint>) connection.getRoutingConstraint();
                if (routingConstraint.size() > 0) {
                    IFigure figure = ((GraphicalEditPart) ((GraphicalEditPart) viewer.getEditPartRegistry().get(this.gmLink.getDiagram())).getParent()).getFigure();
                    Rectangle originBounds = figure.getBounds().getCopy();
                    figure.translateToAbsolute(originBounds);
                    Point negated = originBounds.getTopLeft().getNegated();
            
                    List<Point> points = BendPointUtils.draw2dConstraintToModelConstraint(routingConstraint);
                    for (Point p : points) {
                        p.translate(negated);
                    }
                    applyLayoutData(points);
                }
            }
        }

        /**
         * Update the layout data of the {@link #gmLink}.
         * 
         * @param newPathData a list of points.
         */
        @objid ("445b27f2-6384-479c-9045-d12762e8f5c9")
        private void applyLayoutData(List<Point> newPathData) {
            GmPath newGmPath = new GmPath(this.gmLink.getPath());
            newGmPath.setPathData(newPathData);
            this.gmLink.setLayoutData(newGmPath);
        }

    }

}

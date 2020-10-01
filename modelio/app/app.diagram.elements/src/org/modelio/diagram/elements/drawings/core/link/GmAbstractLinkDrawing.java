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

package org.modelio.diagram.elements.drawings.core.link;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmAbstractLinkAnchor;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.extensions.GmConnectionEndpoinLocator;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLinkable;
import org.modelio.diagram.elements.drawings.core.node.GmNodeDrawing;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Represents a link between 2 nodes in the diagram.
 */
@objid ("c39f58ce-8b68-428a-b662-7a9ed0d85c18")
public abstract class GmAbstractLinkDrawing extends GmDrawing implements IGmDrawingLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d0758e6f-c2ef-424e-8d93-d02059d35c6d")
    private static final int MINOR_VERSION = 0;

    @objid ("fd1cf3f0-4a36-4741-a9c3-3955292a640c")
    private static final int MAJOR_VERSION = 0;

    @objid ("5b455c35-e7eb-4a04-9a94-4c69b6491af3")
    protected IGmDrawingLinkable from;

    @objid ("56c71222-5b7f-4a4e-a643-36bccab68c09")
    protected IGmDrawingLinkable to;

    @objid ("e4406a7e-e8ca-4133-b6e6-14b4502dbd9f")
    private Map<GmNodeDrawing, IGmLocator> extensions = new HashMap<>();

    @objid ("cd66b2aa-8e05-43f9-bd6e-59dbb589af92")
    private final List<IGmDrawingLink> startingLinks = new ArrayList<>();

    @objid ("af579b34-4410-4ff9-8e5f-2709cac70d0f")
    private final List<IGmDrawingLink> endingLinks = new ArrayList<>();

    /**
     * Initialize a new GmLink.
     * 
     * @param diagram The diagram containing the link.
     * @param identifier the drawing identifier, must be unique in the diagram.
     */
    @objid ("e6ba11da-2c10-44c2-8f70-9b6059ef559e")
    public GmAbstractLinkDrawing(IGmDiagram diagram, String identifier) {
        super(diagram, identifier);
        
        this.from = null;
        this.to = null;
        init();
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("ff8a48ea-735d-40df-aa73-47b4594191e6")
    public GmAbstractLinkDrawing() {
        init();
    }

    @objid ("d1428106-ad8c-47b9-a32c-3c3c0326d901")
    @Override
    public final void addEndingDrawingLink(final IGmDrawingLink link) {
        this.endingLinks.add(link);
        link.setTo(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, null, link);
    }

    /**
     * Add a link extension.
     * 
     * @param extension The link extension.
     * @param constraint The extension layout constraint.
     */
    @objid ("beff9d4f-8d57-4dcb-a81d-7086d92ddf68")
    public void addExtension(GmNodeDrawing extension, IGmLocator constraint) {
        this.extensions.put(extension, constraint);
        extension.setParentLink(this);
        firePropertyChange(PROPERTY_CHILDREN, null, extension);
    }

    /**
     * Add a link extension.
     * 
     * @param key extension key
     * @param extension the extension to add.
     */
    @objid ("58631994-d99d-48ac-8166-a6c121c516d2")
    public void addExtension(String key, GmNodeDrawing extension) {
        if (key.equals(ExtensionLocation.SourceNW)) {
            final GmConnectionEndpoinLocator constraint = new GmConnectionEndpoinLocator();
            constraint.setEnd(false);
            constraint.setVDistance(-5);
            constraint.setUDistance(5);
            this.extensions.put(extension, constraint);
        } else if (key.equals(ExtensionLocation.SourceSE)) {
            final GmConnectionEndpoinLocator constraint = new GmConnectionEndpoinLocator();
            constraint.setEnd(false);
            constraint.setVDistance(5);
            constraint.setUDistance(5);
            this.extensions.put(extension, constraint);
        } else if (key.equals(ExtensionLocation.TargetNW)) {
            final GmConnectionEndpoinLocator constraint = new GmConnectionEndpoinLocator();
            constraint.setEnd(true);
            constraint.setVDistance(-5);
            constraint.setUDistance(5);
        
            this.extensions.put(extension, constraint);
        } else if (key.equals(ExtensionLocation.TargetSE)) {
            final GmConnectionEndpoinLocator constraint = new GmConnectionEndpoinLocator();
            constraint.setEnd(true);
            constraint.setVDistance(5);
            constraint.setUDistance(5);
        
            this.extensions.put(extension, constraint);
        } else if (key.equals(ExtensionLocation.MiddleSE)) {
            /*final GmConnectionLocator constraint = new GmConnectionLocator();
            constraint.setAlignment(ConnectionLocator.MIDDLE);
            constraint.setRelativePosition(PositionConstants.SOUTH_EAST);
            constraint.setGap(5);*/
            final GmFractionalConnectionLocator constraint = new GmFractionalConnectionLocator();
            constraint.setFraction(0.5);
            constraint.setUDistance(0);
            constraint.setVDistance(-20);
        
            this.extensions.put(extension, constraint);
        } else if (key.equals(ExtensionLocation.MiddleNW)) {
            /*final GmConnectionLocator constraint = new GmConnectionLocator();
            constraint.setAlignment(ConnectionLocator.MIDDLE);
            constraint.setRelativePosition(PositionConstants.NORTH_WEST);
            constraint.setGap(5);*/
            final GmFractionalConnectionLocator constraint = new GmFractionalConnectionLocator();
            constraint.setFraction(0.5);
            constraint.setUDistance(0);
            constraint.setVDistance(20);
        
            this.extensions.put(extension, constraint);
        } else if (key.equals(ExtensionLocation.OnSourceThird)) {
            final GmFractionalConnectionLocator constraint = new GmFractionalConnectionLocator();
            constraint.setFraction(0.25);
            constraint.setUDistance(0);
            constraint.setVDistance(0);
        
            this.extensions.put(extension, constraint);
        } else if (key.equals(ExtensionLocation.OnTargetThird)) {
            final GmFractionalConnectionLocator constraint = new GmFractionalConnectionLocator();
            constraint.setFraction(0.75);
            constraint.setUDistance(0);
            constraint.setVDistance(0);
        
            this.extensions.put(extension, constraint);
        } else {
            throw new IllegalArgumentException("'" + extension + "' is not supproted");
        }
        
        extension.setParentLink(this);
        
        firePropertyChange(PROPERTY_CHILDREN, null, extension);
    }

    @objid ("1f5d5af1-7148-4bfc-ace8-637d0cf8bbe8")
    @Override
    public final void addStartingDrawingLink(final IGmDrawingLink link) {
        this.startingLinks.add(link);
        link.setFrom(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, null, link);
    }

    /**
     * Delete the link from the diagram.
     * <p>
     * Delete its links, extensions and then detach from its source and destination.
     */
    @objid ("cf143ce8-ebe6-49d4-9a31-b331807cc011")
    @Override
    public void delete() {
        // delete outgoing links
        for (IGmDrawingLink l : new ArrayList<>(this.startingLinks)) {
            l.delete();
        }
        // delete incoming links
        for (IGmDrawingLink l : new ArrayList<>(this.endingLinks)) {
            l.delete();
        }
        
        // delete extensions
        for (GmNodeDrawing extension : new ArrayList<>(this.extensions.keySet())) {
            extension.delete();
        }
        
        // detach from source
        if (this.from != null) {
            this.from.removeStartingDrawingLink(this);
            this.from = null;
        }
        
        // detach from target
        if (this.to != null) {
            this.to.removeEndingDrawingLink(this);
            this.to = null;
        }
        
        // now I can die
        super.delete();
    }

    /**
     * Fires a {@link org.modelio.diagram.elements.core.model.IGmObject#PROPERTY_CHILDREN PROPERTY_CHILDREN}
     * property change.
     * <p>
     * To be called when the result of {@link GmNodeModel#isVisible()} on the given link extension changes.
     * 
     * @param child The link extension node whose visibility changed.
     */
    @objid ("b6b077c0-5ed2-4b8b-b0d7-e5d9e1f9934d")
    public final void fireChildVisibilityChanged(GmNodeModel child) {
        firePropertyChange(PROPERTY_CHILDREN, null, child);
    }

    @objid ("2cdb8e9e-ced3-460f-8037-38d418e9040a")
    @Override
    public final void firePathChanged(final IGmPath path) {
        firePropertyChange(PROPERTY_LAYOUTDATA, null, path);
    }

    @objid ("2333bc36-2622-4338-8fb0-27c61493f206")
    @Override
    public final List<IGmDrawingLink> getEndingDrawingLinks() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.endingLinks;
    }

    /**
     * Get all link extensions.
     * <p>
     * Link extensions are roundly all labels related to the link, eg: association role name and cardinality.
     * 
     * @return all link extensions.
     */
    @objid ("8d19cb1c-fd4e-4380-b118-ea14e4f499f8")
    public final Collection<GmNodeDrawing> getExtensions() {
        return this.extensions.keySet();
    }

    /**
     * @return The link source
     */
    @objid ("9a0d82a4-59e5-4075-8a31-e7f2d7ba330f")
    @Override
    public final IGmDrawingLinkable getFrom() {
        return this.from;
    }

    @objid ("8f44cd76-dbed-4dc1-9910-ce902b344376")
    @Override
    public final IGmLocator getLayoutContraint(IGmObject extension) {
        return this.extensions.get(extension);
    }

    @objid ("8c652ed1-4b2d-48b2-8b54-246681e62fbd")
    @Override
    public final IGmPath getPath() {
        return (IGmPath) getLayoutData();
    }

    /**
     * Get the source anchor model.
     * 
     * @return the source anchor.
     */
    @objid ("37e80cc5-ee5e-455f-b98b-fb04a8df8024")
    public final Object getSourceAnchor() {
        return getPath().getSourceAnchor();
    }

    /**
     * Get the links starting from this node.
     * 
     * @return the links starting from this node.
     */
    @objid ("9743c3b1-4db1-4f17-9898-2b260f8b089d")
    @Override
    public final List<IGmDrawingLink> getStartingDrawingLinks() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.startingLinks;
    }

    /**
     * Get the target anchor model.
     * 
     * @return the target anchor.
     */
    @objid ("56472a2e-c6fa-44fd-b410-9fa381c404aa")
    public final Object getTargetAnchor() {
        return getPath().getTargetAnchor();
    }

    /**
     * @return the link destination
     */
    @objid ("fa4325fd-879c-46db-8982-4e7a9d502a06")
    @Override
    public final IGmDrawingLinkable getTo() {
        return this.to;
    }

    @objid ("9863c10a-dd4d-437f-80de-c440842b9e2c")
    @Override
    public Collection<IGmObject> getVisibleExtensions() {
        final Collection<IGmObject> ret = new ArrayList<>(getExtensions().size());
        ret.addAll(getExtensions());
        return ret;
    }

    /**
     * This method is final. Subclasses should override {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("16b39107-8094-49aa-b315-582a7a116f17")
    @Override
    public final void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmAbstratLinkDrawing.");
        switch (readVersion) {
            case 0: {
                read_0(in);
                break;
            }
            default: {
                assert (false) : "version number not covered!";
                // reading as last handled version: 0
                read_0(in);
                break;
            }
        }
    }

    @objid ("2dc5b5eb-b4be-4df1-b62d-bad122e5262e")
    @Override
    public final void removeEndingDrawingLink(final IGmDrawingLink gmLink) {
        this.endingLinks.remove(gmLink);
        gmLink.setTo(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, gmLink, null);
    }

    /**
     * Remove a link extension.
     * 
     * @param gmNodeModel the link extension to remove.
     * @throws java.lang.IllegalArgumentException if the link does not own this node.
     */
    @objid ("0b619a28-66fb-40dd-9db9-7752dab519a9")
    public void removeExtension(GmNodeDrawing gmNodeModel) throws IllegalArgumentException {
        assert (this.extensions.containsKey(gmNodeModel));
        
        this.extensions.remove(gmNodeModel);
        firePropertyChange(PROPERTY_CHILDREN, gmNodeModel, null);
        
        gmNodeModel.setParentLink(null);
    }

    @objid ("e8b8c3d3-2267-4dbd-a295-0598f8e95f0e")
    @Override
    public final void removeStartingDrawingLink(final IGmDrawingLink gmLink) {
        this.startingLinks.remove(gmLink);
        gmLink.setFrom(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, gmLink, null);
    }

    /**
     * Update the link origin.
     * <p>
     * This method is intended to be called only by {@link IGmLinkable#addEndingLink(IGmLink)}.
     * It fires a {@link IGmDrawingLink#PROP_SOURCE_GM} change event.
     * 
     * @param from The new link origin
     */
    @objid ("ee1f3250-6c58-4985-8a39-29f0d2d67b64")
    @Override
    public void setFrom(IGmDrawingLinkable from) {
        if (from != this.from) {
            Object oldFrom = this.from;
            this.from = from;
            
            updateDiagram();
            
            firePropertyChange(PROP_SOURCE_GM, oldFrom, from);
        }
    }

    /**
     * Change the given extension location.
     * 
     * @param extension The link extension.
     * @param layoutData The extension layout constraint.
     */
    @objid ("a48fbb99-8392-4f38-a9ea-614fefc024e4")
    public final void setLayoutConstraint(GmNodeDrawing extension, IGmLocator layoutData) {
        this.extensions.put(extension, layoutData);
        firePropertyChange(PROPERTY_LAYOUTDATA, extension, layoutData);
    }

    @objid ("5c07e2ae-f5b0-4d09-84cb-155fe75b6dee")
    @Override
    public final void setLayoutData(final Object layoutData) {
        final IGmPath oldPath = getPath();
        final IGmPath newPath = (IGmPath) layoutData;
        
        if (newPath != null && getDisplayedStyle() != null) {
            if (newPath.getRouterKind() == null) {
                // Set the router kind from the style if not specified.
        
                newPath.setRouterKind(getRouterFromStyle(getDisplayedStyle()));
            } else {
                // Update the connection router in the style.
        
                // Inhibit style update notification
                getPath().setRouterKind(newPath.getRouterKind());
        
                // Update the connection router in the style.
                final StyleKey routerStyleKey = getStyleKey(MetaKey.CONNECTIONROUTER);
                if (routerStyleKey != null &&
                    getDisplayedStyle().getProperty(routerStyleKey) != newPath.getRouterKind()) {
                    getDisplayedStyle().setProperty(routerStyleKey, newPath.getRouterKind());
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
     * This method is intended to be called only by {@link IGmLinkable#addEndingLink(IGmLink)}.
     * It fires a {@link IGmDrawingLink#PROP_TARGET_GM} change event.
     * 
     * @param to The new destination
     */
    @objid ("8c8749f5-0018-403c-b1ef-c8df1e20279b")
    @Override
    public void setTo(IGmDrawingLinkable to) {
        this.to = to;
        if (to != this.to) {
            Object oldTo = this.to;
            this.to = to;
            
            updateDiagram();
            
            firePropertyChange(PROP_TARGET_GM, oldTo, to);
        }
    }

    @objid ("f2340a51-f5ad-46fa-91bc-15119b4165d6")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        assert (this.from != null && this.to != null) : "this.from == null || this.to == null";
        
        out.writeProperty("Source", this.from);
        out.writeProperty("Dest", this.to);
        out.writeProperty("extensions", this.extensions);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmLink.", MINOR_VERSION);
    }

    /**
     * Called by the anchor when its location changes.
     * 
     * @param gmLinkAnchor The moved anchor.
     */
    @objid ("216ebeb3-4f14-47b3-bfa8-a62c82332fbc")
    @Override
    public final void fireAnchorMoved(GmAbstractLinkAnchor gmLinkAnchor) {
        firePropertyChange(PROPERTY_LAYOUTDATA, null, gmLinkAnchor);
    }

    /**
     * Get the connection router id stored in the given style. If no StyleKey is found, the default value for the router
     * is DIRECT.
     * 
     * @param style a style
     * @return the connection router.
     */
    @objid ("ee905d4e-124e-41a9-9085-666f71d6503d")
    final ConnectionRouterId getRouterFromStyle(final IStyle style) {
        final StyleKey routerStyleKey = getStyleKey(MetaKey.CONNECTIONROUTER);
        if (routerStyleKey != null) {
            final ConnectionRouterId newRouter = (ConnectionRouterId) style.getProperty(routerStyleKey);
            return newRouter;
        }
        return ConnectionRouterId.DIRECT;
    }

    /**
     * Convenience method that compare 2 potentially null references.
     * <p>
     * Returns true if both references are null or a.equals(b) return true.
     * 
     * @param a first object
     * @param b second object
     * @return true if both references are null or a.equals(b) return true.
     */
    @objid ("267d6cdb-2a13-4e93-930a-f629f6179dd3")
    protected final boolean areEqual(final Object a, final Object b) {
        if (a == null) {
            return (b == null);
        } else {
            return a.equals(b);
        }
    }

    /**
     * Redefinable method called by {@link GmLink#read(IDiagramReader)} before adding the link to the diagram.
     * <p>
     * Subclasses should redefine this method instead of {@link #read(IDiagramReader)}.
     * <p>
     * The default implementation does nothing.
     * 
     * @param in a reader to build the graphic model from.
     */
    @objid ("206ebf38-2926-4423-95fb-c6b50a9028f3")
    protected void readLink(IDiagramReader in) {
        // Nothing to do
    }

    @objid ("9494ab77-005d-4383-9c55-a5e95f90b4bf")
    private void init() {
        GmPath path = new GmPath();
        path.setPathData(new ArrayList<>());
        setLayoutData(path);
    }

    /**
     * This method is final. Subclasses should override {@link #readLink(IDiagramReader)} instead.
     */
    @objid ("87090165-ae1b-4d2f-9d3b-78454c116144")
    private final void read_0(IDiagramReader in) {
        super.read(in);
        this.from = (IGmDrawingLinkable) in.readProperty("Source");
        this.to = (IGmDrawingLinkable) in.readProperty("Dest");
        this.extensions = in.readMapProperty("extensions");
                
        // FIXME Connect anchors
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
        for (GmNodeDrawing ext : this.extensions.keySet()) {
            ext.setParentLink(this);
        }
                
        // Connect extremities
        if (this.from != null) {
            this.from.addStartingDrawingLink(this);
        }
        if (this.to != null) {
            this.to.addEndingDrawingLink(this);
        }
    }

    @objid ("0058edbe-2910-4e20-b52a-5680e99416f6")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("29aac8b3-4ec7-420f-821d-da21acdd2b98")
    @Override
    public IGmDrawingLayer getLayer() {
        if (this.from != null) {
            return this.from.getLayer();
        }
        
        if (this.to != null) {
            return this.to.getLayer();
        }
        return null;
    }

    @objid ("c95b4424-a73e-4fdd-bbe6-f9d3d9131dc0")
    @Override
    public boolean updateDiagram() {
        IGmDiagram myDiagram = getDiagram();
        IGmDiagram myNewDiagram = myDiagram;
        IGmDrawingLinkable lSrc = getFrom();
        IGmDrawingLinkable lTarget = getTo();
        IGmDiagram sourceDiagram = lSrc!=null  ? lSrc.getDiagram() : null;
        IGmDiagram targetDiagram = lTarget!=null ? lTarget.getDiagram() : null;
        
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
        
        
        // Propagate to extensions
        for (GmNodeDrawing gm : getExtensions()) {
            gm.updateDiagram();
        }
        
        // Propagate to links on this link
        if (this.startingLinks != null ) {
            this.startingLinks.forEach(IGmDrawingLink::updateDiagram);
        }
        if (this.endingLinks != null) {
            this.endingLinks.forEach(IGmDrawingLink::updateDiagram);
        }
        return true;
    }

}

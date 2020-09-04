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

package org.modelio.diagram.elements.common.abstractdiagram;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmReference;
import org.modelio.diagram.elements.core.model.IPostLoadAction;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.layer.GmDrawingLayer;
import org.modelio.diagram.elements.factories.StandardGmLinkFactory;
import org.modelio.diagram.elements.factories.StandardGmNodeFactory;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.PersistenceException;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.Style;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vbasic.collections.MultiHashMap;
import org.modelio.vcore.session.api.model.change.ChangeCause;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeSupport;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a diagram and its content.
 * <p>
 * The diagram owns a map of all represented elements mapped by their MRef.<br>
 * The diagram is a model change listener and make impacted GmModel refreshing from the Ob model changes.
 */
@objid ("7e169b53-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmAbstractDiagram extends GmCompositeNode implements IGmDiagram {
    @objid ("7e169b68-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7e169b65-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("c21d5b02-6ce1-4f68-b4e4-853f6e86816b")
    private static final String PROP_BG_DRAWING_LAYER = "BgDrawingLayer";

    /**
     * Event property telling the diagram finished reading the model from persistence.
     * <p>
     * The old value is always null. The new value is a {@link PersistenceException} on failure, anything else on success.
     */
    @objid ("8b7f6064-0742-4b35-b72c-ca1057e6e7ea")
    public static final String PROP_DIAGRAM_LOAD_END = "diagram_load_finished";

    /**
     * Property name for UI data version change events.
     * <p>
     * The old value is the former UI data version, the new value the new UI data version.
     * </p>
     * Changes typically occur after a diagram save operation.
     */
    @objid ("9aa29637-f040-46a5-b0c0-709f9e335a4c")
    public static final String PROP_UIDATA_VERSION = "diagram_save_finished";

    @objid ("9d3eb649-bff4-4544-a308-b8edc1b5f779")
    private static final String PROP_DRAWING_LAYERS = "DrawingLayers";

    @objid ("7e169b62-1dec-11e2-8cad-001ec947c8cc")
    protected long lastSavedUiDataVersion = -1;

    @objid ("7e169b6a-1dec-11e2-8cad-001ec947c8cc")
    private transient boolean visible = false;

    /**
     * If true, {@link #visibleRefresher} is the current refresher. If false {@link #hiddenRefresher} is the current one.
     */
    @objid ("3990ccc5-548f-4f73-9fc5-399926f24874")
    private boolean visibleRefresherEnabled;

    @objid ("fc0a1ce2-102f-4343-90aa-b66c00d92ddf")
    private IGmDrawingLayer backgroundDrawingLayer;

    @objid ("bcbddc8f-2844-491a-a770-6c319533b101")
    private final List<IGmDrawingLayer> drawingLayers = new CopyOnWriteArrayList<>();

    @objid ("898a69f8-6243-4aec-b266-6e68ca4ee393")
    private final Map<String, IGmDrawing> drawingMap = new HashMap<>();

    @objid ("f60e1e9c-0d1f-491e-ad76-2790d9e81a90")
    private IDynamicStyler dynamicStyler = null;

    @objid ("0617b441-55c8-4547-bff2-e18c43e52dbf")
    private final Collection<IGmDiagram> embeddedDiagrams;

    @objid ("7e169b5d-1dec-11e2-8cad-001ec947c8cc")
    private final IGmLinkFactory gmLinkFactory;

    @objid ("7e169b5c-1dec-11e2-8cad-001ec947c8cc")
    private final IGmNodeFactory gmNodeFactory;

    @objid ("dae59296-0a2f-4f73-90e8-c0125a4e94a5")
    private final Collection<IGmReference<?>> gmReferences = new ArrayList<>();

    @objid ("7e169b63-1dec-11e2-8cad-001ec947c8cc")
    private final IDiagramRefresher hiddenRefresher;

    @objid ("7e169b5e-1dec-11e2-8cad-001ec947c8cc")
    private final List<IGmLinkObject> links = new ArrayList<>();

    @objid ("7e169b55-1dec-11e2-8cad-001ec947c8cc")
    private IModelManager modelManager;

    @objid ("24914a6e-1f69-11e2-a30a-001ec947c8cc")
    protected final MultiHashMap<MRef,GmModel> models = new MultiHashMap<>();

    @objid ("7e169b5b-1dec-11e2-8cad-001ec947c8cc")
    private final IDiagramRefresher visibleRefresher;

    @objid ("62c963ea-6a84-46c2-a05f-ede359cff644")
    private final List<IPostLoadAction> postLoadActions = new ArrayList<>();

    @objid ("c1a93cf2-d154-4f15-8592-08ddc1ab5827")
    private final PropertyChangeListener embeddedDiagramChangedListener = this::embeddedDiagramChanged;

    /**
     * Initialize a GmAbstractDiagram.
     * 
     * @param modelManager The model manager
     * @param diagramRef a reference to the diagram.
     */
    @objid ("7e169b6f-1dec-11e2-8cad-001ec947c8cc")
    public GmAbstractDiagram(IModelManager modelManager, MRef diagramRef) {
        super((IGmDiagram) null, diagramRef);
        this.modelManager = modelManager;
        this.embeddedDiagrams = new ArrayList<>();
        this.models.putValue(diagramRef, this);
        
        this.visibleRefresher = createVisibleDiagramRefresher();
        this.hiddenRefresher = createHiddentDiagramRefresher();
        
        this.gmLinkFactory = createGmLinkFactory();
        this.gmNodeFactory = createGmNodeFactory();
        
        enableRefresh(true);
        
        if (this.drawingLayers.isEmpty()) {
            // Add a default layer
            final GmDrawingLayer childNode = new GmDrawingLayer(this, diagramRef, GmDrawingLayer.LAYER_ID_TOP);
            this.drawingLayers.add(childNode);
        }
        
        this.backgroundDrawingLayer = new GmDrawingLayer(this, diagramRef, GmDrawingLayer.LAYER_ID_BACKGROUND);
    }

    /**
     * Add a drawing to the diagram.
     * 
     * @param child a drawing.
     */
    @objid ("98cd15c5-bb69-4f5c-87c2-b3f42b25e77e")
    @Override
    public void addDrawingLayer(IGmDrawingLayer child) {
        this.drawingLayers.add(child);
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, child);
    }

    @objid ("b62067cf-7851-4376-9f0a-8b274341cd23")
    @Override
    public void addEmbeddedDiagram(IGmDiagram embeddedDiagram) {
        this.embeddedDiagrams.add(embeddedDiagram);
        
        embeddedDiagram.addPropertyChangeListener(this.embeddedDiagramChangedListener);
        
        refreshAllGmReferences();
    }

    /**
     * Register a graphic element in the diagram.
     * <p>
     * This method should only be called by the GmModel constructor, its read() method or {@link IGmObject#updateDiagram()}.
     * 
     * @param model the graphic element to add.
     */
    @objid ("7e169b77-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void addGraphicModel(IGmObject model) {
        if (model != this && model instanceof GmModel) {
            final GmModel gmModel = (GmModel) model;
            this.models.putValue(gmModel.getRepresentedRef(), gmModel);
        }
        
        if (model instanceof IGmLinkObject) {
            this.links.add((IGmLinkObject) model);
        }
        
        if (model instanceof IGmDrawing) {
            IGmDrawing dg = (IGmDrawing) model;
            IGmDrawing old = this.drawingMap.put(dg.getIdentifier(), dg);
            if (old != null) {
                this.drawingMap.put(dg.getIdentifier(), old);
                throw new IllegalArgumentException("Diagram already contains a " + old.getClass().getSimpleName()
                        + " with '" + dg.getIdentifier() + "' identifier");
            }
        }
    }

    @objid ("d8c53b63-a56e-4a6e-ad60-f693a7f5c246")
    @Override
    public void addGraphicReference(IGmReference<?> ref) {
        this.gmReferences.add(ref);
    }

    /**
     * Tells whether this composite node support child nodes of the given java class.
     * <p>
     * {@link GmElementLabel GmElementLabel} cannot be contained directly by the diagram's background.
     * 
     * @return <i>false</i> if nodeClass is GmElementLabel or a subclass, <i>true</i> otherwise.
     */
    @objid ("7e169b7b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return !(GmElementLabel.class.isAssignableFrom(nodeClass)
                || GmModelElementHeader.class.isAssignableFrom(nodeClass));
    }

    /**
     * Empties the model.
     * <p>
     * To be redefined by subclasses if they need to always contain some child GMs...
     * </p>
     * 
     * @param hasPersistedData if <code>false</code>, this GM should be just like it was after a call to its constructor at the end of the reset method. Should be empty otherwise.
     */
    @objid ("7e18fdb1-1dec-11e2-8cad-001ec947c8cc")
    protected void reset(boolean hasPersistedData) {
        for (final GmNodeModel child : this.getChildren()) {
            removeChild(child);
        }
        for (final IGmDrawingLayer child : getDrawingLayers()) {
            removeLayer(child);
        }
        for (final IGmLink l : new ArrayList<>(getStartingLinks())) {
            removeStartingLink(l);
        }
        for (final IGmLink l : new ArrayList<>(getEndingLinks())) {
            removeEndingLink(l);
        }
        
        for (GmModel gm : getAllModels()) {
            gm.removedFromDiagram();
        }
        
        this.links.clear();
        this.gmReferences.clear();
        this.drawingLayers.clear();
        this.drawingMap.clear();
        this.models.clear();
        
        // Put back the GmAbstractDiagram
        this.models.putValue(getRepresentedRef(), this);
        
        for (IGmDiagram gmDiagram : this.embeddedDiagrams) {
            gmDiagram.removePropertyChangeListener(this.embeddedDiagramChangedListener);
            gmDiagram.dispose();
        }
        this.embeddedDiagrams.clear();
        
        // Add a default foreground layer
        this.drawingLayers.add(new GmDrawingLayer(this, getRepresentedRef(), GmDrawingLayer.LAYER_ID_TOP));
        // Add a default background layer
        this.backgroundDrawingLayer = new GmDrawingLayer(this, getRepresentedRef(), GmDrawingLayer.LAYER_ID_BACKGROUND);
        
        final GmCompositeNode gmParent = getParentNode();
        if (gmParent != null) {
            gmParent.removeChild(this);
        }
        
        final GmLink gmLink = getParentLink();
        if (gmLink != null) {
            gmLink.removeExtension(this);
        }
    }

    /**
     * Removes the model listener.
     */
    @objid ("7e18fdb4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void dispose() {
        if (this.modelManager != null) {
            final IModelChangeSupport modelChangeSupport = this.modelManager.getModelingSession().getModelChangeSupport();
            // Note: it is safe to remove listeners event if they are null
        
            modelChangeSupport.removePersistentViewListener(this.visibleRefresher);
            modelChangeSupport.removeModelChangeListener(this.visibleRefresher);
            modelChangeSupport.removeStatusChangeListener(this.visibleRefresher);
        
            modelChangeSupport.removePersistentViewListener(this.hiddenRefresher);
            modelChangeSupport.removeModelChangeListener(this.hiddenRefresher);
            modelChangeSupport.removeStatusChangeListener(this.hiddenRefresher);
        
            // Delete embedded diagrams
            for (IGmDiagram gmDiagram : new ArrayList<>(this.embeddedDiagrams)) {
                gmDiagram.removePropertyChangeListener(this.embeddedDiagramChangedListener);
                gmDiagram.dispose();
                gmDiagram.delete();
            }
            this.embeddedDiagrams.clear();
        
            // Do not forget to delete the diagram itself, except for embedded diagrams
            if (getDiagramOwner() == null) {
                delete();
            }
        
            this.modelManager = null;
        }
    }

    /**
     * Reconfigure the current refresher for either processing or ignoring the model change events.
     * 
     * @param onOff true to process events, false to defer them.
     */
    @objid ("3e21c01e-b5d2-4134-b25a-a07714f79292")
    @Override
    public void enableRefresh(boolean onOff) {
        if (this.visibleRefresherEnabled != onOff) {
            this.visibleRefresherEnabled = onOff;
        
            final IModelChangeSupport changeSupport = this.modelManager.getModelingSession().getModelChangeSupport();
            if (onOff) {
                changeSupport.removeStatusChangeListener(this.hiddenRefresher);
                changeSupport.removePersistentViewListener(this.hiddenRefresher);
                changeSupport.removeModelChangeListener(this.hiddenRefresher);
        
                changeSupport.addPersistentViewListener(this.visibleRefresher);
                changeSupport.addModelChangeListener(this.visibleRefresher);
                changeSupport.addStatusChangeListener(this.visibleRefresher);
                this.visibleRefresher.visibilityChanged(true);
            } else {
                changeSupport.removeStatusChangeListener(this.visibleRefresher);
                changeSupport.removePersistentViewListener(this.visibleRefresher);
                changeSupport.removeModelChangeListener(this.visibleRefresher);
        
                changeSupport.addPersistentViewListener(this.hiddenRefresher);
                changeSupport.addModelChangeListener(this.hiddenRefresher);
                changeSupport.addStatusChangeListener(this.hiddenRefresher);
                this.hiddenRefresher.visibilityChanged(false);
            }
        
            for (IGmDiagram d : getEmbeddedDiagrams()) {
                d.enableRefresh(onOff);
            }
        }
    }

    /**
     * Returns all {@link IGmDrawing} that are in this diagram, or an empty list if none is found.
     * 
     * @return the list of {@link IGmDrawing}, or an empty list if none is found.
     */
    @objid ("10c7326d-4ff3-4f53-ab4f-5e9b7d2ed76e")
    public final Collection<IGmDrawing> getAllDrawings() {
        return this.drawingMap.values();
    }

    /**
     * Returns all {@link GmModel} that are somehow related to the given reference in this diagram, or an empty list if none is found.
     * 
     * @param representedElementRef a reference to a model element for which we are searching Gm.
     * @return the list of all Gm related to the passed reference, or an empty list if none is found.
     */
    @objid ("7e18fdb7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final Collection<GmModel> getAllGMRelatedTo(MRef representedElementRef) {
        List<GmModel> modelsRelated = new ArrayList<>(this.models.getList(representedElementRef));
        
        // Look in embedded diagrams too
        for (IGmDiagram embeddedDiagram : this.embeddedDiagrams) {
            modelsRelated.addAll(embeddedDiagram.getAllGMRelatedTo(representedElementRef));
        }
        return modelsRelated;
    }

    /**
     * Returns the list of graphic models (Gm) representing (ie: for which the getRepresentedElement() method does return the element of) the given reference in this diagram or an empty list if none is found.
     * 
     * @param representedElementRef a reference to a model element for which we are searching Gm.
     * @return the list of all Gm representing the passed reference, or an empty list if none is found.
     */
    @objid ("7e18fdbf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final List<GmModel> getAllGMRepresenting(MRef representedElementRef) {
        final List<GmModel> modelsRepresenting = new ArrayList<>();
        for (final GmModel model : this.models.getList(representedElementRef)) {
            // Filter the list to keep only the "representing" Gms.
            if (model.getRepresentedElement() != null) {
                modelsRepresenting.add(model);
            }
        }
        
        // Look in embedded diagrams too
        for (IGmDiagram embeddedDiagram : this.embeddedDiagrams) {
            modelsRepresenting.addAll(embeddedDiagram.getAllGMRepresenting(representedElementRef));
        }
        return modelsRepresenting;
    }

    /**
     * Get all represented graphic models.
     * <p>
     * The returned collection is a snapshot copy of the contained graphics. It may freely be modified.
     * 
     * @return all graphic models.
     */
    @objid ("7e18fdc7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final Collection<GmModel> getAllModels() {
        final ArrayList<GmModel> ret = new ArrayList<>(this.models.size() * 5);
        
        for (final List<GmModel> l : this.models.values()) {
            ret.addAll(l);
        }
        return ret;
    }

    /**
     * Get the background drawing layer.
     * 
     * @return the background drawing layer.
     */
    @objid ("d6e87752-e465-4828-86ae-418b86338533")
    @Override
    public final IGmDrawingLayer getBackgroundDrawingLayer() {
        return this.backgroundDrawingLayer;
    }

    @objid ("7e18fdce-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final IGmDiagram getDiagram() {
        return this;
    }

    /**
     * Get the drawing identified by the given string.
     * 
     * @param identifier the drawing identifier.
     * @return the found drawing or <i>null</i>.
     */
    @objid ("d69caf90-c1d2-4ee5-805b-b8bbdf1c16f1")
    @Override
    public final IGmDrawing getDrawing(String identifier) {
        return this.drawingMap.get(identifier);
    }

    /**
     * @return the diagram drawings
     */
    @objid ("f630fc18-b847-4543-beb0-8014587c61ce")
    @Override
    public final List<IGmDrawingLayer> getDrawingLayers() {
        return this.drawingLayers;
    }

    @objid ("82af8b2d-3d26-4291-b329-12d2715fc90c")
    @Override
    public final IDynamicStyler getDynamicStyler() {
        return this.dynamicStyler;
    }

    /**
     * Get the existing GmModel for a given element.
     * 
     * @param element a model element.
     * @return null if the model element is not currently (already) unmasked and visible in the diagram
     */
    @objid ("db08900d-49a0-47dd-845b-4b6882cfc905")
    @Override
    public final GmModel getExistingModelFor(final MObject element) {
        final List<GmModel> allGMRepresenting = getAllGMRepresenting(new MRef(element));
        for (final GmModel gmRepresenting : allGMRepresenting) {
            if (!(gmRepresenting instanceof GmNodeModel) || ((GmNodeModel) gmRepresenting).isVisible()) {
                return gmRepresenting;
            }
        }
        return null;
    }

    @objid ("7e18fdd3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmAbstractDiagram.MAJOR_VERSION;
    }

    /**
     * Get the model manager storing the session and model factory.
     * 
     * @return the model manager.
     */
    @objid ("7e18fdde-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final IModelManager getModelManager() {
        return this.modelManager;
    }

// @objid ("5e95821e-500e-4451-94e0-194c2ab30263")
// protected final IDiagramPersister getPersister() {
// return ;
// }
    @objid ("3c23003d-7004-4364-8e8d-1faf17294fc6")
    @Override
    public AbstractDiagram getRelatedElement() {
        return (AbstractDiagram) super.getRelatedElement();
    }

    @objid ("e9d709c2-1a6c-4ee2-94e0-a57f42b1f712")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return new DiagramSymbolViewModelProvider().create(getPersistedStyle(), this);
    }

    /**
     * Tells whether the diagram model is disposed.
     * <p>
     * A disposed diagram model won't react to model modifications and shouldn't be used anymore.
     * 
     * @return <code>true</code> if the diagram model is disposed, else <code>false</code>.
     */
    @objid ("7e18fde3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isDisposed() {
        return this.modelManager == null;
    }

    @objid ("7e1b6048-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isUserEditable() {
        MObject relatedElement = getRelatedElement();
        return relatedElement != null
                && !(relatedElement.isShell() || relatedElement.isDeleted())
                && relatedElement.getStatus().isModifiable()
                && getDiagramOwner() == null;
    }

    @objid ("7e18fde8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final boolean isVisible() {
        return this.visible;
    }

    @objid ("7e18fded-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmAbstractDiagram.");
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        
        default:
            assert false : readVersion + " version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        
        }
    }

    /**
     * Force refresh of the whole diagram.
     */
    @objid ("7e18fdf1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void refreshAllFromObModel() {
        for (final GmModel m : getAllModels()) {
            if (m != this && m.isValid()) {
                m.obElementsUpdated();
            }
        }
    }

    @objid ("11595976-fa7c-4cab-bd49-a24999ab18c6")
    @Override
    public final void refreshAllGmReferences() {
        for (IGmReference<?> ref : new ArrayList<>(this.gmReferences)) {
            ref.refresh();
        }
        
        for (IGmDiagram gmDiagram : this.embeddedDiagrams) {
            gmDiagram.refreshAllGmReferences();
        }
    }

    /**
     * Make sure dynamic styler is applied.
     */
    @objid ("faf3cae0-4bad-4f66-bcaa-7b9b679e09ab")
    @Override
    public final void refreshDynamicStyle() {
        if (this.dynamicStyler != null) {
            doRefreshModelStyles();
        }
    }

    @objid ("2faa9acd-d874-47dc-8a44-839224264f97")
    @Override
    public void removeEmbeddedDiagram(IGmDiagram embeddedDiagram) {
        this.embeddedDiagrams.remove(embeddedDiagram);
        embeddedDiagram.removePropertyChangeListener(this.embeddedDiagramChangedListener);
        refreshAllGmReferences();
    }

    /**
     * Remove a graphic model from the diagram.
     * <p>
     * To be called only by {@link GmModel#delete()}.
     * 
     * @param model the graphic element to remove.
     */
    @objid ("7e18fdf4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void removeGraphicModel(IGmObject model) {
        if (model instanceof GmModel) {
            GmModel gmmodel = (GmModel) model;
            this.models.remove(gmmodel.getRepresentedRef(), gmmodel);
        }
        
        if (model instanceof IGmLinkObject) {
            this.links.remove(model);
        }
        
        if (model instanceof IGmDrawing) {
            this.drawingMap.remove(((IGmDrawing) model).getIdentifier());
        }
    }

    @objid ("fa3665e5-a87e-45ba-b8b4-66fe7f44be3f")
    @Override
    public void removeGraphicReference(IGmReference<?> ref) {
        this.gmReferences.remove(ref);
    }

    /**
     * Remove a drawing layer
     * 
     * @param gmDrawingLayer a drawing layer
     */
    @objid ("44a65a3a-8d44-48ab-96ec-10da54535eb2")
    @Override
    public final void removeLayer(IGmDrawingLayer gmDrawingLayer) {
        if (this.backgroundDrawingLayer == gmDrawingLayer) {
            this.backgroundDrawingLayer = null;
            firePropertyChange(IGmObject.PROPERTY_CHILDREN, gmDrawingLayer, null);
        } else if (this.drawingLayers.remove(gmDrawingLayer)) {
            firePropertyChange(IGmObject.PROPERTY_CHILDREN, gmDrawingLayer, null);
        } else {
            throw new IllegalArgumentException(gmDrawingLayer + " not owned by the diagram.");
        }
    }

    @objid ("f52a8e29-0a64-4cbb-8a22-b811decc2f13")
    @Override
    public final void setDynamicStyler(IDynamicStyler dynamicStyler) {
        this.dynamicStyler = dynamicStyler;
        doRefreshModelStyles();
    }

    /**
     * Initialize a graphic link model factory for this diagram.
     * <p>
     * This method is called during the class initialization, and should not access fields (that may not be initialized yet).
     * </p>
     * 
     * @return the link creation factory. Must not be <code>null</code>.
     */
    @objid ("7e18fdf8-1dec-11e2-8cad-001ec947c8cc")
    protected final StandardGmLinkFactory createGmLinkFactory() {
        return new StandardGmLinkFactory(getFactoryIdentifier(), canUnmaskGenericElements());
    }

    /**
     * Initialize a graphic node model factory for this diagram.
     * <p>
     * This method is called during the class initialization, and should not access fields (that may not be initialized yet).
     * </p>
     * 
     * @return the node creation factory. Must not be <code>null</code>.
     */
    @objid ("7e1b6009-1dec-11e2-8cad-001ec947c8cc")
    protected final StandardGmNodeFactory createGmNodeFactory() {
        return new StandardGmNodeFactory(getFactoryIdentifier(), canUnmaskGenericElements());
    }

    /**
     * Unmask the given model element as a node inside the given graphic node.
     * 
     * @param parentNode The parent graphic node that will contain the element
     * @param newElement The element to unmask
     * @param initialLayoutData The initial layout data of the unmasked element.<br>
     * The specified initial layout data are taken as a hint, they may be taken into account or not.
     * @return The unmasked node, or <code>null</code> if the newElement can't be unmasked.
     */
    @objid ("7e1b600d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public GmNodeModel unmask(GmCompositeNode parentNode, MObject newElement, Object initialLayoutData) {
        List<GmModel> allGMRepresenting = getAllGMRepresenting(new MRef(newElement));
        for (GmModel gmModel : allGMRepresenting) {
            if (gmModel instanceof GmNodeModel) {
                GmNodeModel gmNodeModel = (GmNodeModel) gmModel;
                if (parentNode.equals(gmNodeModel.getParentNode())) {
                    return gmNodeModel;
                }
            }
        }
        
        // No GmNode under this parent, create a new one...
        try {
            return getGmNodeFactory().create(this, parentNode, newElement, initialLayoutData);
        } catch (UnsupportedOperationException e) {
            // Failed to unmask, log error.
            DiagramElements.LOG.warning(e.getMessage());
        
            // No valid GM found, return null
            return null;
        }
    }

    @objid ("f66ed098-6abc-4ea5-a812-6991a70c7553")
    @Override
    public GmNodeModel unmaskAsChild(MObject newElement, Object initialLayoutData) {
        return unmask(this, newElement, initialLayoutData);
    }

    /**
     * Unmask the given link element in the diagram.
     * <p>
     * 
     * @param createdLinkElement The link to unmask
     * @param fromNode The source node
     * @param toNode The destination node
     * @param initialLayoutData The initial layout data of the unmasked element.<br>
     * @return the link graphic element or <i>null</i> if one of the 2 extremities cannot be unmasked..
     */
    @objid ("7e1b6014-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IGmLink unmaskLink(MObject createdLinkElement, final IGmLinkable fromNode, final IGmLinkable toNode, Object initialLayoutData) {
        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException("Source and destination nodes must not be null.");
        }
        
        List<GmModel> allGMRepresenting = getAllGMRepresenting(new MRef(createdLinkElement));
        for (GmModel gmModel : allGMRepresenting) {
            if (gmModel instanceof GmLink) {
                GmLink gmLink = (GmLink) gmModel;
                if (fromNode.equals(gmLink.getFrom()) && toNode.equals(gmLink.getTo())) {
                    return gmLink;
                }
            }
        }
        
        // No GmLink between those nodes, create a new one...
        final IGmLink newLink = unmaskLink(createdLinkElement);
        
        fromNode.addStartingLink(newLink);
        toNode.addEndingLink(newLink);
        newLink.setLayoutData(initialLayoutData);
        return newLink;
    }

    /**
     * Creates a GmLink for the given element. Link is not initialized (ie: it have no source node, no destination node and no layout data).
     * 
     * @param linkElement the element for which to create a GmLink
     * @return the uninitialized GmLink for the element, or <code>null</code>.
     */
    @objid ("7e1b601f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final IGmLink unmaskLink(MObject linkElement) {
        return getGmLinkFactory().create(this, linkElement);
    }

    /**
     * Update The diagram graphic model last save date from the represented diagram model element.
     */
    @objid ("7e1b6025-1dec-11e2-8cad-001ec947c8cc")
    public final void updateLastSaveDate() {
        this.lastSavedUiDataVersion = getRelatedElement().getUiDataVersion();
    }

    @objid ("7e1b6028-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        out.writeProperty("Links", this.links);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmAbstractDiagram.", GmAbstractDiagram.MINOR_VERSION);
        
        // Write background drawings layer
        out.writeProperty(GmAbstractDiagram.PROP_BG_DRAWING_LAYER, this.backgroundDrawingLayer);
        
        // Write foreground drawings layers
        out.writeProperty(GmAbstractDiagram.PROP_DRAWING_LAYERS, this.drawingLayers);
    }

    /**
     * Read the diagram model from the persisted serialized data.
     */
    @objid ("8b0e6312-451e-4bed-acc0-926c4af02dc0")
    @Override
    public void load() {
        try {
            new DiagramPersistence(this).load();
            firePropertyChange(GmAbstractDiagram.PROP_DIAGRAM_LOAD_END, null, Boolean.TRUE);
        } catch (PersistenceException e) {
            // Failed to read string, log error.
            DiagramElements.LOG.error(e);
            firePropertyChange(GmAbstractDiagram.PROP_DIAGRAM_LOAD_END, null, e);
        }
    }

    /**
     * Initialize a diagram refresher to be called when the model changes and the diagram is not visible., i.e. <code>enableRefresh(false)</code> has been called.
     * <p>
     * This method is called during the class initialization, and should not access fields (that may not be initialized yet).
     * </p>
     * 
     * @return a diagram refresher. Must not be <code>null</code>.
     */
    @objid ("9e4cd3c3-52b7-404e-bad6-65e7be8e0a7f")
    protected IDiagramRefresher createHiddentDiagramRefresher() {
        return new HiddenDiagramRefresher(this);
    }

    /**
     * Creates the diagram style from the style manager default style.
     */
    @objid ("7e1b6031-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IStyle createStyle(final IGmDiagram aDiagram) {
        return new Style(DiagramStyles.getStyleManager().getDefaultTheme());
    }

    /**
     * Initialize a diagram refresher to be called when the model changes and the diagram is visible, i.e. <code>enableRefresh(true)</code> has been called.
     * <p>
     * This method is called during the class initialization, and should not access fields (that may not be initialized yet).
     * </p>
     * 
     * @return a diagram refresher. Must not be <code>null</code>.
     */
    @objid ("7e1b6043-1dec-11e2-8cad-001ec947c8cc")
    protected IDiagramRefresher createVisibleDiagramRefresher() {
        return new VisibleDiagramRefresher(this);
    }

    @objid ("7e1b6039-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void doSetVisible(final boolean newVisible) {
        // Avoid useless and harmful clear if no change
        if (this.visible != newVisible) {
            this.visible = newVisible;
            super.doSetVisible(newVisible);
        }
    }

    @objid ("7e169b74-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final void finalize() throws Throwable {
        dispose();
        super.finalize();
    }

    /**
     * Get the factory used to unmask relationship model elements as links in this diagram.
     * 
     * @return The graphic link factory.
     */
    @objid ("7e1b603e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final IGmLinkFactory getGmLinkFactory() {
        return this.gmLinkFactory;
    }

    /**
     * Get the factory used to unmask relationship model elements as nodes in this diagram.
     * 
     * @return The graphic node factory
     */
    @objid ("7e1b602c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final IGmNodeFactory getGmNodeFactory() {
        return this.gmNodeFactory;
    }

    /**
     * Call 'styleChanged' on each GmModel belonging to the diagram.
     */
    @objid ("7169671b-7400-419d-b160-2183332dcf29")
    private void doRefreshModelStyles() {
        if (isVisible()) {
            for (GmModel model : getAllModels()) {
                if (model.isValid()) {
                    model.styleChanged(model.getDisplayedStyle());
                }
            }
        }
    }

    @objid ("b836a875-7a3d-4048-8ac2-314f29e096a9")
    private void embeddedDiagramChanged(PropertyChangeEvent ev) {
        if (GmAbstractDiagram.PROP_DIAGRAM_LOAD_END.equals(ev.getPropertyName())) {
            refreshAllGmReferences();
        }
    }

    @objid ("7e1b6045-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    private void read_0(IDiagramReader in) throws PersistenceException {
        // Clear existing/default layers
        for (final IGmDrawingLayer child : new ArrayList<>(getDrawingLayers())) {
            child.delete();
        }
        if (this.backgroundDrawingLayer != null) {
            this.backgroundDrawingLayer.delete();
        }
        
        // Call inherited loading
        super.read(in);
        
        // re-normalize the identifier (because copied diagrams are deserialized
        // with a wrong id)
        // "this" diagram provides the right one.
        final MRef newId = new MRef(getRelatedElement());
        getRepresentedRef().mc = newId.mc;
        getRepresentedRef().uuid = newId.uuid;
        
        // Resume deserialization :
        // Read all links. No need to get the result of readListProperty() :
        // links add themselves to this.links
        in.readListProperty("Links");
        
        // Read foreground drawing layers
        this.drawingLayers.addAll((Collection<? extends IGmDrawingLayer>) in.readListProperty(GmAbstractDiagram.PROP_DRAWING_LAYERS));
        
        if (this.drawingLayers.isEmpty()) {
            // Add a default layer
            final GmDrawingLayer childNode = new GmDrawingLayer(this, newId, GmDrawingLayer.LAYER_ID_TOP);
            this.drawingLayers.add(childNode);
        }
        
        // Read background layer
        this.backgroundDrawingLayer = (IGmDrawingLayer) in.readProperty(GmAbstractDiagram.PROP_BG_DRAWING_LAYER);
        if (this.backgroundDrawingLayer == null) {
            this.backgroundDrawingLayer = new GmDrawingLayer(this, newId, GmDrawingLayer.LAYER_ID_BACKGROUND);
        }
        
        // Make sure the current style is a theme or the default style
        IStyle persistedStyle = getPersistedStyle();
        if (!persistedStyle.isTheme()) {
            if (persistedStyle.getCascadedStyle() instanceof NamedStyle) {
                NamedStyle elementStyle = (NamedStyle) persistedStyle.getCascadedStyle();
                if (!DiagramStyles.getStyleManager().getDefaultStyle().equals(elementStyle)) {
                    // Style migration: replace the current 'X' style with 'X theme'
                    String themeName = elementStyle.getName() + " theme";
                    NamedStyle theme = DiagramStyles.getStyleManager().findStyle(themeName);
                    if (theme == null) {
                        // Create new theme by cascading on the previously used style, to preserve the behavior
                        theme = DiagramStyles.getStyleManager().createStyle(themeName, elementStyle.getName(), true);
                    }
                    persistedStyle.setCascadedStyle(theme);
                }
            }
        }
        
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, this.drawingLayers);
        firePropertyChange(IGmObject.PROPERTY_STYLE, null, getDisplayedStyle());
    }

    @objid ("bdd8f4bd-0838-41db-a381-8abda7fddeb7")
    @Override
    public Collection<IGmDiagram> getEmbeddedDiagrams() {
        return new ArrayList<>(this.embeddedDiagrams);
    }

    /**
     * Get the registered post load actions.
     * <p>
     * The list is returned by reference. To be used by the controller to run them.
     * 
     * @return the registered post load actions.
     */
    @objid ("3a624f0c-8c0c-4433-a45f-8403e3400efa")
    protected final List<IPostLoadAction> getPostLoadActions() {
        return this.postLoadActions;
    }

    @objid ("59c32f0f-1083-4414-ae8a-6614806141de")
    @Override
    public void addPostLoadAction(IPostLoadAction action) {
        IGmDiagram diagramOwner = getDiagramOwner();
        if (diagramOwner != null) {
            diagramOwner.addPostLoadAction(action);
        } else {
            // Post-load actions must be registered on the top-most diagram only
            this.postLoadActions.add(action);
        }
    }

    /**
     * Tells whether the gm is usable or not.
     * <p>
     * An diagram is usable if its model manager field has not been nullified.
     * </p>
     * 
     * @return <code>true</code> if the gm is valid, <code>false</code> otherwise.
     * @since Modelio 3.7
     */
    @objid ("621c231a-09aa-4dba-bc2b-135e3e0de50b")
    @Override
    public boolean isValid() {
        return this.modelManager != null;
    }

    @objid ("85b90c03-bebc-4748-af41-ef582faa7c55")
    @Override
    public IGmDiagram getDiagramOwner() {
        // Embedded diagram owner is the parent node diagram.
        // See GmEmbeddedDiagram.getViewedDiagramModel()
        final GmCompositeNode parentNode = getParentNode();
        if (parentNode != null) {
            return parentNode.getDiagram();
        }
        return null;
    }

    @objid ("9e2f96fa-6243-4863-899e-9f2b5838b6f7")
    @Override
    public abstract boolean canUnmaskGenericElements();

    @objid ("3ae81920-5a03-4460-9cdf-04718086ab73")
    @Override
    public final boolean canUnmask(MObject el) {
        return doCanUnmask(el) || canUnmaskGenericElements();
    }

    @objid ("07d6c476-fd4d-481f-9f1f-17ed2e086179")
    protected abstract boolean doCanUnmask(MObject el);

    @objid ("c5431dcc-29bd-4d7d-96d5-605af2fadcc1")
    @Override
    public void save(boolean withEmbeddeddiagrams) throws PersistenceException {
        try {
            long ref = this.lastSavedUiDataVersion;
            new DiagramPersistence(this).save(withEmbeddeddiagrams);
            if (ref != this.lastSavedUiDataVersion) {
                firePropertyChange(GmAbstractDiagram.PROP_UIDATA_VERSION, ref, this.lastSavedUiDataVersion);
            }
        
        } catch (PersistenceException e) {
            // Failed to write string, log error.
            DiagramElements.LOG.error(e);
        }
    }

    /**
     * Updates the Graphic Model from the Ob model.
     */
    @objid ("7e1dc274-1dec-11e2-8cad-001ec947c8cc")
    private static class HiddenDiagramRefresher implements IDiagramRefresher {
        @objid ("ce962625-925c-4396-9550-c9863e2252ba")
        private GmAbstractDiagram gmAbstractDiagram;

        /**
         * Default constructor.
         * 
         * @param gmAbstractDiagram the opened diagram.
         */
        @objid ("7e1dc276-1dec-11e2-8cad-001ec947c8cc")
        public HiddenDiagramRefresher(GmAbstractDiagram gmAbstractDiagram) {
            this.gmAbstractDiagram = gmAbstractDiagram;
        }

        @objid ("7e1dc279-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void updateView(final IModelChangeEvent event) {
            final AbstractDiagram obDiagram = this.gmAbstractDiagram.getRelatedElement();
            // Remove deleted elements from the diagram in the display thread
            Display.getDefault().syncExec(() -> {
            
                if (!obDiagram.isShell()
                        && !obDiagram.isDeleted()
                        && !this.gmAbstractDiagram.isDisposed()
                        && obDiagram.getStatus().isModifiable()) {
                    for (final GmModel model : this.gmAbstractDiagram.getAllModels()) {
                        final MObject el = model.getRelatedElement();
                        if (el != null && el.isDeleted()) {
                            model.obElementDeleted();
                        }
                    }
            
                    // Save the refreshed diagram only if sync with the Ob model
                    if (obDiagram.getUiDataVersion() == this.gmAbstractDiagram.lastSavedUiDataVersion) {
                        this.gmAbstractDiagram.save(false);
                    }
                }
            });
        }

        @objid ("7e1dc27e-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public final void modelChanged(IModelChangeEvent event) {
            // Do nothing.
        }

        @objid ("9c87e127-7998-4459-9c7b-3d287a4700f0")
        @Override
        public void visibilityChanged(boolean visible) {
            // do nothing
        }

        @objid ("55203d10-c164-4434-8c85-94dfa5ea846f")
        @Override
        public void statusChanged(IStatusChangeEvent ev) {
            // do nothing
        }

    }

    /**
     * Updates the Graphic Model from the Ob model.
     */
    @objid ("7e1b604d-1dec-11e2-8cad-001ec947c8cc")
    private static class VisibleDiagramRefresher implements IDiagramRefresher {
        @objid ("0f0f5f0b-600d-4ea4-8c6c-920b913f87e5")
        private final GmAbstractDiagram gmAbstractDiagram;

        /**
         * Used by {@link #scheduleDiagramReload()} to schedule reload only once.
         */
        @objid ("394c4167-45a7-4870-911f-d9afe96e4a5e")
        private final AtomicBoolean reloadScheduled = new AtomicBoolean();

        /**
         * Default constructor.
         * 
         * @param gmAbstractDiagram the opened diagram.
         */
        @objid ("7e1dc262-1dec-11e2-8cad-001ec947c8cc")
        public VisibleDiagramRefresher(GmAbstractDiagram gmAbstractDiagram) {
            this.gmAbstractDiagram = gmAbstractDiagram;
        }

        /**
         * Invoked when the model has changed.
         * <p>
         * Delegates to {@link #refreshAllDiagram()}.
         */
        @objid ("7e1dc265-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void updateView(final IModelChangeEvent event) {
            final AbstractDiagram obDiagram = this.gmAbstractDiagram.getRelatedElement();
            // Refresh the diagram in the display thread
            Display.getDefault().syncExec(() -> {
            
                // Guard agains'st disposed diagram editor and deleted element
                if (this.gmAbstractDiagram.isDisposed()
                        || obDiagram == null
                        || obDiagram.isShell()
                        || obDiagram.isDeleted()
                        || !obDiagram.getStatus().isModifiable()) {
                    // The diagram has been deleted or closed, do nothing.
                    // Another listener will close the view.
                    return;
                } else if (obDiagram.getUiDataVersion() != this.gmAbstractDiagram.lastSavedUiDataVersion) {
                    // The diagram data itself is modified.
                    // If represented elements are also deleted,
                    // reload the diagram and remove deleted elements.
            
                    // Get all refs from invalid elements
                    final Set<MRef> invalidRefs = new HashSet<>();
                    for (final GmModel gm : this.gmAbstractDiagram.getAllModels()) {
                        final MObject el = gm.getRelatedElement();
                        if (el != null && el.isDeleted()) {
                            invalidRefs.add(gm.getRepresentedRef());
                        }
                    }
            
                    if (!invalidRefs.isEmpty()) {
                        this.gmAbstractDiagram.load();
            
                        // Delete all Gm whose refs were invalid, to avoid
                        // unwanted ghosts.
                        int deletedNodes = 0;
                        for (final GmModel gm : this.gmAbstractDiagram.getAllModels()) {
                            if (invalidRefs.contains(gm.getRepresentedRef())) {
                                gm.obElementDeleted();
                                deletedNodes++;
                            }
                        }
            
                        // Save the refreshed diagram if necessary
                        if (deletedNodes > 0) {
                            this.gmAbstractDiagram.save(false);
                        }
                    }
                } else {
                    // Standard case : refresh the diagram
                    refreshAllDiagram();
                }
            });
        }

        /**
         * Reload the diagram if it has been modified outside of the diagram editor.
         * 
         * @param event The change event.
         */
        @objid ("7e1dc26b-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public final void modelChanged(final IModelChangeEvent event) {
            final AbstractDiagram obDiagram = this.gmAbstractDiagram.getRelatedElement();
            
            if (obDiagram == null || obDiagram.isShell() || obDiagram.isDeleted()) {
                // The diagram has been deleted, do nothing.
                // Another listener will close the view.
                return;
            } else if (obDiagram.getUiDataVersion() != this.gmAbstractDiagram.lastSavedUiDataVersion) {
                // Schedule a diagram reload
                scheduleDiagramReload();
            
            } else if (!obDiagram.getStatus().isModifiable()) {
                // When the diagram is read only:
                // - The diagram was not refreshed by updateView(...) on transaction commit
                // - as it is not refreshed on commit, nothing happens on undo/redo
                // do all of these here.
                //
                // FIXME: The ghost nodes can't resurrect because
                // GmModel#obElementResolved() is neither called
                // nor implemented .
                // The only thing to do is reload completely the diagram.
                scheduleDiagramReload();
            } else {
                switch (event.getCause()) {
                case REPOSITORY:
                case UNDO:
                case REDO:
                    // updateView(...) is not called in these cases
                    scheduleDiagramReload();
                    break;
                default:
                    // do nothing
                }
            }
        }

        /**
         * Force a refresh of the whole diagram.
         */
        @objid ("7e1dc271-1dec-11e2-8cad-001ec947c8cc")
        protected final void refreshAllDiagram() {
            final Collection<GmModel> toRefresh = this.gmAbstractDiagram.getAllModels();
            
            for (final GmModel model : toRefresh) {
                if (model.getDiagram() != null) {
                    final MObject el = model.getRelatedElement();
                    if (el != null && el.isDeleted()) {
                        model.obElementDeleted();
                    } else if (model.isValid()) {
                        model.obElementsUpdated();
                    }
                }
            }
            
            // Save the refreshed diagram
            final AbstractDiagram obDiagram = this.gmAbstractDiagram.getRelatedElement();
            if (obDiagram.isModifiable()) {
                this.gmAbstractDiagram.save(false);
            }
        }

        @objid ("f20f0c34-0ce5-4d42-965c-35d018a91077")
        private void scheduleDiagramReload() {
            if (this.reloadScheduled.compareAndSet(false, true)) {
                Display.getDefault().asyncExec(() -> {
                    this.reloadScheduled.set(false);
                    if (!this.gmAbstractDiagram.isDisposed() && this.gmAbstractDiagram.getModelManager().getModelingSession().isValid()) {
                        this.gmAbstractDiagram.load();
                    }
                });
            }
        }

        @objid ("4dc517e1-6318-4912-92b9-d8428ef2ac0c")
        @Override
        public void visibilityChanged(boolean visible) {
            if (visible) {
                MObject relatedElement = this.gmAbstractDiagram.getRelatedElement();
                if (relatedElement != null) {
                    this.gmAbstractDiagram.load();
                }
            }
        }

        @objid ("c0205b31-9645-4acc-ab34-662fe2f8a17c")
        @Override
        public void statusChanged(IStatusChangeEvent ev) {
            if (ev.getCause() == ChangeCause.REPOSITORY) {
                // module may have been added/removed : all icons must be reloaded
                // don't filter on ev.getShellStateChanged().isEmpty(), it is often empty because elements are unloaded then reloaded to same state
                scheduleDiagramReload();
            }
        }

    }

}

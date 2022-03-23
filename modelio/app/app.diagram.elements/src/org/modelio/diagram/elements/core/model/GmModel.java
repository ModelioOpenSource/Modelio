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
package org.modelio.diagram.elements.core.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.AbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.Style;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.core.view.LegacyStyleKeyProviderSymbolViewModel;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Base class of all Graphic Models that are related to an {@link MObject} model.
 * <p>
 * A <code>GmModel</code> may be a node ({@link GmNodeModel}) or a link ({@link GmLink}).
 */
@objid ("807b57d8-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmModel extends GmAbstractObject implements IObModelChangeListener, IGmModelRelated {
    @objid ("807b57e2-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("807b57df-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("91f88201-1e83-11e2-8cad-001ec947c8cc")
    private String lastKnownLabel;

    @objid ("807b57da-1dec-11e2-8cad-001ec947c8cc")
    private MRef relatedRef;

    /**
     * <p>
     * Constructor.
     * </p>
     * <p>
     * Registers the new GmModel in the diagram if it represents an {@link MObject} (ie the {@link #getRepresentedElement()} returns a non-null element).
     * </p>
     * @param diagram The diagram owning this element.
     * @param relatedRef a reference to the element this GmModel is related to. Must be non null.
     */
    @objid ("807b57e4-1dec-11e2-8cad-001ec947c8cc")
    protected  GmModel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram);
        assert relatedRef != null : "Error: a GmModel should never be instantiated with a null reference.";
        this.relatedRef = relatedRef;
        init();
        
    }

    /**
     * constructor to call when the model is to be read from a serialized form.
     * <p>
     * {@link #read(IDiagramReader)} <strong>must</strong> be called after so that it is correctly initialized.
     */
    @objid ("807b57e9-1dec-11e2-8cad-001ec947c8cc")
    protected  GmModel() {
        // Empty constructor needed for (de-)serialisation.
    }

    /**
     * Tells whether elements of the given metaclass can be created inside this graphic node.
     * <p>
     * This method should return true only if it is consistent to display the given metaclass elements inside this graphic element.
     * <p>
     * <b>eg:</b> IAttributes can be displayed in a GmClass .
     * @param type The metaclass to unmask.
     * @return true only if it is consistent to display elements of the given metaclass inside this graphic element, false in the other cases.
     */
    @objid ("807b57ec-1dec-11e2-8cad-001ec947c8cc")
    public abstract boolean canCreate(Class<? extends MObject> type);

    /**
     * Tells whether the given model element can be unmasked inside this graphic node.
     * <p>
     * This method should return true only if it is consistent to display the given element inside this graphic element.
     * <p>
     * <b>eg:</b> An IAttribute can be displayed in a GmClass only if the attribute belongs to the Class represented by this GmClass, and the attribute is not already unmasked.
     * @param el The model element to unmask.
     * @return true only if it is consistent to display the given element inside this graphic element, false in the other cases.
     */
    @objid ("807b57f2-1dec-11e2-8cad-001ec947c8cc")
    public abstract boolean canUnmask(MObject el);

    /**
     * Get the interface that allows to edit the label representing the element name, signature or other property.
     * @return the edition interface.
     */
    @objid ("807b57fa-1dec-11e2-8cad-001ec947c8cc")
    public IEditableText getEditableText() {
        return null;
    }

    @objid ("807dba08-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public String getGhostId() {
        return this.relatedRef.uuid;
    }

    /**
     * @return the main label of the element, usually its name.
     */
    @objid ("807dba0d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public String getGhostLabel() {
        return this.lastKnownLabel;
    }

    /**
     * @return the metaclass of the element.
     */
    @objid ("807dba13-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public String getGhostMetaclass() {
        return this.relatedRef.mc;
    }

    @objid ("80801c68-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmModel.MAJOR_VERSION;
    }

    /**
     * Get the parent of the {@link GmModel}. The 'parent' concept is to be understood at the graphic model level here. The returned parent may be null, this must not be an issue.
     * @return The parent (graphic model level) or null
     */
    @objid ("807dba19-1dec-11e2-8cad-001ec947c8cc")
    public abstract GmModel getParent();

    /**
     * Get the element this {@link GmModel} is related to.
     * <p>
     * <b>Note:</b> May return <i>null</i> if the element is not resolved.
     * @return the represented element or <i>null</i> if the element is not resolved.
     */
    @objid ("807dba1c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getRelatedElement() {
        // If this GmModel represent an element, return it.
        if (getRepresentedElement() != null) {
            return getRepresentedElement();
        }
        // else
        // Otherwise, try to ask the parent of this GmModel.
        if (getParent() != null) {
            return getParent().getRelatedElement();
        }
        // else
        return null;
    }

    /**
     * <p>
     * Get the metaclass of the element this {@link GmModel} is related to.
     * </p>
     * <p>
     * <strong>Note:</strong> This method should never return <code>null</code> and is not intended to be overridden.
     * </p>
     * @return the metaclass this GmModel is in charge of relating.
     */
    @objid ("023775d9-e397-41d5-bf05-4ee7999674e3")
    public final MClass getRelatedMClass() {
        MObject el = getRelatedElement();
        if (el != null) {
            return el.getMClass();
        } else {
            MRef ref = getRepresentedRef();
            return getDiagram().getModelManager().getMetamodel().getMClass(ref.mc);
        }
        
    }

    /**
     * Get the element representation mode.
     * <p>
     * Must return {@link RepresentationMode#STRUCTURED} if no mode is applicable.
     * @return the element representation mode or {@link RepresentationMode#STRUCTURED} if no mode is applicable.
     */
    @objid ("807dba22-1dec-11e2-8cad-001ec947c8cc")
    public abstract RepresentationMode getRepresentationMode();

    /**
     * <p>
     * Get the element this {@link GmModel} represents.
     * </p>
     * <p>
     * <strong>Note:</strong> return <code>null</code> if this GmModel is not the one "in charge" or representing the element. You may want to use {@link #getRelatedElement()} instead.
     * </p>
     * <p>
     * Default implementation returns <code>null</code>. Subclasses may override this method to provide an actual MObject if they are the GmModel representing the element.
     * </p>
     * @return <i>null</i> or the represented element if this GmModel is in charge of representing an element.
     */
    @objid ("807dba25-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getRepresentedElement() {
        return null;
    }

    /**
     * <p>
     * Get reference to the element this {@link GmModel} is related to.
     * </p>
     * <p>
     * <strong>Note:</strong> This method should never return <code>null</code> and is not intended to be overridden.
     * </p>
     * TODO : this method should be named getRelatedReference().
     * @return a {@link MRef reference} to the related element.
     */
    @objid ("807dba2b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final MRef getRepresentedRef() {
        return this.relatedRef;
    }

    /**
     * This implementation is just here to avoid compilation errors until everybody has an implementation.
     * <p>
     * To be deleted before Modelio 3.7 release.
     * @since 3.7
     */
    @objid ("e1964f72-0b60-4682-81f1-45f2909a7f24")
    @SuppressWarnings ("deprecation")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        AbstractStyleKeyProvider skProv = getLegacyStyleKeyProvider(getRepresentationMode());
        if (skProv != null) {
            return new LegacyStyleKeyProviderSymbolViewModel(skProv, getDiagram().getPersistedStyle());
        } else if (getPersistedStyle() instanceof ProxyStyle && getParent() != null) {
            return getParent().getSymbolViewModel();
        } else {
            return ISymbolViewModel.EMPTY;
        }
        
    }

    @objid ("807dba34-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void obElementDeleted() {
        if (true) {
            firePropertyChange(PROP_OBMODEL_DELETED, this, null);
        } else {
            if (getParent() != null) {
                getParent().obChildDeleted(this);
            } else {
                // Default implementation
                delete();
            }
        }
        
    }

    /**
     * Called when an MObject with the same identifier as the represented element reference (#representedRef()) appears in the model.
     * <p>
     * The graphic element should then refresh completely from the MObject.
     */
    @objid ("807dba37-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void obElementResolved(MObject ev) {
        // Default implementation
        if (getRelatedElement() != null) {
            this.lastKnownLabel = getRelatedElement().getName();
        }
        refreshFromObModel();
        
    }

    /**
     * Called when some Elements attributes are modified.
     * <p>
     * The GmModel element should update here its label.
     * <p>
     * The default implementation calls {@link #refreshFromObModel()}.
     */
    @objid ("807dba3b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void obElementsUpdated() {
        // Default implementation
        if (getRelatedElement() != null) {
            this.lastKnownLabel = getRelatedElement().getName();
        }
        refreshFromObModel();
        
    }

    @objid ("807dba3f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmModel.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert false : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
        
    }

    @objid ("cabfcfa6-ff73-4ce7-85be-6c172a490902")
    @Override
    public String toString() {
        Object model = getRelatedElement();
        if (model == null) {
            model = getRepresentedRef();
        }
        return String.format("%s [model=%s, layout=%s]",
                getClass().getSimpleName(),
                model,
                getLayoutData());
        
    }

    @objid ("807dba43-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty("relatedRef", this.relatedRef);
        out.writeProperty("lastKnownLabel", this.lastKnownLabel);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmModel.", GmModel.MINOR_VERSION);
        
    }

    /**
     * Instantiate the graphic model style.
     * <p>
     * Called by the standard {@link GmAbstractObject#GmAbstractObject(IGmDiagram)} constructor.
     * <p>
     * Default implementation makes the style derive from the RAMC style for RAMC elements, from the diagram style in the other case.
     * <p>
     * Can be redefined to create another style or to return <tt>null<tt/> if
     * {@link #getStyle()} is redefined to return another style.
     * &#64;param aDiagram the diagram where the object will be
     * &#64;return the created style or <tt>null</tt> if the creation is postponed
     */
    @objid ("807dba47-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IStyle createStyle(IGmDiagram aDiagram) {
        final MObject el = getRelatedElement();
        IStyle baseStyle;
        if (el != null && el.getStatus().isRamc()) {
            // If represented element is ramc, use the ramc named style.
            baseStyle = DiagramStyles.getStyleManager().getStyle(DiagramStyles.RAMC_STYLE_NAME);
        } else {
            baseStyle = aDiagram.getPersistedStyle();
        }
        return new Style(baseStyle);
    }

    /**
     * Find by introspection the style key provider for this graphic model.
     * @param mode the current representation mode.
     * @return the used style key provider
     * @deprecated for 3.6 compatibility only, {@link #getSymbolViewModel()} should be reimplemented by sub classes.
     * @since 3.7
     */
    @objid ("681b903c-03ee-4ae1-8d7e-d394f12b2e2b")
    @Deprecated
    protected final AbstractStyleKeyProvider getLegacyStyleKeyProvider(RepresentationMode mode) {
        switch (mode) {
        case IMAGE:
            return getLegacyStyleKeyProvider("IMAGE_KEYS");
        case SIMPLE:
            return getLegacyStyleKeyProvider("SIMPLE_KEYS");
        case USER_IMAGE:
            return getLegacyStyleKeyProvider("USERIMAGE_KEYS");
        case STRUCTURED:
        default:
            return getLegacyStyleKeyProvider("STRUCTURED_KEYS");
        }
        
    }

    /**
     * Called when the Ob Element of a child GM is deleted.
     * <p>
     * Default implementation calls {GmAbstractObject{@link #delete()} on the child.
     * </p>
     * @param child the gm being deleted.
     * @since 5.1.0
     */
    @objid ("09ada002-8ed9-4084-b1cb-86ac988e7c1e")
    protected void obChildDeleted(GmModel child) {
        child.delete();
    }

    /**
     * Refresh completely the {@link GmModel} element from the {@link MObject Ob model}.
     */
    @objid ("807dba4e-1dec-11e2-8cad-001ec947c8cc")
    protected abstract void refreshFromObModel();

    /**
     * Convenience method that resolve the given reference by asking the diagram model manager.
     * <p>
     * To be called by {@link #read(IDiagramReader)}.
     * @param ref an MRef to resolve.
     * @return the found MObject or <i>null</i> if the element is not present in the project.
     */
    @objid ("807dba50-1dec-11e2-8cad-001ec947c8cc")
    protected final MObject resolveRef(MRef ref) {
        return getDiagram().getModelManager().resolveRef(ref);
    }

    /**
     * <p>
     * Initialize the object.
     * </p>
     * <p>
     * Must be called before usage by and only by:
     * <ul>
     * <li>The {@link #GmModel(GmAbstractDiagram, MRef)} constructor (but NOT by the parameter less constructor).
     * <li>and the {@link #read(IDiagramReader)} method
     * </ul>
     * </p>
     * <p>
     * The same method may exist in subclasses. In this case:
     * <ul>
     * <li>the child <em>init()</em> method must be private too,
     * <li>it must <strong>never</strong> call <em>super.init()</em>
     * <li>it must be called by the above 2 methods. they must be created if absent.
     * </ul>
     * </p>
     */
    @objid ("80801c62-1dec-11e2-8cad-001ec947c8cc")
    private void init() {
        // If the GmAbstractDiagram is reachable.
        if (getDiagram() != null) {
            // Register element in the diagram
            getDiagram().addGraphicModel(this);
        }
        
    }

    @objid ("80801c65-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.relatedRef = (MRef) in.readProperty("relatedRef");
        this.lastKnownLabel = (String) in.readProperty("lastKnownLabel");
        init();
        
    }

}

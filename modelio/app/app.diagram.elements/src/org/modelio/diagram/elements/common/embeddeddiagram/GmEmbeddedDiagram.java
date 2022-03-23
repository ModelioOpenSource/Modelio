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
package org.modelio.diagram.elements.common.embeddeddiagram;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a potentially modifiable {@link AbstractDiagram} in another diagram.
 */
@objid ("0c3e8533-4da7-4764-879e-9f75c1cbfc2e")
public class GmEmbeddedDiagram extends GmNoStyleCompositeNode {
    @objid ("68a5f00d-d917-4df0-a5f0-7441f4cbc5af")
    private static final int MAJOR_VERSION = 0;

    @objid ("f027c7fa-689b-4e26-bc44-9c51da56b1b3")
    private static final String MINOR_PREFIX = "GmEmbeddedDiagram.";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("64e7c2cb-04c5-4cd5-a5fc-904d5f1c6147")
    private static final int MINOR_VERSION = 0;

    /**
     * Property fired when an embedded diagram changes.
     */
    @objid ("840daeac-9d5f-4055-b4f7-b154498511a2")
    public static final String PROP_INNER_DIAGRAM = "viewedDiagramModel";

    /**
     * The viewed diagram element.
     */
    @objid ("b1c9ad51-a07f-4be0-89e0-b3ea4ee9b7bf")
    private AbstractDiagram viewedDiagram;

    /**
     * The viewed diagram graphic model.
     */
    @objid ("9ba48100-def1-4d89-83d5-df6007494bd9")
    private GmAbstractDiagram viewedDiagramModel;

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is used.
     * @param viewedDiagram the unmasked diagram (can be <code>null</code>)
     * @param relatedRef a reference to the unmasked diagram (cannot be <code>null</code>).
     */
    @objid ("5f87d5fb-4271-4d34-86ed-b5bfb1431aeb")
    public  GmEmbeddedDiagram(final IGmDiagram diagram, final AbstractDiagram viewedDiagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.viewedDiagram = viewedDiagram;
        
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("3e7ed134-1ee9-4768-ab4c-01af511b6efe")
    public  GmEmbeddedDiagram() {
        // Nothing to do.
    }

    @objid ("7088b5a8-664d-483b-9950-01000330907d")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("fc676e42-f7c6-4d11-aa25-ead223810213")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("f25f8c82-e5b4-4ca8-96d9-7a7a2bfd703b")
    @Override
    public void delete() {
        dispose();
        
        super.delete();
        
    }

    @objid ("5469766c-c380-482a-b129-8fe668f14691")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        return null;
    }

    @objid ("33f8bec5-ea3a-489e-bf6b-fe6f8483a81e")
    @Override
    public int getMajorVersion() {
        return GmEmbeddedDiagram.MAJOR_VERSION;
    }

    @objid ("6bddddb8-bd13-4b3b-8b33-072f3ce9d637")
    @Override
    public AbstractDiagram getRelatedElement() {
        return this.viewedDiagram;
    }

    /**
     * Get the viewed diagram model.
     * <p>
     * Loads the diagram if not already done and loadIfNeeded is true.
     * </p>
     * @param loadIfNeeded Loads the diagram if not already done if true. If false and the diagram model is not loaded, return null.
     * @return the viewed diagram model. Might be <code>null</code>.
     */
    @objid ("6c264694-8b3d-4567-b2d5-d162178266a4")
    public GmAbstractDiagram getViewedDiagramModel(final boolean loadIfNeeded) {
        if (loadIfNeeded) {
            if (this.viewedDiagram != null
                    && this.viewedDiagram.isValid()
                    && (this.viewedDiagramModel == null || this.viewedDiagramModel.isDisposed())
                    && getDiagram() != null
                    && !isDiagramCycle()) {
                // Load the diagram
                final GmAbstractDiagram newDiagramModel = createGmDiagram(getDiagram().getModelManager(), this.viewedDiagram);
                newDiagramModel.setVisible(isVisible());
                newDiagramModel.load();
        
                // We do not want the diagram's content to be persisted, do not add it to the children
                newDiagramModel.setParentNode(this);
                newDiagramModel.refreshAllFromObModel();
                newDiagramModel.enableRefresh(true);
                setViewedDiagramModel(newDiagramModel);
                onViewedDiagramModelLoaded(newDiagramModel);
            }
            return this.viewedDiagramModel;
        } else {
            return this.viewedDiagramModel;
        }
        
    }

    @objid ("8c271621-31be-441e-836d-f19e3dd0eaf3")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        final List<GmNodeModel> ret = new ArrayList<>();
        final GmAbstractDiagram viewedModel = getViewedDiagramModel(true);
        if (viewedModel != null) {
            ret.addAll(viewedModel.getVisibleChildren());
        }
        return ret;
    }

    @objid ("e6a14e14-6d63-48e3-8bf8-85dc1fb644ee")
    protected boolean isDiagramCycle() {
        final IGmDiagram myDiagram = getDiagram();
        
        final MRef viewedRef = getRepresentedRef();
        
        for (IGmDiagram d = myDiagram; d != null; d = d.getDiagramOwner()) {
            if (d.getRepresentedRef().equals(viewedRef)) {
                return true;
            }
        }
        return false;
    }

    @objid ("a2b094c8-5159-48c5-93d1-7a10aedbbdee")
    @Override
    public boolean isUserEditable() {
        return super.isUserEditable() && this.viewedDiagramModel != null && this.viewedDiagramModel.isUserEditable();
    }

    /**
     * Tells whether the viewed diagram should be centered.
     * <p>
     * If not the diagram will be aligned on top left.
     * @return whether the viewed diagram should be centered
     */
    @objid ("db4ac006-626e-467c-bc47-abc367f84762")
    public boolean isViewToCenter() {
        final IGmDiagram m = getViewedDiagramModel(true);
        return m == null || !m.isUserEditable();
    }

    @objid ("b3c704ea-452a-4459-97f9-a4712ab5867f")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        final int readVersion = GmAbstractObject.readMinorVersion(in, GmEmbeddedDiagram.MINOR_PREFIX);
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        default:
            assert false : "version number not covered!";
        // reading as last handled version: 0
        read_0(in);
        break;
        }
        
    }

    @objid ("f6f975f2-93d3-4223-90af-598fca08be71")
    @Override
    public void removeChild(final GmNodeModel child) {
        // Viewed diagram is not really a child, ignore it
        assert child instanceof GmAbstractDiagram : child;
        
    }

    @objid ("be680020-0fdf-443f-b37a-13f330f5038c")
    @Override
    public void write(final IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, GmEmbeddedDiagram.MINOR_PREFIX, GmEmbeddedDiagram.MINOR_VERSION);
        
    }

    @objid ("1f45d5b4-0cda-41c7-906a-649eb1baeb6c")
    @Override
    protected void doSetVisible(final boolean visible) {
        super.doSetVisible(visible);
        if (this.viewedDiagramModel != null) {
            this.viewedDiagramModel.setVisible(visible);
        }
        
    }

    @objid ("4b2bb817-7274-4ae8-9f67-eec6722458c9")
    @Override
    protected void finalize() throws Throwable {
        dispose();
        super.finalize();
        
    }

    /**
     * Hook for sub classes called when the embedded diagram model is loaded.
     * @param newDiagramModel the loaded diagram.
     */
    @objid ("c77f80ba-8ffd-42e2-86eb-2d9665cb16f5")
    protected void onViewedDiagramModelLoaded(final IGmDiagram newDiagramModel) {
        // nothing by default
    }

    /**
     * Make sure the viewed diagram is deleted, to avoid leaks.
     */
    @objid ("cce4775f-000e-455b-ad10-cb80f17608c4")
    private void dispose() {
        if (this.viewedDiagramModel != null) {
            this.viewedDiagramModel.dispose();
            this.viewedDiagramModel.delete();
            setViewedDiagramModel(null);
        }
        
    }

    @objid ("2ead4e75-cc1d-47da-b03b-41654afd243b")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.viewedDiagram = (AbstractDiagram) resolveRef(getRepresentedRef());
        
    }

    @objid ("c5af0616-de6e-42c6-ac6a-a1702c848fff")
    private void setViewedDiagramModel(final GmAbstractDiagram newViewedModel) {
        final IGmDiagram oldViewedModel = this.viewedDiagramModel;
        final IGmDiagram ownGmDiagram = getDiagram();
        
        assert !(newViewedModel != null && ownGmDiagram == null) : String.format("setViewedDiagramModel(%s) on disposed %s.", newViewedModel, this);
        
        if (oldViewedModel != null && ownGmDiagram != null) {
            ownGmDiagram.removeEmbeddedDiagram(oldViewedModel);
        }
        
        this.viewedDiagramModel = newViewedModel;
        
        if (newViewedModel != null && ownGmDiagram != null) {
            ownGmDiagram.addEmbeddedDiagram(newViewedModel);
        }
        
        firePropertyChange(GmEmbeddedDiagram.PROP_INNER_DIAGRAM, oldViewedModel, newViewedModel);
        
    }

    @objid ("8c47f9e3-bfd7-4e6c-b814-42d2f0f984fd")
    private GmAbstractDiagram createGmDiagram(final IModelManager modelManager, final AbstractDiagram obDiagram) {
        Object lastProvider = null;
        for (final IConfigurationElement e : new ExtensionPointContributionManager("org.modelio.app.diagram.editor.inputprovider").getExtensions("inputprovider")) {
            try {
                final Object inputProvider = e.createExecutableExtension("class");
                if (inputProvider != null) {
                    if (inputProvider.getClass().getName().contains("StaticDiagramEditorInputProvider")) {
                        // TODO 'static' editor should always be handled last, but checking the class name is kind of ugly
                        lastProvider = inputProvider;
                    } else {
                        final GmAbstractDiagram ret = invokeCreator(inputProvider, modelManager, obDiagram);
                        if (ret != null) {
                            return ret;
                        }
                    }
                }
            } catch (final CoreException e1) {
                DiagramElements.LOG.error(e1);
            }
        }
        return lastProvider != null ? invokeCreator(lastProvider, modelManager, obDiagram) : null;
    }

    @objid ("97b27978-eeef-4a28-a85b-41a9c63cccfd")
    protected GmAbstractDiagram invokeCreator(final Object inputProvider, final IModelManager modelManager, final AbstractDiagram obDiagram) {
        Method getCreatorMethod;
        try {
            getCreatorMethod = inputProvider.getClass().getMethod("getDiagramCreator");
            final Object creator = getCreatorMethod.invoke(inputProvider);
            if (creator != null) {
                final Method createDiagramMethod = creator.getClass().getMethod("createDiagram", IModelManager.class, AbstractDiagram.class);
                final Object result = createDiagramMethod.invoke(creator, modelManager, obDiagram);
                if (result instanceof GmAbstractDiagram) {
                    return (GmAbstractDiagram) result;
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            DiagramElements.LOG.error(e);
        }
        return null;
    }

}

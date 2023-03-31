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
package org.modelio.diagram.diagramauto.diagram.creator;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram;

/**
 * Skeleton for a Model View template implementation.
 * <p>
 * By default the {@link #updateView(AbstractDiagram)} method sequences the diagram's contents creation to avoid performance issues caused by heavy link routing. This default sequence is as follows:
 * <ol>
 * <li>Clean existing contents</li>
 * <li>Unmask the nodes that are part of the created diagram.</li>
 * <li>Layout these nodes.</li>
 * <li>Unmask the links that are part of the created diagram.</li>
 * <li>Layout these links.</li>
 * </ol>
 * </p>
 * 
 * 
 * <p>
 * For implementers: how to implement
 * <ol>
 * <li>Create a subclass</li>
 * <li>Implement the abstract methods (see their definitions for documentation):
 * <ul>
 * <li>{@link #reset()}</li>
 * <li>{@link #createDiagramElement()}</li>
 * <li>{@link #generateNodesContent(IDiagramHandle, ModelElement)}
 * <li>{@link #layoutNodes(IDiagramHandle)}</li>
 * <li>{@link #generateLinksContent(IDiagramHandle, ModelElement)}
 * <li>{@link #layoutLinks(IDiagramHandle)}</li>
 * </ul>
 * <li>If the provided default behavior does not suit your needs you may also redefine the following methods:
 * <ul>
 * <li>{@link #updateView(AbstractDiagram)} <br/>
 * to change the default update view sequence (clean contents -> gen nodes -> layout nodes -> gen links -> layout links)</li>
 * <li>{@link #clearContent(DiagramHandle)} <br/>
 * to change the default 'clear existing contents' behavior.
 * </ul>
 * </ol>
 * </p>
 * 
 * <p>
 * For Callers : how to use
 * <ol>
 * <li>Use {@link #createView(ModelElement)} : this method creates a diagram no matter such a diagram already exists. The caller is invited to use {@link #getExistingView(ModelElement)} to check for an existing diagram and call
 * {@link #updateView(AbstractDiagram)} to update it if only one diagram instance is expected.
 * <li>Use {@link #updateView(AbstractDiagram)} : to update an already existing 'auto' diagram</li>
 * <li>Use {@link #getExistingView(ModelElement)} : to get the already existing diagram if such a diagram already exists.</li>
 * </ol>
 * </p>
 */
@objid ("7b0608cc-5339-4a12-91aa-df36556c07a9")
public abstract class AbstractDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("3839fb56-8140-491b-b056-ac5f1c42655c")
    @Inject
    @Optional
    private IModuleContext localModuleContext;

    @objid ("82e36ced-0fe5-4c0e-ba2e-cc78480e886a")
    @Inject
    protected IMModelServices modelServices;

    /**
     * Clear the contents of the diagram simply masking all its nodes and links.
     */
    @objid ("db0a94b0-ce28-4aec-b8d3-d411176a067c")
    protected void clearContent(final IDiagramHandle dh) {
        // Mask all the nodes will mask links as a side effects
        for (IDiagramNode node : dh.getDiagramNode().getNodes()) {
            node.mask();
        }
        
    }

    @objid ("8128a926-bcfa-4a9b-889e-d25217dc22c8")
    private AbstractDiagram createAutoDiagram(final ModelElement main) {
        ModelElement context = resolveOrigin(main);
        if (context == null) {
            return null;
        }
        
        AbstractDiagram diagram = createDiagramElement();
        context.getProduct().add(diagram);
        diagram.setName(main.getName() + " (" + getId() + ")");
        diagram.getExtension().add(AutoDiagram.MdaTypes.STEREOTYPE_ELT);
        return diagram;
    }

    @objid ("6a8cce36-ce9e-46f5-ae6c-94c7c8778eec")
    protected abstract AbstractDiagram createDiagramElement();

    @objid ("d2278822-5961-4832-b4ba-3b7572246fb8")
    @Override
    public final AbstractDiagram createView(final ModelElement main) {
        // Get the auto diagram
        try {
            AbstractDiagram newDiagram = createAutoDiagram(main);
            newDiagram.setProperty("AutoDiagram", "Kind", getId());
            updateView(newDiagram);
            return newDiagram;
        } catch (Exception e) {
            DiagramAuto.LOG.debug(e);
        }
        return null;
    }

    /**
     * Perform the initial link unmasking.
     * @param dh the edited diagram.
     * @param main the main node.
     * @since 5.1.1
     */
    @objid ("24a5876f-a59f-49ef-9c7a-404aaaf3f0e8")
    protected abstract void generateLinksContent(final IDiagramHandle dh, final ModelElement main);

    /**
     * Perform the initial node unmasking.
     * @param dh the edited diagram.
     * @param main the main node.
     * @since 5.1.1
     */
    @objid ("f20e003d-dec3-4970-a37b-999c617a62af")
    protected abstract void generateNodesContent(final IDiagramHandle dh, final ModelElement main);

    /**
     * Get an already existing diagram of this type for the element.
     * @return the diagram if it exists, <code>null</code> otherwise.
     */
    @objid ("bbbe495b-7957-486c-94ec-0fe2a0406fcc")
    @Override
    public final AbstractDiagram getExistingView(final ModelElement main) {
        ModelElement context = resolveOrigin(main);
        if (context != null) {
            for (AbstractDiagram diagram : context.getProduct()) {
                if (diagram.isStereotyped(AutoDiagram.MdaTypes.STEREOTYPE_ELT)) {
                    // Match by type property
                    String kind = diagram.getProperty("AutoDiagram", "Kind");
                    if (getId().equals(kind) || getOldType().equals(kind)) {
                        return diagram;
                    }
                }
            }
        }
        return null;
    }

    @objid ("e8422567-8e74-4e8e-9686-31219caefb2e")
    @Override
    public final String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("96be1893-87d4-4878-804c-2d3d73716c70")
    public abstract String getOldType();

    /**
     * Layout all links in the diagram.
     * @param dh the edited diagram.
     * @since 5.1.1
     */
    @objid ("b377e0c2-c8e7-4fdc-bbfd-c0962aff59f4")
    protected abstract void layoutLinks(final IDiagramHandle dh);

    /**
     * Layout all nodes in the diagram.
     * @param dh the edited diagram.
     * @since 5.1.1
     */
    @objid ("9dc8bd3a-53b1-41cb-b963-a867e6150278")
    protected abstract void layoutNodes(final IDiagramHandle dh);

    @objid ("6147d67a-7d48-4b79-a8f4-140d6a56067b")
    protected abstract void reset();

    @objid ("3c1ef72a-0dc4-4ded-b604-83886edc49b2")
    @Override
    public final void updateView(AbstractDiagram existingDiagram) {
        ModelElement main = getMainElement(existingDiagram);
        
        // update diagram kind
        String kind = existingDiagram.getProperty("AutoDiagram", "Kind");
        if (getOldType().equals(kind)) {
            // Migration case
            existingDiagram.setProperty("AutoDiagram", "Kind", getId());
        } else if (!getId().equals(kind)) {
            throw new IllegalArgumentException("Incompatible diagram type, update is not possible for " + existingDiagram);
        }
        
        try {
            reset();
            // get the diagram handle to work with
            try (IDiagramHandle dh = this.localModuleContext.getModelioServices().getDiagramService().getDiagramHandle(existingDiagram)) {
                dh.setBatchMode(true);
        
                clearContent(dh);
        
                generateNodesContent(dh, main);
                layoutNodes(dh);
        
                // Performance optimization: unmask links AFTER node layout to minimize routing calls
                generateLinksContent(dh, main);
                layoutLinks(dh);
        
                // open the resulting diagram
                dh.setBatchMode(false);
                dh.save();
            }
        } catch (Exception e) {
            DiagramAuto.LOG.debug(e);
        } finally {
            reset();
        }
        
    }

    @objid ("18d10845-f0d8-428a-814d-eedf5bfa578c")
    protected boolean isUnmasked(IDiagramHandle dh, ModelElement me) {
        List<IDiagramGraphic> nodes = dh.getDiagramGraphics(me);
        return (nodes != null) && !nodes.isEmpty();
    }

}

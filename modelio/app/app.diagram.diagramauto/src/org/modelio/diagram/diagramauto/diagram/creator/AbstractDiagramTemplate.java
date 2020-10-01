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

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram;

@objid ("7b0608cc-5339-4a12-91aa-df36556c07a9")
public abstract class AbstractDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("82e36ced-0fe5-4c0e-ba2e-cc78480e886a")
    @Inject
    protected IMModelServices modelServices;

    @objid ("3839fb56-8140-491b-b056-ac5f1c42655c")
    @Inject
    @Optional
    private IModuleContext localModuleContext;

    @objid ("a068b3d7-d0c3-4804-ad04-d37951c95983")
    public AbstractDiagramTemplate() {
        super();
    }

    @objid ("d2278822-5961-4832-b4ba-3b7572246fb8")
    @Override
    public final AbstractDiagram createView(final ModelElement main) {
        // get the auto diagram
        try {
            AbstractDiagram newDiagram = createAutoDiagram(main);
            newDiagram.setProperty("AutoDiagram", "Kind", getId());
            updateView(newDiagram);
            return newDiagram;
        } catch (Exception e) {
            DiagramAuto.LOG.debug(e);
            return null;
        }
    }

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

    @objid ("af1ac11c-3710-4f9a-900d-aca9dd06863a")
    protected abstract void generateContent(final IDiagramHandle dh, final ModelElement main);

    @objid ("86b6e380-ffa6-43f4-9d2a-e38a6b33177c")
    protected abstract void layout(final IDiagramHandle dh);

    @objid ("6a8cce36-ce9e-46f5-ae6c-94c7c8778eec")
    protected abstract AbstractDiagram createDiagramElement();

    @objid ("e8422567-8e74-4e8e-9686-31219caefb2e")
    @Override
    public final String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("96be1893-87d4-4878-804c-2d3d73716c70")
    public abstract String getOldType();

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
        
                // perform the inital unmasking (mainly nodes)
                generateContent(dh, main);
        
                // layout the diagram
                layout(dh);
        
                // open the resulting diagram
                dh.save();
                dh.setBatchMode(false);
            }
        } catch (Exception e) {
            DiagramAuto.LOG.debug(e);
        } finally {
            reset();
        }
    }

    @objid ("6147d67a-7d48-4b79-a8f4-140d6a56067b")
    protected abstract void reset();

}

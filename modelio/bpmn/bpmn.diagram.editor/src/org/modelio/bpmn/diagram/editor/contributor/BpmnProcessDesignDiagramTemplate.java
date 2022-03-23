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
package org.modelio.bpmn.diagram.editor.contributor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.bpmn.diagram.editor.layout.BpmnLayouter;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("8e0bcce7-2904-433b-add9-970340132578")
public class BpmnProcessDesignDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("9686e518-a477-423a-88f6-cc449ec67009")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("b6c8a960-1c82-439c-923c-c4e1bc789619")
    public  BpmnProcessDesignDiagramTemplate() {
        super();
    }

    @objid ("d81ad301-af8d-4da0-8383-387ebb1cb453")
    @Override
    public String getId() {
        return BpmnProcessDesignDiagramTemplate.class.getSimpleName();
    }

    @objid ("2d5867ad-e5c5-4d84-93f2-4fedfe821ed0")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        if (base == null) {
            return null;
        }
        final IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        
        BpmnProcessDesignDiagram diagram = modelFactory.createBpmnProcessDesignDiagram();
        diagram.setOrigin(base);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        
        // Layout diagram
        new BpmnLayouter(diagram).run();
        return diagram;
    }

    @objid ("e4ad5c85-1b84-472c-8068-4ff20cc5e1e7")
    @Override
    public AbstractDiagram getExistingView(ModelElement main) {
        // Not supported concept
        return null;
    }

    @objid ("9338ec96-6cf8-49b0-97fd-134566ffb83f")
    @Override
    public void updateView(AbstractDiagram existingDiagram) {
        // Not supported concept
    }

    @objid ("48d179d3-40e2-42a3-b97b-47e9f3744a3c")
    @Override
    public ModelElement resolveOrigin(ModelElement main) {
        return main;
    }

    @objid ("d0297721-0133-4130-a34a-05aca13362ea")
    @Override
    public ModelElement getMainElement(AbstractDiagram diagram) {
        return diagram.getOrigin();
    }

}

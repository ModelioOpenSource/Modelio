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

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.bpmn.diagram.editor.layout.BpmnLayouter;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("e746c04a-72f3-409a-942f-33bc3bae6206")
public class BpmnSubProcessDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("492c2d59-2aff-4a2e-b953-79404654b9dd")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("b9187053-6fb0-4a77-a616-495b8e6d9019")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("0e2c969f-b630-4c9c-a3a0-8ef33fd168d2")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        
        AbstractDiagram diagram = modelFactory.createBpmnSubProcessDiagram();
        diagram.setOrigin(base);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        new BpmnLayouter(diagram).run();
        return diagram;
    }

    @objid ("58c30f86-54e4-4e99-bd4c-16bc64fc8125")
    @Override
    public AbstractDiagram getExistingView(ModelElement base) {
        // Not supported concept
        return null;
    }

    @objid ("ffb5c2c9-1b45-4b77-bb31-3a6b7876bc46")
    @Override
    public void updateView(AbstractDiagram existingView) {
        // Not supported concept
    }

    @objid ("98322c14-a442-4f95-be8f-27b4541dd98a")
    @Override
    public ModelElement resolveOrigin(ModelElement base) {
        return base;
    }

    @objid ("9c1de149-1bd5-48b5-86e7-0e9d51a60714")
    @Override
    public ModelElement getMainElement(AbstractDiagram view) {
        return view.getOrigin();
    }

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("f6ddb00c-52b6-4eda-aeb6-9e5df97f6b56")
    public BpmnSubProcessDiagramTemplate() {
        super();
    }

}

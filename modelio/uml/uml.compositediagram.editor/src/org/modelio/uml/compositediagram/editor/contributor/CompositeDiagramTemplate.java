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
package org.modelio.uml.compositediagram.editor.contributor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("2e6f3eda-6333-49cf-a7bf-b5e6e642c2cb")
public class CompositeDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("32c72900-bd70-4237-8085-1faa9e9203cf")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("0965d1d3-17b6-433b-83f5-5424e09de274")
    public  CompositeDiagramTemplate() {
        super();
    }

    @objid ("ac4301bc-948a-44b2-a806-ae999e653f0b")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("eca7c4b9-90ed-430f-973e-b451d80c8854")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        StaticDiagram diagram = modelFactory.createCompositeStructureDiagram();
        diagram.setOrigin(base);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        return diagram;
    }

    @objid ("94ad87ec-1b4f-485d-9462-041df66d3f92")
    @Override
    public AbstractDiagram getExistingView(ModelElement base) {
        // Not supported concept
        return null;
    }

    @objid ("1ba6e38d-282f-404a-8234-a302ceca9229")
    @Override
    public void updateView(AbstractDiagram existingView) {
        // Not supported concept
    }

    @objid ("3b07babf-f58c-4160-92d8-615f77556ea7")
    @Override
    public ModelElement resolveOrigin(ModelElement base) {
        return base;
    }

    @objid ("8f9fce15-f280-4b6f-93e1-ddd91e1effcf")
    @Override
    public ModelElement getMainElement(AbstractDiagram view) {
        return view.getOrigin();
    }

}

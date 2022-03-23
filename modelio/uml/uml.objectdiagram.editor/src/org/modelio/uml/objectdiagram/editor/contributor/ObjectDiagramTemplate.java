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
package org.modelio.uml.objectdiagram.editor.contributor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("1c7942cd-59dd-44ff-8c13-a777067fe1e9")
public class ObjectDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("00255442-4351-4584-8904-ac66c891e9d4")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("e6a04bcf-e88e-44ac-9a31-5c0040b6a2ce")
    public  ObjectDiagramTemplate() {
        super();
    }

    @objid ("9b9a2f29-e073-42de-90f7-fac3cf4257e0")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("bc8da2bc-ec2d-4524-9a3c-2fbf2d8f85f2")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        StaticDiagram diagram = modelFactory.createObjectDiagram();
        diagram.setOrigin(base);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        return diagram;
    }

    @objid ("75d774c0-71ce-43c9-97bd-45e0a99e7d52")
    @Override
    public AbstractDiagram getExistingView(ModelElement base) {
        // Not supported concept
        return null;
    }

    @objid ("2afa9fd0-5c89-46cf-9252-70ebd03af31a")
    @Override
    public void updateView(AbstractDiagram existingView) {
        // Not supported concept
    }

    @objid ("c9dccf55-032c-467e-b585-8e213f28cf76")
    @Override
    public ModelElement resolveOrigin(ModelElement base) {
        return base;
    }

    @objid ("e9039fb4-9a7e-4dc3-a269-ccd9075c2ce0")
    @Override
    public ModelElement getMainElement(AbstractDiagram view) {
        return view.getOrigin();
    }

}

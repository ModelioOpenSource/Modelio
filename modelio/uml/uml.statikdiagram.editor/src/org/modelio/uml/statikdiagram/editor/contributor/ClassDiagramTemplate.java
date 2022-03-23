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
package org.modelio.uml.statikdiagram.editor.contributor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("008b64d7-8649-483c-9e08-973242d8c807")
public class ClassDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("973b854e-30f3-4f03-8b97-5a0d2743c3c9")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("16762993-a2d6-4f36-900b-167113d65d05")
    @Override
    public String getId() {
        return ClassDiagramTemplate.class.getSimpleName();
    }

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("0663e01c-6553-491f-b3f6-26a2cc265b0e")
    public  ClassDiagramTemplate() {
        super();
    }

    @objid ("26fe9c76-3e9a-48a5-b764-0ba2393f40db")
    @Override
    public AbstractDiagram createView(ModelElement main) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        AbstractDiagram diagram =  modelFactory.createClassDiagram();
        diagram.setOrigin(main);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        return diagram;
    }

    @objid ("1c39d6fa-5dcf-499f-a0fd-ac30b49127b0")
    @Override
    public AbstractDiagram getExistingView(ModelElement main) {
        // Not supported concept
        return null;
    }

    @objid ("f798169c-0a36-40a9-9116-f7dbd51a59a4")
    @Override
    public void updateView(AbstractDiagram existingDiagram) {
        // Not supported concept
    }

    @objid ("092a1276-57ee-437c-871b-d7a7e424fe35")
    @Override
    public ModelElement resolveOrigin(ModelElement main) {
        return main;
    }

    @objid ("a8126e9b-a70f-43a2-b21f-c416362d5693")
    @Override
    public ModelElement getMainElement(AbstractDiagram diagram) {
        return diagram.getOrigin();
    }

}

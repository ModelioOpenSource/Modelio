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
package org.modelio.uml.deploymentdiagram.editor.contributor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("31fc409b-07ee-4168-b24b-37466afa3bf1")
public class DeploymentDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("826e1477-dd8a-49eb-9132-acfdb9040a76")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("2472a0f3-1f98-4f98-a341-dcce42928479")
    public  DeploymentDiagramTemplate() {
        super();
    }

    @objid ("58774f24-8bd2-4bc2-8e74-f87bcb1f4478")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("7d1edfc8-e9c2-4e01-b02e-b3b0f3ae9b1f")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        StaticDiagram diagram = modelFactory.createDeploymentDiagram();
        diagram.setOrigin(base);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        return diagram;
    }

    @objid ("99b79e81-7d35-4758-a1b4-505e451d76d3")
    @Override
    public AbstractDiagram getExistingView(ModelElement base) {
        // Not supported concept
        return null;
    }

    @objid ("1f5ab9a9-ee4f-4735-9e25-29ad31633950")
    @Override
    public void updateView(AbstractDiagram existingView) {
        // Not supported concept
    }

    @objid ("bdc70a2d-2629-4b4b-8b0c-20cf97cc8543")
    @Override
    public ModelElement resolveOrigin(ModelElement base) {
        return base;
    }

    @objid ("129ddf69-0d04-461f-a3ef-f2d36f7a9aa4")
    @Override
    public ModelElement getMainElement(AbstractDiagram view) {
        return view.getOrigin();
    }

}

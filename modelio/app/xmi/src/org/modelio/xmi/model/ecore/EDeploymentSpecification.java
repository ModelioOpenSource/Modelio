/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.module.modelermodule.api.xmi.standard.artifact.UML2DeploymentSpecification;

@objid ("4fd7b757-d1c3-493d-98ff-6e3b8ff54e07")
public class EDeploymentSpecification extends EArtifact {
    @objid ("a5c6095e-2aa1-415e-9ef0-3b46b9e84050")
    @Override
    public Element createObjingElt() {
        return UML2DeploymentSpecification.create().getElement();
    }

    @objid ("217c6da0-1819-40cf-98b1-ba75c1d0df92")
    public EDeploymentSpecification(org.eclipse.uml2.uml.DeploymentSpecification element) {
        super(element);
    }

}

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

package org.modelio.metamodel.impl.mmextensions.infrastructure.namer.helpers;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.vbasic.i18n.MessageBundle;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("70cec782-ba1d-4c9e-9aef-76874d7e1821")
public class GetInfraNameVisitor extends DefaultInfrastructureVisitor {
    @objid ("dba0fa06-a5ef-4ef9-9728-712b6d96e73a")
    private static final MessageBundle I18N = new MessageBundle(ResourceBundle.getBundle("infrastructure_default_name"));

    @objid ("2611cacb-4f23-4b7b-9218-f3d29fd95b2d")
    public GetInfraNameVisitor() {
        // Empty
    }

    @objid ("9e5a8f4a-2f85-4007-b1d1-98f047368106")
    public String getDefaultName(MObject element) {
        return (String) element.accept(this);
    }

    @objid ("2fb45438-3721-46a9-9921-b48e114d0035")
    @Override
    public Object visitAbstractDiagram(AbstractDiagram theAbstractDiagram) {
        return GetInfraNameVisitor.I18N.getMessage("$DefaultName.Diagram.IAbstract", theAbstractDiagram.getOrigin().getName());
    }

    @objid ("9e581df5-7b97-4f05-bfdb-ebb44f77d56d")
    @Override
    public Object visitDependency(Dependency theDependency) {
        return "";
    }

    @objid ("bbf5ed7c-00a7-4a9a-b7ed-f8d7c2609cb4")
    @Override
    public Object visitDiagramSet(DiagramSet theDiagramSet) {
        if (theDiagramSet.getOwner() != null) {
            return GetInfraNameVisitor.I18N.getMessage("$DefaultName.DiagramSet.Root");
        } else {
            return GetInfraNameVisitor.I18N.getMessage("$DefaultName.DiagramSet");
        }
    }

    @objid ("811e0c0e-e477-4912-9006-670df6750963")
    @Override
    public Object visitElement(Element theElement) {
        return "";
    }

    @objid ("55cc9f50-b56d-46d9-9a6d-7a1a52374f83")
    @Override
    public Object visitModelElement(ModelElement theModelElement) {
        return theModelElement.getMClass().getName();
    }

    @objid ("316b79b9-49d2-467e-bc7a-fc313f944fc4")
    @Override
    public Object visitModuleParameter(ModuleParameter theConfigParam) {
        return GetInfraNameVisitor.I18N.getMessage("$DefaultName.ConfigParam");
    }

    @objid ("5517c9f7-2364-4ed5-a3af-39483d1060ca")
    @Override
    public Object visitImpactProject(ImpactProject theImpactProject) {
        return GetInfraNameVisitor.I18N.getMessage("$DefaultName.ImpactProject");
    }

}

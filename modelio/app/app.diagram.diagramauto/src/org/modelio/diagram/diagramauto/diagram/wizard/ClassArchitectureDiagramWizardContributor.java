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

package org.modelio.diagram.diagramauto.diagram.wizard;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("84a28ca5-b3ec-47ce-8d63-717c12badb78")
public class ClassArchitectureDiagramWizardContributor extends AbstractAutoDiagramWizardContributor {
    @objid ("8a733c23-e442-4dac-91c7-e9b5d5020b37")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Artifact.class), true, null, true));
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Class.class), true, null, true));
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Component.class), true, null, true));
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(DataType.class), true, null, true));
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Enumeration.class), true, null, true));
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Interface.class), true, null, true));
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Node.class), true, null, true));
        return allowedScopes;
    }

    @objid ("930409b4-f1bb-4d78-a990-c51687f77566")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(ClassDiagram.class);
        if (AutoDiagram.MdaTypes.STEREOTYPE_ELT != null) {
            return new ElementDescriptor(mClass, AutoDiagram.MdaTypes.STEREOTYPE_ELT);
        } else {
            return null;
        }
    }

    @objid ("9cc2f8b6-a3ec-44b5-825b-ae56229474bc")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("d567ad58-dcd4-4cf6-8288-01056c0ec845")
public class DependencyDiagramWizardContributor extends AbstractAutoDiagramWizardContributor {
    @objid ("c08ba07b-2fb0-4b12-bf41-701f00b2fbd7")
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
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Package.class), true, null, true));
        return allowedScopes;
    }

    @objid ("8f1fd6f1-7106-4f2a-9861-62be69c8ca8f")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(ClassDiagram.class);
        if (AutoDiagram.MdaTypes.STEREOTYPE_ELT != null) {
            return new ElementDescriptor(mClass, AutoDiagram.MdaTypes.STEREOTYPE_ELT);
        } else {
            return null;
        }
    }

    @objid ("de8c330b-acda-43c3-a3bd-b4a2d14a8302")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}

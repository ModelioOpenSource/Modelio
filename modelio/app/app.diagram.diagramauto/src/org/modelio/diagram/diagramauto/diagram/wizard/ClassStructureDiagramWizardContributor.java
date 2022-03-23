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

@objid ("327a99b7-f436-4891-9e5f-b02e0495f72f")
public class ClassStructureDiagramWizardContributor extends AbstractAutoDiagramWizardContributor {
    @objid ("104e99f0-1920-48f5-bbc8-f8b82d85482b")
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

    @objid ("b173946c-e5a7-46f6-806b-7d1d33328ecd")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(ClassDiagram.class);
        if (AutoDiagram.MdaTypes.STEREOTYPE_ELT != null) {
            return new ElementDescriptor(mClass, AutoDiagram.MdaTypes.STEREOTYPE_ELT);
        } else {
            return null;
        }
        
    }

    @objid ("02d0ecfc-a163-4231-8fe5-6a09b577cba8")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}

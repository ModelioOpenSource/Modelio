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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("904d8230-8b74-4b82-96fa-f1b253b015e6")
public class SubPackageStructureDiagramWizardContributor extends AbstractAutoDiagramWizardContributor {
    @objid ("7b14a4c9-ce7a-4a34-8e14-597758af4415")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Package.class), true, null, true));
        return allowedScopes;
    }

    @objid ("7940f082-3adb-4371-8454-c640b5f545f4")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(ClassDiagram.class);
        if (AutoDiagram.MdaTypes.STEREOTYPE_ELT != null) {
            return new ElementDescriptor(mClass, AutoDiagram.MdaTypes.STEREOTYPE_ELT);
        } else {
            return null;
        }
    }

    @objid ("c7c18fd1-80cf-451a-9bf6-ae3f6cc606b6")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}

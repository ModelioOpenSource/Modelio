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

package org.modelio.diagram.diagramauto.handlers;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.diagram.diagramauto.diagram.creator.PackageContentStructureCreator;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("07c0ade8-005e-486e-abdf-25b0868fd7f6")
public class PackageStructureDiagram extends AbstractHandler {
    @objid ("42ea8325-f2ca-43a2-ad04-06935469512a")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        PackageContentStructureCreator csc = new PackageContentStructureCreator(this.mmServices);
        AbstractDiagram diagram = csc.createDiagram(diagramContext);
        diagram.setName(diagramName);
        try {
            diagram.putNoteContent("ModelerModule", "description", diagramDescription);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return diagram;
    }

    @objid ("ce0c34e7-4fcd-42e4-8b76-5bb77ae19510")
    @Override
    public String getDetails() {
        return DiagramAuto.I18N.getString("CreationWizard.PackageStructure.Details");
    }

    @objid ("4c6a645e-6558-44ff-9525-ac4b9c1a19ab")
    @Override
    public String getInformation() {
        return DiagramAuto.I18N.getString("CreationWizard.PackageStructure.Information");
    }

    @objid ("e2a2104d-2d86-44a4-ae5c-38a797ed6795")
    @Override
    public String getLabel() {
        return DiagramAuto.I18N.getString("CreationWizard.PackageStructure.Name");
    }

    @objid ("954752f3-5d77-49cf-99a4-8f243fef795b")
    @Override
    protected String getPreviewImagePath() {
        return DiagramAuto.I18N.getString("CreationWizard.PackageStructure.PreviewImage");
    }

    @objid ("ffeeeb49-16a6-4af5-a3d1-910e003fab50")
    @Override
    protected String getIconPath() {
        return DiagramAuto.I18N.getString("CreationWizard.PackageStructure.Icon");
    }

    @objid ("ec17474e-aa6f-4a18-a46e-3d063f1c66a4")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Package.class), true, null, true));
        return allowedScopes;
    }

    @objid ("c6b59110-bf24-4c7e-8b11-a30f01e61472")
    @Override
    public boolean accept(MObject owner) {
        if (!super.accept(owner)) {
            return false;
        }
        
        PackageContentStructureCreator pc = new PackageContentStructureCreator(this.mmServices);
        
        if ((owner instanceof Package)) {
            // Deactivate if no context is found
            if (pc.getAutoDiagramContext((Package) owner) == null) {
                return false;
            }
        
            AbstractDiagram existingdiagramauto = pc.getExistingAutoDiagram((Package) owner);
        
            // Unmodifiable diagram means the command is disabled
            if (existingdiagramauto != null && !existingdiagramauto.getStatus().isModifiable()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @objid ("ec043216-3876-4ce6-8f2e-64f4fa90de68")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(ClassDiagram.class);
        if (AutoDiagram.MdaTypes.STEREOTYPE_ELT != null) {
            return new ElementDescriptor(mClass, AutoDiagram.MdaTypes.STEREOTYPE_ELT);
        } else {
            return null;
        }
    }

}

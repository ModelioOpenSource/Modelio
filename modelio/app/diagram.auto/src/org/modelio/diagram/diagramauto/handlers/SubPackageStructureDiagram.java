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
import org.modelio.diagram.diagramauto.diagram.creator.SubPackageStructureCreator;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("904d8230-8b74-4b82-96fa-f1b253b015e6")
public class SubPackageStructureDiagram extends AbstractHandler {
    @objid ("fd54dab9-0bd6-486e-8eef-664e21bb6126")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        SubPackageStructureCreator csc = new SubPackageStructureCreator(this.mmServices);
        AbstractDiagram diagram = csc.createDiagram(diagramContext);
        diagram.setName(diagramName);
        try {
            diagram.putNoteContent("ModelerModule", "description", diagramDescription);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return diagram;
    }

    @objid ("a3640e94-e48b-44ad-a7dd-3db6bb4c3638")
    @Override
    public String getDetails() {
        return DiagramAuto.I18N.getString("CreationWizard.SubPackageStructure.Details");
    }

    @objid ("6e20c71f-7bb4-4f48-a6db-435a52323ff5")
    @Override
    public String getInformation() {
        return DiagramAuto.I18N.getString("CreationWizard.SubPackageStructure.Information");
    }

    @objid ("29a1b172-4cce-4043-97de-38d0fb0bc976")
    @Override
    public String getLabel() {
        return DiagramAuto.I18N.getString("CreationWizard.SubPackageStructure.Name");
    }

    @objid ("abec6eaa-8450-4d1c-bbeb-dfcf9eb13527")
    @Override
    protected String getPreviewImagePath() {
        return DiagramAuto.I18N.getString("CreationWizard.SubPackageStructure.PreviewImage");
    }

    @objid ("0677df64-be5d-42b3-acf8-5692ed969786")
    @Override
    protected String getIconPath() {
        return DiagramAuto.I18N.getString("CreationWizard.SubPackageStructure.Icon");
    }

    @objid ("7b14a4c9-ce7a-4a34-8e14-597758af4415")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        allowedScopes.add(new ElementScope(getMetamodel().getMClass(Package.class), true, null, true));
        return allowedScopes;
    }

    @objid ("f178b37e-78df-4a5d-99bd-d0fe80017560")
    @Override
    public boolean accept(MObject owner) {
        if (!super.accept(owner)) {
            return false;
        }
        
        SubPackageStructureCreator pc = new SubPackageStructureCreator(this.mmServices);
        
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

}

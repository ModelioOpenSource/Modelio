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
import org.modelio.diagram.diagramauto.diagram.creator.ClassStructureCreator;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.module.modelermodule.api.default_.infrastructure.abstractdiagram.AutoDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("327a99b7-f436-4891-9e5f-b02e0495f72f")
public class ClassStructureDiagram extends AbstractHandler {
    @objid ("fc101633-7fd1-411f-b717-f833b4fb246e")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        ClassStructureCreator csc = new ClassStructureCreator(this.mmServices);
        AbstractDiagram diagram = csc.createDiagram(diagramContext);
        diagram.setName(diagramName);
        try {
            diagram.putNoteContent("ModelerModule", "description", diagramDescription);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return diagram;
    }

    @objid ("56257bcd-a5b3-451c-9d8c-df0b05157b1a")
    @Override
    public String getDetails() {
        return DiagramAuto.I18N.getString("CreationWizard.ClassStructure.Details");
    }

    @objid ("1817522b-f2b0-4bc1-ae77-3e32cb94749b")
    @Override
    public String getInformation() {
        return DiagramAuto.I18N.getString("CreationWizard.ClassStructure.Information");
    }

    @objid ("a8886c88-5ae5-4f54-9a86-08019f54b217")
    @Override
    public String getLabel() {
        return DiagramAuto.I18N.getString("CreationWizard.ClassStructure.Name");
    }

    @objid ("df02b31e-4c7f-4b51-b8cb-5c428b76f8a5")
    @Override
    protected String getPreviewImagePath() {
        return DiagramAuto.I18N.getString("CreationWizard.ClassStructure.PreviewImage");
    }

    @objid ("6fa50015-f6af-441d-b995-00fce73ee94d")
    @Override
    protected String getIconPath() {
        return DiagramAuto.I18N.getString("CreationWizard.ClassStructure.Icon");
    }

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

    @objid ("7e98b3e1-9ddb-4fc4-9ce2-4d55961aa4c8")
    @Override
    public boolean accept(MObject owner) {
        if (!super.accept(owner)) {
            return false;
        }
        
        ClassStructureCreator pc = new ClassStructureCreator(this.mmServices);
        
        if ((owner instanceof Classifier)) {
            // Deactivate if no context is found
            if (pc.getAutoDiagramContext((Classifier) owner) == null) {
                return false;
            }
        
            AbstractDiagram existingdiagramauto = pc.getExistingAutoDiagram((Classifier) owner);
        
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

}

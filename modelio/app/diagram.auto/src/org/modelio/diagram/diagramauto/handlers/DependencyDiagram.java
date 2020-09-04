/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.diagram.diagramauto.diagram.creator.DependencyCreator;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d567ad58-dcd4-4cf6-8288-01056c0ec845")
public class DependencyDiagram extends AbstractHandler {
    @objid ("afab25aa-fb00-4acf-acad-c1f9bd4e5fc4")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        DependencyCreator csc = new DependencyCreator(this.mmServices);
        AbstractDiagram diagram = csc.createDiagram(diagramContext);
        diagram.setName(diagramName);
        try {
            diagram.putNoteContent("ModelerModule", "description", diagramDescription);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return diagram;
    }

    @objid ("50fa8899-978c-4113-9188-a6b5177ad5ec")
    @Override
    public String getDetails() {
        return DiagramAuto.I18N.getString("CreationWizard.Dependency.Details");
    }

    @objid ("40c602d9-6194-4163-abb7-4cfd416220c1")
    @Override
    public String getInformation() {
        return DiagramAuto.I18N.getString("CreationWizard.Dependency.Information");
    }

    @objid ("23cac369-3403-4e98-a0c0-f96dc79b8350")
    @Override
    public String getLabel() {
        return DiagramAuto.I18N.getString("CreationWizard.Dependency.Name");
    }

    @objid ("273dc8a6-8c70-4966-9849-623ca72dd675")
    @Override
    protected String getPreviewImagePath() {
        return DiagramAuto.I18N.getString("CreationWizard.Dependency.PreviewImage");
    }

    @objid ("c882b754-7049-47c3-bc5b-e1f257516348")
    @Override
    protected String getIconPath() {
        return DiagramAuto.I18N.getString("CreationWizard.Dependency.Icon");
    }

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

    @objid ("23ec0cb8-c800-4b15-8b29-3f45aaf4038f")
    @Override
    public boolean accept(MObject owner) {
        if (!super.accept(owner)) {
            return false;
        }
        
        DependencyCreator pc = new DependencyCreator(this.mmServices);
        
        if ((owner instanceof ModelElement)) {
            // Deactivate if no context is found
            if (pc.getAutoDiagramContext((ModelElement) owner) == null) {
                return false;
            }
        
            AbstractDiagram existingdiagramauto = pc.getExistingAutoDiagram((ModelElement) owner);
        
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

}

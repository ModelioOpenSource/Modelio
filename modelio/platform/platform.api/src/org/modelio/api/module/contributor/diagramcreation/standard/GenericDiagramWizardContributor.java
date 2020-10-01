/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.module.contributor.diagramcreation.standard;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Generic diagram wizard contribution class.
 * <p>
 * Uses the following contribution parameters:
 * <ul>
 * <li>metaclass
 * <li>stereotype
 * <li>name
 * <li>style
 * </ul>
 * 
 * @since 3.4
 */
@objid ("da1043d3-d7e1-47ea-9329-be797a702b59")
public class GenericDiagramWizardContributor extends AbstractDiagramWizardContributor {
    @objid ("23d9d4aa-326e-4393-8468-0aa99e41446b")
    @Override
    public AbstractDiagram actionPerformed(ModelElement diagramContext, String diagramName, String diagramDescription) {
        String mc = getParameters().get("metaclass");
        String st = getParameters().get("stereotype");
        
        if (mc != null) {
            MClass metaclass = diagramContext.getMClass().getMetamodel().getMClass(mc);
        
            AbstractDiagram diagram = (AbstractDiagram) getModule().getModuleContext().getModelingSession().getModel().createElement(mc, diagramContext, "Product");
            if (st != null) {
                Stereotype stereotype = findStereotypeFromSpec(metaclass, st);
                if (stereotype != null) {
                    diagram.getExtension().add(stereotype);
                }
            }
        
            String name = getParameters().get("name");
            if (name == null) {
                name = diagramName;
            }
            name = getModule().getModuleContext().getI18nSupport().getString(name);
            diagram.setName(name);
        
        
        
            try {
                diagram.putNoteContent("ModelerModule", ModelElement.MQNAME, "description", diagramDescription);
            } catch (@SuppressWarnings("unused") ExtensionNotFoundException e) {
                getModule().getModuleContext().getLogService().error("Unable to find \"description\" note type, please deploy ModelerModule.");
            }
        
        
        
            // Set new diagram style
            final IDiagramService diagramService = getModule().getModuleContext().getModelioServices().getDiagramService();
            final IStyleHandle styleHandle = diagramService.getStyle(getParameters().get("style"));
            if (styleHandle != null) {
                try (IDiagramHandle handle = diagramService.getDiagramHandle(diagram)) {
                    handle.getDiagramNode().setStyle(styleHandle);
                    handle.save();
                }
            }
            return diagram;
        }
        return null;
    }

    @objid ("99233992-a138-4be0-ad0f-03466bed7ad0")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, getParameters().get("metaclass"));
    }

    @objid ("49433a9a-0076-4fb1-bc0b-c2073eace4eb")
    @Override
    public ElementDescriptor getCreatedElementType() {
        String mc = getParameters().get("metaclass");
        String st = getParameters().get("stereotype");
        
        if (mc != null) {
            MClass metaclass = getModule().getModuleContext().getModel().getMClass().getMetamodel().getMClass(mc);
            Stereotype stereotype = findStereotypeFromSpec(metaclass, st);
        
            return new ElementDescriptor(metaclass, stereotype);
        }
        return null;
    }

    @objid ("ee0ed474-d68a-4e2a-8a7f-7246aab01b94")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}

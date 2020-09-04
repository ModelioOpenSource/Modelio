/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.api.module.command.standard;

import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Generic diagram creation command handler.
 * <p>
 * Used parameters:
 * <ul>
 * <li>style : the diagram style name
 * <li>open : "true" to open the created diagram.
 * </ul>
 */
@objid ("e4f33d6b-b3fb-4e6b-af5b-f177635fef05")
public class DiagramCreationStandardHandler extends ElementCreationStandardHandler {
    @objid ("5e9147b9-8cc6-4c14-9871-25dd01784948")
    private String style;

    @objid ("3f0f561f-5570-452c-acd0-7f9545c477a4")
    private boolean open;

    /**
     * Command parameter indicating wheter or not the created diagram is to be opened in an editor.
     */
    @objid ("b3903c99-c912-440b-aaa4-b19e0e4528b7")
    public static final String IS_TO_OPEN = "open";

    /**
     * Command parameter containing created diagram style name.
     */
    @objid ("cbb6e0af-2f08-4ae8-8541-eb7202a7ae57")
    public static final String STYLE_NAME = "style";

    /**
     * Default constructor.
     */
    @objid ("2371011f-6d06-43e8-9e9f-bb79693d176f")
    public DiagramCreationStandardHandler() {
        super();
    }

    /**
     * Applies the diagram style and open the diagram if asked for.
     * <p>
     * Deleting "newElement" in this method triggers an automatic rollback for the current transaction.
     * </p>
     */
    @objid ("e49b47e4-9019-4b82-859c-bc3e448f7c6b")
    @Override
    protected void postConfigureElement(MObject newElement, IModule module) {
        super.postConfigureElement(newElement, module);
        
        if (newElement instanceof AbstractDiagram) {
            final AbstractDiagram diagram = (AbstractDiagram) newElement;
        
            // Set new diagram style
            IModelioServices modelioServices = module.getModuleContext().getModelioServices();
            final IDiagramService diagramService = modelioServices.getDiagramService();
            final IStyleHandle styleHandle = diagramService.getStyle(getStyle());
            if (styleHandle != null) {
                try (IDiagramHandle handle = diagramService.getDiagramHandle(diagram)) {
                    handle.getDiagramNode().setStyle(styleHandle);
                    handle.save();
                }
            }
        
            // Open diagram
            if (isToOpen()) {
                modelioServices.getEditionService().openEditor(diagram);
            }
        }
    }

    @objid ("8f33aae2-7678-4482-8528-5c74cf6519a1")
    @Override
    public void initialize(List<ElementScope> scopes, Map<String, String> hParameters) {
        super.initialize(scopes, hParameters);
        
        for (Entry<String, String> param : hParameters.entrySet()) {
            switch (param.getKey()) {
            case STYLE_NAME:
                this.style = param.getValue();
                break;
            case IS_TO_OPEN:
                this.open = "true".equals(param.getValue());
                break;
            default:
                continue;
            }
        }
    }

    /**
     * @return the diagram style name.
     */
    @objid ("641f420f-8992-4830-b70d-37b66d0b5677")
    protected String getStyle() {
        return this.style;
    }

    /**
     * @return true if the diagram is to be opened in an editor.
     */
    @objid ("4a99502c-4ddb-41d3-808a-1fd3ac72511e")
    protected boolean isToOpen() {
        return this.open;
    }

}

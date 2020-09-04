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

package org.modelio.api.modelio.diagram.tools.standard;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Generic diagram node creation tool.
 */
@objid ("cdadbe69-1cce-45c0-a5a5-5c1cc6eac7da")
public class GenericBoxTool extends DefaultBoxTool {
    @objid ("754e51be-a602-4c85-829e-f1c8d34bef15")
    @Override
    public boolean acceptElement(final IDiagramHandle diagramHandle, final IDiagramGraphic targetNode) {
        ModelElement owner = null;
        
        if (targetNode instanceof IDiagramDG) {
            owner = diagramHandle.getDiagram().getOrigin();
        } else {
            owner = (ModelElement) targetNode.getElement();
        }
        
        for (ElementScope aScope : getSourceScopes()) {
            if (aScope.isMatching(owner)) {
                return true;
            }
        }
        return false;
    }

    @objid ("db3e214d-b93f-4cca-b75d-4a5ad6944889")
    @Override
    public void actionPerformed(final IDiagramHandle diagramHandle, final IDiagramGraphic graphic, final Rectangle rect) {
        IModelingSession session = getModule().getModuleContext().getModelingSession();
        
        try (ITransaction tr = session.createTransaction("Create box")) {
            ModelElement parent = null;
        
            if (graphic instanceof IDiagramDG) {
                parent = diagramHandle.getDiagram().getOrigin();
            } else {
                parent = (ModelElement) graphic.getElement();
            }
        
            String metaclass = getParameter("metaclass");
            if (metaclass != null) {
                IUmlModel modelFactory = session.getModel();
                MObject newElement = modelFactory.createElement(metaclass);
        
                // Get dependency by name.
                MDependency dependency = parent.getMClass().getDependency(getParameter("relation"));
                if (dependency == null) {
                    dependency = parent.getMClass().getMetamodel().getMExpert().getDefaultCompositionDep(parent,
                            newElement);
                }
        
                if (dependency != null) {
                    // Append new instance of said dependency
                    parent.mGet(dependency).add(newElement);
        
                    if (newElement instanceof ModelElement) {
                        String relationParam = getParameters().get("relation");
                        if (relationParam != null) {
                            ((ModelElement) newElement).getExtension().add(session.getMetamodelExtensions()
                                    .getStereotype(relationParam, newElement.getMClass()));
                        }
        
                        String stereotype = getParameter("stereotype");
                        Stereotype ster = findStereotypeFromSpec(newElement.getMClass(), stereotype);
                        if (ster != null) {
                            ((ModelElement) newElement).getExtension().add(ster);
                        }
        
                        String name = getParameter("name");
                        if (name == null) {
                            name = getLabel();
                        }
                        name = getModule().getModuleContext().getI18nSupport().getString(name);
                        modelFactory.getDefaultNameService().setDefaultName((ModelElement) newElement, name);
                    }
        
                    List<IDiagramGraphic> graph = diagramHandle.unmask(newElement, rect.x, rect.y);
                    if (graph.size() > 0) {
                        ((IDiagramNode) graph.get(0)).setBounds(rect);
                    }
        
                    postConfigure(diagramHandle, graphic, rect, parent, newElement, graph);
        
                    diagramHandle.save();
                } else {
                    getModule().getModuleContext().getLogService()
                            .error("Metamodel relation on '" + getParameter("metaclass)") + "' not found.");
                    newElement.delete();
                }
        
            }
        
            tr.commit();
        } catch (RuntimeException e) {
            getModule().getModuleContext().getLogService().error(e);
        }
    }

    /**
     * Hook called once the element is created, configured, unmasked and before the transaction is committed.
     * <p>
     * Does nothing by default. Sub classes may redefine this method to make additional modifications.
     * 
     * @param diagramHandle the diagram handle
     * @param parentGraphic the graphic under which the element was unmasked
     * @param rect the new graphic bounds.
     * @param parentElement the model element owning the new element.
     * @param newElement the created model element.
     * @param newGraphics the graphics relating the new element that were unmasked.
     */
    @objid ("2afe021d-2661-4c27-8035-12c2c06c5c43")
    protected void postConfigure(IDiagramHandle diagramHandle, IDiagramGraphic parentGraphic, Rectangle rect, ModelElement parentElement, MObject newElement, List<IDiagramGraphic> newGraphics) {
        // nothing by default
    }

}

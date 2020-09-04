/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.tools.DefaultAttachedBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Generic tool to create node attached to another node (Notes).
 * <p>
 * This class provides hook methods to allow sub classes to configure created elements.
 * <p>
 * {@link #postConfigure(IDiagramHandle, IDiagramGraphic, LinkRouterKind, ILinkPath, Point, MObject, MObject, List)} is called by {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, LinkRouterKind, ILinkPath, Point)} .
 * <p>
 * {@link #postInDiagramConfigure(IDiagramHandle, Rectangle, ModelElement, MObject, List)} is called by {@link #actionPerformedInDiagram(IDiagramHandle, Rectangle)}
 */
@objid ("df300bf8-87a3-49f5-ab7b-ef9dfdac0fee")
public class GenericAttachedBoxTool extends DefaultAttachedBoxTool {
    @objid ("0ad5b665-d59e-4458-b45e-afeff602a3f3")
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

    @objid ("371030bb-e9e4-43e9-9429-252ce2dfa843")
    @Override
    public void actionPerformed(final IDiagramHandle diagramHandle, final IDiagramGraphic graphic, final LinkRouterKind routerType, final ILinkPath path, final Point point) {
        IModelingSession session = getModule().getModuleContext().getModelingSession();
        try (ITransaction tr = session.createTransaction("GenericBoxCommand")) {
        
            ModelElement parentElement = null;
        
            if (graphic instanceof IDiagramDG) {
                parentElement = diagramHandle.getDiagram().getOrigin();
            } else {
                parentElement = (ModelElement) graphic.getElement();
            }
        
            String metaclass = getParameter("metaclass");
            if (metaclass != null) {
                IUmlModel modelFactory = session.getModel();
        
                MObject newElement = modelFactory.createElement(metaclass);
        
                MDependency dependency = parentElement.getMClass().getDependency(getParameter("relation"));
        
                if (dependency == null) {
                    dependency = parentElement.getMClass().getMetamodel().getMExpert()
                            .getDefaultCompositionDep(parentElement, newElement);
                }
        
                if (dependency != null) {
                    // Append new instance of said dependency
                    parentElement.mGet(dependency).add(newElement);
                }
        
                if (newElement instanceof ModelElement) {
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
        
                List<IDiagramGraphic> newGraphics = diagramHandle.unmask(newElement, point.x, point.y);
        
                postConfigure(diagramHandle, graphic, routerType, path, point, parentElement, newElement, newGraphics);
        
                diagramHandle.save();
            }
        
            tr.commit();
        } catch (RuntimeException e) {
            getModule().getModuleContext().getLogService().error(e);
        }
    }

    @objid ("20dcf7c2-62f1-49f0-a814-fe16ed422d73")
    @Override
    public void actionPerformedInDiagram(final IDiagramHandle diagramHandle, final Rectangle rect) {
        IModelingSession session = getModule().getModuleContext().getModelingSession();
        String metaclass = getParameter("metaclass");
        if (metaclass != null) {
            try (ITransaction tr = session.createTransaction(getLabel())) {
                ModelElement parentElement = diagramHandle.getDiagram().getOrigin();
        
                IUmlModel modelFactory = session.getModel();
                MObject newElement = modelFactory.createElement(metaclass, parentElement, getParameter("relation"));
        
                if (newElement instanceof ModelElement) {
                    Stereotype ster = findStereotypeFromSpec(newElement.getMClass(), getParameter("stereotype"));
                    if (ster != null) {
                        ((ModelElement) newElement).getExtension().add(ster);
                    }
                    ((ModelElement) newElement).setName(getLabel());
                }
        
                List<IDiagramGraphic> newGraphics = diagramHandle.unmask(newElement, rect.x, rect.y);
                if (newGraphics.size() > 0) {
                    ((IDiagramNode) newGraphics.get(0)).setBounds(rect);
                }
        
                postInDiagramConfigure(diagramHandle, rect, parentElement, newElement, newGraphics);
        
                diagramHandle.save();
                tr.commit();
            } catch (RuntimeException e) {
                getModule().getModuleContext().getLogService().error(e);
            }
        }
    }

    /**
     * Hook called by {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, LinkRouterKind, ILinkPath, Point)} once the element is created, configured, unmasked and before the transaction is committed.
     * <p>
     * Does nothing by default. Sub classes may redefine this method to make additional modifications.
     * @param diagramHandle the diagram handle
     * @param parentGraphic the graphic under which the element was unmasked
     * @param routerType the router type that is currently defined to compute the path of the link.
     * @param path the link path deduced from the user interactions.
     * @param point the new graphic position.
     * @param parentElement the model element owning the new element.
     * @param newElement the created model element.
     * @param newGraphics the graphics relating the new element that were unmasked.
     */
    @objid ("91f81e87-f318-4a93-9d49-e8a702af00a3")
    protected void postConfigure(final IDiagramHandle diagramHandle, final IDiagramGraphic parentGraphic, final LinkRouterKind routerType, final ILinkPath path, final Point point, MObject parentElement, MObject newElement, List<IDiagramGraphic> newGraphics) {
        // nothing by default
    }

    /**
     * Hook called by {@link #actionPerformedInDiagram(IDiagramHandle, Rectangle)} once the element is created, configured, unmasked and before the transaction is committed.
     * <p>
     * Does nothing by default. Sub classes may redefine this method to make additional modifications.
     * @param diagramHandle the diagram handle
     * @param rect the new graphic bounds.
     * @param parentElement the model element owning the new element.
     * @param newElement the created model element.
     * @param newGraphics the graphics relating the new element that were unmasked.
     */
    @objid ("0ebcaefe-0f85-48c0-9dbc-0ea33f6f977e")
    protected void postInDiagramConfigure(IDiagramHandle diagramHandle, Rectangle rect, ModelElement parentElement, MObject newElement, List<IDiagramGraphic> newGraphics) {
        // nothing by default
    }

}

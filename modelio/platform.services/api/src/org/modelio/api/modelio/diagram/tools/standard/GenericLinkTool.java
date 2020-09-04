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

package org.modelio.api.modelio.diagram.tools.standard;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.tools.DefaultLinkTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Generic diagram link creation tool.
 */
@objid ("189d9f4d-c0da-4680-b7ee-06b3e9e2ba4c")
public class GenericLinkTool extends DefaultLinkTool {
    @objid ("3389a04b-a143-4ee0-bcf4-cfc1b2302a83")
    @Override
    public boolean acceptFirstElement(final IDiagramHandle diagramHandle, final IDiagramGraphic targetNode) {
        ModelElement owner = null;
        
        if (targetNode instanceof IDiagramDG) {
            owner = diagramHandle.getDiagram().getOrigin();
        } else {
            owner = (ModelElement) targetNode.getElement();
        }
        
        for (final ElementScope aScope : getSourceScopes()) {
            if (aScope.isMatching(owner)) {
                return true;
            }
        }
        return false;
    }

    @objid ("b3d48044-6bfb-4622-9d3d-e8a23577e824")
    @Override
    public boolean acceptSecondElement(final IDiagramHandle diagramHandle, final IDiagramGraphic originNode, final IDiagramGraphic targetNode) {
        ModelElement target = null;
        
        if (targetNode instanceof IDiagramDG) {
            target = diagramHandle.getDiagram().getOrigin();
        } else {
            target = (ModelElement) targetNode.getElement();
        }
        
        for (final ElementScope aScope : getTargetScopes()) {
            if (aScope.isMatching(target)) {
                return true;
            }
        }
        return false;
    }

    @objid ("aafda059-0c7c-47e2-994c-b5bee70564fd")
    @Override
    public void actionPerformed(final IDiagramHandle diagramHandle, final IDiagramGraphic originNode, final IDiagramGraphic targetNode, final LinkRouterKind routerType, final ILinkPath path) {
        final IModelingSession session = getModule().getModuleContext().getModelingSession();
        try (ITransaction tr = session.createTransaction("Create link")) {
            final ModelElement source = (ModelElement) originNode.getElement();
            final ModelElement target = (ModelElement) targetNode.getElement();
        
            final String mc = getParameter("metaclass");
        
            if (mc != null) {
                MObject newElement;
                switch (mc) {
                case Association.MQNAME:
                case Association.MNAME:
                    newElement = doCreateAssociation(session, source, target);
                    break;
                case AssociationEnd.MQNAME:
                case AssociationEnd.MNAME:
                    newElement = doCreateAssociationEnd(session, source, target);
                    break;
                case Connector.MQNAME:
                case Connector.MNAME:
                    newElement = doCreateConnector(session, source, target);
                    break;
                case ConnectorEnd.MQNAME:
                case ConnectorEnd.MNAME:
                    newElement = doCreateConnectorEnd(session, source, target);
                    break;
                case Link.MQNAME:
                case Link.MNAME:
                    newElement = doCreateLink(session, source, target);
                    break;
                case LinkEnd.MQNAME:
                case LinkEnd.MNAME:
                    newElement = doCreateLinkEnd(session, source, target);
                    break;
                case InformationFlow.MQNAME:
                case InformationFlow.MNAME:
                    newElement = doCreateInformationFlow(session, source, target);
                    break;
                default:
                    newElement = doCreateGenericLink(session, source, target, mc);
                }
        
                final List<IDiagramGraphic> graph = diagramHandle.unmask(newElement, 0, 0);
                final IDiagramLink link = (IDiagramLink) graph.get(0);
                link.setRouterKind(routerType);
                link.setFrom(originNode);
                link.setTo(targetNode);
                link.setPath(path);
        
                postConfigure(diagramHandle, originNode, targetNode, source, target, newElement, link, graph, routerType, path);
        
                diagramHandle.save();
            }
            tr.commit();
        } catch (final Exception e) {
            getModule().getModuleContext().getLogService().error(e);
        }
    }

    @objid ("85e08e4f-a903-401e-99c8-36d33a648902")
    private MObject doCreateAssociation(final IModelingSession session, final ModelElement source, final ModelElement target) {
        final IUmlModel modelFactory = session.getModel();
        
        Association newElement = modelFactory.createAssociation((Classifier) source, (Classifier) target, getLabel());
        
        initStereotype(newElement);
        
        initName(modelFactory, newElement);
        return newElement;
    }

    @objid ("a6a4a2ea-e507-417c-9737-aa13e0e72d0e")
    private MObject doCreateAssociationEnd(final IModelingSession session, final ModelElement source, final ModelElement target) {
        final IUmlModel modelFactory = session.getModel();
        
        Association newElement = modelFactory.createAssociation((Classifier) source, (Classifier) target, getLabel());
        
        for (AssociationEnd end : newElement.getEnd()) {
            if (source.equals(end.getOwner())) {
                initStereotype(end);
        
                initName(modelFactory, end);
        
                // Make sure 'source' is the first end
                newElement.getEnd().remove(end);
                newElement.getEnd().add(0, end);
                break;
            }
        }
        return newElement;
    }

    @objid ("a1e7a13e-35a5-4576-9e4e-5730ab1a3e46")
    private MObject doCreateConnector(final IModelingSession session, final ModelElement source, final ModelElement target) {
        final IUmlModel modelFactory = session.getModel();
        
        Connector newElement = modelFactory.createConnector((BindableInstance) source, (BindableInstance) target,
                getLabel());
        
        initStereotype(newElement);
        
        initName(modelFactory, newElement);
        return newElement;
    }

    @objid ("c9f7b64c-7b8b-41ad-a338-6d746c4c2f7d")
    private MObject doCreateConnectorEnd(final IModelingSession session, final ModelElement source, final ModelElement target) {
        final IUmlModel modelFactory = session.getModel();
        
        Connector newElement = modelFactory.createConnector((BindableInstance) source, (BindableInstance) target,
                getLabel());
        
        for (ConnectorEnd end : newElement.getLinkEnd(ConnectorEnd.class)) {
            if (source.equals(end.getOwner())) {
                initStereotype(end);
        
                initName(modelFactory, end);
        
                // Make sure 'source' is the first end
                newElement.getLinkEnd().remove(end);
                newElement.getLinkEnd().add(0, end);
                break;
            }
        }
        return newElement;
    }

    @objid ("df5ef08b-e6a9-457a-bae6-bfeaab23a354")
    private MObject doCreateGenericLink(final IModelingSession session, final ModelElement source, final ModelElement target, final String mc) {
        final IUmlModel modelFactory = session.getModel();
        
        MObject newElement = modelFactory.createElement(mc);
        final MExpert e = newElement.getMClass().getMetamodel().getMExpert();
        e.setSource(newElement, null, source);
        e.setTarget(newElement, null, target);
        
        if (newElement instanceof ModelElement) {
            initStereotype(newElement);
        
            initName(modelFactory, (ModelElement) newElement);
        }
        return newElement;
    }

    @objid ("f35a5987-14ec-4adc-842e-0b99dd299f7b")
    private Link doCreateLink(final IModelingSession session, final ModelElement source, final ModelElement target) {
        final IUmlModel modelFactory = session.getModel();
        
        Link newElement = modelFactory.createLink((BindableInstance) source, (BindableInstance) target, getLabel());
        
        initStereotype(newElement);
        
        initName(modelFactory, newElement);
        return newElement;
    }

    @objid ("1a03f7bd-cf04-448c-b870-9c1358e5ba35")
    private MObject doCreateLinkEnd(final IModelingSession session, final ModelElement source, final ModelElement target) {
        final IUmlModel modelFactory = session.getModel();
        
        Link newElement = modelFactory.createLink((BindableInstance) source, (BindableInstance) target, getLabel());
        
        for (LinkEnd end : newElement.getLinkEnd()) {
            if (source.equals(end.getOwner())) {
                initStereotype(end);
        
                initName(modelFactory, end);
        
                // Make sure 'source' is the first end
                newElement.getLinkEnd().remove(end);
                newElement.getLinkEnd().add(0, end);
                break;
            }
        }
        return newElement;
    }

    @objid ("68cf5125-2752-4da1-969e-a089c2db5941")
    private void initName(final IUmlModel modelFactory, ModelElement newElement) {
        String name = getParameter("name");
        if (name == null) {
            name = getLabel();
        }
        name = getModule().getModuleContext().getI18nSupport().getString(name);
        modelFactory.getDefaultNameService().setDefaultName(newElement, name);
    }

    @objid ("181a5202-313d-40c3-b921-b5e860025b63")
    private void initStereotype(MObject newElement) {
        Stereotype stereotype = findStereotypeFromSpec(newElement.getMClass(), getParameter("stereotype"));
        if (stereotype != null) {
            ((ModelElement) newElement).getExtension().add(stereotype);
        }
    }

    /**
     * Hook called once the element is created, configured, unmasked and before the transaction is committed.
     * <p>
     * Does nothing by default. Sub classes may redefine this method to make additional modifications.
     * 
     * @param diagramHandle the diagram handle
     * @param originNode the source graphic node
     * @param targetNode the target graphic node
     * @param source the source model element
     * @param target the target model element
     * @param newElement the created link element
     * @param newLink the unmasked link graphic
     * @param newGraphics the graphics relating the new element that were unmasked. The first in this list is 'newLink'.
     * @param routerType the router type that is currently defined to compute the path of the link.
     * @param path the link path deduced from the user interactions.
     */
    @objid ("a80525b4-bab1-4176-8a5e-293ef3d4a214")
    protected void postConfigure(IDiagramHandle diagramHandle, IDiagramGraphic originNode, IDiagramGraphic targetNode, ModelElement source, ModelElement target, MObject newElement, IDiagramLink newLink, List<IDiagramGraphic> newGraphics, final LinkRouterKind routerType, final ILinkPath path) {
        // nothing by default
    }

    @objid ("992f082a-afa8-42b7-8396-d346bf025ed5")
    private InformationFlow doCreateInformationFlow(final IModelingSession session, final ModelElement source, final ModelElement target) {
        final IUmlModel modelFactory = session.getModel();
        
        InformationFlow newElement = modelFactory.createInformationFlow();
        newElement.getInformationSource().add((UmlModelElement) source);
        newElement.getInformationTarget().add((UmlModelElement) target);
        
        MObject owner = source;
        while ((owner != null) && !(owner instanceof NameSpace)) {
            owner = owner.getCompositionOwner();
        }
        
        if (owner != null) {
            newElement.setOwner((NameSpace) owner);
        }
        
        initStereotype(newElement);
        
        initName(modelFactory, newElement);
        return newElement;
    }

}

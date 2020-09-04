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

package org.modelio.diagram.diagramauto.diagram.creator;

import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.autodiagram.IDiagramCreator;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("7b0608cc-5339-4a12-91aa-df36556c07a9")
public abstract class AbstractDiagramCreator implements IDiagramCreator {
    @objid ("82e36ced-0fe5-4c0e-ba2e-cc78480e886a")
    protected IMModelServices modelServices;

    @objid ("a068b3d7-d0c3-4804-ad04-d37951c95983")
    public AbstractDiagramCreator(IMModelServices modelServices) {
        this.modelServices = modelServices;
    }

    @objid ("d2278822-5961-4832-b4ba-3b7572246fb8")
    @Override
    public final AbstractDiagram createDiagram(final ModelElement main) {
        // get the auto diagram
        AbstractDiagram diagram = getExistingAutoDiagram(main);
        try {
            if (diagram == null) {
                diagram = createAutoDiagram(main);
            }
        
            if (diagram == null) {
                return null;
            }
        
            // update diagram kind
            String kind = diagram.getProperty("AutoDiagram", "Kind");
            if (!getAutoDiagramName().equals(kind)) {
                diagram.setProperty("AutoDiagram", "Kind", getAutoDiagramName());
            }
        
            // get the diagram handle to work with
            try (IDiagramHandle dh = Modelio.getInstance().getDiagramService().getDiagramHandle(diagram)) {
                dh.setBatchMode(true);
        
                // perform the inital unmasking (mainly nodes)
                initialUnmasking(dh, main);
        
                // layout the diagram
                layout(dh);
        
                // open the resulting diagram
                dh.save();
                dh.setBatchMode(false);
                return diagram;
            }
        } catch (Exception e) {
            DiagramAuto.LOG.debug(e);
            if (diagram != null) {
                diagram.delete();
            }
            return null;
        }
    }

    @objid ("bbbe495b-7957-486c-94ec-0fe2a0406fcc")
    @Override
    public final AbstractDiagram getExistingAutoDiagram(final ModelElement main) {
        ModelElement context = getAutoDiagramContext(main);
        if (context != null) {
            for (AbstractDiagram diagram : context.getProduct()) {
                if (diagram.isStereotyped("ModelerModule", "AutoDiagram")) {
                    // Match by type property
                    String kind = diagram.getProperty("AutoDiagram", "Kind");
                    if (getAutoDiagramName().equals(kind)) {
                        return diagram;
                    }
        
                    // Match by name
                    if (diagram.getName().equals(main.getName() + " (" + getAutoDiagramName() + ")")) {
                        return diagram;
                    }
                }
            }
        }
        return null;
    }

    @objid ("8128a926-bcfa-4a9b-889e-d25217dc22c8")
    private AbstractDiagram createAutoDiagram(final ModelElement main) {
        IModelFactoryService modelFactory = this.modelServices.getModelFactory();
        IStandardModelFactory standardFactory = modelFactory.getFactory(IStandardModelFactory.class);
        IInfrastructureModelFactory infrastructureFactory = modelFactory.getFactory(IInfrastructureModelFactory.class);
        
        ModelElement context = getAutoDiagramContext(main);
        if (context == null) {
            return null;
        }
        
        //
        AbstractDiagram diagram = createDiagramElement(standardFactory);
        Stereotype stereotype = this.modelServices.findStereotypes("ModelerModule", "AutoDiagram", ClassDiagram.MQNAME).get(0);
        diagram.setName(main.getName() + " (" + getAutoDiagramName() + ")");
        diagram.getExtension().add(stereotype);
        context.getProduct().add(diagram);
        
        DiagramSet autogroup = getAutomaticDiagramSet(infrastructureFactory, context);
        if (autogroup != null) {
            autogroup.getReferencedDiagram().add(diagram);
        }
        return diagram;
    }

    @objid ("d2fe6e08-6c0a-49ef-93b0-3fa813f5585e")
    private DiagramSet getDiagramSet(ModelElement context) {
        if (context == null) {
            return null;
        }
        List<MObject> roots = GProject.getProject(context).getFragment(context).getRoots().stream().filter(root -> root instanceof Project).collect(Collectors.toList());
        for (MObject root : roots) {
            Project project = (Project) root;
            DiagramSet diagramRoot = project.getDiagramRoot();
            if (diagramRoot != null) {
                return diagramRoot;
            }
        }
        
        // No diagram set found, create one
        for (MObject root : roots) {
            Project project = (Project) root;
            DiagramSet ds = MTools.get(root).getModelFactory(IStandardModelFactory.class).createDiagramSet();
            ds.setName(project.getName());
            project.setDiagramRoot(ds);
            return ds;
        }
        return null;
    }

    @objid ("af1ac11c-3710-4f9a-900d-aca9dd06863a")
    protected abstract void initialUnmasking(final IDiagramHandle dh, final ModelElement main);

    @objid ("86b6e380-ffa6-43f4-9d2a-e38a6b33177c")
    protected abstract void layout(final IDiagramHandle dh);

    @objid ("24098d2f-2828-4ac5-9be8-6b1244f2d53a")
    protected DiagramSet getAutomaticDiagramSet(IInfrastructureModelFactory infrastructureFactory, ModelElement context) {
        DiagramSet rootSet = getDiagramSet(context);
        if (rootSet == null) {
            // No root set found, return null...
            return null;
        }
        // get global auto diagram root
        DiagramSet autoSet = null;
        for (DiagramSet dset : rootSet.getSub()) {
            if (dset.getName().equals("Auto Diagrams")) {
                autoSet = dset;
                break;
            }
        }
        
        if (autoSet == null) {
            autoSet = infrastructureFactory.createDiagramSet("Auto Diagrams", rootSet);
        }
        
        // get root for the current diagram
        for (DiagramSet dset : autoSet.getSub()) {
            if (dset.getName().equals(getAutoDiagramGroup())) {
                return dset;
            }
        }
        
        DiagramSet autogroup = infrastructureFactory.createDiagramSet(getAutoDiagramGroup(), autoSet);
        return autogroup;
    }

    @objid ("6a8cce36-ce9e-46f5-ae6c-94c7c8778eec")
    protected abstract AbstractDiagram createDiagramElement(IStandardModelFactory standardFactory);

}

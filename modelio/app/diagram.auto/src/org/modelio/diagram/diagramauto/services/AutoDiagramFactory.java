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

package org.modelio.diagram.diagramauto.services;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.autodiagram.IAutoDiagramFactory;
import org.modelio.api.modelio.diagram.autodiagram.IDiagramCreator;
import org.modelio.diagram.diagramauto.diagram.creator.ClassArchitectureCreator;
import org.modelio.diagram.diagramauto.diagram.creator.ClassStructureCreator;
import org.modelio.diagram.diagramauto.diagram.creator.CompositionNavigationCreator;
import org.modelio.diagram.diagramauto.diagram.creator.DependencyCreator;
import org.modelio.diagram.diagramauto.diagram.creator.InheritanceCreator;
import org.modelio.diagram.diagramauto.diagram.creator.PackageContentStructureCreator;
import org.modelio.diagram.diagramauto.diagram.creator.SubPackageStructureCreator;
import org.modelio.diagram.diagramauto.diagram.creator.UseCaseFocusCreator;
import org.modelio.diagram.diagramauto.plugin.DiagramAuto;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;

@objid ("2625bf4b-74e0-4394-889f-7e05d8dbc73c")
public class AutoDiagramFactory implements IAutoDiagramFactory {
    @objid ("8cd07e77-70f6-41d5-a7c3-8fb4c4ab9d3a")
    private IMModelServices modelServices;

    @objid ("96a64f59-9c46-462b-9fa9-68e33d87a690")
    public AutoDiagramFactory(IMModelServices modelServices) {
        this.modelServices = modelServices;
    }

    @objid ("33d70042-435b-46cb-8a72-d4380d223685")
    @Override
    public IDiagramCreator createClassStructureCreator() {
        return createDiagramCreator("ClassStructureCreator");
    }

    @objid ("9ab57b06-1a43-45a2-900d-e107ecaafc09")
    @Override
    public IDiagramCreator createInheritanceCreator() {
        return createDiagramCreator("InheritanceCreator");
    }

    @objid ("983bd3a2-44c2-4f05-bb41-55c619259faf")
    @Override
    public IDiagramCreator createSubPackageStructureCreator() {
        return createDiagramCreator("SubPackageStructureCreator");
    }

    @objid ("cf108cb6-d420-49c6-897c-8444ee4f6cae")
    @Override
    public IDiagramCreator createPackageContentStructureCreator() {
        return createDiagramCreator("PackageContentStructureCreator");
    }

    @objid ("f33f6479-7001-4bfb-9ca4-337e8f587513")
    @Override
    public IDiagramCreator createDependencyCreator() {
        return createDiagramCreator("DependencyCreator");
    }

    @objid ("9b643b8f-134a-4838-b2cd-b5a68006efec")
    @Override
    public IDiagramCreator createClassArchitectureCreator() {
        return createDiagramCreator("ClassArchitectureCreator");
    }

    @objid ("75188581-9a06-43c0-8c8a-39b6874664be")
    @Override
    public IDiagramCreator createCompositionNavigationCreator() {
        return createDiagramCreator("CompositionNavigationCreator");
    }

    @objid ("3cc79be2-e5e9-4477-a7c6-28b3f4287903")
    @Override
    public IDiagramCreator createUseCaseFocusCreator() {
        return createDiagramCreator("UseCaseFocusCreator");
    }

    @objid ("44883520-1791-4cb6-94e7-4fca1de5b762")
    @Override
    public IDiagramCreator createDiagramCreator(String creatorId) {
        switch (creatorId) {
        case "ClassArchitectureCreator":
            return new DiagramCreatorProxy(new ClassArchitectureCreator(this.modelServices));
        case "ClassStructureCreator":
            return new DiagramCreatorProxy(new ClassStructureCreator(this.modelServices));
        case "CompositionNavigationCreator":
            return new DiagramCreatorProxy(new CompositionNavigationCreator(this.modelServices));
        case "DependencyCreator":
            return new DiagramCreatorProxy(new DependencyCreator(this.modelServices));
        case "InheritanceCreator":
            return new DiagramCreatorProxy(new InheritanceCreator(this.modelServices));
        case "PackageContentStructureCreator":
            return new DiagramCreatorProxy(new PackageContentStructureCreator(this.modelServices));
        case "SubPackageStructureCreator":
            return new DiagramCreatorProxy(new SubPackageStructureCreator(this.modelServices));
        case "UseCaseFocusCreator":
            return new DiagramCreatorProxy(new UseCaseFocusCreator(this.modelServices));
        default:
            DiagramAuto.LOG.error("AutoDiagram identifier not found: " + creatorId);
            return null;
        }
    }

}

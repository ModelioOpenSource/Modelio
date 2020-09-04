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

package org.modelio.diagram.diagramauto.services;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.autodiagram.IDiagramCreator;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("db558712-1857-4bd9-92aa-d9c83ec99c02")
public class DiagramCreatorProxy implements IDiagramCreator {
    @objid ("8855b70d-69af-41fc-89c5-6fb249573f77")
    private IDiagramCreator diagramCreator;

    @objid ("5e1e9cd8-b7ff-42fd-8ab0-6fd1f06cbf09")
    public DiagramCreatorProxy(final IDiagramCreator diagramCreator) {
        this.diagramCreator = diagramCreator;
    }

    @objid ("ca2ba5da-a10c-49d3-ad96-6051ec2db2b0")
    @Override
    public AbstractDiagram createDiagram(final ModelElement main) {
        return this.diagramCreator.createDiagram(main);
    }

    @objid ("5ea0d97a-50e1-4dab-9aed-52dfb0a81c34")
    @Override
    public AbstractDiagram getExistingAutoDiagram(final ModelElement main) {
        return this.diagramCreator.getExistingAutoDiagram(main);
    }

    @objid ("7b25a246-b2a2-4f0a-b5b8-9172ec2cb7d5")
    @Override
    public String getAutoDiagramName() {
        return this.diagramCreator.getAutoDiagramName();
    }

    @objid ("deca4fcf-ca1d-4caa-a470-b18cff108ec9")
    @Override
    public String getAutoDiagramGroup() {
        return this.diagramCreator.getAutoDiagramGroup();
    }

    @objid ("46f5cbf1-145e-40c3-8426-ef6e727552a0")
    @Override
    public ModelElement getAutoDiagramContext(ModelElement main) {
        return this.diagramCreator.getAutoDiagramContext(main);
    }

    @objid ("96d66f7d-1981-4d95-bfc0-82c20db5756d")
    @Override
    public ModelElement getMainElement(AbstractDiagram autoDiagram) {
        return this.diagramCreator.getMainElement(autoDiagram);
    }

}

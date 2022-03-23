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
package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IDiagramPersister;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.persistence.ExtReferenceResolver;
import org.modelio.diagram.elements.persistence.InstanceFactory;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.PersistenceException;
import org.modelio.diagram.persistence.XmlDiagramReader;
import org.modelio.diagram.persistence.XmlDiagramWriter;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Service class that load and save a {@link GmAbstractDiagram} from its {@link AbstractDiagram} saved data.
 * 
 * @author cmarin
 */
@objid ("7e143931-1dec-11e2-8cad-001ec947c8cc")
public class DiagramPersistence implements IDiagramPersister {
    @objid ("c0115129-dbd0-4e47-a7ab-fa75054ed82e")
    private GmAbstractDiagram gmDiagram;

    /**
     * C'tor.
     * @param gmDiagram the diagram to manage persistence for.
     */
    @objid ("c893559b-26fc-40c7-a044-f72a5f7c5662")
    public  DiagramPersistence(GmAbstractDiagram gmDiagram) {
        this.gmDiagram = gmDiagram;
    }

    /**
     * Load a {@link GmAbstractDiagram} from its {@link AbstractDiagram} saved data.
     * <p>
     * Resets the diagram before (re)loading it.
     * </p>
     * @throws PersistenceException on load failure
     */
    @objid ("613b9470-edbd-4d30-90ba-57415ce7b42c")
    @Override
    public void load() throws PersistenceException {
        final MRef diagramRef = this.gmDiagram.getRepresentedRef();
        final AbstractDiagram obDiagram = (AbstractDiagram) this.gmDiagram.getModelManager().resolveRef(diagramRef);
        
        if (obDiagram == null) {
            throw new PersistenceException("{" + diagramRef.uuid + "} " + diagramRef.mc
                    + " not found, it may have been deleted.");
        }
        
        final String data = obDiagram.getUiData();
        boolean hasPersistedData = data != null && !data.isEmpty();
        this.gmDiagram.reset(hasPersistedData);
        
        if (hasPersistedData) {
            final IDiagramReader reader = new XmlDiagramReader(new InstanceFactory(this.gmDiagram), new ExtReferenceResolver());
            reader.readDiagram(data, this.gmDiagram);
        }
        this.gmDiagram.updateLastSaveDate();
        this.gmDiagram.refreshAllFromObModel();
        this.gmDiagram.refreshFromObModel();
        
    }

    /**
     * Save the {@link GmAbstractDiagram} content in the {@link AbstractDiagram}.
     * <p>
     * A transaction must already be open.
     * @param withEmbeddeddiagrams whether or not embedded diagrams should also be saved.
     * @throws PersistenceException on save failure
     */
    @objid ("36e1ff90-cbe4-496a-8cc5-5f9a41b6d96f")
    public void save(boolean withEmbeddeddiagrams) throws PersistenceException {
        final IDiagramWriter writer = new XmlDiagramWriter();
        writer.save(this.gmDiagram);
        
        final AbstractDiagram obDiagram = (this.gmDiagram.getRelatedElement());
        if (!obDiagram.isShell() && !obDiagram.isDeleted()) {
            String newUidata = writer.getOutput();
            if (!Objects.equals(newUidata, obDiagram.getUiData())) {
                obDiagram.setUiData(writer.getOutput());
                obDiagram.setUiDataVersion(obDiagram.getUiDataVersion() + 1);
                updateDiagramElementRelation(obDiagram);
                this.gmDiagram.updateLastSaveDate();
            }
        }
        
        if (withEmbeddeddiagrams) {
            for (IGmDiagram embedded : this.gmDiagram.getEmbeddedDiagrams()) {
                MObject relatedElement = embedded.getRelatedElement();
                if (relatedElement != null && relatedElement.getStatus().isModifiable()) {
                    embedded.save(true);
                }
            }
        }
        
    }

    /**
     * Update the {@link AbstractDiagram#getRepresented()} relation from the diagram graphic model.
     * @param gmDiagram the graphic model
     * @param obDiagram the model element
     */
    @objid ("c091670c-4b2e-4a7a-978d-0ae199225bb9")
    private void updateDiagramElementRelation(AbstractDiagram obDiagram) {
        Collection<Element> dgRepresented = obDiagram.getRepresented();
        Collection<Element> newcontent = new HashSet<>();
        
        for (Entry<MRef, List<GmModel>> entry : this.gmDiagram.models.entrySet()) {
            List<GmModel> l = entry.getValue();
            boolean found = false;
            for (Iterator<GmModel> it = l.iterator(); it.hasNext() && !found;) {
                GmModel gmModel = it.next();
                MObject representedEl = gmModel.getRepresentedElement();
                if (representedEl instanceof Element && !representedEl.equals(obDiagram) && representedEl.isValid()) {
                    newcontent.add((Element) representedEl);
                    found = true;
                }
            }
        }
        
        // remove spare
        dgRepresented.retainAll(newcontent);
        
        // add missing
        for (Element el : newcontent) {
            if (!dgRepresented.contains(el)) {
                dgRepresented.add(el);
            }
        }
        
    }

}

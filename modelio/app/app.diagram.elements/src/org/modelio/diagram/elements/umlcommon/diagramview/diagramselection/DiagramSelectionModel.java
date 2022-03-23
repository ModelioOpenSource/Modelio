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
package org.modelio.diagram.elements.umlcommon.diagramview.diagramselection;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;

/**
 * Data model for the {@link DiagramSelectionPopup}.
 * <p>
 * Used to select an {@link AbstractDiagram} amongst a list...
 * </p>
 */
@objid ("f0b10818-681b-427f-a8d3-11e5769b156e")
public class DiagramSelectionModel {
    /**
     * A list of all the types that are possible to create.
     */
    @objid ("281c883f-7cee-4a65-ac43-d7e24e4b965b")
    private Collection<AbstractDiagram> selectableDiagrams;

    @objid ("9d950371-608f-4adc-9a37-2c59d60c7c89")
    private AbstractDiagram selectedDiagram;

    /**
     * C'tor initializing the diagram list.
     * @param selectableDiagrams all the diagrams that can be selected.
     */
    @objid ("ce6a6e04-ef70-4b23-89f5-4274019ab363")
    public  DiagramSelectionModel(final Collection<AbstractDiagram> selectableDiagrams) {
        this.selectableDiagrams = selectableDiagrams;
    }

    @objid ("70f6bab6-4e48-4096-a91d-4a9bc16663e8")
    Collection<AbstractDiagram> getAllowed() {
        return this.selectableDiagrams;
    }

    /**
     * @return the selected diagram. Might be <code>null</code>.
     */
    @objid ("77e0e04a-44e0-422b-9065-30cbfef85c64")
    public AbstractDiagram getSelected() {
        return this.selectedDiagram;
    }

    @objid ("916886f4-8e4b-4562-bd10-6e3547618701")
    void setSelected(AbstractDiagram selectedDiagram) {
        this.selectedDiagram = selectedDiagram;
    }

}

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
package org.modelio.diagram.elements.drawings.note;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.drawings.core.node.GmNodeDrawing;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Graphical model for a {@link NoteDrawing}.
 */
@objid ("37aba97a-0e47-462a-84ac-7094fa1b1594")
public class GmNoteDrawing extends GmNodeDrawing {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3121b69d-f9e6-48c7-914f-9970f26f6275")
    private static final int MINOR_VERSION = 0;

    @objid ("ba3284ad-1274-487a-b916-af3b3ecbab57")
    private static final int MAJOR_VERSION = 0;

    @objid ("fa472ee6-e295-4319-9782-284a1bc851fa")
    static final GmNoteDrawingStyleKeys KEYS = new GmNoteDrawingStyleKeys();

    /**
     * Constructor to use only for deserialization.
     */
    @objid ("e8502d00-c1b9-4d90-81c7-55a8c4f3d63a")
    public  GmNoteDrawing() {
        setLabel("Text");
    }

    @objid ("adbc5040-7715-47b1-b029-77d477a72301")
    @Override
    public void setLabel(String text) {
        if (! Objects.equals( super.getLabel(), text)) {
            String oldLabel = text;
            super.setLabel(text);
        
            firePropertyChange(PROPERTY_LABEL, oldLabel, text);
        }
        
    }

    /**
     * Creates a GmNoteDrawing.
     * @param note The represented note element
     * @param ref The represented note reference
     * @param diagram The diagram owning the node
     */
    @objid ("166d50fd-2f16-4c39-9713-141ed58aef7a")
    public  GmNoteDrawing(IGmDiagram diagram, String identifier) {
        super(diagram, identifier);
    }

    @objid ("095ffd02-1fa9-42eb-b160-b6206898671d")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmNoteDrawing.KEYS.getStyleKey(metakey);
    }

    @objid ("8dbe4b5d-b3a6-45d2-937b-a2bfd0b19eef")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmNoteDrawing.KEYS.getStyleKeys();
    }

}

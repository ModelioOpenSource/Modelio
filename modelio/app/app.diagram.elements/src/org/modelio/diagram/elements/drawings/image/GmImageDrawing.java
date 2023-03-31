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
package org.modelio.diagram.elements.drawings.image;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.drawings.core.node.GmNodeDrawing;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Rectangle not representing any model element.
 */
@objid ("7017451c-a063-4e7c-be26-944d01354ab9")
public class GmImageDrawing extends GmNodeDrawing {
    @objid ("71e9b5dd-7373-4108-9de0-ca3ae2d78729")
    static final GmImageDrawingStyleKeys KEYS = new GmImageDrawingStyleKeys();

    /**
     * Deserialization only constructor.
     */
    @objid ("65e9b6a9-e083-464d-a7a8-1aef4bf21368")
    public  GmImageDrawing() {
        super();
    }

    /**
     * Default constructor.
     * @param diagram the owner diagram.
     * @param identifier drawing identifier, must be unique in the diagram.
     */
    @objid ("5ce11658-13ee-4259-8db6-8c6bc4de5b1f")
    public  GmImageDrawing(IGmDiagram diagram, String identifier) {
        super(diagram, identifier);
    }

    @objid ("a05ee7f1-839c-4269-832e-48eff4d7fec3")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("8db96254-6f12-4b5b-a63f-a1b8bb7fd511")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

}

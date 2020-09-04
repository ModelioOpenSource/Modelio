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

package org.modelio.diagram.elements.drawings.rectangle;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.drawings.core.node.GmNodeDrawing;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Rectangle not representing any model element.
 */
@objid ("5125b1b4-5530-41af-9128-697599f167e3")
public class GmRectangleDrawing extends GmNodeDrawing {
    @objid ("c588bd1e-4d40-432b-bc64-4f1e05839617")
     static final GmRectangleStyleKeys KEYS = new GmRectangleStyleKeys();

    /**
     * Deserialization only constructor.
     */
    @objid ("d4121d2c-b734-4794-9a65-10ed558db20a")
    public GmRectangleDrawing() {
        super();
    }

    /**
     * Default constructor.
     * 
     * @param diagram the owner diagram.
     * @param identifier drawing identifier, must be unique in the diagram.
     */
    @objid ("38be2d33-6373-47e0-9d55-af69c20b547f")
    public GmRectangleDrawing(IGmDiagram diagram, String identifier) {
        super(diagram, identifier);
    }

    @objid ("64f2ae8f-99fc-4a95-b21e-0c2bb737892b")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("c08a9a41-4a9c-40b2-9eae-fbba349ff87b")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("637ebc4e-fb2e-4451-9031-5184a2853ecc")
    @Override
    protected String getObsoleteHyperLinkStyleKey() {
        return "RECTANGLE_HYPERLINK";
    }

}

/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.elements.drawings.text;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.drawings.core.node.GmNodeDrawing;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Rectangle not representing any model element.
 */
@objid ("255fe396-56e2-4308-9bc6-5bcbeb3b4f69")
public class GmTextDrawing extends GmNodeDrawing {
    @objid ("d766f0c8-0ff9-499b-946a-0f51b671913f")
    private static final int MINOR_VERSION = 0;

    @objid ("34ff56f8-2f12-43af-8aa7-ac3e0e84f84b")
     static final GmTextStyleKeys KEYS = new GmTextStyleKeys();

    /**
     * Deserialization only constructor.
     */
    @objid ("0f3ac5ec-fcf3-471b-b790-2cdda7ab8faa")
    public GmTextDrawing() {
        super();
    }

    /**
     * Default constructor.
     * @param diagram the owner diagram.
     * @param identifier drawing identifier, must be unique in the diagram.
     */
    @objid ("2731003f-730a-4629-bddf-be9eaf078346")
    public GmTextDrawing(IGmDiagram diagram, String identifier) {
        super(diagram, identifier);
        setLabel("Text");
    }

    @objid ("c5afbc22-49f4-4450-9daf-4d73c20472ba")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("fa2eeab1-f48c-4a97-b116-1aa6e36212db")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("c75a851f-56df-469e-98bd-d827d4a96fa6")
    @Override
    protected String getObsoleteHyperLinkStyleKey() {
        return "DRAWTEXT_HYPERLINK";
    }

}

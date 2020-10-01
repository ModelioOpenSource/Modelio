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

package org.modelio.bpmn.diagram.editor.elements.bpmndataobject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.bpmndataobject.dataobject.GmBpmnDataObject;
import org.modelio.bpmn.diagram.editor.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmBpmnDataObject}.
 */
@objid ("60d76028-55b6-11e2-877f-002564c97630")
public class GmBpmnDataObjectStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("7168db4b-55c1-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("DATA_REPMODE", MetaKey.REPMODE);

    @objid ("7168db4d-55c1-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("DATA_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("7168db4f-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("DATA_FILLMODE", MetaKey.FILLMODE);

    @objid ("7168db51-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("DATA_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("7168db53-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("DATA_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("7168db55-55c1-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("DATA_FONT", MetaKey.FONT);

    @objid ("7168db57-55c1-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("DATA_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("7168db59-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("DATA_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("7168db5b-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("DATA_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     * 
     * @see MetaKey#SHOWLABEL
     */
    @objid ("7168db5d-55c1-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = createStyleKey("DATA_SHOWLABEL", MetaKey.SHOWLABEL);

    @objid ("7168db60-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWREPRESENTED = createStyleKey("DATA_SHOWREPRESENTED", Boolean.class);

}

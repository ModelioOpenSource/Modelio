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

package org.modelio.diagram.editor.bpmn.elements.bpmnlane.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link GmBpmnLaneV0} when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("40841d1c-29d7-4e99-ab89-597d24735947")
public class GmBpmnLaneStructuredStyleKeysV0 extends BpmnAbstractStyleKeyProvider {
    @objid ("7545e6fa-094a-4e8d-b96f-92855bee01cb")
    public static final StyleKey FILLCOLOR = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("053dc925-4fa6-41c4-9a2f-51e97892eae0")
    public static final StyleKey FILLMODE = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_FILLMODE", MetaKey.FILLMODE);

    @objid ("ef78e3b0-531c-42f4-b127-e4d1a8e9c107")
    public static final StyleKey LINECOLOR = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("b1c55813-77f3-47c8-8116-c36ab348ba63")
    public static final StyleKey LINEWIDTH = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("05e6ee2d-8dbc-4d68-b03f-d9822354e97c")
    public static final StyleKey FONT = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_FONT", MetaKey.FONT);

    @objid ("05457510-0ed6-4495-be84-22293fc8fd07")
    public static final StyleKey TEXTCOLOR = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("c9effc2a-9407-4b26-9afd-31d23e6e1c43")
    public static final StyleKey SHOWSTEREOTYPES = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("e0218b65-0fda-45cd-b440-9464594e8845")
    public static final StyleKey SHOWTAGS = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNLANE_SHOWTAGS", MetaKey.SHOWTAGS);

}

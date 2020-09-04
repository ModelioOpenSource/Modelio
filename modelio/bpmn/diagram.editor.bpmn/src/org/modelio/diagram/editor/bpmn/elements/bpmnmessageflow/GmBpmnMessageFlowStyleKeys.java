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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmBpmnMessageFlow}.
 */
@objid ("616e7012-55b6-11e2-877f-002564c97630")
public class GmBpmnMessageFlowStyleKeys extends BpmnAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("71f84a0c-55c1-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("BPMNMESSAGEFLOW_ROUTINGMODE",
            MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("71f84a0f-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("BPMNMESSAGEFLOW_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("71f84a12-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("BPMNMESSAGEFLOW_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line radius
     */
    @objid ("71f84a15-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("BPMNMESSAGEFLOW_LINERADIUS", MetaKey.LINERADIUS);

    /**
     * Bridge
     */
    @objid ("71f84a18-55c1-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("BPMNMESSAGEFLOW_DRAWLINEBRIDGES",
            MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("71f84a1b-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("BPMNMESSAGEFLOW_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("71f84a1e-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("BPMNMESSAGEFLOW_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("71f84a21-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNMESSAGEFLOW_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("71f84a24-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("BPMNMESSAGEFLOW_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Name
     */
    @objid ("71f84a27-55c1-11e2-9337-002564c97630")
    public static final StyleKey NAMEVISIBLE = createStyleKey("BPMNMESSAGEFLOW_NAMEVISIBLE",
            MetaKey.SHOWLABEL);

    /**
     * Line pattern
     */
    @objid ("71f84a2a-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("BPMNMESSAGEFLOW_LINEPATTERN",
            MetaKey.LINEPATTERN);

}

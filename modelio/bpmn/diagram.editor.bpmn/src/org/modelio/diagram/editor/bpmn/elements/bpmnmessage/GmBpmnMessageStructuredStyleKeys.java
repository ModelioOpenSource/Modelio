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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link GmBpmnMessage} when its representation mode is {@link RepresentationMode#STRUCTURED}.
 */
@objid ("4fb69b57-a8fd-47fa-903c-aae9f2c38aed")
public class GmBpmnMessageStructuredStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("9335e754-45ba-40cd-9f9e-6547101600da")
    public static final StyleKey REPMODE = createStyleKey("BPMNMESSAGE_REPMODE", MetaKey.REPMODE);

    @objid ("042a9aec-05e0-4b83-91d6-fadd9b1d5890")
    public static final StyleKey FILLCOLOR = createStyleKey("BPMNMESSAGE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("5ae1964e-545d-4a32-bd41-41f93af1693b")
    public static final StyleKey FILLMODE = createStyleKey("BPMNMESSAGE_FILLMODE", MetaKey.FILLMODE);

    @objid ("feb1d1f7-8542-4abb-9699-be10f82795e7")
    public static final StyleKey LINECOLOR = createStyleKey("BPMNMESSAGE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("6fb9f5dd-6f1a-4645-8c5c-b3f72b54b72a")
    public static final StyleKey LINEWIDTH = createStyleKey("BPMNMESSAGE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("11551fd2-2627-4255-9d1d-bf56cddf2cf2")
    public static final StyleKey FONT = createStyleKey("BPMNMESSAGE_FONT", MetaKey.FONT);

    @objid ("bd43c9e2-2ed3-415e-90ed-fcf109d477c8")
    public static final StyleKey TEXTCOLOR = createStyleKey("BPMNMESSAGE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("9cca53b3-80f0-43a5-bb9c-482be6eb2c18")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNMESSAGE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("b0901bcb-05c0-4b6b-af7e-48222f718934")
    public static final StyleKey SHOWTAGS = createStyleKey("BPMNMESSAGE_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the element name label.
     * 
     * @see MetaKey#SHOWLABEL
     */
    @objid ("f14f7747-2d8d-4081-9967-945598d4715e")
    public static final StyleKey SHOWLABEL = createStyleKey("BPMNMESSAGE_SHOWLABEL", MetaKey.SHOWLABEL);

    @objid ("d6978633-6f39-4e99-9a04-bb3127d0dad6")
    public static final StyleKey SHOWREPRESENTED = createStyleKey("BPMNMESSAGE_SHOWREPRESENTED", Boolean.class);

}

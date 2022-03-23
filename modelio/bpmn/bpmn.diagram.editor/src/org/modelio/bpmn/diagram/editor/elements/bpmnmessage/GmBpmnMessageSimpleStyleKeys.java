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
package org.modelio.bpmn.diagram.editor.elements.bpmnmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.common.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;

/**
 * This class provides the StyleKey constants for a {@link GmBpmnMessage} when its representation mode is {@link RepresentationMode#SIMPLE}.
 */
@objid ("86a45c48-2d25-41e5-8be4-9ec18afe2878")
public class GmBpmnMessageSimpleStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("a0ab976b-4104-49ae-9798-d7cfcd8aae5c")
    public static final StyleKey REPMODE = createStyleKey("BPMNMESSAGE_REPMODE", MetaKey.REPMODE);

    @objid ("30e525eb-e5e7-495c-a7cc-2d6bcf6305b9")
    public static final StyleKey FILLCOLOR = createStyleKey("BPMNMESSAGE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("ed5ec2a8-6c2c-4d65-94a3-b27cf01f44ec")
    public static final StyleKey FILLMODE = createStyleKey("BPMNMESSAGE_FILLMODE", MetaKey.FILLMODE);

    @objid ("a510e775-8a67-4696-8a8a-8dbba60d4c9c")
    public static final StyleKey LINECOLOR = createStyleKey("BPMNMESSAGE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("dde08a68-8318-486a-8658-20c4b5503be5")
    public static final StyleKey LINEWIDTH = createStyleKey("BPMNMESSAGE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("311bac70-09bb-4e9f-8a5a-68b3309c9f84")
    public static final StyleKey FONT = createStyleKey("BPMNMESSAGE_FONT", MetaKey.FONT);

    @objid ("25b6b7c6-223b-48c5-9ba4-06a974b4d679")
    public static final StyleKey TEXTCOLOR = createStyleKey("BPMNMESSAGE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("55809973-6b3b-4821-97e4-5e7420d750a6")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNMESSAGE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("4e5421a6-acf3-46a2-863c-0f6ed6c09b2b")
    public static final StyleKey SHOWTAGS = createStyleKey("BPMNMESSAGE_SHOWTAGS", MetaKey.SHOWTAGS);

}

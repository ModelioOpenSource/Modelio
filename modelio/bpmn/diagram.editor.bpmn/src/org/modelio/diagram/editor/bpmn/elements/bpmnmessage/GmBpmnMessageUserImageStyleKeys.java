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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link GmBpmnMessage} when its representation mode is {@link RepresentationMode#USER_IMAGE}.
 */
@objid ("ef682c52-1ada-4aa2-8077-be82636d5cf4")
public class GmBpmnMessageUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("535508f0-35ec-41b4-a693-9856c98ee575")
    public static final StyleKey REPMODE = createStyleKey("BPMNMESSAGE_REPMODE", MetaKey.REPMODE);

    @objid ("30cbf224-241f-4930-bf0d-7cf37c2c6648")
    public static final StyleKey FONT = createStyleKey("BPMNMESSAGE_FONT", MetaKey.FONT);

    @objid ("7f30ed58-833f-4bed-be64-5545a80cf730")
    public static final StyleKey TEXTCOLOR = createStyleKey("BPMNMESSAGE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("de9b1bf8-b8ba-412a-901d-f132ed015ba3")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNMESSAGE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("1949be50-6756-49b7-85ec-b5f5bbc1f061")
    public static final StyleKey SHOWTAGS = createStyleKey("BPMNMESSAGE_SHOWTAGS", MetaKey.SHOWTAGS);

}

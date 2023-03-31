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
package org.modelio.bpmn.diagram.editor.elements.bpmnusertask;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.common.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnUserTask when its representation mode is RepresentationMode.IMAGE
 */
@objid ("896a50d6-6122-4295-b895-76947fd64015")
public class GmBpmnUserTaskUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("999038fa-53a8-4958-8730-f43718b6861c")
    public static final StyleKey REPMODE = createStyleKey("CALLOPERATION_REPMODE", MetaKey.REPMODE);

    @objid ("00e79725-025f-456d-956f-7c8e5ff64627")
    public static final StyleKey FONT = createStyleKey("CALLOPERATION_FONT", MetaKey.FONT);

    @objid ("2d40239a-f835-4073-8aa5-9c5ef911de7d")
    public static final StyleKey TEXTCOLOR = createStyleKey("CALLOPERATION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("4fc083d8-4841-47b3-862a-e9c49a69bb76")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("CALLOPERATION_SHOWSTEREOTYPES",
                    MetaKey.SHOWSTEREOTYPES);

    @objid ("4f58ec9a-17e1-4774-8a24-48969cb01fdc")
    public static final StyleKey SHOWTAGS = createStyleKey("CALLOPERATION_SHOWTAGS", MetaKey.SHOWTAGS);

}

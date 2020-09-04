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
 * This class provides the StyleKey constants for a {@link GmBpmnMessage} when its representation mode is {@link RepresentationMode#IMAGE}.
 */
@objid ("12b59ef1-00bb-45e3-b2c5-d82fd8e079e7")
public class GmBpmnMessageImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("8c93cc75-f650-44e0-894b-5ebc1292742e")
    public static final StyleKey REPMODE = createStyleKey("BPMNMESSAGE_REPMODE", MetaKey.REPMODE);

    @objid ("eeca87a3-4575-4206-ad75-c6b93252d962")
    public static final StyleKey FONT = createStyleKey("BPMNMESSAGE_FONT", MetaKey.FONT);

    @objid ("eed19b8b-8799-45bf-90da-572f1b9b168d")
    public static final StyleKey TEXTCOLOR = createStyleKey("BPMNMESSAGE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d542c5a4-0ac5-4d63-83e4-86fa782ab3b4")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNMESSAGE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("4bb6dc95-042b-49bc-bc6a-da2e796ea3f0")
    public static final StyleKey SHOWTAGS = createStyleKey("BPMNMESSAGE_SHOWTAGS", MetaKey.SHOWTAGS);

}

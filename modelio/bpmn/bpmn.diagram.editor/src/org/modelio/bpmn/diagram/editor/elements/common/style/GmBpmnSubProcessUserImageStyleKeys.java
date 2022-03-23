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
package org.modelio.bpmn.diagram.editor.elements.common.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnBusinessRuleTask when its representation mode is RepresentationMode.IMAGE
 */
@objid ("3107cef7-42a3-4364-9b9a-519d7af5db04")
public class GmBpmnSubProcessUserImageStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("f43b908d-fd45-4c69-aebf-c932d030a09a")
    public static final StyleKey REPMODE = createStyleKey("SUBPROCESS_REPMODE", MetaKey.REPMODE);

    @objid ("f07f66de-868e-4490-aa76-7e42a34728d6")
    public static final StyleKey FONT = createStyleKey("SUBPROCESS_FONT", MetaKey.FONT);

    @objid ("ce52c8a0-dbf7-4341-afff-34970b50bb9d")
    public static final StyleKey TEXTCOLOR = createStyleKey("SUBPROCESS_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("4ed943f3-603e-4c9e-9b9a-55f99553578d")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("SUBPROCESS_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("f0a9a6df-25c4-46bd-9a09-82871028aa6e")
    public static final StyleKey SHOWTAGS = createStyleKey("SUBPROCESS_SHOWTAGS", MetaKey.SHOWTAGS);

}

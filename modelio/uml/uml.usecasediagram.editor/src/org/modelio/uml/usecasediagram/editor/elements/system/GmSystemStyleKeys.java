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
package org.modelio.uml.usecasediagram.editor.elements.system;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.usecasediagram.editor.style.UseCaseAbstractStyleKeyProvider;

@objid ("5e51d149-55b7-11e2-877f-002564c97630")
public class GmSystemStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("d9d365ae-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("SYSTEM_REPMODE", MetaKey.REPMODE);

    @objid ("d9d365b1-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("SYSTEM_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d9d365b4-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("SYSTEM_FILLMODE", MetaKey.FILLMODE);

    @objid ("d9d365b7-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("SYSTEM_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d9d365ba-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("SYSTEM_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d9d365bd-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("SYSTEM_FONT", MetaKey.FONT);

    @objid ("d9d4ec4a-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("SYSTEM_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d9d4ec4d-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("SYSTEM_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    @objid ("d9d4ec50-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("SYSTEM_SHOWTAGS", MetaKey.SHOWTAGS);

}

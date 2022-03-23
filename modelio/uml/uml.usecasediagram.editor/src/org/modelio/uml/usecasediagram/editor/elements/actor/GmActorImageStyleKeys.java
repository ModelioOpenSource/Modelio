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
package org.modelio.uml.usecasediagram.editor.elements.actor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.usecasediagram.editor.style.UseCaseAbstractStyleKeyProvider;

@objid ("5e396736-55b7-11e2-877f-002564c97630")
public class GmActorImageStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("d9aa32c9-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("ACTOR_REPMODE", MetaKey.REPMODE);

    @objid ("d9aa32cc-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("ACTOR_FONT", MetaKey.FONT);

    @objid ("d9aa32cf-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("ACTOR_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d9aa32d2-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("ACTOR_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("d9aa32d5-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTOR_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    @objid ("d9aa32d8-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("ACTOR_SHOWTAGS", MetaKey.SHOWTAGS);

}

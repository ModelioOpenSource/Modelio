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

package org.modelio.diagram.editor.usecase.elements.actor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.usecase.style.UseCaseAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

@objid ("5e3dfb1a-55b7-11e2-877f-002564c97630")
public class GmActorSimpleStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("d99969ea-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("ACTOR_REPMODE", MetaKey.REPMODE);

    @objid ("d99969ed-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("ACTOR_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d99969f0-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("ACTOR_FILLMODE", MetaKey.FILLMODE);

    @objid ("d99969f3-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("ACTOR_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d99969f6-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("ACTOR_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d99969f9-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("ACTOR_FONT", MetaKey.FONT);

    @objid ("d99969fc-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("ACTOR_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d99969ff-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("ACTOR_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("d99af08b-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTOR_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("d99af08e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("ACTOR_SHOWTAGS", MetaKey.SHOWTAGS);

}

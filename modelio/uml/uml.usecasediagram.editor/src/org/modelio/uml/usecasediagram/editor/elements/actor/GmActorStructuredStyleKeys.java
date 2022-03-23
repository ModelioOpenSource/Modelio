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

@objid ("5e3f81a4-55b7-11e2-877f-002564c97630")
public class GmActorStructuredStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("d9ad400c-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("ACTOR_REPMODE", MetaKey.REPMODE);

    @objid ("d9ad400f-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("ACTOR_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d9ad4012-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("ACTOR_FILLMODE", MetaKey.FILLMODE);

    @objid ("d9ad4015-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("ACTOR_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d9ad4018-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("ACTOR_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d9ad401b-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("ACTOR_FONT", MetaKey.FONT);

    @objid ("d9ad401e-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("ACTOR_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d9ad4021-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("ACTOR_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("d9ad4024-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTOR_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    @objid ("d9ad4027-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("ACTOR_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d9ad402a-55c2-11e2-9337-002564c97630")
    public static final StyleKey ATTGROUPVISIBLE = createStyleKey("ACTOR_ATT_GROUPVISIBLE",
                                                                      MetaKey.AttGroup.ATTSHOWGROUP);

    @objid ("d9ad402d-55c2-11e2-9337-002564c97630")
    public static final StyleKey OPERATIONGROUPVISIBLE = createStyleKey("ACTOR_OP_GROUPVISIBLE",
                                                                            MetaKey.OperationGroup.OPSHOWGROUP);

    @objid ("d9ad4030-55c2-11e2-9337-002564c97630")
    public static final StyleKey FEATURES = createStyleKey("ACTOR_FEATURES", MetaKey.VISIBILITYFILTER);

    @objid ("5e410848-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Attribute extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9aec6a9-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("ACTOR_ATT_TEXTCOLOR",
                                                                        MetaKey.AttGroup.ATTTEXTCOLOR);

        @objid ("d9aec6ac-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("ACTOR_ATT_FONT", MetaKey.AttGroup.ATTFONT);

        @objid ("d9aec6af-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTOR_ATT_SHOWSTEREOTYPES",
                                                                              MetaKey.AttGroup.ATTSHOWSTEREOTYPES);

        @objid ("d9aec6b2-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("ACTOR_ATT_SHOWTAGS",
                                                                       MetaKey.AttGroup.ATTSHOWTAGS);

        @objid ("d9aec6b5-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWVISIBILITY = createStyleKey("ACTOR_ATT_SHOWVISIBILITY",
                                                                             MetaKey.AttGroup.ATTSHOWVISIBILITY);

    }

    @objid ("5e410865-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Operation extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9aec6b8-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("ACTOR_OP_TEXTCOLOR",
                                                                        MetaKey.OperationGroup.OPTEXTCOLOR);

        @objid ("d9aec6bb-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("ACTOR_OP_FONT", MetaKey.OperationGroup.OPFONT);

        @objid ("d9aec6be-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSIGNATURE = createStyleKey("ACTOR_OP_SHOWSIGNATURE",
                                                                            MetaKey.OperationGroup.OPSHOWSIGNATURE);

        @objid ("d9aec6c1-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTOR_OP_SHOWSTEREOTYPES",
                                                                              MetaKey.OperationGroup.OPSHOWSTEREOTYPES);

        @objid ("d9aec6c4-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("ACTOR_OP_SHOWTAGS",
                                                                       MetaKey.OperationGroup.OPSHOWTAGS);

        @objid ("d9aec6c7-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWVISIBILITY = createStyleKey("ACTOR_OP_SHOWVISIBILITY",
                                                                             MetaKey.OperationGroup.OPSHOWVISIBILITY);

    }

    @objid ("5e428eed-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class InternalStructure extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9aec6ca-55c2-11e2-9337-002564c97630")
        static final StyleKey INTERNALSVIEWMODE = createStyleKey("ACTOR_INTERNALSVIEWMODE",
                                                                         MetaKey.InternalGroup.INTVIEWMODE);

        @objid ("d9aec6cd-55c2-11e2-9337-002564c97630")
        static final StyleKey TEXTCOLOR = createStyleKey("ACTOR_INTERNAL_TEXTCOLOR",
                                                                 MetaKey.InternalGroup.INTTEXTCOLOR);

        @objid ("d9aec6d0-55c2-11e2-9337-002564c97630")
        static final StyleKey FONT = createStyleKey("ACTOR_INTERNAL_FONT", MetaKey.InternalGroup.INTFONT);

        @objid ("d9b04d49-55c2-11e2-9337-002564c97630")
        static final StyleKey SHOWSTEREOTYPES = createStyleKey("ACTOR_INTERNAL_SHOWSTEREOTYPES",
                                                                       MetaKey.InternalGroup.INTSHOWSTEREOTYPES);

        @objid ("d9b04d4c-55c2-11e2-9337-002564c97630")
        static final StyleKey SHOWTAGS = createStyleKey("ACTOR_INTERNAL_SHOWTAGS",
                                                                MetaKey.InternalGroup.INTSHOWTAGS);

        @objid ("d9b04d4f-55c2-11e2-9337-002564c97630")
        static final StyleKey AUTOUNMASK = createStyleKey("ACTOR_INTERNAL_AUTOUNMASK",
                                                                  MetaKey.InternalGroup.INTAUTOUNMASK);

    }

}

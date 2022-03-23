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
package org.modelio.uml.usecasediagram.editor.elements.usecase.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;
import org.modelio.uml.usecasediagram.editor.style.UseCaseAbstractStyleKeyProvider;

@objid ("5e7055a0-55b7-11e2-877f-002564c97630")
class GmUseCaseStructuredStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("d9c730b1-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("USECASE_REPMODE", MetaKey.REPMODE);

    @objid ("d9c730b4-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("USECASE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d9c730b7-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("USECASE_FILLMODE", MetaKey.FILLMODE);

    @objid ("d9c730ba-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("USECASE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d9c730bd-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("USECASE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d9c730c0-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("USECASE_FONT", MetaKey.FONT);

    @objid ("d9c730c3-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d9c730c6-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("USECASE_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("d9c730c9-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    @objid ("d9c730cc-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("USECASE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d9c730cf-55c2-11e2-9337-002564c97630")
    public static final StyleKey ATTGROUPVISIBLE = createStyleKey("USECASE_ATT_GROUPVISIBLE",
                                                                      MetaKey.AttGroup.ATTSHOWGROUP);

    @objid ("d9c730d2-55c2-11e2-9337-002564c97630")
    public static final StyleKey OPERATIONGROUPVISIBLE = createStyleKey("USECASE_OP_GROUPVISIBLE",
                                                                            MetaKey.OperationGroup.OPSHOWGROUP);

    @objid ("d9c730d5-55c2-11e2-9337-002564c97630")
    public static final StyleKey FEATURES = createStyleKey("USECASE_FEATURES", MetaKey.VISIBILITYFILTER);

    @objid ("d9c730d8-55c2-11e2-9337-002564c97630")
    public static final StyleKey EXTENSIONPOINTGROUPVISIBLE = createStyleKey("USECASE_EXTENSIONPOINT_GROUPVISIBLE",
                                                                                 Boolean.class);

    @objid ("5e71dc49-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Attribute extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9c8b749-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_ATT_TEXTCOLOR",
                                                                        MetaKey.AttGroup.ATTTEXTCOLOR);

        @objid ("d9c8b74c-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("USECASE_ATT_FONT", MetaKey.AttGroup.ATTFONT);

        @objid ("d9c8b74f-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_ATT_SHOWSTEREOTYPES",
                                                                              MetaKey.AttGroup.ATTSHOWSTEREOTYPES);

        @objid ("d9c8b752-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("USECASE_ATT_SHOWTAGS",
                                                                       MetaKey.AttGroup.ATTSHOWTAGS);

        @objid ("d9c8b755-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWVISIBILITY = createStyleKey("USECASE_ATT_SHOWVISIBILITY",
                                                                             MetaKey.AttGroup.ATTSHOWVISIBILITY);

    }

    @objid ("5e71dc66-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class ExtensionPoint extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9c8b758-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_EXTENSIONPOINT_TEXTCOLOR",
                                                                        Color.class);

        @objid ("d9c8b75b-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("USECASE_EXTENSIONPOINT_FONT", Font.class);

        @objid ("d9c8b75e-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_EXTENSIONPOINT_SHOWSTEREOTYPES",
                                                                              ShowStereotypeMode.class);

        @objid ("d9c8b761-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("USECASE_EXTENSIONPOINT_SHOWTAGS",
                                                                       Boolean.class);

    }

    @objid ("5e7362e1-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Operation extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9c8b764-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_OP_TEXTCOLOR",
                                                                        MetaKey.OperationGroup.OPTEXTCOLOR);

        @objid ("d9c8b767-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("USECASE_OP_FONT", MetaKey.OperationGroup.OPFONT);

        @objid ("d9c8b76a-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSIGNATURE = createStyleKey("USECASE_OP_SHOWSIGNATURE",
                                                                            MetaKey.OperationGroup.OPSHOWSIGNATURE);

        @objid ("d9c8b76d-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_OP_SHOWSTEREOTYPES",
                                                                              MetaKey.OperationGroup.OPSHOWSTEREOTYPES);

        @objid ("d9c8b770-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("USECASE_OP_SHOWTAGS",
                                                                       MetaKey.OperationGroup.OPSHOWTAGS);

        @objid ("d9c8b773-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWVISIBILITY = createStyleKey("USECASE_OP_SHOWVISIBILITY",
                                                                             MetaKey.OperationGroup.OPSHOWVISIBILITY);

    }

    @objid ("5e736303-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class InternalStructure extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9ca3deb-55c2-11e2-9337-002564c97630")
        static final StyleKey INTERNALSVIEWMODE = createStyleKey("USECASE_INTERNALSVIEWMODE",
                                                                         MetaKey.InternalGroup.INTVIEWMODE);

        @objid ("d9ca3dee-55c2-11e2-9337-002564c97630")
        static final StyleKey TEXTCOLOR = createStyleKey("USECASE_INTERNAL_TEXTCOLOR",
                                                                 MetaKey.InternalGroup.INTTEXTCOLOR);

        @objid ("d9ca3df1-55c2-11e2-9337-002564c97630")
        static final StyleKey FONT = createStyleKey("USECASE_INTERNAL_FONT", MetaKey.InternalGroup.INTFONT);

        @objid ("d9ca3df4-55c2-11e2-9337-002564c97630")
        static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_INTERNAL_SHOWSTEREOTYPES",
                                                                       MetaKey.InternalGroup.INTSHOWSTEREOTYPES);

        @objid ("d9ca3df7-55c2-11e2-9337-002564c97630")
        static final StyleKey SHOWTAGS = createStyleKey("USECASE_INTERNAL_SHOWTAGS",
                                                                MetaKey.InternalGroup.INTSHOWTAGS);

        @objid ("d9ca3dfa-55c2-11e2-9337-002564c97630")
        static final StyleKey AUTOUNMASK = createStyleKey("USECASE_INTERNAL_AUTOUNMASK",
                                                                  MetaKey.InternalGroup.INTAUTOUNMASK);

    }

    @objid ("5e74e987-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Inner extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9ca3dfd-55c2-11e2-9337-002564c97630")
        public static final StyleKey INNERVIEWMODE = createStyleKey("USECASE_INNERVIEWMODE",
                                                                            MetaKey.InnerGroup.INNERVIEWMODE);

        @objid ("d9ca3e00-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_INNER_TEXTCOLOR",
                                                                        MetaKey.InnerGroup.INNERTEXTCOLOR);

        @objid ("d9ca3e03-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("USECASE_INNER_FONT", MetaKey.InnerGroup.INNERFONT);

        @objid ("d9ca3e06-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWNAME = createStyleKey("USECASE_INNER_SHOWNAME",
                                                                       MetaKey.InnerGroup.INNERSHOWNAME);

        @objid ("d9ca3e09-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_INNER_SHOWSTEREOTYPES",
                                                                              MetaKey.InnerGroup.INNERSHOWSTEREOTYPES);

        @objid ("d9ca3e0c-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("USECASE_INNER_SHOWTAGS",
                                                                       MetaKey.InnerGroup.INNERSHOWTAGS);

        @objid ("d9ca3e0f-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWVISIBILITY = createStyleKey("USECASE_INNER_SHOWVISIBILITY",
                                                                             MetaKey.InnerGroup.INNERSHOWVISIBILITY);

    }

}

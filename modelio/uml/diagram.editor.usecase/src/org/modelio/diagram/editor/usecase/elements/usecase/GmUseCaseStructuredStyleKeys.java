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

package org.modelio.diagram.editor.usecase.elements.usecase;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.editor.usecase.style.UseCaseAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ShowStereotypeMode;
import org.modelio.diagram.styles.core.StyleKey;

@objid ("5e65a73c-55b7-11e2-877f-002564c97630")
public class GmUseCaseStructuredStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("d9be0916-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("USECASE_REPMODE", MetaKey.REPMODE);

    @objid ("d9bf8f89-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("USECASE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("d9bf8f8c-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("USECASE_FILLMODE", MetaKey.FILLMODE);

    @objid ("d9bf8f8f-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("USECASE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("d9bf8f92-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("USECASE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("d9bf8f95-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("USECASE_FONT", MetaKey.FONT);

    @objid ("d9bf8f98-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("d9bf8f9b-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("USECASE_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("d9bf8f9e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    @objid ("d9bf8fa1-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("USECASE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("d9bf8fa4-55c2-11e2-9337-002564c97630")
    public static final StyleKey ATTGROUPVISIBLE = createStyleKey("USECASE_ATT_GROUPVISIBLE",
                                                                  MetaKey.AttGroup.ATTSHOWGROUP);

    @objid ("d9bf8fa7-55c2-11e2-9337-002564c97630")
    public static final StyleKey OPERATIONGROUPVISIBLE = createStyleKey("USECASE_OP_GROUPVISIBLE",
                                                                        MetaKey.OperationGroup.OPSHOWGROUP);

    @objid ("d9bf8faa-55c2-11e2-9337-002564c97630")
    public static final StyleKey FEATURES = createStyleKey("USECASE_FEATURES", MetaKey.VISIBILITYFILTER);

    @objid ("d9bf8fad-55c2-11e2-9337-002564c97630")
    public static final StyleKey EXTENSIONPOINTGROUPVISIBLE = createStyleKey("USECASE_EXTENSIONPOINT_GROUPVISIBLE",
                                                                             Boolean.class);

    @objid ("5e672de6-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Attribute extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9bf8fb0-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_ATT_TEXTCOLOR",
                                                                MetaKey.AttGroup.ATTTEXTCOLOR);

        @objid ("d9bf8fb3-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("USECASE_ATT_FONT", MetaKey.AttGroup.ATTFONT);

        @objid ("d9bf8fb6-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_ATT_SHOWSTEREOTYPES",
                                                                      MetaKey.AttGroup.ATTSHOWSTEREOTYPES);

        @objid ("d9bf8fb9-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("USECASE_ATT_SHOWTAGS",
                                                               MetaKey.AttGroup.ATTSHOWTAGS);

        @objid ("d9c1162a-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWVISIBILITY = createStyleKey("USECASE_ATT_SHOWVISIBILITY",
                                                                     MetaKey.AttGroup.ATTSHOWVISIBILITY);

    }

    @objid ("5e672e03-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class ExtensionPoint extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9c1162d-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_EXTENSIONPOINT_TEXTCOLOR",
                                                                Color.class);

        @objid ("d9c11630-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("USECASE_EXTENSIONPOINT_FONT", Font.class);

        @objid ("d9c11633-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_EXTENSIONPOINT_SHOWSTEREOTYPES",
                                                                      ShowStereotypeMode.class);

        @objid ("d9c11636-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("USECASE_EXTENSIONPOINT_SHOWTAGS",
                                                               Boolean.class);

    }

    @objid ("5e68b481-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Operation extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9c11639-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_OP_TEXTCOLOR",
                                                                MetaKey.OperationGroup.OPTEXTCOLOR);

        @objid ("d9c1163c-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("USECASE_OP_FONT", MetaKey.OperationGroup.OPFONT);

        @objid ("d9c1163f-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSIGNATURE = createStyleKey("USECASE_OP_SHOWSIGNATURE",
                                                                    MetaKey.OperationGroup.OPSHOWSIGNATURE);

        @objid ("d9c11642-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_OP_SHOWSTEREOTYPES",
                                                                      MetaKey.OperationGroup.OPSHOWSTEREOTYPES);

        @objid ("d9c11645-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("USECASE_OP_SHOWTAGS",
                                                               MetaKey.OperationGroup.OPSHOWTAGS);

        @objid ("d9c11648-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWVISIBILITY = createStyleKey("USECASE_OP_SHOWVISIBILITY",
                                                                     MetaKey.OperationGroup.OPSHOWVISIBILITY);

    }

    @objid ("5e68b4a3-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class InternalStructure extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9c1164b-55c2-11e2-9337-002564c97630")
         static final StyleKey INTERNALSVIEWMODE = createStyleKey("USECASE_INTERNALSVIEWMODE",
                                                                 MetaKey.InternalGroup.INTVIEWMODE);

        @objid ("d9c1164e-55c2-11e2-9337-002564c97630")
         static final StyleKey TEXTCOLOR = createStyleKey("USECASE_INTERNAL_TEXTCOLOR",
                                                         MetaKey.InternalGroup.INTTEXTCOLOR);

        @objid ("d9c11651-55c2-11e2-9337-002564c97630")
         static final StyleKey FONT = createStyleKey("USECASE_INTERNAL_FONT", MetaKey.InternalGroup.INTFONT);

        @objid ("d9c29cc9-55c2-11e2-9337-002564c97630")
         static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_INTERNAL_SHOWSTEREOTYPES",
                                                               MetaKey.InternalGroup.INTSHOWSTEREOTYPES);

        @objid ("d9c29ccc-55c2-11e2-9337-002564c97630")
         static final StyleKey SHOWTAGS = createStyleKey("USECASE_INTERNAL_SHOWTAGS",
                                                        MetaKey.InternalGroup.INTSHOWTAGS);

        @objid ("d9c29ccf-55c2-11e2-9337-002564c97630")
         static final StyleKey AUTOUNMASK = createStyleKey("USECASE_INTERNAL_AUTOUNMASK",
                                                          MetaKey.InternalGroup.INTAUTOUNMASK);

    }

    @objid ("5e6a3b25-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Inner extends UseCaseAbstractStyleKeyProvider {
        @objid ("d9c29cd2-55c2-11e2-9337-002564c97630")
        public static final StyleKey INNERVIEWMODE = createStyleKey("USECASE_INNERVIEWMODE",
                                                                    MetaKey.InnerGroup.INNERVIEWMODE);

        @objid ("d9c29cd5-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("USECASE_INNER_TEXTCOLOR",
                                                                MetaKey.InnerGroup.INNERTEXTCOLOR);

        @objid ("d9c29cd8-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("USECASE_INNER_FONT", MetaKey.InnerGroup.INNERFONT);

        @objid ("d9c29cdb-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWNAME = createStyleKey("USECASE_INNER_SHOWNAME",
                                                               MetaKey.InnerGroup.INNERSHOWNAME);

        @objid ("d9c29cde-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("USECASE_INNER_SHOWSTEREOTYPES",
                                                                      MetaKey.InnerGroup.INNERSHOWSTEREOTYPES);

        @objid ("d9c29ce1-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("USECASE_INNER_SHOWTAGS",
                                                               MetaKey.InnerGroup.INNERSHOWTAGS);

        @objid ("d9c29ce4-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWVISIBILITY = createStyleKey("USECASE_INNER_SHOWVISIBILITY",
                                                                     MetaKey.InnerGroup.INNERSHOWVISIBILITY);

    }

}

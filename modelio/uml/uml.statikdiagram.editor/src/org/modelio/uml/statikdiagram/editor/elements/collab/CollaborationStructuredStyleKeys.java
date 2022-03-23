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
package org.modelio.uml.statikdiagram.editor.elements.collab;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmUseCase when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("3453b1ff-55b7-11e2-877f-002564c97630")
public class CollaborationStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a60deb0c-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("COLLABORATION_REPMODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a60deb0f-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("COLLABORATION_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a60deb12-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("COLLABORATION_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color.
     */
    @objid ("a60deb15-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("COLLABORATION_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("a60deb18-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("COLLABORATION_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a60deb1b-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("COLLABORATION_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a60deb1e-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("COLLABORATION_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display name.
     */
    @objid ("a60deb21-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("COLLABORATION_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("a60deb24-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("COLLABORATION_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("a60deb27-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("COLLABORATION_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Display class visibility.
     */
    @objid ("a60deb2a-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWVISIBILITY = createStyleKey("COLLABORATION_SHOWVISIBILITY",
                                                                     MetaKey.SHOWVISIBILITY);

    /**
     * Filter on features
     */
    @objid ("a60deb2d-55c2-11e2-9337-002564c97630")
    public static final StyleKey FEATURES = createStyleKey("COLLABORATION_FEATURES", MetaKey.VISIBILITYFILTER);

    /**
     * Inner classes visualization mode: labels, diagram or none.
     */
    @objid ("a60deb30-55c2-11e2-9337-002564c97630")
    public static final StyleKey INNERVIEWMODE = createStyleKey("COLLABORATION_INNERVIEWMODE",
                                                                    MetaKey.InnerGroup.INNERVIEWMODE);

    /**
     * Style keys for all internal structure group members.
     * <p>
     * INTERNALSVIEWMODE and INTERNALS are used for internal structure zone too.
     * 
     * @author cmarin
     */
    @objid ("345538a9-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class InternalStructure extends StaticAbstractStyleKeyProvider {
        /**
         * Internal structure visualization mode: labels, diagram or none.
         */
        @objid ("a60deb33-55c2-11e2-9337-002564c97630")
        static final StyleKey INTERNALSVIEWMODE = createStyleKey("COLLABORATION_INTERNALSVIEWMODE",
                                                                         MetaKey.InternalGroup.INTVIEWMODE);

        /**
         * Text color.
         */
        @objid ("a60f71ab-55c2-11e2-9337-002564c97630")
        static final StyleKey TEXTCOLOR = createStyleKey("COLLABORATION_INTERNAL_TEXTCOLOR",
                                                                 MetaKey.InternalGroup.INTTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a60f71ae-55c2-11e2-9337-002564c97630")
        static final StyleKey FONT = createStyleKey("COLLABORATION_INTERNAL_FONT",
                                                            MetaKey.InternalGroup.INTFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a60f71b1-55c2-11e2-9337-002564c97630")
        static final StyleKey SHOWSTEREOTYPES = createStyleKey("COLLABORATION_INTERNAL_SHOWSTEREOTYPES",
                                                                       MetaKey.InternalGroup.INTSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a60f71b4-55c2-11e2-9337-002564c97630")
        static final StyleKey SHOWTAGS = createStyleKey("COLLABORATION_INTERNAL_SHOWTAGS",
                                                                MetaKey.InternalGroup.INTSHOWTAGS);

        /**
         * Auto unmask internal structure content. Boolean type.
         */
        @objid ("a60f71b7-55c2-11e2-9337-002564c97630")
        static final StyleKey AUTOUNMASK = createStyleKey("COLLABORATION_INTERNAL_AUTOUNMASK",
                                                                  MetaKey.InternalGroup.INTAUTOUNMASK);

    }

    /**
     * Style keys for all inner classifiers group members.
     * 
     * @author cmarin
     */
    @objid ("3456bf2f-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static class Inner extends StaticAbstractStyleKeyProvider {
        /**
         * Text color.
         */
        @objid ("a60f71bc-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("COLLABORATION_INNER_TEXTCOLOR",
                                                                        MetaKey.InnerGroup.INNERTEXTCOLOR);

        /**
         * Font
         */
        @objid ("a60f71bf-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("COLLABORATION_INNER_FONT",
                                                                   MetaKey.InnerGroup.INNERFONT);

        /**
         * Stereotype display mode.
         */
        @objid ("a60f71c2-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("COLLABORATION_INNER_SHOWSTEREOTYPES",
                                                                              MetaKey.InnerGroup.INNERSHOWSTEREOTYPES);

        /**
         * Display tagged values.
         */
        @objid ("a60f71c5-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("COLLABORATION_INNER_SHOWTAGS",
                                                                       MetaKey.InnerGroup.INNERSHOWTAGS);

    }

}

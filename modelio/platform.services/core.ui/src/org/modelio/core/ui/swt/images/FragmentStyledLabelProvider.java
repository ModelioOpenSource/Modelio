/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.core.ui.swt.images;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.TextStyle;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.gproject.fragment.FragmentState;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.ui.UIColor;

/**
 * Styled label provider for {@link IProjectFragment}.
 */
@objid ("3022cc50-1ae7-4263-a6c4-55bc862fd794")
public class FragmentStyledLabelProvider {
    /**
     * @param fragment a project fragment
     * @return its styled text.
     */
    @objid ("eb258ac6-3161-4cfe-a96e-2e33a1e53543")
    public static StyledString getStyledText(IProjectFragment fragment) {
        return new StyledString(fragment.getId(), FragmentStyler.getStyler(fragment));
    }

    @objid ("321f33e1-ba8c-4ebf-b29f-b41d7898b329")
    private static class FragmentStyler extends Styler {
        @objid ("2d7ef825-c638-4096-83f8-dfa1d94b97e6")
        private final IProjectFragment fragment;

        @objid ("e461e956-70dd-4472-a699-7d17aae165ff")
        public static Styler getStyler(IProjectFragment fragment) {
            return new FragmentStyler(fragment);
        }

        @objid ("16b66ba2-0cba-475c-9698-c94df0249b4b")
        @Override
        public void applyStyles(TextStyle textStyle) {
            if (this.fragment == null) {
                return;
            }
            textStyle.foreground = getForeground(this.fragment);
            textStyle.background = getBackground(this.fragment);
        }

        /**
         * Return the foreground color for the given element according to those rules:
         * <ul>
         * <li>Modifiable model elements font color is black #000000.</li>
         * <li>Non-modifiable model component elements font color is dark grey #606060.</li>
         * <li>Incomplete model elements font color is light red #FF8080.</li>
         * <li>Ramc model elements font color is modified yellow #A0A000.</li>
         * </ul>
         * @return a Color.
         */
        @objid ("8d26a3b5-12e7-4d5b-92a5-0ab3dd8dbcd4")
        private static Color getForeground(IProjectFragment aFragment) {
            if (aFragment.getState() == FragmentState.DOWN) {
                return UIColor.RED;
            }
            
            switch (aFragment.getType()) {
            case EXML:
            case EXML_SVN:
                if (Boolean.parseBoolean(aFragment.getProperties().getValue(IProjectFragment.PROP_READ_ONLY)))
                    return UIColor.NONMODIFIABLE_ELEMENT_FG;
                else
                    return UIColor.BLACK;
            case RAMC:
            case MDA:
                return UIColor.RAMC_ELEMENT_FG;
            case EXML_URL:
                return UIColor.NONMODIFIABLE_ELEMENT_FG;
            default:
                CoreUi.LOG.warning("No color found for fragment type: " + aFragment.getType());
                break;
            }
            return UIColor.BLACK;
        }

        /**
         * Get the background color for the given element
         * @param aFragment a Fragment
         * @return its background color
         */
        @objid ("39127849-3761-4310-89fe-a02b49aafb00")
        private static Color getBackground(IProjectFragment aFragment) {
            return null;
        }

        @objid ("a2ae9eff-bb4f-47db-8ac6-02e8b076d66a")
        public FragmentStyler(IProjectFragment fragment) {
            this.fragment = fragment;
        }

    }

}

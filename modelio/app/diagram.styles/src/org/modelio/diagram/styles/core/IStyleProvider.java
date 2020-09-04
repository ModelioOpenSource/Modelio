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

package org.modelio.diagram.styles.core;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;

/**
 * Interface for all graphic elements that support styles.
 * 
 * @author cmarin
 */
@objid ("8555e8f6-1926-11e2-92d2-001ec947c8cc")
public interface IStyleProvider {
    /**
     * Get the element style, ignoring dynamic styling.
     * 
     * @return the element style.
     * @since 3.6 . Before 3.6 this method was called "getStyle()"
     */
    @objid ("8555e8f8-1926-11e2-92d2-001ec947c8cc")
    IStyle getPersistedStyle();

    /**
     * Get all style keys the element supports.
     * 
     * @return style keys supported by the element.
     * @deprecated Since 3.7, {@link #getSymbolViewModel()} is used to get the symbol view content.
     */
    @objid ("8555e8fb-1926-11e2-92d2-001ec947c8cc")
    @Deprecated
    List<StyleKey> getStyleKeys();

    /**
     * Get the style key corresponding to the given meta key.
     * 
     * @param metakey a meta key
     * @return the corresponding style key or null if none maps.
     */
    @objid ("8555e900-1926-11e2-92d2-001ec947c8cc")
    StyleKey getStyleKey(MetaKey metakey);

    /**
     * Get the element style, including dynamic styling.
     * 
     * @return the element style.
     * @since 3.6
     */
    @objid ("a4f2a37e-a617-4d1a-894e-dcbeba5c3456")
    IStyle getDisplayedStyle();

    /**
     * Get the edition model of the element persisted style.
     * 
     * @return the tree model of the symbol view.
     * @since 3.7
     */
    @objid ("4e6c63bd-2d1e-49d5-99eb-d56ed3e32ab2")
    ISymbolViewModel getSymbolViewModel();

}

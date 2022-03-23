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
package org.modelio.diagram.elements.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.AbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class is an extension of AbstractStyleKeyProvider managing i18n for StyleKeys.
 * <p>
 * You must use {@link ElementsAbstractStyleKeyProvider#createStyleKey} to create each StyleKey.
 * </p>
 */
@objid ("81118bd4-1dec-11e2-8cad-001ec947c8cc")
public abstract class ElementsAbstractStyleKeyProvider extends AbstractStyleKeyProvider {
    /**
     * Creates a StyleKey based on a MetaKey.
     * @param key The style key.
     * @param metakey the base meta key.
     * @return a StyleKey with i18n informations filled.
     */
    @objid ("81118bde-1dec-11e2-8cad-001ec947c8cc")
    public static StyleKey createStyleKey(String key, MetaKey metakey) {
        return new StyleKey(key,
                        metakey,
                        metakey.getLabel(),
                        metakey.getTooltip(),
                        DiagramElements.I18N.getMessage(AbstractStyleKeyProvider.STYLEKEY_PREFIX + key + AbstractStyleKeyProvider.CATEGORY_SUFFIX));
    }

    /**
     * Creates a style key.
     * @param key The style key id.
     * @param type The type of the style key.
     * @return a StyleKey with i18n informations filled.
     */
    @objid ("81118be5-1dec-11e2-8cad-001ec947c8cc")
    public static StyleKey createStyleKey(String key, Class<?> type) {
        return new StyleKey(key,
                        type,
                        DiagramElements.I18N.getMessage(AbstractStyleKeyProvider.STYLEKEY_PREFIX + key + AbstractStyleKeyProvider.LABEL_SUFFIX),
                        DiagramElements.I18N.getMessage(AbstractStyleKeyProvider.STYLEKEY_PREFIX + key + AbstractStyleKeyProvider.TOOLTIP_SUFFIX),
                        DiagramElements.I18N.getMessage(AbstractStyleKeyProvider.STYLEKEY_PREFIX + key + AbstractStyleKeyProvider.CATEGORY_SUFFIX));
    }

}

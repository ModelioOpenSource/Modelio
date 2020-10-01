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

package org.modelio.platform.model.ui.nattable.parts.data.string.choice;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.ISingleNatValue;

/**
 * Defines an {@link INatValue} wrapping a single {@link String} instance containing only one line.
 */
@objid ("68f00c51-a044-48ee-9e95-d2b81253dec5")
public interface IStringChoiceNatValue extends ISingleNatValue {
    @objid ("04085424-1163-4bc4-8742-bf1a421775f7")
    @Override
    String getValue();

    /**
     * @return A list to choose the value from at edition.
     */
    @objid ("d46120e5-5984-4ca1-a479-8e63dc520db7")
    List<String> getValueChoices();

    /**
     * @return Whether the choice list can be manually extended or not.
     */
    @objid ("2be61e79-634a-45e0-9dd7-cba4b520dcb7")
    boolean isEditable();

}

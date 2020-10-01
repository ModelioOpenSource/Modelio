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

package org.modelio.diagram.styles.core.view;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Item in the symbol view model.
 * @author cma
 * @since 3.7
 */
@objid ("6e0cbe6a-585a-462a-963d-c71de9b36358")
public interface ISymbolViewItem {
    /**
     * Please implement {@link #equals(Object)}.
     */
    @objid ("8bf9284f-b0d7-48cb-a7e7-5c00c4f47488")
    @Override
    boolean equals(Object obj);

    /**
     * Get a more detailed description to display in a tooltip or a wider widget.
     * 
     * @return a detailed description.
     */
    @objid ("72077239-e08c-428e-b056-7c18a80580bc")
    String getDescription();

    /**
     * @return the label to display
     */
    @objid ("99a46820-926b-4b68-90bd-bb89cd05c532")
    String getLabel();

    /**
     * Get the possible values for an enumerated property.
     * <p>
     * Returns an empty collection if the property is not enumerated.
     * 
     * @return the possible values.
     */
    @objid ("4be457ba-d77e-44e2-bf6c-32803e3bd5ce")
    List<Choice> getPossibleValues();

    /**
     * Get the edited {@link StyleKey}.
     * <p>
     * If null it means it represents just a label.
     * 
     * @return the represented field.
     */
    @objid ("75288660-b218-4a98-bb5a-56c52c69bed4")
    StyleKey getStyleKey();

    /**
     * Get the type of the editable field.
     * <p>
     * If null it means it represents just a label.
     * 
     * @return The type of the editable field.
     */
    @objid ("b2d389a6-e8cf-4f44-940e-34c026997ad3")
    Class<?> getType();

    /**
     * Get the value of this field from the given style.
     * 
     * @param input a style
     * @return the filed value.
     */
    @objid ("9008aabe-a9cc-47be-abfe-bb0098205e4d")
    Object getValue(IStyle input);

    /**
     * Tells whether this item is modifiable in the given style.
     * 
     * @param style the edited style
     * @return true only if the item is modifiable.
     */
    @objid ("f4af8871-41ad-4844-81af-b52d76792688")
    boolean isEditable(IStyle style);

    /**
     * @param input a style
     * @return true if the value is different than the default value.
     */
    @objid ("22e415eb-252f-4072-90d3-f0a60b3a553c")
    boolean isLocallyModified(IStyle input);

    /**
     * Save the new value in the given style.
     * 
     * @param input the edited style
     * @param newValue the new value.
     */
    @objid ("691562c1-23b3-42ce-bcf7-b44bb77e68ed")
    void setValue(IStyle input, Object newValue);

    /**
     * Possible choice in an enumerated symbol view item.
     * @author cma
     * @since 3.7
     */
    @objid ("ca48dc93-eca5-408f-858d-4eef8218f7a9")
    static final class Choice {
        @objid ("73df7186-e6d7-4558-99d2-b5f4d9142a18")
        public final String label;

        @objid ("0fcace90-70c7-4fd3-ba57-092e72afc00d")
        public final Object value;

        @objid ("5df9ce76-0d3b-45ef-9291-35a0d182ff78")
        public Choice(Object value, String label) {
            this.value = value;
            this.label = label;
        }

        @objid ("464b0bb0-f32e-4bf1-af80-4d2ba5cc9c2c")
        @Override
        public String toString() {
            return "Choice [label=" + this.label + ", value=" + this.value + "]";
        }

    }

}

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

package org.modelio.uml.ui.modelproperty.uml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.hybrid.DefaultHybridNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <i>TemplateParameterSubstitution</i> data model.
 * <p>
 * This class provides the list of properties for the <i>TemplateParameterSubstitution</i> metaclass.
 */
@objid ("9222546c-ce5c-491b-9bdd-d5df6613502c")
public class TemplateParameterSubstitutionPropertyModel extends AbstractPropertyModel<TemplateParameterSubstitution> {
    /**
     * Properties to display for <i>TemplateParameterSubstitution</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the metaclass name)
     * <li>for otheEditedElement rows the values usually match the meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("93f089d1-da6a-4270-9ad0-160e2c98487c")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID,
            "Value" };

    @objid ("1e542a41-2510-45af-a260-142f4e66bb2b")
    private SubstitutionValue substitutionValue;

    /**
     * Create a new <i>TemplateParameterSubstitution</i> data model from an <i>TemplateParameterSubstitution</i>.
     * 
     * @param theEditedElement the model to edit.
     */
    @objid ("1000e6c8-3ed4-4019-a1c7-2b392de91525")
    public TemplateParameterSubstitutionPropertyModel(TemplateParameterSubstitution theEditedElement) {
        super(theEditedElement);
        this.substitutionValue = new SubstitutionValue();
    }

    /**
     * The number of columns that the properties table must display.
     * 
     * @return the number of columns
     */
    @objid ("f22868e8-86a1-4fb7-a3a7-e347ba004072")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * 
     * @return the number of rows
     */
    @objid ("5af4fb0a-0321-499c-b060-24790963611e")
    @Override
    public int getRowsNumber() {
        return TemplateParameterSubstitutionPropertyModel.PROPERTIES.length;
    }

    /**
     * Return the value that will be displayed at the specified row and column.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number
     * @param col the column number
     * @return the value corresponding to the row and column
     */
    @objid ("49109288-952a-443a-b899-37165b64dafd")
    private Object getValue(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key
            return getPropertyI18n(TemplateParameterSubstitutionPropertyModel.PROPERTIES[row]);
        case 1: // col 1 is the property value
            switch (row) {
            case 0: // Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            case 1:
                return SubstitutionValue.getValue(this.theEditedElement);
            // case 2:
            // return theEditedElement.getFormalParameter();
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * Return the type of the element displayed at the specified row and column.
     * <p>
     * This type will be used to choose an editor and a renderer for each cell of the properties table.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("519d68e8-418a-416a-b12a-47fb56368d77")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: // col 0 is the property key type
            return new DefaultStringNatValue((String) getValue(row, col), false);
        case 1: // col 1 is the property value type
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            case 1:
                DefaultHybridNatValue cell = new DefaultHybridNatValue(getValue(row, col), true, SubstitutionValue.getAllowedMetaclasses(this.theEditedElement), true);
                Collection<NameSpace> typeConstraint = getTypeConstraint(this.theEditedElement.getFormalParameter());
                if (!typeConstraint.isEmpty()) {
                    cell.setElementFilter(new IsSubTypeFilter(typeConstraint));
                }
                return cell;
            // case 2:
            // return new EnumType(TemplateParameter.class);
            default:
                return null;
            }
        default:
            return null;
        }
    }

    /**
     * Set value in the model for the specified row and column.
     * <p>
     * The first column contains the properties names.
     * 
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("dcb51b84-d462-4bca-84eb-2990430f9913")
    @Override
    public void setValueAt(int row, int col, Object value) {
        switch (col) {
        case 0: // Keys cannot be modified
            return;
        case 1: // col 1 is the property value
            switch (row) {
            case 0:
                return; // Header cannot be modified
            case 1:
                SubstitutionValue.setValue(this.theEditedElement, value);
                break;
            // case 2:
            // theEditedElement.setFormalParameter((TemplateParameter) value);
            // break;
            default:
                return;
            }
            break;
        default:
            return;
        }
    }

    /**
     * Get all types that a template substitution value must match.
     * 
     * @param t a template parameter
     * @return all namespaces a substitution must sub type to be allowed.
     */
    @objid ("d93544d8-61d1-40a5-ad11-565eae7e76e9")
    private static Collection<NameSpace> getTypeConstraint(TemplateParameter t) {
        List<NameSpace> l = new ArrayList<>(2);
        if (t == null) {
            return l;
        }
        
        // This is the Modelio way to get type constraint.
        UmlModelElement o = t.getType();
        if (o instanceof NameSpace) {
            l.add((NameSpace) o);
        }
        
        // This is advanced mode for defining type constraint
        // There is at Modelio 3.7 no GUI for setting this.
        for (Generalization g : t.getParent()) {
            l.add(g.getSuperType());
        }
        
        for (InterfaceRealization r : t.getRealized()) {
            l.add(r.getImplemented());
        }
        
        for (ModelTree node : t.getOwnedElement()) {
            if (node instanceof NameSpace) {
                // This is the OMG UML official way of specifying constraint on template parameter.
                // There is at Modelio 3.7 no GUI for setting this.
                for (Generalization g : t.getParent()) {
                    l.add(g.getSuperType());
                }
        
                for (InterfaceRealization r : t.getRealized()) {
                    l.add(r.getImplemented());
                }
            }
        }
        return l;
    }

    /**
     * Get the metaclasses allowed for {@link TemplateParameterSubstitution} on the given {@link TemplateParameter}.
     * <p>
     * By default allow anything that can type an attribute/association/parameter. If the template parameter type is an Interface, accept Interface, Classes and Signals (exceptions classes might be modeled as Signals) .
     * 
     * @param param a TemplateParameter
     * @return the allowed substitutions value metaclasses.
     */
    @objid ("423b2214-81a5-4bfc-8dab-acf2875366c6")
    public static List<Class<? extends MObject>> getSubstitutionsAllowedMetaclasses(TemplateParameter param) {
        if (param == null) {
            return Collections.singletonList(UmlModelElement.class);
        }
        
        UmlModelElement paramType = param.getType();
        if (paramType == null) {
            // By default allow anything that can type an attribute/association/parameter.
            return Collections.singletonList(GeneralClass.class);
        }
        
        if (paramType instanceof Interface) {
            // if type is interface, accept Classes and Signals (exceptions classes might be modeled as Signals)
            return Arrays.asList(Interface.class, org.modelio.metamodel.uml.statik.Class.class, Signal.class);
        }
        return Collections.singletonList(paramType.getMClass().getJavaInterface());
    }

    /**
     * Represents the {@link TemplateParameterSubstitution#getActual() 'Actual'} and {@link TemplateParameterSubstitution#getValue() 'Value'} properties on {@link TemplateParameterSubstitution}.
     */
    @objid ("a95dd9c3-adde-43d6-8213-0a171e651362")
    private static class SubstitutionValue {
        @objid ("93309d27-4497-400f-bc65-10d1db1e9906")
        public static Object getValue(TemplateParameterSubstitution el) {
            UmlModelElement r1 = el.getActual();
            if (r1 != null) {
                return r1;
            }
            return el.getValue();
        }

        @objid ("b92244a9-d22b-4dcd-b838-60c0362a85c9")
        public static void setValue(TemplateParameterSubstitution el, Object value) {
            if (value != null && value.getClass() == String.class) {
                if (el.getActual() != null) {
                    el.setActual(null);
                }
                el.setValue((String) value);
            } else {
                if (!el.getValue().isEmpty()) {
                    el.setValue("");
                }
                el.setActual((UmlModelElement) value);
            
            }
        }

        @objid ("309d09a8-54c9-4da9-9a0f-1ce7f9ee7693")
        public static List<Class<? extends MObject>> getAllowedMetaclasses(TemplateParameterSubstitution el) {
            TemplateParameter param = el.getFormalParameter();
            return getSubstitutionsAllowedMetaclasses(param);
        }

        @objid ("0438028f-e694-4cd2-95fc-17428763651f")
        public SubstitutionValue() {
            super();
        }

    }

    @objid ("6fb6127b-fe6a-4de7-9445-50822bff3bb6")
    private static class IsSubTypeFilter implements IMObjectFilter {
        @objid ("f9a1694d-f602-4e26-b109-32643d97ad4a")
        private Collection<NameSpace> typeConstraint;

        @objid ("b324bb36-20ce-4e8b-86bb-2772862fbb8a")
        public IsSubTypeFilter(Collection<NameSpace> typeConstraint) {
            this.typeConstraint = typeConstraint;
        }

        @objid ("698a63b3-5124-43f8-8dc6-e326110f1d10")
        @Override
        public boolean accept(MObject element) {
            if (!(element instanceof NameSpace)) {
                return false;
            }
            
            NameSpace ns = (NameSpace) element;
            
            for (NameSpace constraint : this.typeConstraint) {
                if (!isSubType(ns, constraint, 200)) {
                    return false;
                }
            }
            return true;
        }

        @objid ("dbee7c87-bc3e-4ec5-b7ca-62ad69f400ed")
        private boolean isSubType(NameSpace ns, NameSpace constraint, int deep) {
            if (ns == null) {
                return false;
            }
            if (ns.equals(constraint)) {
                return true;
            }
            if (deep == 0) {
                return false;
            }
            
            for (Generalization g : ns.getParent()) {
                if (isSubType(g.getSuperType(), constraint, deep - 1)) {
                    return true;
                }
            }
            
            for (InterfaceRealization r : ns.getRealized()) {
                if (isSubType(r.getImplemented(), constraint, deep - 1)) {
                    return true;
                }
            }
            return false;
        }

    }

}

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
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.DefaultElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.hybrid.DefaultHybridNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.platform.model.ui.nattable.viewer.model.AbstractPropertyModel;

/**
 * <i>TemplateBinding</i> data model.
 * <p>
 * This class provides the list of properties for the <i>TemplateBinding</i>
 * metaclass.
 */
@objid ("24d33055-6bbb-4323-bb4d-8c5d5f907cd7")
public class TemplateBindingPropertyModel extends AbstractPropertyModel<TemplateBinding> {
    /**
     * Properties to display for <i>TemplateBinding</i>.
     * <p>
     * This array contains the first column values:
     * <ul>
     * <li>for the first row the value is the table header label (usually the
     * metaclass name)
     * <li>for otheEditedElement rows the values usually match the
     * meta-attributes and roles names of the metaclass
     * </ul>
     */
    @objid ("5f89f47b-3419-4b3c-95cb-4558202fe852")
    private static final String[] PROPERTIES = new String[] { AbstractPropertyModel.PROPERTY_ID,
    			"InstanciatedTemplate" };

    @objid ("4ea4fdfb-5ad0-4dc8-ae7b-176eea985cbd")
    private boolean isOperation;

    @objid ("13e0c15d-400a-4761-9507-4ba8987c4f5d")
    private IMModelServices modelService;

    /**
     * Change the binding substitutions so as it reflects the template
     * parameters on the bound namespace or operations. Deletes the obsolete
     * substitutions and create the missing ones with theEditedElement default
     * values.
     * @param aTemplateBinding the binding to update
     */
    @objid ("c16f92d9-fedd-447f-9241-63fe857e0f22")
    static void updateTemplateBinding(TemplateBinding aTemplateBinding, IMModelServices mmService) {
        List<TemplateParameter> parameters;
        
        Operation op = aTemplateBinding.getInstanciatedTemplateOperation();
        NameSpace ns = aTemplateBinding.getInstanciatedTemplate();
        if (op != null && aTemplateBinding.getBoundOperation() != null) {
            parameters = new ArrayList<>(op.getTemplate());
            aTemplateBinding.setName("->" + op.getName());
        } else if (ns != null) {
            parameters = new ArrayList<>(ns.getTemplate());
            aTemplateBinding.setName("->" + ns.getName());
        } else {
            parameters = new ArrayList<>();
            aTemplateBinding.setName("");
        }
        
        // Clear all obsolete TemplateParameterSubstitution
        for (TemplateParameterSubstitution sub : new ArrayList<>(aTemplateBinding.getParameterSubstitution())) {
            if (!parameters.contains(sub.getFormalParameter())) {
                sub.delete();
            }
        }
        
        // Create missing substitutions
        EList<TemplateParameterSubstitution> substitutions = aTemplateBinding.getParameterSubstitution();
        List<TemplateParameter> substituedParameters = new ArrayList<>(substitutions.size());
        
        for (TemplateParameterSubstitution sub : substitutions) {
            substituedParameters.add(sub.getFormalParameter());
        }
        
        IStandardModelFactory mmFactory = mmService.getModelFactory().getFactory(IStandardModelFactory.class);
        for (TemplateParameter param : parameters) {
            if (!substituedParameters.contains(param)) {
                
                TemplateParameterSubstitution newSub = mmFactory.createTemplateParameterSubstitution();
                newSub.setFormalParameter(param);
                newSub.setName(param.getName());
                newSub.setActual(param.getDefaultType());
                newSub.setValue(param.getDefaultValue());
                aTemplateBinding.getParameterSubstitution().add(newSub);
            }
        }
        
    }

    /**
     * Create a new <i>TemplateBinding</i> data model from an
     * <i>TemplateBinding</i>.
     * @param theEditedElement the edited element.
     * @param modelService the model services
     */
    @objid ("61202dfd-d4f8-44d2-8488-01aa6387d2ef")
    public  TemplateBindingPropertyModel(TemplateBinding theEditedElement, IMModelServices modelService) {
        super(theEditedElement);
        this.modelService = modelService;
        this.isOperation = (theEditedElement.getBoundOperation() != null);
        
    }

    /**
     * The number of columns that the properties table must display.
     * @return the number of columns
     */
    @objid ("2a895fd0-1fa4-4e1b-a6f4-3e7b04f9e23e")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    /**
     * The number of rows that the properties table must display.
     * @return the number of rows
     */
    @objid ("5abcf1c5-ed9d-4599-b4ca-6bf6d10a0123")
    @Override
    public int getRowsNumber() {
        return TemplateBindingPropertyModel.PROPERTIES.length + getTemplateParameters().size();
    }

    @objid ("cd744976-ad7c-41be-bba2-6b471bc29e67")
    private List<TemplateParameter> getTemplateParameters() {
        if (this.isOperation) {
            Operation op = this.theEditedElement.getInstanciatedTemplateOperation();
            if (op != null) {
                return op.getTemplate();
            }
            return Collections.emptyList();
        }
        
        NameSpace ns = this.theEditedElement.getInstanciatedTemplate();
        if (ns != null) {
            return ns.getTemplate();
        }
        return Collections.emptyList();
    }

    /**
     * Return the type of the element displayed at the specified row and column.
     * <p>
     * This type will be used to choose an editor and a renderer for each cell
     * of the properties table.
     * <p>
     * The first column contains the properties names.
     * @param row the row number
     * @param col the column number
     * @return the type of the element corresponding to the row and column
     */
    @objid ("dcbd082d-2e5f-4542-b71b-133fcf5c94ca")
    @Override
    public INatValue getValueAt(int row, int col) {
        switch (col) {
        case 0: 
            // col 0 is the property key
            String val;
            if (row < 2) {
                val = getPropertyI18n(PROPERTIES[row]);
            } else {
                // It is a template substitution row
                val = "  " + getTemplateParameters().get(row - 2).getName() + " = ";
            }
            
            return new DefaultStringNatValue(val, false);
        case 1: 
            // col 1 is the property value 
            switch (row) {
            case 0: // Header
                return new DefaultStringNatValue(getPropertyI18n(AbstractPropertyModel.VALUE_ID), false);
            case 1:
                // instantiated template
                if (this.isOperation) {
                    return new DefaultElementNatValue(
                            this.theEditedElement.getInstanciatedTemplateOperation(), true,
                            Collections.singletonList(Operation.class));
                } else {
                    return new DefaultElementNatValue(
                            this.theEditedElement.getInstanciatedTemplate(), true,
                            Collections.singletonList(NameSpace.class));
                }
        
            default:
                // parameter substitutions
                TemplateParameterSubstitutionPropertyModel subModel = getSubModel(row, col);
                if (subModel != null) {
                    return subModel.getValueAt(1, col);
                } else {
                    TemplateParameter templateParam = getTemplateParameters().get(row - 2);
                    return new DefaultHybridNatValue(
                            getDefaultValue(templateParam), 
                            true,
                            TemplateParameterSubstitutionPropertyModel.getSubstitutionsAllowedMetaclasses(templateParam), 
                            true);
                }
            }
        default:
            return null;
        }
        
    }

    /**
     * Set value in the model for the specified row and column.
     * <p>
     * The first column contains the properties names.
     * @param row the row number.
     * @param col the column number.
     * @param value the value specified by the user.
     */
    @objid ("43e5eaef-697d-4cbd-92e5-c2734329aad2")
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
                if (this.isOperation) {
                    this.theEditedElement.setInstanciatedTemplateOperation((Operation) value);
                } else {
                    this.theEditedElement.setInstanciatedTemplate((NameSpace) value);
                }
        
                updateTemplateBinding(this.theEditedElement, this.modelService);
        
                break;
            default:
                // It is a template substitution
                updateTemplateBinding(this.theEditedElement, this.modelService);
                getSubModel(row, col).setValueAt(1, col, value);
            }
            break;
        default:
            return;
        }
        
    }

    /**
     * Allows delegating some behavior to {@link TemplateParameterSubstitutionPropertyModel}
     * when a {@link TemplateParameterSubstitution} already exists.
     * <p>
     * Calling {@link #updateTemplateBinding(TemplateBinding, IMModelServices)} before this method should
     * ensure it won't return <i>null</i>.
     * @param row the edited row
     * @param col the edited column
     * @return a matching {@link TemplateBindingPropertyModel} or <i>null</i>.
     */
    @objid ("b9849450-534b-4ea0-8566-b4e285930457")
    protected TemplateParameterSubstitutionPropertyModel getSubModel(int row, int col) {
        EList<TemplateParameterSubstitution> substitutions = this.theEditedElement.getParameterSubstitution();
        if (substitutions.size() > (row -2)) {
            TemplateParameterSubstitution v = substitutions.get(row - 2);
            return new TemplateParameterSubstitutionPropertyModel(v);
        } else {
            return null;
        }
        
    }

    @objid ("1a6300ac-8375-4fa2-b955-31409a4c9026")
    private static Object getDefaultValue(TemplateParameter param) {
        UmlModelElement defaultType = param.getDefaultType();
        if (defaultType != null) {
            return defaultType;
        }
        return param.getDefaultValue();
    }

}

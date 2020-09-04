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

package org.modelio.edition.dialogs.dialog.panels.operation.params;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

/**
 * Data model for an operation parameter in the Operation Wizard.
 */
@objid ("77e6695e-24c6-40c4-a802-61e1db5df249")
public class ParameterPropertyModel {
    @objid ("7a363ee5-4747-4c06-bb94-31448bba8260")
     boolean isReturn;

    @objid ("5bea9709-83b7-4316-98e2-9a5c2cd3a16f")
     Parameter parameter;

    /**
     * Default c'tor.
     * @param model
     * the parameter's owner operation model.
     */
    @objid ("d6c3565f-cba8-439b-8264-bff284934f3a")
    public ParameterPropertyModel() {
    }

    @objid ("b3fed7df-5f5d-44c0-9097-80addf7086fe")
    private String getParameterCardinality(Parameter p) {
        final String multiplicityMin = p.getMultiplicityMin();
        final String multiplicityMax = p.getMultiplicityMax();
        
        if (multiplicityMin.equals(multiplicityMax)) {
            return multiplicityMax;
        } else if (multiplicityMax.equals("*")) {
            return "0..*";
        } else {
            return multiplicityMin + ".." + multiplicityMax;
        }
    }

    @objid ("bcc18b92-ce7c-47db-b7cd-939015f98efa")
    public ParameterPropertyModel(Parameter parameter, boolean isReturn) {
        this.parameter = parameter;
        this.isReturn = isReturn;
    }

    @objid ("3813c3a8-f26d-45d8-95c8-8e24e6e06bea")
    public Image getIcon() {
        // FIXME differentiate icons for return / IO parameters
        return this.isReturn ? MetamodelImageService.getIcon(this.parameter.getMClass(), "return") : MetamodelImageService.getIcon(this.parameter.getMClass(), "io");
    }

    @objid ("adf76437-3b8a-4ba8-b270-4b4add2602cd")
    public String getDefaultValue() {
        return this.isReturn ? "" : this.parameter.getDefaultValue();
    }

    @objid ("17f5e3b6-3484-4577-9b16-86a83d457b4f")
    public String getCard() {
        return getParameterCardinality(this.parameter);
    }

    @objid ("945c1f86-5203-482f-aa39-305a89f5c276")
    public PassingMode getPassing() {
        return this.isReturn ? null : this.parameter.getParameterPassing();
    }

    @objid ("17bab82c-1fc3-44f9-8c59-ee270d2359b2")
    public GeneralClass getType() {
        return this.parameter.getType();
    }

    @objid ("337caf57-1f30-4e68-a1bf-d9b031114a00")
    public String getName() {
        return this.isReturn ? EditionDialogs.I18N.getString("OperationEditPanel.rpName") : this.parameter.getName();
    }

    @objid ("b3095046-0909-4607-8c9b-1a017a58fb67")
    public boolean isReturn() {
        return this.isReturn;
    }

    @objid ("23dd47b1-811e-4d60-bcdd-202fbfd78f38")
    public void setName(String value) {
        if (!this.parameter.getName().equals(value)) {
            final ICoreSession session = CoreSession.getSession(this.parameter);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter name")) {
                this.parameter.setName(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("fb2693a8-ffa2-4ed8-89c5-45dd50286ed2")
    public void setDefaultValue(String value) {
        if (!this.parameter.getDefaultValue().equals(value)) {
            final ICoreSession session = CoreSession.getSession(this.parameter);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter value")) {
                this.parameter.setDefaultValue(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("d10684fc-ad39-4837-9f35-a2768fb424b7")
    public void setPassingMode(PassingMode value) {
        if (this.parameter.getParameterPassing() != value) {
            final ICoreSession session = CoreSession.getSession(this.parameter);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter passing mode")) {
                this.parameter.setParameterPassing(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("c043d70f-3db9-4988-b217-658494ae36b0")
    public void setCard(String value) {
        final ICoreSession session = CoreSession.getSession(this.parameter);
        try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter passing mode")) {
            final String[] values = value.split("\\.\\.");
            if ((values.length == 1) && values[0].equals("*")) {
                this.parameter.setMultiplicityMin("0");
                this.parameter.setMultiplicityMax(values[0]);
            } else if (values.length == 1) {
                this.parameter.setMultiplicityMin(values[0]);
                this.parameter.setMultiplicityMax(values[0]);
            } else if (values.length == 2) {
                this.parameter.setMultiplicityMin(values[0]);
                this.parameter.setMultiplicityMax(values[1]);
            }
            t.commit();
        } catch (final Exception e) {
            // Ignore error
        }
    }

    @objid ("389cb65b-3eb6-4b08-a9b5-293ae2848ed4")
    public void setType(Object value) {
        if (!Objects.equals(this.parameter.getType(), value)) {
            final ICoreSession session = CoreSession.getSession(this.parameter);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter type")) {
                this.parameter.setType((GeneralClass) value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("2367eab8-acda-4a18-85f2-7489e1626093")
    public Parameter getParameter() {
        return this.parameter;
    }

}

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

package org.modelio.edition.dialogs.dialog.panels.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.edition.dialogs.dialog.panels.operation.params.ParameterPropertyModel;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.MethodPassingMode;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

@objid ("fd3314f7-aed8-434f-8347-5a1654d896f1")
public class OperationModel implements IOperationPropertyModel {
    @objid ("42ef016b-0d6e-4ab4-bed7-18cbd81e7fc3")
    private Operation operation = null;

    @objid ("e7433068-11cc-4952-9416-9518368060e2")
    public OperationModel(Operation theOperation) {
        this.operation = theOperation;
    }

    @objid ("224e208a-6f7e-49b9-8acb-f1a5e5e175b2")
    @Override
    public String getName() {
        return this.operation != null ? this.operation.getName() : "";
    }

    @objid ("58097b4e-2ad6-4255-9c2b-f7a06975cca6")
    @Override
    public void setName(String name) {
        if (this.operation != null) {
            if (!getName().equals(name)) {
                final ICoreSession session = CoreSession.getSession(this.operation);
        
                try (ITransaction t = session.getTransactionSupport().createTransaction("Update operation Name")) {
                    this.operation.setName(name);
                    t.commit();
                }
            }
        }
    }

    @objid ("6c8e3b1d-0ffb-4c72-afe8-8a4aa6e09d8b")
    @Override
    public MethodPassingMode getPassing() {
        return this.operation.getPassing();
    }

    @objid ("8f75b6f6-d842-45de-a4a7-3a2aa2feb21d")
    @Override
    public VisibilityMode getVisibility() {
        return this.operation.getVisibility();
    }

    @objid ("659fbc5f-1aa6-4071-a171-d2518b1b8eae")
    @Override
    public boolean isAbstract() {
        return this.operation.isIsAbstract();
    }

    @objid ("1aa10df6-7ba7-4e50-99d7-be611513cf05")
    @Override
    public boolean isClass() {
        return this.operation.isIsClass();
    }

    @objid ("d2a783c8-98cc-423f-a4f8-3802e0ac2dc4")
    @Override
    public boolean isFinal() {
        return this.operation.isFinal();
    }

    @objid ("e78d15f0-2ba7-4d85-9148-f140776dd33e")
    @Override
    public void setAbstract(Boolean value) {
        if (isAbstract() != value) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update operation Abstract")) {
                this.operation.setIsAbstract(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("4ce4bbfd-51c4-4db9-af9b-94f849c7cbe9")
    @Override
    public void setClass(Boolean value) {
        if (isClass() != value) {
            final ICoreSession session = CoreSession.getSession(this.operation);
        
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update operation Class")) {
                this.operation.setIsClass(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("27b97735-06af-4280-90eb-f39ad2ede119")
    @Override
    public void setFinal(Boolean value) {
        if (isFinal() != value) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update operation Final")) {
                this.operation.setFinal(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("431c400d-3645-4e3d-9a80-4c7d282cf408")
    @Override
    public void setPassing(MethodPassingMode value) {
        final ICoreSession session = CoreSession.getSession(this.operation);
        try (ITransaction t = session.getTransactionSupport().createTransaction("Update operation Passing")) {
        
            this.operation.setPassing(value);
            t.commit();
        
        } catch (final Exception e) {
            // Ignore error
        }
    }

    @objid ("b3da4c90-1457-4af9-aaef-4ca821d0162d")
    @Override
    public void setVisibility(VisibilityMode value) {
        final ICoreSession session = CoreSession.getSession(this.operation);
        try (ITransaction t = session.getTransactionSupport().createTransaction("Update operation Visibility")) {
            this.operation.setVisibility(value);
            t.commit();
        } catch (final Exception e) {
            // Ignore error
        }
    }

    @objid ("08e520a9-393d-4be8-8b71-86500dbad3cb")
    @Override
    public boolean isModifiable() {
        return this.operation.isModifiable();
    }

    @objid ("2ea0c859-d9c8-4dfe-84fd-021e848f2c80")
    @Override
    public int getIOParameterSize() {
        return this.operation.getIO().size();
    }

    @objid ("5765bede-11b7-4a74-bd37-ece1aebc6d9d")
    @Override
    public String getParameterMultiplicityMin(int index) {
        return this.operation.getIO().get(index).getMultiplicityMin();
    }

    @objid ("e038df24-9038-4e59-b59c-e43cb7faad2f")
    @Override
    public String getParameterMultiplicityMax(int index) {
        return this.operation.getIO().get(index).getMultiplicityMax();
    }

    @objid ("36048142-e762-419f-8345-da0adca48025")
    @Override
    public String getParameterDefaultValue(int index) {
        return this.operation.getIO().get(index).getDefaultValue();
    }

    @objid ("0809a778-8d16-4bf2-a0e1-0de5e5e0ef22")
    @Override
    public String getParameterName(int index) {
        return this.operation.getIO().get(index).getName();
    }

    @objid ("ba402ef7-800b-4ea4-8ee0-37dff5c86b61")
    @Override
    public PassingMode getParameterPassingMode(int index) {
        return this.operation.getIO().get(index).getParameterPassing();
    }

    @objid ("bbd60b83-4e82-43f6-acdb-64f9d89e0e87")
    @Override
    public GeneralClass getParameterType(int index) {
        return this.operation.getIO().get(index).getType();
    }

    @objid ("b6bbfb93-a6a6-4227-93f8-5cd23fe3c816")
    @Override
    public void setParameterDefaultValue(int index, String value) {
        final Parameter parameter = this.operation.getIO().get(index);
        if (!parameter.getDefaultValue().equals(value)) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter Default value")) {
                parameter.setDefaultValue(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        
        }
    }

    @objid ("dc5354c6-e5b0-4869-ab78-313384b2238b")
    @Override
    public void setParameterMultiplicityMax(int index, String value) {
        final Parameter parameter = this.operation.getIO().get(index);
        if (!parameter.getMultiplicityMax().equals(value)) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter multiplicity max")) {
                parameter.setMultiplicityMax(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("7e93349a-7ffa-4dd1-ac30-5025ad7268b4")
    @Override
    public void setParameterMultiplicityMin(int index, String value) {
        final Parameter parameter = this.operation.getIO().get(index);
        if (!parameter.getMultiplicityMin().equals(value)) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter multiplicity min")) {
                parameter.setMultiplicityMin(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("515b1c9c-e7b7-46cc-93a5-d010cc79348a")
    @Override
    public void setParameterName(int index, String value) {
        final Parameter parameter = this.operation.getIO().get(index);
        if (!parameter.getName().equals(value)) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter name")) {
                parameter.setName(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("8863f8fd-b3fa-4536-a6d1-e81a147c6166")
    @Override
    public void setParameterPassingMode(int index, PassingMode value) {
        final Parameter parameter = this.operation.getIO().get(index);
        if (parameter.getParameterPassing() != value) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter passing mode")) {
                parameter.setParameterPassing(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("3e7efc42-a9ad-4561-b216-54bced32a53f")
    @Override
    public void setParameterType(int index, final GeneralClass newType) {
        final Parameter parameter = this.operation.getIO().get(index);
        final GeneralClass oldType = parameter.getType();
        boolean doSetType;
        if (oldType == null) {
            doSetType = newType != null;
        } else {
            doSetType = !oldType.equals(newType);
        }
        if (doSetType) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update parameter type")) {
                parameter.setType(newType);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("39129fb6-0c19-4c46-ae45-5b7bc70211bf")
    @Override
    public boolean isParameterModifiable(int index) {
        final Parameter parameter = this.operation.getIO().get(index);
        return parameter.isModifiable();
    }

    @objid ("a9d93d3e-7a28-4f58-bdc1-b91d7f1f96a9")
    @Override
    public boolean isReturnParameterModifiable() {
        return this.operation.isModifiable();
    }

    @objid ("f5d6cbfb-6b3f-47b5-b7c1-193e9ea2775e")
    @Override
    public String getReturnParameterMultiplicityMax() {
        final Parameter returnParameter = this.operation.getReturn();
        if (returnParameter != null)
            return returnParameter.getMultiplicityMax();
        else
            return "";
    }

    @objid ("e6a7016e-6848-46b4-9ac9-1429459ec588")
    @Override
    public String getReturnParameterMultiplicityMin() {
        final Parameter returnParameter = this.operation.getReturn();
        if (returnParameter != null)
            return returnParameter.getMultiplicityMin();
        else
            return "";
    }

    @objid ("9739264e-9ab7-4589-b8e3-61e05bd55d22")
    @Override
    public GeneralClass getReturnParameterType() {
        final Parameter returnParameter = this.operation.getReturn();
        if (returnParameter != null)
            return returnParameter.getType();
        else
            return null;
    }

    @objid ("4ef3ea5e-bbcf-4b50-8e00-2a9773b468a2")
    @Override
    public void setReturnParameterMultiplicityMax(String value) {
        final Parameter returnParameter = this.operation.getReturn();
        if (!returnParameter.getMultiplicityMax().equals(value)) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update return parameter multiplicity max")) {
                returnParameter.setMultiplicityMax(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("afe7dcce-55d6-48a1-9d62-10bbb2535de7")
    @Override
    public void setReturnParameterMultiplicityMin(String value) {
        final Parameter returnParameter = this.operation.getReturn();
        if (!returnParameter.getMultiplicityMin().equals(value)) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Update return parameter multiplicity min")) {
                returnParameter.setMultiplicityMin(value);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("f9ff1ab5-540f-4fb1-8120-6bca2534bb74")
    @Override
    public void setReturnParameterType(GeneralClass type) {
        final Parameter returnParameter = this.operation.getReturn();
        final ICoreSession session = CoreSession.getSession(this.operation);
        
        try (ITransaction t = session.getTransactionSupport().createTransaction("Set return parameter")) {
            if (type == null) {
                if (returnParameter != null) {
                    returnParameter.delete();
                }
            } else {
                if (returnParameter == null) {
                    IStandardModelFactory factory = MTools.get(session).getModelFactory(IStandardModelFactory.class);
                    factory.createReturnParameter("", type, this.operation);
                } else {
                    returnParameter.setType(type);
                }
            }
            t.commit();
        }
    }

    @objid ("c6266378-be65-4334-9785-9526da044744")
    @Override
    public void addParameter() {
        final ICoreSession session = CoreSession.getSession(this.operation);
        try (ITransaction t = session.getTransactionSupport().createTransaction("Add parameter")) {
            IStandardModelFactory factory = MTools.get(session).getModelFactory(IStandardModelFactory.class);
            final Parameter parameter = factory.createParameter();
            parameter.setName("p" + Integer.toString(this.operation.getIO().size() + 1));
            this.operation.getIO().add(parameter);
        
            t.commit();
        } catch (final Exception e) {
            // Ignore error
        }
    }

    @objid ("2a6ba0a4-ee16-4084-a479-c6d085ca1c7f")
    @Override
    public void removeParameter(List<Parameter> parameters) {
        final ICoreSession session = CoreSession.getSession(this.operation);
        try (ITransaction t = session.getTransactionSupport().createTransaction("Add parameter")) {
            for (final Parameter parameter : parameters) {
                parameter.delete();
            }
            t.commit();
        } catch (final Exception e) {
            // Ignore error
        }
    }

    @objid ("3415125c-9107-4362-82eb-bef35ff4b6d3")
    @Override
    public Parameter getParameter(int index) {
        return this.operation.getIO().get(index);
    }

    /**
     * MUST be called within a Transaction
     */
    @objid ("998601e9-5574-4065-a67a-ff159d28fe9b")
    private boolean moveOneParameterDown(Parameter p) {
        final int pos = this.operation.getIO().indexOf(p);
        if ((pos == -1) || (pos == (this.operation.getIO().size() - 1)))
            return false;
        
        // Important note:
        // Collections.swap(parameters, pos, pos - 1) CANNOT be used directly on
        // the list returned by this.operation.getIO()
        // as it is a SmList whose redefinition of List<?>#set() use semantic
        // accessors like setDepVal
        // is not compatible with the Collections.swap() algorithm
        final List<Parameter> parameters = new ArrayList<>(this.operation.getIO());
        Collections.swap(parameters, pos, pos + 1);
        this.operation.getIO().clear();
        this.operation.getIO().addAll(parameters);
        return true;
    }

    @objid ("bcef4e5d-b311-4a1a-9138-fdc71c24f1a4")
    @Override
    public void moveParametersDown(List<Parameter> parametersToMove) {
        final ICoreSession session = CoreSession.getSession(this.operation);
        try (ITransaction t = session.getTransactionSupport().createTransaction("Move parameters down")) {
            for (int i = parametersToMove.size() - 1; i > -1; i--) {
                moveOneParameterDown(parametersToMove.get(i));
            }
            t.commit();
        }
    }

    /**
     * MUST be called within a Transaction
     */
    @objid ("3f0cfc34-395a-48c4-8776-29bbeeca1e1e")
    private boolean moveOneParameterUp(Parameter p) {
        final int pos = this.operation.getIO().indexOf(p);
        if ((pos == -1) || (pos == 0))
            return false;
        
        // Important note:
        // Collections.swap(parameters, pos, pos - 1) CANNOT be used directly on
        // the list returned by this.operation.getIO()
        // as it is a SmList whose redefinition of List<?>#set() use semantic
        // accessors like setDepVal
        // is not compatible with the Collections.swap() algorithm
        final List<Parameter> parameters = new ArrayList<>(this.operation.getIO());
        Collections.swap(parameters, pos, pos - 1);
        this.operation.getIO().clear();
        this.operation.getIO().addAll(parameters);
        return true;
    }

    @objid ("fd25c5b0-ddfc-4326-87f2-e0311c123a94")
    @Override
    public void moveParametersUp(List<Parameter> parametersToMove) {
        final ICoreSession session = CoreSession.getSession(this.operation);
        try (ITransaction t = session.getTransactionSupport().createTransaction("Move parameters up")) {
            for (int i = 0; i < parametersToMove.size(); i++) {
                moveOneParameterUp(parametersToMove.get(i));
            }
            t.commit();
        }
    }

    @objid ("8a7a275b-65d8-4965-bd86-e947c50f97a6")
    private int getIndexUp(Parameter parameter, List<Parameter> list) {
        int index = list.indexOf(parameter);
        if (index < 1)
            return -1;
        index--;
        return index;
    }

    @objid ("90130ff2-ae83-424b-834f-74de270f9230")
    private int getIndexDown(Parameter parameter, List<Parameter> list) {
        int index = list.indexOf(parameter);
        
        if (index == -1)
            return -1;
        
        index++;
        
        if (index >= list.size())
            return -1;
        return index;
    }

    @objid ("c03bbf6e-26b7-4b01-9007-e9d1b339a538")
    @Override
    public int getParameterIndex(Parameter parameter) {
        return this.operation.getIO().indexOf(parameter);
    }

    @objid ("1487d644-930e-4791-9400-49dd57ca29b2")
    @Override
    public Operation getOperation() {
        return this.operation;
    }

    @objid ("63014859-875d-402e-b09a-f6c874f117fa")
    @Override
    public Parameter getReturnParameter() {
        return this.operation.getReturn();
    }

    @objid ("26f8d933-c41c-488d-931c-cca25774e42d")
    @Override
    public void setOperationType(String type) {
        // Avoid useless transactions
        if (type.equals(this.getOperationType()))
            return;
        
        final ICoreSession session = CoreSession.getSession(this.operation);
        try (ITransaction t = session.getTransactionSupport().createTransaction("Add parameter")) {
            if (type.equals("Constructor")) {
                if (this.operation.isStereotyped("ModelerModule", "destroy")) {
                    this.operation.removeStereotypes("ModelerModule", "destroy");
                }
                if (!this.operation.isStereotyped("ModelerModule", "create")) {
        
                    this.operation.addStereotype("ModelerModule", "create");
                }
            } else if (type.equals("Destructor")) {
                if (this.operation.isStereotyped("ModelerModule", "create")) {
                    this.operation.removeStereotypes("ModelerModule", "create");
                }
                if (!this.operation.isStereotyped("ModelerModule", "destroy")) {
                    this.operation.addStereotype("ModelerModule", "destroy");
                }
            } else {
                if (this.operation.isStereotyped("ModelerModule", "destroy")) {
                    this.operation.removeStereotypes("ModelerModule", "destroy");
                }
                if (this.operation.isStereotyped("ModelerModule", "create")) {
                    this.operation.removeStereotypes("ModelerModule", "create");
                }
            }
            t.commit();
        } catch (final ExtensionNotFoundException e) {
            EditionDialogs.LOG.error(e);
        }
    }

    @objid ("1c6e9b24-5b98-4f56-9032-b6998d4aec3e")
    @Override
    public String getOperationType() {
        if (this.operation.isStereotyped("ModelerModule", "create"))
            return "Constructor";
        else if (this.operation.isStereotyped("ModelerModule", "destroy"))
            return "Destructor";
        else
            return "Operation";
    }

    @objid ("953c69ca-a84e-48cb-970c-3f232b17347e")
    @Override
    public void addReturnParameter() {
        if (this.operation.getReturn() == null) {
            final ICoreSession session = CoreSession.getSession(this.operation);
            try (ITransaction t = session.getTransactionSupport().createTransaction("Add parameter")) {
                IStandardModelFactory factory = MTools.get(session).getModelFactory(IStandardModelFactory.class);
                final Parameter parameter = factory.createParameter();
                parameter.setName("p" + Integer.toString(this.operation.getIO().size() + 1));
                this.operation.setReturn(parameter);
                t.commit();
            } catch (final Exception e) {
                // Ignore error
            }
        }
    }

    @objid ("24481ec3-b763-4c78-92ac-f123471a8d50")
    @Override
    public List<ParameterPropertyModel> getParameters() {
        List<ParameterPropertyModel> results = new ArrayList<>();
        
        Parameter returnParameter = this.operation.getReturn();
        if (returnParameter != null) {
            results.add(new ParameterPropertyModel(returnParameter, true));
        }
        
        for (Parameter p : this.operation.getIO()) {
            results.add(new ParameterPropertyModel(p, false));
        }
        return results;
    }

}

/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.generalization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Generalization} with << UML2GeneralizationSet >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6f03c25c-fcc3-4bd8-8f86-1f1194db1551")
public class UML2GeneralizationSet {
    @objid ("f27ced33-453c-4baf-8637-85657c4d9143")
    public static final String STEREOTYPE_NAME = "UML2GeneralizationSet";

    @objid ("2658f4b0-0432-4d6a-8a0f-b12898055437")
    public static final String ID_TAGTYPE = "Id";

    /**
     * The underlying {@link Generalization} represented by this proxy, never null.
     */
    @objid ("b4a37219-4ebc-4646-a261-b77ba306976a")
    protected final Generalization elt;

    /**
     * Tells whether a {@link UML2GeneralizationSet proxy} can be instantiated from a {@link MObject} checking it is a {@link Generalization} stereotyped << UML2GeneralizationSet >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4c63df51-c479-4777-a4fc-756edd9db06b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Generalization) && ((Generalization) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2GeneralizationSet.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Generalization} stereotyped << UML2GeneralizationSet >> then instantiate a {@link UML2GeneralizationSet} proxy.
     * 
     * @return a {@link UML2GeneralizationSet} proxy on the created {@link Generalization}.
     */
    @objid ("22492d50-a7e4-4f30-8a62-2807ef91097c")
    public static UML2GeneralizationSet create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Generalization");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2GeneralizationSet.STEREOTYPE_NAME);
        return UML2GeneralizationSet.instantiate((Generalization)e);
    }

    /**
     * Tries to instantiate a {@link UML2GeneralizationSet} proxy from a {@link Generalization} stereotyped << UML2GeneralizationSet >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Generalization
     * @return a {@link UML2GeneralizationSet} proxy or <i>null</i>.
     */
    @objid ("7c46e92f-91f9-42c6-b754-0b1f4da4bc17")
    public static UML2GeneralizationSet instantiate(Generalization obj) {
        return UML2GeneralizationSet.canInstantiate(obj) ? new UML2GeneralizationSet(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2GeneralizationSet} proxy from a {@link Generalization} stereotyped << UML2GeneralizationSet >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Generalization}
     * @return a {@link UML2GeneralizationSet} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bcf113b1-5e29-4dac-a467-af4aa373a98c")
    public static UML2GeneralizationSet safeInstantiate(Generalization obj) throws IllegalArgumentException {
        if (UML2GeneralizationSet.canInstantiate(obj))
        	return new UML2GeneralizationSet(obj);
        else
        	throw new IllegalArgumentException("UML2GeneralizationSet: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9e628305-ff01-4630-890f-05ca7a03e895")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2GeneralizationSet other = (UML2GeneralizationSet) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Generalization}. 
     * @return the Generalization represented by this proxy, never null.
     */
    @objid ("9582492e-595d-4f7f-bdcb-7707b4028b52")
    public Generalization getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Id'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("45efd6e8-b2aa-47b0-bce2-633fb972950a")
    public String getId() {
        return this.elt.getTagValue(UML2GeneralizationSet.MdaTypes.ID_TAGTYPE_ELT);
    }

    @objid ("205e29f1-7f84-43a6-8ae2-8445c835a1ef")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'Id'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("f61dae87-f83a-467c-b8ba-1211e4947503")
    public void setId(String value) {
        this.elt.putTagValue(UML2GeneralizationSet.MdaTypes.ID_TAGTYPE_ELT, value);
    }

    @objid ("0aba7413-9a69-4045-b796-f6ddd3e96123")
    protected  UML2GeneralizationSet(Generalization elt) {
        this.elt = elt;
    }

    @objid ("fd725dfe-dab3-4c76-a9dc-aaa1a63b1b0b")
    public static final class MdaTypes {
        @objid ("ad18cf13-ba13-4fa5-b029-f98b6ae974e6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("205ca41f-973f-46f8-b025-13418c6f0b71")
        public static TagType ID_TAGTYPE_ELT;

        @objid ("e63388df-0dc3-410c-86d5-0c8c4ac753f2")
        private static Stereotype MDAASSOCDEP;

        @objid ("7cc181b6-b94d-4894-84a5-6887ad980158")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c615368e-2c6d-45fa-9965-6feabea6c737")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "40400bbd-0b5d-11df-8680-001302895b2b");
            ID_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "c2c549ec-26ab-11df-ac88-001302895b2b");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
            
        }

	static {
        		if(ModelerModuleModule.getInstance() != null) {
        			init(ModelerModuleModule.getInstance().getModuleContext());
        		}
        	}
        
    }

}

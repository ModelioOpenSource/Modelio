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
package org.modelio.module.modelermodule.api.default_.standard.usecasedependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link UseCaseDependency} with << extend >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5babb04d-9057-43e4-9e92-b0706d209ff8")
public class Extend {
    @objid ("7a1887e9-5934-45ce-a401-816506441a6e")
    public static final String STEREOTYPE_NAME = "extend";

    /**
     * The underlying {@link UseCaseDependency} represented by this proxy, never null.
     */
    @objid ("47daf328-746c-4a07-ad15-6e14a02ca59b")
    protected final UseCaseDependency elt;

    /**
     * Tells whether a {@link Extend proxy} can be instantiated from a {@link MObject} checking it is a {@link UseCaseDependency} stereotyped << extend >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9cec84c3-6185-4223-b414-511726a55b1a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UseCaseDependency) && ((UseCaseDependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Extend.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UseCaseDependency} stereotyped << extend >> then instantiate a {@link Extend} proxy.
     * 
     * @return a {@link Extend} proxy on the created {@link UseCaseDependency}.
     */
    @objid ("60c35ca6-c102-4a70-b6a5-7f7a165e9575")
    public static Extend create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.UseCaseDependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Extend.STEREOTYPE_NAME);
        return Extend.instantiate((UseCaseDependency)e);
    }

    /**
     * Tries to instantiate a {@link Extend} proxy from a {@link UseCaseDependency} stereotyped << extend >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UseCaseDependency
     * @return a {@link Extend} proxy or <i>null</i>.
     */
    @objid ("c7173543-653b-4b18-9174-fdd1e7b98a8e")
    public static Extend instantiate(UseCaseDependency obj) {
        return Extend.canInstantiate(obj) ? new Extend(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Extend} proxy from a {@link UseCaseDependency} stereotyped << extend >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UseCaseDependency}
     * @return a {@link Extend} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("54985cad-516c-489c-9ade-277a3f07499f")
    public static Extend safeInstantiate(UseCaseDependency obj) throws IllegalArgumentException {
        if (Extend.canInstantiate(obj))
        	return new Extend(obj);
        else
        	throw new IllegalArgumentException("Extend: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4852e5b9-4fdc-45c4-be18-22ba185e7901")
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
        Extend other = (Extend) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UseCaseDependency}. 
     * @return the UseCaseDependency represented by this proxy, never null.
     */
    @objid ("b569c851-b95c-4c70-b499-c5c7a988a7d5")
    public UseCaseDependency getElement() {
        return this.elt;
    }

    @objid ("d598b3dc-eaea-4e98-85cf-2393e8d63fa5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("a7c556a0-dbb3-4980-a53f-d8d689d80cb1")
    protected  Extend(UseCaseDependency elt) {
        this.elt = elt;
    }

    @objid ("c8564055-bb69-4426-9956-d7b941a73e96")
    public static final class MdaTypes {
        @objid ("4db2bf19-9e34-48a5-af03-f46f09aa6746")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a611c8bf-55a2-49b3-b38a-1770e7c95989")
        private static Stereotype MDAASSOCDEP;

        @objid ("74b66e65-d1cb-4623-a826-1c1de0ac8390")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("12453c54-f2f1-4f63-b4b4-b82e4558f0ea")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c48-0000-000000000000");
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

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
package org.modelio.module.modelermodule.api.mda.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << MDAAssocDep >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Used to mark Dependencies representing an emulated association in a MDA profiled model.</i></p>
 */
@objid ("ff4c64b9-ac0c-4cbb-b591-dd6886604c51")
public class MDAAssocDep {
    @objid ("b60856fb-e81b-4212-ad4f-f3afdd557180")
    public static final String STEREOTYPE_NAME = "MDAAssocDep";

    @objid ("f7cc0bbf-0362-466f-9aef-e01d21ddcf5c")
    public static final String ROLE_TAGTYPE = "role";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7e7a7a95-f994-46fe-8525-20eddf82506b")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MDAAssocDep proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << MDAAssocDep >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("fe995516-ab52-48b3-a6e3-b8d69377e300")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, MDAAssocDep.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << MDAAssocDep >> then instantiate a {@link MDAAssocDep} proxy.
     * 
     * @return a {@link MDAAssocDep} proxy on the created {@link Dependency}.
     */
    @objid ("5bccf434-7b1a-491b-a8e9-1ef984120ec4")
    public static MDAAssocDep create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, MDAAssocDep.STEREOTYPE_NAME);
        return MDAAssocDep.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link MDAAssocDep} proxy from a {@link Dependency} stereotyped << MDAAssocDep >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link MDAAssocDep} proxy or <i>null</i>.
     */
    @objid ("d3f321ad-c147-4a89-9ac5-cc40d5a5c956")
    public static MDAAssocDep instantiate(Dependency obj) {
        return MDAAssocDep.canInstantiate(obj) ? new MDAAssocDep(obj) : null;
    }

    /**
     * Tries to instantiate a {@link MDAAssocDep} proxy from a {@link Dependency} stereotyped << MDAAssocDep >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link MDAAssocDep} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0b7a6855-2b50-498a-80a3-20b93ce5b9e8")
    public static MDAAssocDep safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (MDAAssocDep.canInstantiate(obj))
        	return new MDAAssocDep(obj);
        else
        	throw new IllegalArgumentException("MDAAssocDep: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cf130b1f-b1e3-4d47-8e7e-4019e9abc4a7")
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
        MDAAssocDep other = (MDAAssocDep) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("ce9140d1-2f36-410e-8302-c6fe1d9e8092")
    public Dependency getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'role'
     * <p>Property description:
     * <br/><i>Identifies the MDA role supported by the stereotyped Dependency</i></p>
     */
    @objid ("401bee90-1b30-48f3-ae31-242b4e80d183")
    public String getRole() {
        return this.elt.getTagValue(MDAAssocDep.MdaTypes.ROLE_TAGTYPE_ELT);
    }

    @objid ("e353636b-9eb1-41ae-8ac8-37fcb20100b6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    /**
     * Setter for string property 'role'
     * <p>Property description:
     * <br/><i>Identifies the MDA role supported by the stereotyped Dependency</i></p>
     */
    @objid ("bb44e3ac-c336-408b-b25a-c360310e7863")
    public void setRole(String value) {
        this.elt.putTagValue(MDAAssocDep.MdaTypes.ROLE_TAGTYPE_ELT, value);
    }

    @objid ("d4a76921-a8a5-411b-8bcb-0437c5a6922a")
    protected  MDAAssocDep(Dependency elt) {
        this.elt = elt;
    }

    @objid ("6d7dd74b-8894-4e3b-9fbc-53ed8d225760")
    public static final class MdaTypes {
        @objid ("cf1d28ec-356c-44a2-a6f2-9673fafe1ecf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6037c535-e3de-4fe5-9103-49fea65a2029")
        public static TagType ROLE_TAGTYPE_ELT;

        @objid ("6c4394bb-05e9-45e6-9e47-52f16515e2c1")
        private static Stereotype MDAASSOCDEP;

        @objid ("e7ea34ff-67ef-4b72-8bac-95516273a425")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("25a076e4-1e4c-4053-b483-9206d585ffd3")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            ROLE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
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

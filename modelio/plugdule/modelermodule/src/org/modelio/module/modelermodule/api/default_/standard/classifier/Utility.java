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
package org.modelio.module.modelermodule.api.default_.standard.classifier;

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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Classifier} with << utility >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("361f955a-46bb-4174-ae99-420074e6c928")
public class Utility {
    @objid ("f4864ab2-dc6c-4a8b-a241-0c9956b905ca")
    public static final String STEREOTYPE_NAME = "utility";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("d02310b0-7af6-4acd-850e-4bd894a6a8cb")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Utility proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << utility >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("934e1b92-9a61-4c8f-a878-d6b8d81bdf4e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Utility.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << utility >> then instantiate a {@link Utility} proxy.
     * 
     * @return a {@link Utility} proxy on the created {@link Classifier}.
     */
    @objid ("20cbc1ef-7667-4030-8a67-a98e3b5ecd2f")
    public static Utility create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Utility.STEREOTYPE_NAME);
        return Utility.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Utility} proxy from a {@link Classifier} stereotyped << utility >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Utility} proxy or <i>null</i>.
     */
    @objid ("13f9cf74-8658-4d43-8152-4c19142b369e")
    public static Utility instantiate(Classifier obj) {
        return Utility.canInstantiate(obj) ? new Utility(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Utility} proxy from a {@link Classifier} stereotyped << utility >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Classifier}
     * @return a {@link Utility} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("47563dc5-f0d9-4d3d-84e6-5a1dc8ba79cf")
    public static Utility safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Utility.canInstantiate(obj))
        	return new Utility(obj);
        else
        	throw new IllegalArgumentException("Utility: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("420a4a91-a1ba-4acf-a8b1-f372375f0e7d")
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
        Utility other = (Utility) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("eb886b70-ad6e-4778-b65a-6bdbef03eda1")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("4ccfb97e-03b8-425b-9ee1-82e7a0a1663a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("8168d84a-c8ec-4dbb-9619-9596705ce8d3")
    protected  Utility(Classifier elt) {
        this.elt = elt;
    }

    @objid ("0a6fc122-40b5-4691-a722-7d1756955c7a")
    public static final class MdaTypes {
        @objid ("a22c42c9-3280-4845-bb48-ab067edb2edb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ae99d286-9b13-43cf-b68b-86772cf33883")
        private static Stereotype MDAASSOCDEP;

        @objid ("fe9ff01d-8aec-4427-bdc0-c03b7ba67946")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1f67aacb-11c7-4c06-81ea-6e31026ea839")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01bf-0000-000000000000");
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

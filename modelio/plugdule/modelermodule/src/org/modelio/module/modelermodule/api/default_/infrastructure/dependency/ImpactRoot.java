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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << impact_root >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3ffb8a66-6e57-4ac5-9cfc-a8913d48cb7a")
public class ImpactRoot {
    @objid ("70c9812f-8771-4a5e-9030-655a50938540")
    public static final String STEREOTYPE_NAME = "impact_root";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("71aebd97-63b1-4ec2-9198-179d6c8ec8fd")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactRoot proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_root >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("89513040-1e7f-406f-b11d-0c1f7b1559c6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactRoot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_root >> then instantiate a {@link ImpactRoot} proxy.
     * 
     * @return a {@link ImpactRoot} proxy on the created {@link Dependency}.
     */
    @objid ("c1c9a0bf-c262-464f-a834-7663c18200aa")
    public static ImpactRoot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactRoot.STEREOTYPE_NAME);
        return ImpactRoot.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactRoot} proxy from a {@link Dependency} stereotyped << impact_root >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactRoot} proxy or <i>null</i>.
     */
    @objid ("597a8d37-aa5f-4911-b445-5e7ac366ce0f")
    public static ImpactRoot instantiate(Dependency obj) {
        return ImpactRoot.canInstantiate(obj) ? new ImpactRoot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactRoot} proxy from a {@link Dependency} stereotyped << impact_root >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactRoot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("68a102b7-14b0-4674-bef3-94133bc690c9")
    public static ImpactRoot safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactRoot.canInstantiate(obj))
        	return new ImpactRoot(obj);
        else
        	throw new IllegalArgumentException("ImpactRoot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("93ebe1e7-b282-4e2e-92e9-4898252f9ca7")
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
        ImpactRoot other = (ImpactRoot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("7153e623-abb4-4fa8-98ea-d2827074cc84")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("f56c558b-fc8b-4722-ab51-908ecf3752d0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("aab3f2ae-be4e-44e1-a9dd-5abdcea7cf50")
    protected  ImpactRoot(Dependency elt) {
        this.elt = elt;
    }

    @objid ("40cc123a-c9ad-4e12-9d54-e544e33ec0f4")
    public static final class MdaTypes {
        @objid ("c761e67b-b115-462b-95d5-1d3df9b7e177")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("86d84e68-919e-409b-80bc-8944009e6dc5")
        private static Stereotype MDAASSOCDEP;

        @objid ("afb83a07-2e5a-469e-b648-2b9b02b6c69f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0f22f699-1cbe-453b-81c9-53990f5fbd07")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec2468-0000-0ac1-0000-000000000000");
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

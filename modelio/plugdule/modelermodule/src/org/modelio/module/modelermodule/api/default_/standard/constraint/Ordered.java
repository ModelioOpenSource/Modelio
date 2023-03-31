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
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << ordered >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42a643e7-92ca-450c-bc7b-c44d1f137341")
public class Ordered {
    @objid ("77e03772-2c88-43f4-a4ac-0699e4f13072")
    public static final String STEREOTYPE_NAME = "ordered";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("dd76d1df-c9b3-46d2-a229-93a19ac0c9b1")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Ordered proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << ordered >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("52abfdd5-1957-4e3d-90d5-05864ea11f7a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Ordered.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << ordered >> then instantiate a {@link Ordered} proxy.
     * 
     * @return a {@link Ordered} proxy on the created {@link Constraint}.
     */
    @objid ("1daefa14-3f16-4533-84c2-c8e8571cd633")
    public static Ordered create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Ordered.STEREOTYPE_NAME);
        return Ordered.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Ordered} proxy from a {@link Constraint} stereotyped << ordered >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Ordered} proxy or <i>null</i>.
     */
    @objid ("2459b5fa-c327-4ad7-9088-3a5b1ef644f7")
    public static Ordered instantiate(Constraint obj) {
        return Ordered.canInstantiate(obj) ? new Ordered(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Ordered} proxy from a {@link Constraint} stereotyped << ordered >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Ordered} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("12733f89-276d-4a5f-b08f-b4e8fabc088c")
    public static Ordered safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Ordered.canInstantiate(obj))
        	return new Ordered(obj);
        else
        	throw new IllegalArgumentException("Ordered: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("78a50ba7-9139-41fd-a8f2-ff3f92599318")
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
        Ordered other = (Ordered) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("5fca25fa-1282-4822-8b1e-f577b6a42432")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("7388ffd2-faa2-4431-b8bd-74cea69e3f39")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("776961e8-d1e9-4fd1-ac29-abde494feac0")
    protected  Ordered(Constraint elt) {
        this.elt = elt;
    }

    @objid ("ce113c67-b988-43ce-b615-4a87a781f4e7")
    public static final class MdaTypes {
        @objid ("57203d85-6c9f-4b37-8cd0-bfccbc1a9c0f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f1d2a5dd-c94f-4b1b-9338-2d13fdcea82a")
        private static Stereotype MDAASSOCDEP;

        @objid ("627201a9-89ce-474a-a20a-003d07284fa9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c61a7595-69ba-44aa-a17e-c8fde7469d24")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00540a84-0000-0003-0000-000000000000");
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

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
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << metamodel >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a997496b-c74c-4bae-8064-327d1bfe98c0")
public class Metamodel {
    @objid ("3c07f2a1-fe9b-4b4d-8284-81d6ad3bf417")
    public static final String STEREOTYPE_NAME = "metamodel";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("ec877685-df70-49d3-84ea-0f235519ebbf")
    protected final Package elt;

    /**
     * Tells whether a {@link Metamodel proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << metamodel >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("4330fe9b-9f90-47cb-adc9-35968f8bce1d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Metamodel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << metamodel >> then instantiate a {@link Metamodel} proxy.
     * 
     * @return a {@link Metamodel} proxy on the created {@link Package}.
     */
    @objid ("a0656a3f-eba7-461c-afa5-e309ba01b416")
    public static Metamodel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Metamodel.STEREOTYPE_NAME);
        return Metamodel.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Metamodel} proxy from a {@link Package} stereotyped << metamodel >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Metamodel} proxy or <i>null</i>.
     */
    @objid ("eb6fbc1d-9b7d-4e90-a1f1-aea66c4c2155")
    public static Metamodel instantiate(Package obj) {
        return Metamodel.canInstantiate(obj) ? new Metamodel(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Metamodel} proxy from a {@link Package} stereotyped << metamodel >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Metamodel} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("533c3465-439e-4342-94da-22a327db710f")
    public static Metamodel safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Metamodel.canInstantiate(obj))
        	return new Metamodel(obj);
        else
        	throw new IllegalArgumentException("Metamodel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8cc9d6e9-b921-4f67-b0c0-e48917a35807")
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
        Metamodel other = (Metamodel) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("3347eba5-6a67-4fd5-a272-8c921a0324bf")
    public Package getElement() {
        return this.elt;
    }

    @objid ("99272620-0b7b-4aff-b496-e834b7e9e007")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("8598422d-4bbe-4b8d-a8d8-68a0f2b64b3a")
    protected  Metamodel(Package elt) {
        this.elt = elt;
    }

    @objid ("272fd8c2-9080-44cd-9066-fd064321d028")
    public static final class MdaTypes {
        @objid ("7bd208a6-dc7f-43d9-a8da-d6a1ee8ac330")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b87ad0a9-1126-43a4-a1ee-94939af86e0b")
        private static Stereotype MDAASSOCDEP;

        @objid ("546878bd-a328-4a4b-ad8b-ae37e6cafdc0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f1c0bb34-f966-473e-9ed1-39d8c54e1a83")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01e7-0000-000000000000");
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

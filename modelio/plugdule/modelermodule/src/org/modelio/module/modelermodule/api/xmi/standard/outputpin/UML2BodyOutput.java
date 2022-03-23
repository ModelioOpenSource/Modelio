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
package org.modelio.module.modelermodule.api.xmi.standard.outputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
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
 * Proxy class to handle a {@link OutputPin} with << UML2BodyOutput >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dbc1e7ca-f761-4429-b0c7-68a4c218ca5d")
public class UML2BodyOutput {
    @objid ("caf4ea5f-031a-4ef6-a23c-c1bee76aac7d")
    public static final String STEREOTYPE_NAME = "UML2BodyOutput";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("480429b7-9b24-4aca-a522-2f4c879829a4")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2BodyOutput proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2BodyOutput >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("2841bd9d-d719-4874-b1a6-753c788fbc77")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2BodyOutput.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2BodyOutput >> then instantiate a {@link UML2BodyOutput} proxy.
     * 
     * @return a {@link UML2BodyOutput} proxy on the created {@link OutputPin}.
     */
    @objid ("b645a3bf-557a-4ace-8cf4-e35360af851d")
    public static UML2BodyOutput create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2BodyOutput.STEREOTYPE_NAME);
        return UML2BodyOutput.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2BodyOutput} proxy from a {@link OutputPin} stereotyped << UML2BodyOutput >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2BodyOutput} proxy or <i>null</i>.
     */
    @objid ("07f3c686-824e-4272-b524-0c90b8d1174b")
    public static UML2BodyOutput instantiate(OutputPin obj) {
        return UML2BodyOutput.canInstantiate(obj) ? new UML2BodyOutput(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2BodyOutput} proxy from a {@link OutputPin} stereotyped << UML2BodyOutput >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2BodyOutput} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b740036b-3b45-4957-8311-8a7e6edf8478")
    public static UML2BodyOutput safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2BodyOutput.canInstantiate(obj))
        	return new UML2BodyOutput(obj);
        else
        	throw new IllegalArgumentException("UML2BodyOutput: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("df0689f6-6628-451c-8272-81174dbab07b")
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
        UML2BodyOutput other = (UML2BodyOutput) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("a7743d53-5231-4aa5-baf4-3f84305d84c1")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("88895bc4-99fb-4d21-98f5-64ca8884d1ab")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("7056c223-a6f4-4f6b-88c2-cfb677aa6e8c")
    protected  UML2BodyOutput(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("f8fb3049-bf2a-4fe1-9c58-ce020fedf5f3")
    public static final class MdaTypes {
        @objid ("cda5aed0-082c-4b89-b93e-431f2df3acc8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b7fbe9db-c494-4684-9e73-819dc973203a")
        private static Stereotype MDAASSOCDEP;

        @objid ("c23410d2-3788-4d94-a945-050cefdde571")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1fbe6497-f2cc-4ed1-b88a-f8df35471023")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "fdbc7d78-32c7-11e0-91f3-0027103f347c");
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

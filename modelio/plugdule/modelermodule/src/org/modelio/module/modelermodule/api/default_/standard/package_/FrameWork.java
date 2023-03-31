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
 * Proxy class to handle a {@link Package} with << frameWork >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("8c7e5fca-e81e-401f-92b7-8980e98e3dd1")
public class FrameWork {
    @objid ("1e4a75be-c342-430d-b67c-c815438ee44f")
    public static final String STEREOTYPE_NAME = "frameWork";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("98cfdf28-1db4-4262-9860-f22f378eea1d")
    protected final Package elt;

    /**
     * Tells whether a {@link FrameWork proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << frameWork >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("ef630e11-9092-4368-a29f-caa9f864358b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, FrameWork.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << frameWork >> then instantiate a {@link FrameWork} proxy.
     * 
     * @return a {@link FrameWork} proxy on the created {@link Package}.
     */
    @objid ("01195cb1-ce57-4f58-b349-1d967345eed8")
    public static FrameWork create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, FrameWork.STEREOTYPE_NAME);
        return FrameWork.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link FrameWork} proxy from a {@link Package} stereotyped << frameWork >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link FrameWork} proxy or <i>null</i>.
     */
    @objid ("6e9aad2e-936f-4fc7-b8a1-6ccd744d09f8")
    public static FrameWork instantiate(Package obj) {
        return FrameWork.canInstantiate(obj) ? new FrameWork(obj) : null;
    }

    /**
     * Tries to instantiate a {@link FrameWork} proxy from a {@link Package} stereotyped << frameWork >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link FrameWork} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("30861dbf-87d3-46bf-8632-db4cacbf27f2")
    public static FrameWork safeInstantiate(Package obj) throws IllegalArgumentException {
        if (FrameWork.canInstantiate(obj))
        	return new FrameWork(obj);
        else
        	throw new IllegalArgumentException("FrameWork: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("90b56dcc-91e9-4a3c-9be7-2f749ce7d3c8")
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
        FrameWork other = (FrameWork) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("bbd14ed8-37ba-4447-a3ce-3853bb3489fb")
    public Package getElement() {
        return this.elt;
    }

    @objid ("6a0e7ef0-eeb2-415f-99c5-6a18e4407fbc")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("1360899c-6130-454f-b7ec-e0f9cea575ea")
    protected  FrameWork(Package elt) {
        this.elt = elt;
    }

    @objid ("c823ec2f-0b36-488c-9bc7-287eaac9b7cf")
    public static final class MdaTypes {
        @objid ("330c600f-5b54-4f46-a333-9c2ba740b2d6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0f3a4303-2b20-47b0-b87c-ebec750cd1df")
        private static Stereotype MDAASSOCDEP;

        @objid ("26960bbf-847a-4194-b150-abf11eb50258")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4d439b10-a4a5-4085-9468-9eb705d163bb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01db-0000-000000000000");
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

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
package org.modelio.module.modelermodule.api.xmi.standard.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
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
 * Proxy class to handle a {@link Activity} with << UML2Interaction  >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fe0a2eb7-94ca-4d4c-b8b4-0755c72af361")
public class UML2Interaction {
    @objid ("90a85dba-87cc-4c78-82c4-0cf411daa931")
    public static final String STEREOTYPE_NAME = "UML2Interaction ";

    /**
     * The underlying {@link Activity} represented by this proxy, never null.
     */
    @objid ("db754deb-e9ac-4516-a86e-830cba3c75dd")
    protected final Activity elt;

    /**
     * Tells whether a {@link UML2Interaction proxy} can be instantiated from a {@link MObject} checking it is a {@link Activity} stereotyped << UML2Interaction  >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("286659a8-665e-4b49-a12f-74eabc103309")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Activity) && ((Activity) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Interaction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Activity} stereotyped << UML2Interaction  >> then instantiate a {@link UML2Interaction} proxy.
     * 
     * @return a {@link UML2Interaction} proxy on the created {@link Activity}.
     */
    @objid ("f1ee8b20-55b7-44d6-8f14-9c9abd9f0922")
    public static UML2Interaction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Activity");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Interaction.STEREOTYPE_NAME);
        return UML2Interaction.instantiate((Activity)e);
    }

    /**
     * Tries to instantiate a {@link UML2Interaction} proxy from a {@link Activity} stereotyped << UML2Interaction  >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Activity
     * @return a {@link UML2Interaction} proxy or <i>null</i>.
     */
    @objid ("30e2877d-989f-4b5c-b04a-b062f2294a4a")
    public static UML2Interaction instantiate(Activity obj) {
        return UML2Interaction.canInstantiate(obj) ? new UML2Interaction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Interaction} proxy from a {@link Activity} stereotyped << UML2Interaction  >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Activity}
     * @return a {@link UML2Interaction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2cebc72d-75f4-4fea-b88d-2821c3d4a155")
    public static UML2Interaction safeInstantiate(Activity obj) throws IllegalArgumentException {
        if (UML2Interaction.canInstantiate(obj))
        	return new UML2Interaction(obj);
        else
        	throw new IllegalArgumentException("UML2Interaction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d9eb90f1-a39b-4a51-9896-a1309cfe1d19")
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
        UML2Interaction other = (UML2Interaction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Activity}. 
     * @return the Activity represented by this proxy, never null.
     */
    @objid ("dc9b1a8b-bcdf-49ce-a91f-1e0f67669e93")
    public Activity getElement() {
        return this.elt;
    }

    @objid ("71cf9f8b-8950-438b-abd5-ac1106bbc575")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("953b0fb2-4555-4324-bacd-beed23b0b4df")
    protected  UML2Interaction(Activity elt) {
        this.elt = elt;
    }

    @objid ("2d8755d9-18eb-44df-b006-43b75366bab0")
    public static final class MdaTypes {
        @objid ("1a51ad49-c41d-482e-926d-b6423ad5a716")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4a0e7985-9c1b-4dc0-99bb-76c3bc463736")
        private static Stereotype MDAASSOCDEP;

        @objid ("1d52f6f9-cf4b-499e-9683-f04d836d44f0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c2b7a998-ff0a-4685-b954-aefd3617c2cc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "021863a9-f3db-11df-8ada-0027103f347c");
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
